package com.foxconn.lau.wanandroid.network;

import com.foxconn.lau.wanandroid.network.cookie.CookieManager;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.java8.Java8CallAdapterFactory;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author 劉帥
 * @version 1.0
 * @des ${TODO}
 * @date 2019/11/29 下午 03:34
 */
public class RetrofitInfo {

    public static Retrofit getInstence(){
        return new Retrofit.Builder()
                .baseUrl("https://www.wanandroid.com")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addCallAdapterFactory(Java8CallAdapterFactory.create())
                .client(new OkHttpClient.Builder().connectTimeout(8, TimeUnit.SECONDS)
                        .addInterceptor(new CommenInterceptor())
                        .cookieJar(new CookieManager()).build())
                .build();
    }

}
