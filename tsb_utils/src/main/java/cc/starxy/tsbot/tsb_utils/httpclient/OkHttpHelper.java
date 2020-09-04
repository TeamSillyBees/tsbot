package cc.starxy.tsbot.tsb_utils.httpclient;

import okhttp3.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * http client 工具类
 *
 * @author DONG Jixing
 */
public final class OkHttpHelper {
    private static volatile OkHttpHelper okHttpHelperInstance;
    private final OkHttpClient okHttpClient;


    private OkHttpHelper() {
        okHttpClient = new OkHttpClient.Builder()
                .cookieJar(new MyCookieJar())
                .connectTimeout(0, TimeUnit.SECONDS)
                .readTimeout(0, TimeUnit.SECONDS)
                .writeTimeout(0, TimeUnit.SECONDS)
                .build();
    }

    public static OkHttpHelper getSingleton() {
        OkHttpHelper result = okHttpHelperInstance;
        if (result == null) {
            synchronized (OkHttpHelper.class) {
                result = okHttpHelperInstance;
                if (result == null) {
                    result = okHttpHelperInstance = new OkHttpHelper();
                }
            }
        }
        return result;
    }

    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }

    public Response getSync(String url) throws IOException {
        return getSync(url, null);
    }

    public Response getSync(String url, Headers headers) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        if (headers != null) {
            request.newBuilder().headers(headers)
                    .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36");
        }

        Call call = okHttpClient.newCall(request);
        return call.execute();
    }

    public Response postSync(String url, RequestBody requestBody, Headers headers) throws IOException {
        return postSync(url, requestBody, headers, okHttpClient);
    }

    public Response postSync(String url, RequestBody requestBody, Headers headers, OkHttpClient client) throws IOException {
        Request request = new Request.Builder()
                .headers(headers)
                .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36")
                .post(requestBody)
                .url(url)
                .build();
        Call call = client.newCall(request);
        return call.execute();
    }

    public void postAsync(String url, RequestBody requestBody, Headers headers, Callback callback) {
        Request request = new Request.Builder()
                .headers(headers)
                .post(requestBody)
                .url(url)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                callback.onFailure(call, e);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                callback.onResponse(call, response);
            }
        });
    }
}