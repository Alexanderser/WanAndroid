package com.foxconn.lau.wanandroid.module.register.contract;

import com.foxconn.lau.wanandroid.bean.login.LoginResult;

/**
 * @author 劉帥
 * @version 1.0
 * @des ${TODO}
 * @date 2019/12/3 上午 11:43
 */
public interface RegisterContract {

    interface IRegisterModel {
        void register(String username,String password ,RegisterCallBack callBack);
    }

    interface RegisterCallBack{
        void success(LoginResult msg);
        void failed(String errorMsg);
        void start();
        void finish();
    }

    interface IRegisterView {
        String getUserName();
        String getPassWord();
        String getConfirmPw();
        void showUserError(CharSequence text);
        void showPwError(CharSequence text);
        void showRePwError(CharSequence text);
        void showError(String content);
        void showToast(String content);
        void success();
        void showLoading();
        void cancelLoading();
    }

}
