package com.foxconn.lau.wanandroid;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.foxconn.lau.wanandroid.utils.LogUtils;

/**
 * @author 劉帥
 * @version 1.0
 * @des ${TODO}
 * @date 2019/11/28 上午 11:49
 */
public class AppApplication extends Application {

    public static AppApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        LogUtils.open();
        ARouter.openLog();
        ARouter.openDebug();
        ARouter.init(AppApplication.this);
    }

    @Override
    public void onTerminate() {
        ARouter.getInstance().destroy();
        super.onTerminate();
    }

    public static AppApplication getMyApplication(){
        return application;
    }

}
