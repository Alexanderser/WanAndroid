package com.foxconn.lau.wanandroid.network;

import com.foxconn.lau.wanandroid.utils.LogUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * @author 劉帥
 * @version 1.0
 * @des ${拦截器}
 * @date 2019/4/22 下午 01:58
 */
public class CommenInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        LogUtils.s("访问连接:"+chain.request().url()+"header:"+chain.request().headers()
                +"get方法数据:"+chain.request().url().query());
        return chain.proceed(chain.request().newBuilder().build());
    }
}
