package com.foxconn.lau.wanandroid.network.cookie;

import com.foxconn.lau.wanandroid.AppApplication;

import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

public class CookieManager implements CookieJar {
    private final InDiskCookieStore cookieStore = new InDiskCookieStore(AppApplication.getMyApplication());

    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        if (cookies != null && !cookies.isEmpty()) {
            for (Cookie cookie : cookies) {
                cookieStore.add(url.uri(), cookie);
            }
        }
    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {
        if (url != null) {
            return cookieStore.get(url.uri());
        }
        return null;
    }
}
