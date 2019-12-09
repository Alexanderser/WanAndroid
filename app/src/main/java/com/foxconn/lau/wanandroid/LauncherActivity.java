package com.foxconn.lau.wanandroid;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.foxconn.lau.wanandroid.utils.ArouteConstants;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author 劉帥
 * @version 1.0
 * @des ${TODO}
 * @date 2019/11/27 下午 04:07
 */
@Route(path = ArouteConstants.LAUNCHER_ACTIVIYT)
public class LauncherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ARouter.getInstance().build(ArouteConstants.LOGIN_ACTIVIYT)
                .navigation();
        finish();
    }
}
