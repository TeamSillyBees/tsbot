package cc.starxy.tsbot.tsb_plugin.leetcode.nosession;

import cc.starxy.tsbot.tsb_plugin.leetcode.bean.QuestionVO;
import cc.starxy.tsbot.tsb_plugin.leetcode.bean.TodayRecordVO;
import cc.starxy.tsbot.tsb_plugin.leetcode.constants.LeetCodeCn;
import cc.starxy.tsbot.tsb_utils.httpclient.OkHttpHelper;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;

/**
 * 题目信息查询
 *
 * @author DONG Jixing
 */
@Slf4j
public class QuestionInfo {
    private static final OkHttpHelper HTTP_HELPER =OkHttpHelper.getSingleton();

    /**
     * 返回今天的每日一题
     *
     * @return 每日一题
     * @throws IOException
     */
    public static TodayRecordVO questionOfToday() throws IOException {
        String queryString = "query " +
                "questionOfToday {" +
                "  todayRecord {" +
                "    question {" +
                "      questionFrontendId" +
                "      titleSlug" +
                "      translatedTitle" +
                "      stats" +
                "      __typename" +
                "    }" +
                "    date" +
                "    userStatus" +
                "    __typename" +
                "  }" +
                "}";
        JSONObject json = new JSONObject();
        json.put("operationName", "questionOfToday");
        json.put("variables", new HashMap<>(0));
        json.put("query", queryString);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), json.toJSONString());

        Headers headers = new Headers.Builder()
                .add("Content-Type", "application/json")
                .add("Connection", "keep-alive")
                .add("Accept", "*/*")
                .add("Origin", LeetCodeCn.URL_BASE)
                .add("Referer", LeetCodeCn.URL_PROBLEM_SET)
                .build();
        Response response = HTTP_HELPER.postSync(LeetCodeCn.URL_GRAPHQL, requestBody, headers);
        if (response.isSuccessful() && response.code() == HttpURLConnection.HTTP_OK) {
            String resp = response.body().string();
            return JSON.parseObject(resp)
                    .getJSONObject("data")
                    .getJSONArray("todayRecord").getJSONObject(0)
                    .toJavaObject(TodayRecordVO.class);
        } else {
            log.warn("获取每日一题失败");
            return null;
        }
    }

    public static QuestionVO questionData(String titleSlug) throws IOException {
        String queryString = "query " +
                "questionData($titleSlug: String!) {" +
                "  question(titleSlug: $titleSlug) {" +
                "    questionId" +
                "    questionFrontendId" +
                "    title" +
                "    titleSlug" +
                "    translatedTitle" +
                "    difficulty" +
                "    stats" +
                "    isDailyQuestion" +
                "    __typename" +
                "  }" +
                "}";
        Map<String ,Object> variables = new HashMap<>(1);
        variables.put("titleSlug",titleSlug);
        JSONObject json = new JSONObject();
        json.put("operationName", "questionData");
        json.put("variables", variables);
        json.put("query", queryString);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), json.toJSONString());

        Headers headers = new Headers.Builder()
                .add("Content-Type", "application/json")
                .add("Connection", "keep-alive")
                .add("Accept", "*/*")
                .add("Origin", LeetCodeCn.URL_BASE)
                .add("Referer", LeetCodeCn.URL_PROBLEM + titleSlug)
                .build();
        Response response = HTTP_HELPER.postSync(LeetCodeCn.URL_GRAPHQL, requestBody, headers);
        if (response.isSuccessful() && response.code() == HttpURLConnection.HTTP_OK) {
            return JSON.parseObject(response.body().string())
                    .getJSONObject("data")
                    .getJSONObject("question")
                    .toJavaObject(QuestionVO.class);
        } else {
            log.warn("获取题目 {} 失败",titleSlug);
            log.debug(response.body().string());
            return null;
        }
    }
}
