package cc.starxy.tsbot.utils.httpclient;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 自定义Cookie存取类
 * 模拟SetCookie
 *
 * @author DONG Jixing
 */
public class MyCookieJar implements CookieJar {
    /**
     * 根据 url 和 cookie name 存储
     */
    private final Map<String, Cookie> cookieStore = new ConcurrentHashMap<>();

    @Override
    public void saveFromResponse(@NotNull HttpUrl httpUrl, @NotNull List<Cookie> list) {
        for (Cookie cookie : list) {
            cookieStore.put(cookie.domain() + ":" + cookie.name(), cookie);
        }
    }

    @Override
    @NotNull
    public List<Cookie> loadForRequest(@NotNull HttpUrl httpUrl) {
        List<Cookie> cookies = new ArrayList<>();
        cookieStore.forEach((s, cookie) -> {
            if (s.split(":")[0].equals(httpUrl.host())) {
                cookies.add(cookie);
            }
        });
        return cookies;
    }
}
