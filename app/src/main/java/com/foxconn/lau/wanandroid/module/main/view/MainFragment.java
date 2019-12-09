package com.foxconn.lau.wanandroid.module.main.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.foxconn.lau.multistateview.LoadingResultView;
import com.foxconn.lau.wanandroid.R;
import com.foxconn.lau.wanandroid.bean.main.Article;
import com.foxconn.lau.wanandroid.module.base.BaseAdapter;
import com.foxconn.lau.wanandroid.module.base.BaseFragment;
import com.foxconn.lau.wanandroid.module.main.contract.MainContract;
import com.foxconn.lau.wanandroid.module.main.presenter.MainPresenter;
import com.foxconn.lau.wanandroid.utils.LogUtils;
import com.foxconn.lau.wanandroid.utils.SizeUtils;
import com.foxconn.lau.wanandroid.utils.ToastUtil;
import com.foxconn.lau.wanandroid.utils.otherClass.GlideImageLoader;
import com.scwang.smartrefresh.header.PhoenixHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.transformer.ScaleInOutTransformer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author 劉帥
 * @version 1.0
 * @des ${TODO}
 * @date 2019/12/3 下午 06:11
 */
public class MainFragment extends BaseFragment<MainContract.IMainView, MainPresenter> implements MainContract.IMainView {

    @BindView(R.id.main_result_view)
    LoadingResultView mMainResultView;
    private Banner mMainBanner;

    @BindView(R.id.main_rv)
    RecyclerView mMainRv;
    @BindView(R.id.main_fresh)
    SmartRefreshLayout refreshLayout;

    private List<Article> articleList;
    private BaseAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init() {
        articleList = new ArrayList<>();
        mAdapter = new BaseAdapter(getContext(), articleList, (rView, position, checked) -> {

        });
        mMainRv.setLayoutManager(new LinearLayoutManager(getContext()));
        mMainRv.setAdapter(mAdapter);
        addBanner();
        presenter.getData();
        mAdapter.setOnClickListener((viewHolder, i) -> LogUtils.s(i));
        mMainBanner.setOnBannerListener(position -> ToastUtil.shortToast(getActivity(), position + ""));
        initSmartRefreshLayout();
    }

    private void addBanner() {
        mMainBanner = new Banner(Objects.requireNonNull(getContext()));
        mMainBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        mMainBanner.setIndicatorGravity(BannerConfig.LEFT);
        mMainBanner.setBannerAnimation(ScaleInOutTransformer.class);
        mMainBanner.setImageLoader(new GlideImageLoader());
        mMainBanner.setLayoutParams(new ViewGroup.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                SizeUtils.getScreenHeightPx(getContext()) / 4));
        mAdapter.addHeadView(mMainBanner);
    }

    @Override
    public MainPresenter initPresenter() {
        return new MainPresenter();
    }

    @Override
    public void setBanner(List<String> imageList, List<String> titleList) {
        mMainBanner.setImages(imageList)
                .setBannerTitles(titleList)
                .start();
    }

    @Override
    public void showError(String content) {
        ToastUtil.shortToast(getContext(), content);
    }

    @Override
    public void showToast(String content) {

    }

    @Override
    public void addData(List<Article> list, int start, int end) {
        articleList = list;
        mAdapter.notifyItemRangeInserted(start, end);
    }

    private void initSmartRefreshLayout() {
        refreshLayout.setPrimaryColorsId(R.color.app_main_color, android.R.color.white);
        refreshLayout.setRefreshHeader(new PhoenixHeader(Objects.requireNonNull(getContext())));//设置Header
        refreshLayout.setRefreshFooter(new ClassicsFooter(getContext()));//设置Footer
        refreshLayout.setEnableLoadMore(true);
        refreshLayout.setOnLoadMoreListener(refreshLayout1 -> presenter.getArticle());
        refreshLayout.setOnRefreshListener(refreshLayout1 -> presenter.refreshList());
    }

    @Override
    public List<Article> getRecentArticleList() {
        return mAdapter.getRecentAticleList();
    }

    @Override
    public void loadMoreDataSuccess() {
        refreshLayout.finishLoadMore();
    }

    @Override
    public void loadMoreDataError() {
        refreshLayout.finishLoadMore(false);
    }

    @Override
    public void refreshSuccess(List<Article> list) {
        articleList = list;
        refreshLayout.finishRefresh();
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void refreshError() {
        refreshLayout.finishRefresh(false);
    }

    @Override
    public void startLoadingData() {
        mMainResultView.setRecentView(LoadingResultView.LOADING);
    }

    @Override
    public void loadDataError() {
        mMainResultView.setRecentView(LoadingResultView.LOADING_ERROR);
    }

    @Override
    public void loadDataSuccess() {
        mMainResultView.setRecentView(LoadingResultView.LOADING_SUCCESS);
    }
}
