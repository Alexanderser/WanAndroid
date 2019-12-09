package com.foxconn.lau.dialog.recyclerview;

/**
 * @author 劉帥
 * @version 1.0
 * @des ${TODO}
 * @date 2019/5/10 下午 03:35
 */
public interface LauItemViewDelegate<T> {

    int getItemViewLayoutId();

    boolean isForViewType(T item, int position);

    void convert(LauRecyclerViewHolder holder, T t, int position);

}
