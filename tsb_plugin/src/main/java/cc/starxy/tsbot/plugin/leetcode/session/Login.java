package cc.starxy.tsbot.plugin.leetcode.session;


import cc.starxy.tsbot.plugin.leetcode.constants.LeetCodeCn;
import cc.starxy.tsbot.utils.httpclient.OkHttpHelper;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.List;

/**
 * LeetCodeCN 登录逻辑
 *
 * @author DONG Jixing
 */
@Slf4j
public class Login {
    public static final String BOUNDARY = "----WebKitFormBoundaryMIlRKhiheAsBA5xM";
    public static final MediaType MULTIPART = MediaType.parse("multipart/form-data; boundary=" + BOUNDARY);

    private final OkHttpHelper okHttpHelperInstance;

    private final String username;
    private final String password;

    public Login(String username, String password) {
        this.username = username;
        this.password = password;
        okHttpHelperInstance = OkHttpHelper.getSingleton();
    }

    /**
     * 模拟登陆 LeetCodo，登陆过程分析见：https://www.cnblogs.com/ZhaoxiCheung/p/9302510.html
     */
    public boolean doLogin() throws IOException {
        boolean success;


        OkHttpClient client = okHttpHelperInstance.getOkHttpClient().newBuilder()
                .followRedirects(false)
                .followSslRedirects(false)
                .build();

        Headers headers = new Headers.Builder()
                .add("Content-Type", "multipart/form-data; boundary=" + BOUNDARY)
                .add("Connection", "keep-alive")
                .add("Accept", "*/*")
                .add("Origin", LeetCodeCn.URL_BASE)
                .add("Referer", LeetCodeCn.URL_LOGIN)
                .build();

        FormBody formBody = new FormBody.Builder()
                .add("csrfmiddlewaretoken", "")
                .add("login", username)
                .add("passowrd", password)
                .add("next", "/problems")
                .build();

        Response preLogin = okHttpHelperInstance.postSync(LeetCodeCn.URL_LOGIN, formBody, headers, client);
        String csrfToken = null;
        List<String> preLoginCookies = preLogin.headers().values("Set-Cookie");
        for (String cookie : preLoginCookies) {
            if (cookie.contains("csrftoken")) {
                int last = cookie.indexOf(";");
                csrfToken = cookie.substring("csrftoken".length() + 1, last);
            }
        }
        if (StringUtils.isEmpty(csrfToken)) {
            log.warn("获取csrftoken失败");
            return false;
        }


        String formData = "--" + BOUNDARY + "\r\n"
                + "Content-Disposition: form-data; name=\"csrfmiddlewaretoken\"" + "\r\n\r\n"
                + csrfToken + "\r\n"
                + "--" + BOUNDARY + "\r\n"
                + "Content-Disposition: form-data; name=\"login\"" + "\r\n\r\n"
                + username + "\r\n"
                + "--" + BOUNDARY + "\r\n"
                + "Content-Disposition: form-data; name=\"password\"" + "\r\n\r\n"
                + password + "\r\n"
                + "--" + BOUNDARY + "\r\n"
                + "Content-Disposition: form-data; name=\"next\"" + "\r\n\r\n"
                + "/problems" + "\r\n"
                + "--" + BOUNDARY + "--";
        RequestBody requestBody = RequestBody.create(MULTIPART, formData);

        headers = headers.newBuilder()
                .add("Cookie", "csrftoken=" + csrfToken)
                .build();

        Response loginResponse = okHttpHelperInstance.postSync(LeetCodeCn.URL_LOGIN, requestBody, headers, client);


        String leetCodeSession = null;
        List<String> cookies = loginResponse.headers().values("Set-Cookie");
        for (String cookie : cookies) {
            if (cookie.contains("LEETCODE_SESSION")) {
                int last = cookie.indexOf(";");
                leetCodeSession = cookie.substring("LEETCODE_SESSION".length() + 1, last);
            }
        }
        if (StringUtils.isNotEmpty(leetCodeSession)) {
            success = true;
            log.info("LeetCode 登陆成功。用户名 {}", username);
        } else {
            success = false;
            log.warn("LeetCode 登陆失败。用户名 {}", username);
        }
        loginResponse.close();

        return success;
    }
}
