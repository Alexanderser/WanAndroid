package com.foxconn.lau.wanandroid.utils.otherClass;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.youth.banner.loader.ImageLoader;

/**
 * @author 劉帥
 * @version 1.0
 * @des ${TODO}
 * @date 2019/12/4 下午 12:25
 */
public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        Glide.with(context).load(path).into(imageView);
    }
}
