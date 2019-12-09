package com.foxconn.lau.wanandroid.module.login.model;

import com.foxconn.lau.wanandroid.bean.login.LoginResult;
import com.foxconn.lau.wanandroid.module.login.contract.LoginContract;
import com.foxconn.lau.wanandroid.network.AllNetApi;
import com.foxconn.lau.wanandroid.network.RetrofitInfo;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


/**
 * @author 劉帥
 * @version 1.0
 * @des ${TODO}
 * @date 2019/11/29 上午 11:19
 */
public class LoginModel implements LoginContract.iLoginModel {

    @Override
    public void login(String userName,String password,LoginContract.loginCallBack callBack) {
        RetrofitInfo.getInstence().create(AllNetApi.class).login(userName, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginResult>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginResult o) {
                        callBack.success(o);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.fail(e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
