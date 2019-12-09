package com.foxconn.lau.wanandroid.module.main.model;

import com.foxconn.lau.wanandroid.bean.main.ArticleInfo;
import com.foxconn.lau.wanandroid.bean.main.BannerInfo;
import com.foxconn.lau.wanandroid.module.main.contract.MainContract;
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
 * @date 2019/12/4 上午 11:30
 */
public class MainModel implements MainContract.IMainModel {
    @Override
    public void getBanner(MainContract.MainCallBack<BannerInfo> callBack) {
        RetrofitInfo.getInstence().create(AllNetApi.class)
                .getBanner()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> callBack.start())
                .doAfterTerminate(callBack::finish)
                .subscribe(new Observer<BannerInfo>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BannerInfo o) {
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

    @Override
    public void getMainArticleByPage(int page, MainContract.MainCallBack<ArticleInfo> callBack) {
        RetrofitInfo.getInstence().create(AllNetApi.class)
                .getArticle(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> callBack.start())
                .doOnTerminate(callBack::finish)
                .subscribe(new Observer<ArticleInfo>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ArticleInfo articleInfo) {
                        callBack.success(articleInfo);
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
