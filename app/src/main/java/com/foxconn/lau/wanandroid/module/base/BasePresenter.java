package com.foxconn.lau.wanandroid.module.base;

/**
 * @author 劉帥
 * @version 1.0
 * @des ${TODO}
 * @date 2019/11/25 下午 03:20
 */
public class BasePresenter<T> {

    public T mView;
    public void attach(T mView){
        this.mView = mView;
    }

    public void dettach(){
        mView = null;
    }

}
