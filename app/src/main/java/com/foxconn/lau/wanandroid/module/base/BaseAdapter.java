package com.foxconn.lau.wanandroid.module.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;

import com.foxconn.lau.dialog.recyclerview.LauRecyclerAdapter;
import com.foxconn.lau.dialog.recyclerview.LauRecyclerViewHolder;
import com.foxconn.lau.dialog.recyclerview.ViewConfig;
import com.foxconn.lau.wanandroid.R;
import com.foxconn.lau.wanandroid.bean.main.Article;
import com.foxconn.lau.wanandroid.module.main.contract.MainContract;
import com.foxconn.lau.wanandroid.utils.LogUtils;
import com.sackcentury.shinebuttonlib.ShineButton;

import java.util.List;

/**
 * @author 劉帥
 * @version 1.0
 * @des ${TODO}
 * @date 2019/12/5 下午 02:16
 */
public class BaseAdapter extends LauRecyclerAdapter<Article> {
    private List<Article> mList;
    private  MainContract.ItemClickCallback callback ;

    public BaseAdapter(Context mContext, List<Article> mList, MainContract.ItemClickCallback callback) {
        this(mContext,mList,R.layout.layout_article_item);
        this.callback = callback;
    }

    public BaseAdapter(Context mContext, List<Article> mList) {
        this(mContext,mList,R.layout.layout_article_item);
    }

    private BaseAdapter(Context mContext, List<Article> mList, int layoutId) {
        super(mContext, mList, layoutId);
        this.mList = mList;
    }


    @SuppressLint("SetTextI18n")
    //    @Override
    public void bindData(LauRecyclerViewHolder viewHolder, int i) {
        LogUtils.s(i);
        String niceDate = mList.get(i).getNiceDate();
        String superChapterName = mList.get(i).getSuperChapterName();
        viewHolder.setText(R.id.article_item_author_name, mList.get(i).getAuthor())
                .setText(R.id.article_item_chapter_name,superChapterName+"/"
                        +mList.get(i).getChapterName())
                .setText(R.id.article_item_title,mList.get(i).getTitle())
                .setText(R.id.article_item_time,niceDate);
        String descriptionImgUrl = mList.get(i).getEnvelopePic();
        if (!"".equals(descriptionImgUrl) && descriptionImgUrl != null) {
            viewHolder.setVisible(R.id.article_item_img_description, View.VISIBLE);
            viewHolder.setImageUrl(R.id.article_item_img_description, descriptionImgUrl);
        } else {
            viewHolder.setVisible(R.id.article_item_img_description, View.GONE);
        }
        if (niceDate.contains("小时")) {
            viewHolder.setVisible(R.id.article_item_new, View.VISIBLE);
        }else {
            viewHolder.setVisible(R.id.article_item_new, View.GONE);
        }
        if (superChapterName.contains("项目")) {
            viewHolder.setVisible(R.id.article_item_project_description, View.VISIBLE);
        } else {
            viewHolder.setVisible(R.id.article_item_project_description, View.GONE);
        }
        ShineButton shineButton = viewHolder.getView(R.id.article_item_like_img);
        shineButton.setOnCheckStateChangeListener((view, checked) -> {
            if (callback != null) {
                callback.likeChange(view,i,checked);
            }
        });
    }

    public List<Article> getRecentAticleList(){
        return mList;
    }

    public void addHeadView(View view){
        super.addHeaderView(new ViewConfig(view, false));
    }

}