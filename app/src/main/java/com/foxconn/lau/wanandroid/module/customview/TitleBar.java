package com.foxconn.lau.wanandroid.module.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.foxconn.lau.wanandroid.R;
import com.foxconn.lau.wanandroid.utils.LogUtils;

import androidx.annotation.Nullable;

/**
 * @author 劉帥
 * @version 1.0
 * @des ${TODO}
 * @date 2019/12/2 下午 02:44
 */
public class TitleBar extends LinearLayout {

    private View mView;
    private Drawable returnImg,menuImg;
    private String title,subtitle;
    private int titleColor = getResources().getColor(android.R.color.white),
            subtitleColor = getResources().getColor(android.R.color.white),
            returnImgColor = getResources().getColor(android.R.color.white),
            menuImgColor = getResources().getColor(android.R.color.white);
    private ImageView returnIv, menuIv;
    private TextView titleTv, subtitleTv;

    public TitleBar(Context context) {
        super(context,null);
    }

    public TitleBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        getAttrs(attrs);
        init();
    }


    private void getAttrs(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.TitleBar);
        for (int i = 0; i < typedArray.length(); i++) {
            getAttr(typedArray.getIndex(i),typedArray);
        }

        typedArray.recycle();
    }

    private void getAttr(int index,TypedArray array) {
        switch(index){
            case R.styleable.TitleBar_returnImgSrc:
                returnImg = array.getDrawable(R.styleable.TitleBar_returnImgSrc);
                break;
            case R.styleable.TitleBar_menuImgSrc:
                menuImg=array.getDrawable(R.styleable.TitleBar_menuImgSrc);
                break;
            case R.styleable.TitleBar_titleText:
                title = array.getString(R.styleable.TitleBar_titleText);
                break;
            case R.styleable.TitleBar_subtitleText:
                subtitle = array.getString(R.styleable.TitleBar_subtitleText);
                break;
            case R.styleable.TitleBar_titleColor:
                titleColor = array.getColor(R.styleable.TitleBar_titleColor,
                        getResources().getColor(android.R.color.white));
                break;
            case R.styleable.TitleBar_subtitleColor:
                subtitleColor = array.getColor(R.styleable.TitleBar_subtitleColor,
                        getResources().getColor(android.R.color.white));
                break;
            case R.styleable.TitleBar_returnImgTintColor:
                returnImgColor = array.getColor(R.styleable.TitleBar_returnImgTintColor,
                        getResources().getColor(android.R.color.white));
                break;
            case R.styleable.TitleBar_menuImgTintColor:
                menuImgColor = array.getColor(R.styleable.TitleBar_menuImgTintColor,
                        getResources().getColor(android.R.color.white));
                break;
            default:
                break;
        }
    }
    private void init() {
        if (mView == null) {
            mView = LayoutInflater.from(getContext()).inflate(R.layout.layout_titlebar, null);
        }
        returnIv = mView.findViewById(R.id.title_return);
        if (returnImg != null) {
            returnImg.setTint(returnImgColor);
            returnIv.setImageDrawable(returnImg);
        }
        menuIv = mView.findViewById(R.id.title_menu);
        if (menuImg != null) {
            if (returnImgColor != 0) {
                menuImg.setTint(menuImgColor);
            }
            menuIv.setImageDrawable(menuImg);
        }
        titleTv = mView.findViewById(R.id.title_title);
        subtitleTv = mView.findViewById(R.id.title_subtitle);
        if (title == null) {
           titleTv.setVisibility(GONE);
        }else {
            titleTv.setText(title);
            titleTv.setTextColor(titleColor);
        }
        if (subtitle == null) {
            subtitleTv.setVisibility(GONE);
        } else {
            subtitleTv.setText(subtitle);
            subtitleTv.setTextColor(subtitleColor);
        }
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        addView(mView,params);
    }

    public interface OnClickListener{
        void returnImgClick(View v);
        void menuImgClick(View v);
    }

    public interface OnReturnClickListener{
        void returnImgClick(View v);
    }

    public interface OnMnueClickListener{
        void menuImgClick(View v);
    }

    public void setOnClickListener(OnClickListener listener) {
        returnIv.setOnClickListener(listener::returnImgClick);
        menuIv.setOnClickListener(listener::menuImgClick);
    }

    public void setOnReturnClickListener(OnReturnClickListener listener){
        returnIv.setOnClickListener(listener::returnImgClick);
    }

    public void setOnMnueClickListener(OnMnueClickListener listener){
        menuIv.setOnClickListener(listener::menuImgClick);
    }

    public TitleBar setTitle(String title){
        if (!"".equals(title)) {
            titleTv.setVisibility(VISIBLE);
        }
        titleTv.setText(title);
        return this;
    }

    public TitleBar setSubtitle(String subtitle){
        if (!"".equals(subtitle)) {
            titleTv.setVisibility(VISIBLE);
        }
        subtitleTv.setText(subtitle);
        return this;
    }

    public TitleBar setTitleColor(int color){
        titleTv.setTextColor(color);
        return this;
    }

    public TitleBar setSubtitleColor(int color){
        subtitleTv.setTextColor(color);
        return this;
    }

    public TitleBar setReturnImgTintColor(int color){
        if (returnImg != null) {
            returnImg.setTint(color);
            returnIv.setImageDrawable(returnImg);
        } else {
            LogUtils.e("未设置返回图片,无法设置返回图片颜色");
        }
        return this;
    }

    public TitleBar setMenuImgTintColor(int color){
        if (menuImg != null) {
            menuImg.setTint(color);
            menuIv.setImageDrawable(menuImg);
        } else {
            LogUtils.e("未设置返回图片,无法设置返回图片颜色");
        }
        return this;
    }

    public TitleBar setReturnImg(int imgResId){
        returnIv.setImageResource(imgResId);
        return this;
    }

    public TitleBar setMenuImg(int imgResId){
        menuIv.setImageResource(imgResId);
        return this;
    }

    public TitleBar setReturnImg(Drawable drawable){
        returnIv.setImageDrawable(drawable);
        return this;
    }

    public TitleBar setReturnImg(Bitmap bitmap){
        returnIv.setImageBitmap(bitmap);
        return this;
    }

    public TitleBar setMenuImg(Drawable drawable){
        menuIv.setImageDrawable(drawable);
        return this;
    }

    public TitleBar setMenuImg(Bitmap bitmap){
        menuIv.setImageBitmap(bitmap);
        return this;
    }

    public ImageView getReturnIv(){
        return returnIv;
    }

    public ImageView getMenuIv(){
        return menuIv;
    }

    public TextView getTitleTv(){
        return titleTv;
    }

    public TextView getSubtitleTv(){
        return subtitleTv;
    }

}
