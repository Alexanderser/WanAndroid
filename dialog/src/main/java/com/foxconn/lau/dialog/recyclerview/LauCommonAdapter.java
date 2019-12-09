package com.foxconn.lau.dialog.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;

import java.util.List;

/**
 * @author 劉帥
 * @version 1.0
 * @des ${TODO}
 * @date 2019/5/10 下午 04:04
 */
public abstract class LauCommonAdapter<T> extends LauMultiItemTypeAdapter<T> {

    protected Context mContext;
    protected int mLayoutId;
    protected List<T> mDatas;
    protected LayoutInflater mInflater;

    public LauCommonAdapter(final Context context, final int layoutId, List<T> datas) {
      super(context, datas);
    mContext = context;
    mInflater = LayoutInflater.from(context);
    mLayoutId = layoutId;
    mDatas = datas;

    addItemViewDelegate(new LauItemViewDelegate<T>() {
        @Override
        public int getItemViewLayoutId()
        {
            return layoutId;
        }

        @Override
        public boolean isForViewType( T item, int position)
        {
            return true;
        }

        @Override
        public void convert(LauRecyclerViewHolder holder, T t, int position)
        {
            LauCommonAdapter.this.convert(holder, t, position);
        }
    });
}

    protected abstract void convert(LauRecyclerViewHolder holder, T t, int position);


}
