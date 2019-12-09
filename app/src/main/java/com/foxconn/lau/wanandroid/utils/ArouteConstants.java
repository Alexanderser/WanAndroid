package com.foxconn.lau.wanandroid.utils;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * @author 劉帥
 * @version 1.0
 * @des ${TODO}
 * @date 2019/12/2 上午 11:57
 */
public class ArouteConstants {

    public static final String LAUNCHER_ACTIVIYT = "/lau/launcher";
    public static final String LOGIN_ACTIVIYT = "/lau/login";
    public static final String MAIN_ACTIVITY = "/lau/main";
    public static final String REGISTER_ACTIVITY = "/lau/register";

    public static void enterActivity(String route){
        ARouter.getInstance().build(route)
                .navigation();
    }

}
