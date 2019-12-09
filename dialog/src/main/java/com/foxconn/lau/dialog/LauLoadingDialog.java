package com.foxconn.lau.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.wang.avi.AVLoadingIndicatorView;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

/**
 * @author 劉帥
 * @version 1.0
 * @des ${倒计时对话框}
 * @date 2019/11/13 上午 11:42
 */
public class LauLoadingDialog extends DialogFragment {

    private static String sShowTag;
    private static long sLastTime;
    private static LauLoadingDialog loadingDialog = new LauLoadingDialog();
    private int color;

    private LauLoadingDialog(){
//        创建方法
//        LoadingDialog.getInstance().setDialogColor().show(this);
    }


    public static LauLoadingDialog getInstance(){
        return loadingDialog;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.myDialogTheme);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_loading, container, false);
        if (color != 0) {
            ((AVLoadingIndicatorView)view.findViewById(R.id.loading_load)).setIndicatorColor(color);
        }
        return view;
    }

    @Override
    public void onResume() {
        WindowManager.LayoutParams params = Objects.requireNonNull(getDialog().getWindow()).getAttributes();
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height= WindowManager.LayoutParams.WRAP_CONTENT;
        Objects.requireNonNull(getDialog().getWindow()).setAttributes(params);
        getDialog().getWindow().setGravity(Gravity.CENTER);
        super.onResume();
    }


    public void show(Fragment fragment) {
        show(fragment.getFragmentManager(), fragment.getClass().getName());
    }

    public void show(FragmentActivity activity) {
        show(activity.getSupportFragmentManager(), activity.getClass().getName());
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        if (!isRepeatedShow(tag)) {
            try {
                Class c=Class.forName("androidx.fragment.app.DialogFragment");
                Constructor con = c.getConstructor();
                Object obj = con.newInstance();
                Field dismissed = c.getDeclaredField("mDismissed");
                dismissed.setAccessible(true);
                dismissed.set(obj,false);
                Field shownByMe = c.getDeclaredField("mShownByMe");
                shownByMe.setAccessible(true);
                shownByMe.set(obj,false);
            } catch (Exception e) {
                e.printStackTrace();
            }
            FragmentTransaction ft = manager.beginTransaction();
            ft.add(this, tag);
            ft.commitAllowingStateLoss();
        }
    }


    //    @Override
    //    public void show(FragmentManager manager, String tag) {
    //        if (!isRepeatedShow(tag)) {
    //            super.show(manager, tag);
    //        }
    //    }

    @Override
    public int show(FragmentTransaction transaction, String tag) {
        if (!isRepeatedShow(tag)) {
            return super.show(transaction, tag);
        }
        return -1;
    }

    /**
     * 根据 tag 判断这个 Dialog 是否重复显示了
     *
     * @param tag           Tag标记
     */
    private boolean isRepeatedShow(String tag) {
        boolean result = tag.equals(sShowTag) && SystemClock.uptimeMillis() - sLastTime < 500;
        sShowTag = tag;
        sLastTime = SystemClock.uptimeMillis();
        return result;
    }

    public LauLoadingDialog setDialogColor(int color) {
        this.color = color;
        return this;
    }

    public LauLoadingDialog setCancel(boolean cancel){
        super.setCancelable(cancel);
        return this;
    }

    public void cancleDialog(){
        if (this.isAdded()) {
            this.dismiss();
        }
    }

}
