package com.foxconn.lau.wanandroid.utils;

import android.util.Log;

/**
 * @author 劉帥
 * @version 1.0
 * @des ${TODO}
 * @date 2019/11/29 下午 02:12
 */
public class LogUtils {

    public static int LOG_CLOSE = 0;
    public static int LOG_INFO = 3;
    public static int LOG_WARN = 4;
    public static int LOG_ERROR = 5;
    public static int LOG_DEBUG = 2;
    public static int LOG_VERBOSE = 1;

    public static void s(Object... content){
        if (LOG_CLOSE > LOG_INFO) {
            StringBuilder result = new StringBuilder();
            for (Object c : content) {
                result = result .append("---" ) .append(c);
            }
            if (result.length() > 1000) {
                for (int i = 0; i < result.length() / 1000 + (result.length() % 1000 > 0 ? 1 : 0); i++) {
                    Log.i ("Mr.Liu", String.valueOf(result.substring(i*1000,
                            (i+1)*1000>result.length()?result.length():(i+1)*1000)));
                }
            }
            Log.i("Mr.Liu", String.valueOf(result));
        }
    }

    public static void e(Object... content){
        if (LOG_CLOSE > LOG_ERROR) {
            StringBuilder result = new StringBuilder();
            for (Object c : content) {
                result = result .append("---" ) .append(String .valueOf(c));
            }
            Log.e("Mr.Liu", String.valueOf(result));
        }
    }

    public static void open(){
        LOG_CLOSE = 6;
    }

}
