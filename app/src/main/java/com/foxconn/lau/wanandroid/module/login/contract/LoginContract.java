package com.foxconn.lau.wanandroid.module.login.contract;

import com.foxconn.lau.wanandroid.bean.login.LoginResult;

/**
 * @author 劉帥
 * @version 1.0
 * @des ${TODO}
 * @date 2019/11/29 上午 10:12
 */
public interface LoginContract {

    interface ILoginView{
        String getUserName();
        String getPassWord();
        void setUserError(String content);
        void setPwError(String content);
        void showToast(String content);
        void showErrorDialog(String error);
        void success();
        void register();
    }

    interface iLoginModel{
        void login(String userName,String password,loginCallBack callBack);
    }

    interface loginCallBack{
        void success(LoginResult msg);
        void fail(String errorMsg);
    }

}
