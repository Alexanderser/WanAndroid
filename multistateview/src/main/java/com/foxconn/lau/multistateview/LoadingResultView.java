package com.foxconn.lau.multistateview;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.IntDef;
import androidx.annotation.Nullable;

/**
 * @author 劉帥
 * @version 1.0
 * @des ${TODO}
 * @date 2019/12/6 下午 03:26
 */
public class LoadingResultView extends FrameLayout {

    private ImageView loadingImg;
//    private LinearLayout loadingErrorLayout;
//    private ImageView loadingErrorImg;
//    private TextView loadingErrorTv;
    private ObjectAnimator animator;
    private int LoadingViewId;
    private int errorViewId;
    private int emptyViewId;
    private int recentShowViewId;
    public static final int LOADING = 1;
    public static final int LOADING_ERROR = 2;
    public static final int LOADING_SUCCESS = 0;
    public static final int EMPTY = 3;

    private View errorView,loadingView, emptyView,mContentView;


    public LoadingResultView(Context context) {
        this(context,null);
    }

    public LoadingResultView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initAttrs(context,attrs);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.LoadingResultView);
        LoadingViewId = ta.getResourceId(R.styleable.LoadingResultView_LoadingView, -1);
        errorViewId = ta.getResourceId(R.styleable.LoadingResultView_ErrorView, -1);
        emptyViewId = ta.getResourceId(R.styleable.LoadingResultView_EmptyView, -1);
        recentShowViewId = ta.getInt(R.styleable.LoadingResultView_ContentView, 0);
        ta.recycle();
        dealWith();
    }

    private void dealWith() {
        if (errorViewId == -1) {
            errorViewId = R.layout.layout_error;
        }
        if (LoadingViewId == -1) {
            LoadingViewId = R.layout.layout_loading;
        }
        if (emptyViewId == -1) {
            emptyViewId = R.layout.layout_empty;
        }
        LayoutInflater inflater = LayoutInflater.from(getContext());
        errorView = inflater.inflate(errorViewId, this, false);
        emptyView = inflater.inflate(emptyViewId, this, false);
        loadingView = inflater.inflate(LoadingViewId, this, false);
        if (LoadingViewId == R.layout.layout_loading) {
            loadingImg = loadingView.findViewById(R.id.loading_result_loading);
            animator = ObjectAnimator.ofFloat(loadingImg, "rotation", 0, 350);
            animator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    animation.start();
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
            animator.setDuration(1000);
        }
        addView(errorView, errorView.getLayoutParams());
        addView(emptyView, emptyView.getLayoutParams());
        addView(loadingView, loadingView.getLayoutParams());
    }

    @Override
    public void addView(View child, ViewGroup.LayoutParams params) {
        if (isValidContentView(child)) mContentView = child;
        super.addView(child, params);
    }

    @Override
    public void removeView(View view) {
        super.removeView(view);
    }

    private boolean isValidContentView(View view) {
        if (mContentView != null && mContentView != view) {
            return false;
        }
        return view != loadingView && view != errorView && view != emptyView;
    }
    @IntDef(value = {LOADING,LOADING_ERROR,LOADING_SUCCESS,EMPTY})
    public @interface ViewState {}

    public void setRecentView(@ViewState int state){
        switch(state){
            case LOADING_SUCCESS:
                if (mContentView == null) {
                    throw new NullPointerException("Content View");
                }
                if (loadingView != null) loadingView.setVisibility(View.GONE);
                if (errorView != null) errorView.setVisibility(View.GONE);
                if (emptyView != null) emptyView.setVisibility(View.GONE);
                mContentView.setVisibility(View.VISIBLE);
                if (animator != null && animator.isRunning()) {
                    animator.pause();
                }
                break;
            case LOADING:
                if (loadingView == null) {
                    throw new NullPointerException("loadingView View");
                }
                if (mContentView != null) loadingView.setVisibility(View.GONE);
                if (errorView != null) errorView.setVisibility(View.GONE);
                if (emptyView != null) emptyView.setVisibility(View.GONE);
                loadingView.setVisibility(View.VISIBLE);
                if (animator != null) {
                    new Handler(getContext().getMainLooper()).post(() ->  animator.start());
                }
                break;
            case LOADING_ERROR:
                if (errorView == null) {
                    throw new NullPointerException("errorView View");
                }
                if (mContentView != null) loadingView.setVisibility(View.GONE);
                if (loadingView != null) errorView.setVisibility(View.GONE);
                if (emptyView != null) emptyView.setVisibility(View.GONE);
                errorView.setVisibility(View.VISIBLE);
                if (animator != null && animator.isRunning()) {
                    animator.pause();
                }
                break;
            case EMPTY:
                if (emptyView == null) {
                    throw new NullPointerException("emptyView View");
                }
                if (mContentView != null) loadingView.setVisibility(View.GONE);
                if (loadingView != null) errorView.setVisibility(View.GONE);
                if (errorView != null) emptyView.setVisibility(View.GONE);
                emptyView.setVisibility(View.VISIBLE);
                if (animator != null && animator.isRunning()) {
                    animator.pause();
                }
                break;
            default:
                break;
        }
    }

}
