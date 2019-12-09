package com.foxconn.lau.dialog.recyclerview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author 劉帥
 * @version 1.0
 * @des ${封装 recyclerview  普通封装 不支持多类型}
 * @date 2019/5/10 上午 09:49
 */
public abstract class LauRecyclerAdapter<T> extends RecyclerView.Adapter<LauRecyclerViewHolder> {

    private Context mContext;
    private List<T> mList;
    private int layoutId;
    private OnItemClickListener mOnClickListener;
    private OnItemLongClickListener mOnLongClickListener;
    private List<ViewConfig> mHeaderViews;
    private List<ViewConfig> mFooterViews;
    private final int COMMON_VIEW = 0x2111;

    public LauRecyclerAdapter(Context mContext, List<T> mList, int layoutId) {
        if (mList == null) {
            this.mList = new ArrayList<>();
        } else {
            this.mList = mList;
        }
        this.mContext = mContext;
        this.layoutId = layoutId;
    }

    @NonNull
    @Override
    public LauRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i < COMMON_VIEW) {
            return LauRecyclerViewHolder.createLauViewHolder(mHeaderViews.get(Math.abs(COMMON_VIEW - i-1)).getView());
        } else if(i>COMMON_VIEW) {
            return LauRecyclerViewHolder.createLauViewHolder(mFooterViews.get(Math.abs(COMMON_VIEW - i-1)).getView());
        }
        return LauRecyclerViewHolder.createLauViewHolder(mContext,layoutId, viewGroup);
    }

    @Override
    public void onBindViewHolder(@NonNull final LauRecyclerViewHolder viewHolder, final int i) {
        if (getItemViewType(i)!=COMMON_VIEW)
            return;
        int pos = getRealPosition(i);
        bindData(viewHolder,pos);
            viewHolder.getItemView().setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (mOnLongClickListener != null) {
                        mOnLongClickListener.onLongClick(viewHolder,pos);
                    }
                    return true;
                }
            });
            viewHolder.getItemView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnClickListener != null) {
                        mOnClickListener.onClick(viewHolder,pos);
                    }
                }
            });
    }

    public void setOnClickListener(OnItemClickListener listener){
        this.mOnClickListener = listener;
    }

    public void setOnLongClickListener(OnItemLongClickListener listener) {
        this.mOnLongClickListener = listener;
    }

    public interface OnItemLongClickListener{
        void onLongClick(LauRecyclerViewHolder viewHolder, int i);
    }

    public interface OnItemClickListener{
        void onClick(LauRecyclerViewHolder viewHolder, int i);
    }

    public abstract void bindData(LauRecyclerViewHolder viewHolder, int i);

    @Override
    public int getItemViewType(int position) {
        if (mHeaderViews == null && mFooterViews == null) {
            return COMMON_VIEW;
        } else if (mHeaderViews != null && mHeaderViews.size() > position) {
            return COMMON_VIEW-mHeaderViews.size();
        } else if (mFooterViews != null && (mHeaderViews == null ? mList.size() : mHeaderViews.size() + mList.size()) < position) {
            return COMMON_VIEW+mFooterViews.size();
        }
        return COMMON_VIEW;
    }

    public int getRealPosition(int i) {
        return i - getHeaderCount();
    }

    @Override
    public int getItemCount() {
        int headerViewSize = (mHeaderViews == null ? 0 : mHeaderViews.size());
        int footerViewSize = (mFooterViews == null ? 0 : mFooterViews.size());
        return (mList.size() + headerViewSize + footerViewSize);
    }

    public int getCommonListCount(){
        return mList.size();
    }

    public int getHeaderCount(){
        return (mHeaderViews == null ? 0 : mHeaderViews.size());
    }

    public int getFooterCount(){
        return (mFooterViews == null ? 0 : mFooterViews.size());
    }

    public void addHeaderView(ViewConfig view){
        if (getHeaderCount() == 0) {
            mHeaderViews = new ArrayList<>();
        }
        mHeaderViews.add(view);
        notifyItemInserted(mHeaderViews.size()-1);
    }

    public void addFooterView(ViewConfig view){
        if (getHeaderCount() == 0) {
            mFooterViews = new ArrayList<>();
        }
        mFooterViews.add(view);
        notifyItemInserted(getItemCount()-1);
    }

}
