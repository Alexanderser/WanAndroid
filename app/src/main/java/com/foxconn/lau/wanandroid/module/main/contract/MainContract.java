package com.foxconn.lau.wanandroid.module.main.contract;

import android.view.View;

import com.foxconn.lau.wanandroid.bean.main.Article;
import com.foxconn.lau.wanandroid.bean.main.ArticleInfo;
import com.foxconn.lau.wanandroid.bean.main.BannerInfo;

import java.util.List;

/**
 * @author 劉帥
 * @version 1.0
 * @des ${TODO}
 * @date 2019/12/3 下午 06:11
 */
public interface MainContract {

    interface IMainModel{
        void getBanner(MainCallBack<BannerInfo> callBack);

        void getMainArticleByPage(int page, MainCallBack<ArticleInfo> callBack);
    }

    interface MainCallBack<T>{
        void success(T bannerInfo);
        void failed(String errorMsg);
        void start();
        void finish();
    }

    interface IMainView {
        void setBanner(List<String> imageList, List<String> titleList);
        void showError(String content);
        void showToast(String content);
        void addData(List<Article> datas, int start, int end);
        List<Article> getRecentArticleList();
        void loadMoreDataSuccess();
        void loadMoreDataError();
        void refreshSuccess(List<Article> list);
        void refreshError();
        void startLoadingData();
        void loadDataError();
        void loadDataSuccess();

    }

    interface ItemClickCallback{

        void likeChange(View view,int position, boolean checked);
    }
}
