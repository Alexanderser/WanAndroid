package com.foxconn.lau.wanandroid.module.main;

import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.foxconn.lau.wanandroid.R;
import com.foxconn.lau.wanandroid.module.main.view.MainFragment;
import com.foxconn.lau.wanandroid.module.my.view.MyFragment;
import com.foxconn.lau.wanandroid.module.project.view.ProjectFragment;
import com.foxconn.lau.wanandroid.module.system.view.SystemFragment;
import com.foxconn.lau.wanandroid.module.wechat.view.WechatFragment;
import com.foxconn.lau.wanandroid.utils.ArouteConstants;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import butterknife.BindView;
import butterknife.ButterKnife;

@Route(path = ArouteConstants.MAIN_ACTIVITY)
public class MainActivity extends AppCompatActivity {


    @BindView(R.id.main_frame)
    FrameLayout mFrame;
    @BindView(R.id.main_navigation)
    BottomNavigationView mNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initFragment();
    }

    Fragment[] fragments;
    private void initFragment() {
        MainFragment mainFragment = new MainFragment();
        SystemFragment systemFragment = new SystemFragment();
        WechatFragment wechatFragment = new WechatFragment();
        ProjectFragment projectFragment = new ProjectFragment();
        MyFragment myFragment = new MyFragment();
        fragments = new Fragment[]{mainFragment, systemFragment, wechatFragment, projectFragment, myFragment};
        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, mainFragment).show(mainFragment).commit();

        mNavigation.setOnNavigationItemSelectedListener(this::changeSelect);
    }
    private int lastfragment = 0;

    private boolean changeSelect(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.main_home:
                if (lastfragment != 0) {
                    switchFragment(lastfragment, 0);
                    lastfragment = 0;
                }
                return true;
            case R.id.main_system:
                if (lastfragment != 1) {
                    switchFragment(lastfragment, 1);
                    lastfragment = 1;
                }
                return true;
            case R.id.main_public_num:
                if (lastfragment != 2) {
                    switchFragment(lastfragment, 2);
                    lastfragment = 2;
                }
                return true;
            case R.id.main_project:
                if (lastfragment != 3) {
                    switchFragment(lastfragment, 3);
                    lastfragment = 3;
                }
                return true;
            case R.id.main_my:
                if (lastfragment != 4) {
                    switchFragment(lastfragment, 4);
                    lastfragment = 4;
                }
                return true;
            default:
                break;
        }
        return false;
    }

    private void switchFragment(int lastfragment, int index) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.hide(fragments[lastfragment]);
        if (!fragments[index].isAdded()) {
            transaction.add(R.id.main_frame, fragments[index]);
        }
        transaction.show(fragments[index]).commitAllowingStateLoss();
    }

}
