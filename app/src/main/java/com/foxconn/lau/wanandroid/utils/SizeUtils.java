package com.foxconn.lau.wanandroid.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * @author 劉帥
 * @version 1.0
 * @des ${TODO}
 * @date 2019/12/7 上午 08:51
 */
public class SizeUtils {


    //dp
    public static int getScreenWidthDp(Context context){
        WindowManager wm = (WindowManager) (context).getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        int width1 = dm.widthPixels;         // 屏幕宽度（像素）
        float density = dm.density;	// 屏幕密度（0.75 / 1.0 / 1.5）
        // 屏幕宽度算法:屏幕宽度（像素）/屏幕密度
        return (int) (width1 / density);  // 屏幕宽度(dp)
    }

    public static int getScreenHeightDp(Context context){
        WindowManager wm = (WindowManager) (context).getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        int height1 = dm.heightPixels;       // 屏幕高度（像素）
        float density = dm.density;	// 屏幕密度（0.75 / 1.0 / 1.5）
        return  (int) (height1 / density);// 屏幕高度(dp)
    }

    public static int getScreenHeightPx(Context context){
        WindowManager wm = (WindowManager) (context).getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        return  dm.heightPixels;
    }

}
