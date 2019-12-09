package com.foxconn.lau.wanandroid.module.login.presenter;

import android.text.TextUtils;

import com.foxconn.lau.wanandroid.bean.login.LoginResult;
import com.foxconn.lau.wanandroid.module.base.BasePresenter;
import com.foxconn.lau.wanandroid.module.login.contract.LoginContract;
import com.foxconn.lau.wanandroid.module.login.model.LoginModel;
import com.foxconn.lau.wanandroid.utils.Constants;
import com.foxconn.lau.wanandroid.utils.LogUtils;
import com.foxconn.lau.wanandroid.utils.SaveMsgUtils;

/**
 * @author 劉帥
 * @version 1.0
 * @des ${TODO}
 * @date 2019/11/29 上午 11:10
 */
public class LoginPresenter extends BasePresenter<LoginContract.ILoginView> {

    private LoginContract.iLoginModel mModel;

    public LoginPresenter(){
        mModel = new LoginModel();
    }

    public void login (){
        String userName = mView.getUserName();
        String password = mView.getPassWord();
        if (verifyMessage(userName, password)) {
            mModel.login(userName, password, new LoginContract.loginCallBack() {
                @Override
                public void success(LoginResult msg) {
                    LogUtils.s(msg.toString());
                    if (mView != null) {
                        if (msg.getErrorCode() == 0) {
                            SaveMsgUtils.savaMsg(Constants.USERNAME, msg.getData().getUsername());
                            mView.showToast("登陆成功");
                            mView.success();
                        } else {
                            mView.showToast(msg.getErrorMsg());
                        }
                    }
                }

                @Override
                public void fail(String errorMsg) {
                    if (mView != null) {
                        LogUtils.s(errorMsg);
                        mView.showErrorDialog("登录失败");
                    }
                }

            });
        }
    }

    private boolean verifyMessage(String userName,String password){

        if (TextUtils.isEmpty(userName)) {
            mView.setUserError("请输入用户名");
            return false;
        }
        if (TextUtils.isEmpty(password)) {
            mView.setUserError("");
            mView.setPwError("请输入密码");
            return false;
        }
        String result = checkPw(password);
        if (!"".equals(result)) {
            mView.setPwError(result);
            return false;
        }
        return true;
    }

    private String checkPw(String pw){
        String result = "";
        if (pw.length() < 6) {
            result = "密码长度小于6位";
        }else if(pw.length()>12){
            result = "密码长度太长";
        } else if (TextUtils.isDigitsOnly(pw)) {
            result = "密码设置过于简单";
        } else if (numberCount(pw) == 0) {
            result = "密码应包含数字丶字母";
        }
        return result;
    }

    public int numberCount(String s){//检测该密码有几位数字
        int count=0;
        for(int i=0;i<s.length();i++)
        {
            if(Character.isDigit(s.charAt(i)))
                count++;
        }
        return count;
    }
}
