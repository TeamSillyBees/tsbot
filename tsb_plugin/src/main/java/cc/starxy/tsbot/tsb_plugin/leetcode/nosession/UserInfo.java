package cc.starxy.tsbot.tsb_plugin.leetcode.nosession;


import cc.starxy.tsbot.tsb_plugin.leetcode.bean.RecentSubmission;
import cc.starxy.tsbot.tsb_plugin.leetcode.bean.TodayRecordVO;
import cc.starxy.tsbot.tsb_plugin.leetcode.constants.LeetCodeCn;
import cc.starxy.tsbot.tsb_plugin.leetcode.constants.SubmissionStatus;
import cc.starxy.tsbot.tsb_utils.httpclient.OkHttpHelper;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.*;

/**
 * 用户信息查询
 *
 * @author DONG Jixing
 */
@Slf4j
public class UserInfo {

    private static final OkHttpHelper HTTP_HELPER =OkHttpHelper.getSingleton();

    /**
     * @param username 用户名
     * @return 存储日期与提交记录的映射
     */
    public static Map<Date, Integer> userCalendar(String username) {

        return null;
    }

    /**
     * 获取最近提交记录
     *
     * @param username 用户名
     * @return 返回最近
     */
    public static List<RecentSubmission> recentSubmissions(String username) throws IOException {

        String queryString = "query " +
                "recentSubmissions($userSlug: String!) {" +
                "  recentSubmissions(userSlug: $userSlug) {" +
                "    status" +
                "    question {" +
                "      titleSlug" +
                "    }" +
                "    submitTime" +
                "  }" +
                "}";
        Map<String, Object> variables = new HashMap<>(1);
        variables.put("userSlug", username);
        JSONObject json = new JSONObject();
        json.put("operationName", "recentSubmissions");
        json.put("variables", variables);
        json.put("query", queryString);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), json.toJSONString());

        Headers headers = new Headers.Builder()
                .add("Content-Type", "application/json")
                .add("Connection", "keep-alive")
                .add("Accept", "*/*")
                .add("Origin", LeetCodeCn.URL_BASE)
                .add("Referer", LeetCodeCn.URL_USER_PAGE + username)
                .build();
        Response response = HTTP_HELPER.postSync(LeetCodeCn.URL_GRAPHQL, requestBody, headers);
        if (response.isSuccessful() && response.code() == HttpURLConnection.HTTP_OK) {
            String resp = response.body().string();
            List<RecentSubmission> recentSubmissions = new ArrayList<>();
            JSONArray array = JSON.parseObject(resp).getJSONObject("data").getJSONArray("recentSubmissions");
            for (int i = 0; i < array.size(); i++) {
                JSONObject object = array.getJSONObject(i);
                RecentSubmission recentSubmission = new RecentSubmission();
                recentSubmission.setStatus(object.getString("status"));
                recentSubmission.setQuestionTitleSlug(object.getJSONObject("question").getString("titleSlug"));
                recentSubmission.setSubmitTime(object.getDate("submitTime"));
                recentSubmissions.add(recentSubmission);
            }
            return recentSubmissions;
        } else {
            log.warn("获取用户 {} 提交信息失败", username);
            log.debug(response.body().string());
            return null;
        }
    }

    /**
     * 判断每日一题是否完成
     * 判断是否在最近提交记录列表里
     *
     * @param username 用户名
     * @return 是否完成
     */
    public static Boolean dailyQuestionComplete(String username) throws IOException {
        TodayRecordVO dailyQues = QuestionInfo.questionOfToday();
        List<RecentSubmission> list = UserInfo.recentSubmissions(username);
        if (dailyQues == null || list == null) {
            return false;
        }
        for (RecentSubmission submission : list) {
            if (submission.getQuestionTitleSlug().equals(dailyQues.getQuestion().getTitleSlug())) {
                return SubmissionStatus.AC.equals(submission.getStatus());
            }
        }
        // todo 需要重构返回值，返回前确认当天提交的题目是否超过能拿到的最新提交的数量，如果超过了则有可能已经完成了每日一题但是没办法获取到记录了
        return false;
    }

}
