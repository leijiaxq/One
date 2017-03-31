package com.meimengmeng.one.api;


import com.meimengmeng.one.base.BaseApplication;
import com.meimengmeng.one.utils.LogUtil;
import com.meimengmeng.one.utils.NetUtil;

import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by leijiaxq
 * Data       2016/12/20 11:06
 * Describe
 */
public class ApiManager {

    private final static String TAG        = ApiManager.class.getSimpleName();
    private final static int    CACHE_SIZE = 10 * 1024 * 1024; // 10 MiB


    //    public  static String BASE_URL = "http://192.168.0.105:8080/";
//    public static String BASE_URL = "http://119.23.21.244:8080/";
    public static String BASE_URL = "http://119.23.21.244/";
//    public static String BASE_URL = "http://www.powerll.com/";


    public static ApiManager apiManager;

    private File httpCacheDirectory = new File(BaseApplication.getInstance().getCacheDir(), "World");

    private Cache cache = new Cache(httpCacheDirectory, CACHE_SIZE);


    //缓存拦截
    private class CacheInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (NetUtil.isConnected(BaseApplication.getInstance())) {
                Response response = chain.proceed(request);
                // read from cache for 60 s
                int maxAge = 30;
                String cacheControl = request.cacheControl().toString();
                LogUtil.d(TAG, maxAge + "s load cahe:" + cacheControl);
                return response.newBuilder()
                        .removeHeader("Pragma")
                        .removeHeader("Cache-Control")
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .build();
            } else {
                LogUtil.d(TAG, " no network load cahe");
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
                Response response = chain.proceed(request);
                //set cahe times is 3 days
                int maxStale = 60 * 60 * 24 * 3;
                return response.newBuilder()
                        .removeHeader("Pragma")
                        .removeHeader("Cache-Control")
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .build();
            }
        }
    }


    //日志拦截
    private class LogInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            LogUtil.d(TAG, "request:" + request.toString());

            long t1 = System.nanoTime();
            Response response = chain.proceed(chain.request());
            long t2 = System.nanoTime();
            LogUtil.d(TAG, String.format(Locale.getDefault(), "Received response for %s in %.1fms%n%s",
                    response.request().url(), (t2 - t1) / 1e6d, response.headers()));
            okhttp3.MediaType mediaType = response.body().contentType();
            String content = response.body().string();
            LogUtil.d(TAG, "response body:" + content);
            Response build = response.newBuilder()
                    .body(ResponseBody.create(mediaType, content))
                    .build();
            response.body().close();
            return build;
        }
    }

    //OkHttpClient 初始化
    private OkHttpClient client = new OkHttpClient.Builder()
            //            .addInterceptor(new CacheInterceptor())
            //            .addNetworkInterceptor(new CacheInterceptor())
            .cache(cache)
            .addInterceptor(new LogInterceptor())
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build();

    private ApiManager() {
    }

    public static ApiManager getInstance() {
        if (apiManager == null) {
            synchronized (ApiManager.class) {
                if (apiManager == null) {
                    apiManager = new ApiManager();
                }
            }
        }
        return apiManager;
    }

    public Retrofit initRetrofit() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }


}
