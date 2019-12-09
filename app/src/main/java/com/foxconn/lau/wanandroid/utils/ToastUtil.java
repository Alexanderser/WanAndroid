package com.foxconn.lau.wanandroid.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * @author 劉帥
 * @version 1.0
 * @des ${TODO}
 * @date 2019/11/29 上午 10:58
 */
public class ToastUtil {

    public static void shortToast(Context context,String content){
        Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
    }

    public static void longToast(Context context, String content) {
        Toast.makeText(context, content, Toast.LENGTH_LONG).show();
    }

}
