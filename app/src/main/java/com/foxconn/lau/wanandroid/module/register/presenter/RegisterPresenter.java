package com.foxconn.lau.wanandroid.module.register.presenter;

import com.foxconn.lau.wanandroid.bean.login.LoginResult;
import com.foxconn.lau.wanandroid.module.base.BasePresenter;
import com.foxconn.lau.wanandroid.module.register.contract.RegisterContract;
import com.foxconn.lau.wanandroid.module.register.model.RegisterModel;
import com.foxconn.lau.wanandroid.utils.Constants;
import com.foxconn.lau.wanandroid.utils.LogUtils;
import com.foxconn.lau.wanandroid.utils.SaveMsgUtils;

/**
 * @author 劉帥
 * @version 1.0
 * @des ${TODO}
 * @date 2019/12/3 上午 11:45
 */
public class RegisterPresenter extends BasePresenter<RegisterContract.IRegisterView> {

    private RegisterContract.IRegisterModel mModel;

    public RegisterPresenter(){
        mModel = new RegisterModel();
    }

    public boolean register(){
        String username = mView.getUserName();
        String pw = mView.getPassWord();
        String confirmPw = mView.getConfirmPw();
        if ("".equals(username)) {
            mView.showUserError("请输入用户名");
            return false;
        }
        if ("".equals(pw)) {
            mView.showPwError("请输入密码");
            return false;
        }
        if ("".equals(confirmPw)) {
            mView.showRePwError("请输入密码");
            return false;
        }
        if (!pw.equals(confirmPw)) {
            mView.showRePwError("两次输入不一致,请重新输入");
            return false;
        }
        mModel.register(username, pw, new RegisterContract.RegisterCallBack() {
            @Override
            public void success(LoginResult msg) {
                LogUtils.s(msg);
                if (msg.getErrorCode() == 0) {
                    mView.showToast("注册成功,跳过登录");
                    SaveMsgUtils.savaMsg(Constants.USERNAME, msg.getData().getUsername());
                    mView.success();
                } else {
                    mView.showToast(msg.getErrorMsg());
                }
            }

            @Override
            public void failed(String errorMsg) {
                LogUtils.s(errorMsg);
                mView.showError("注册失败");
            }

            @Override
            public void start() {
                mView.showLoading();
            }

            @Override
            public void finish() {
                mView.cancelLoading();
            }
        });
        return true;
    }

}
