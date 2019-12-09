package com.foxconn.lau.wanandroid.module.main.presenter;

import com.foxconn.lau.wanandroid.bean.main.Article;
import com.foxconn.lau.wanandroid.bean.main.ArticleInfo;
import com.foxconn.lau.wanandroid.bean.main.BannerInfo;
import com.foxconn.lau.wanandroid.module.base.BasePresenter;
import com.foxconn.lau.wanandroid.module.main.contract.MainContract;
import com.foxconn.lau.wanandroid.module.main.model.MainModel;
import com.foxconn.lau.wanandroid.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 劉帥
 * @version 1.0
 * @des ${TODO}
 * @date 2019/12/4 上午 11:35
 */
public class MainPresenter extends BasePresenter<MainContract.IMainView> {

    private MainContract.IMainModel mModel;
    private List<Boolean> checkedList;
    private int recentPage;

    public MainPresenter(){
        mModel = new MainModel();
        checkedList = new ArrayList<>();
        recentPage = 0;
    }

    public void getData(){
        mModel.getBanner(new MainContract.MainCallBack<BannerInfo>() {

            @Override
            public void success(BannerInfo bannerInfo) {
                if (bannerInfo.getErrorCode() == 0) {
                    if (mView != null) {
                        LogUtils.s(bannerInfo);
                        List<String> imageList = new ArrayList<>();
                        List<String> titleList = new ArrayList<>();
                        for (int i = 0; i < bannerInfo.getData().size(); i++) {
                            imageList.add(bannerInfo.getData().get(i).getImagePath());
                            titleList.add(bannerInfo.getData().get(i).getTitle());
                        }
                        mView.setBanner(imageList,titleList);
                        recentPage = 0;
                        getArticle();
                    }
                } else {
                    LogUtils.e(bannerInfo.getErrorCode());
                    mView.showError("数据加载失败");
                }

            }

            @Override
            public void failed(String errorMsg) {
                mView.showError("数据加载失败");
                LogUtils.e(errorMsg);
            }

            @Override
            public void start() {
                mView.startLoadingData();
                LogUtils.s("开始访问");
            }

            @Override
            public void finish() {

            }
        });
    }

    public void getArticle(){
        mModel.getMainArticleByPage(recentPage, new MainContract.MainCallBack<ArticleInfo>() {
            @Override
            public void success(ArticleInfo articleInfo) {

                if (articleInfo.getErrorCode() == 0) {
                    if (mView != null) {
                        List<Article> articleList = mView.getRecentArticleList();
                        LogUtils.s(articleList.size());
                        int start = articleList.size();
                        articleList.addAll(articleInfo.getData().getListData());
                        int end = articleList.size();
//                        for (int i = 0; i < articleInfo.getData().getListData().size(); i++) {
//                            LogUtils.s(articleInfo.getData().getListData().get(i).toString());
//                        }
                        mView.addData(articleList, start+1, end+1);
                        recentPage += 1;
                        mView.loadMoreDataSuccess();
                    }
                } else {
                    mView.showError("数据加载失败");
                }
            }

            @Override
            public void failed(String errorMsg) {
                LogUtils.e(errorMsg);
                mView.loadMoreDataError();
            }

            @Override
            public void start() {

            }

            @Override
            public void finish() {
                mView.loadDataSuccess();
            }
        });
    }

    public void refreshList(){
        mModel.getMainArticleByPage(0, new MainContract.MainCallBack<ArticleInfo>() {
            @Override
            public void success(ArticleInfo articleInfo) {
                if (articleInfo.getErrorCode() == 0) {
                    if (mView != null) {
                        List<Article> articleList = mView.getRecentArticleList();
                        articleList.removeAll(mView.getRecentArticleList());
                        articleList.addAll(articleInfo.getData().getListData());
                        mView.refreshSuccess(articleList);
                        recentPage += 1;
                    }
                } else {
                    mView.refreshError();
                }
            }

            @Override
            public void failed(String errorMsg) {
                LogUtils.e(errorMsg);
                mView.refreshError();
            }

            @Override
            public void start() {

            }

            @Override
            public void finish() {

            }
        });
    }

}
