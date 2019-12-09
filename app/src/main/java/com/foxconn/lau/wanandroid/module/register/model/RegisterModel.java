package com.foxconn.lau.wanandroid.module.register.model;

import com.foxconn.lau.wanandroid.bean.login.LoginResult;
import com.foxconn.lau.wanandroid.module.register.contract.RegisterContract;
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
 * @date 2019/12/3 上午 11:52
 */
public class RegisterModel implements RegisterContract.IRegisterModel {

    @Override
    public void register(String username,String password ,RegisterContract.RegisterCallBack callBack) {
        RetrofitInfo.getInstence().create(AllNetApi.class)
                .register(username,password,password)
                .doOnSubscribe(disposable -> callBack.start())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnTerminate(callBack::finish)
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
                        callBack.failed(e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
