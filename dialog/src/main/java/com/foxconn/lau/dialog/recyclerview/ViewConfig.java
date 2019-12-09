package com.foxconn.lau.dialog.recyclerview;

import android.view.View;

/**
 * @author 劉帥
 * @version 1.0
 * @des ${TODO}
 * @date 2019/12/7 上午 10:31
 */
public class ViewConfig {

    private View mView;
    private boolean isSelectable;

    public ViewConfig(View view, boolean isSelectable) {
        mView = view;
        this.isSelectable = isSelectable;
    }

    public View getView() {
        return mView;
    }

    public void setView(View view) {
        mView = view;
    }

    public boolean isSelectable() {
        return isSelectable;
    }

    public void setSelectable(boolean selectable) {
        isSelectable = selectable;
    }
}
