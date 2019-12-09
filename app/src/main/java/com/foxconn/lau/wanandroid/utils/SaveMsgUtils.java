package com.foxconn.lau.wanandroid.utils;

import android.content.Context;

import com.foxconn.lau.wanandroid.AppApplication;

/**
 * @author 劉帥
 * @version 1.0
 * @des ${TODO}
 * @date 2019/12/2 上午 10:57
 */
public class SaveMsgUtils {

    public static void savaMsg(String key,String msg){
        AppApplication.getMyApplication().getSharedPreferences(Constants.SHARED_NAME, Context.MODE_PRIVATE).edit()
        .putString(key,msg).apply();
    }

}
