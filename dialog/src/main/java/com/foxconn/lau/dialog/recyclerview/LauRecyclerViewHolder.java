package com.foxconn.lau.dialog.recyclerview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.CheckedTextView;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author 劉帥
 * @version 1.0
 * @des ${封装recycler viewholder}
 * @date 2019/5/10 上午 09:55
 */
public class LauRecyclerViewHolder extends RecyclerView.ViewHolder {

    private SparseArray<View> mArray;
    private View itemView;
    private Context mContext;

    private LauRecyclerViewHolder(@NonNull View itemView, Context mContext) {
        super(itemView);
        this.mContext = mContext;
        mArray = new SparseArray<>();
        this.itemView = itemView;
    }
    private LauRecyclerViewHolder(@NonNull View itemView){
        super(itemView);
    }

    public static LauRecyclerViewHolder createLauViewHolder(Context mContext,int layoutId, ViewGroup viewGroup){
        return new LauRecyclerViewHolder(LayoutInflater.from(mContext).inflate(layoutId, viewGroup, false),mContext);
    }

    public static LauRecyclerViewHolder createLauViewHolder(View view){
        return new LauRecyclerViewHolder(view);
    }


    public <T extends View>T getView(int key){
        View view = mArray.get(key);
        if (view == null) {
            view = itemView.findViewById(key);
            mArray.put(key, view);
        }
        return (T) view;
    }

    public View getItemView(){
        return itemView;
    }

    public LauRecyclerViewHolder setText(int viewId, String text) {
        TextView textView = getView(viewId);
        textView.setText(text);
        View view = getView(viewId);

        return this;
    }

    public LauRecyclerViewHolder setTextSize(int viewId, int textSize) {
        TextView textView = getView(viewId);
        textView.setTextSize(textSize);
        return this;
    }

    public LauRecyclerViewHolder setTextColor(int viewId, int color) {
        TextView textView = getView(viewId);
        textView.setTextColor(color);
        return this;
    }

    public LauRecyclerViewHolder setVisible(int viewId, int visible) {
        View view = getView(viewId);
        view.setVisibility(visible);
        return this;
    }

    public LauRecyclerViewHolder setBackgroundDrawable(int viewId, Drawable drawable) {
        View view = getView(viewId);
        view.setBackground(drawable);
        return this;
    }

    public LauRecyclerViewHolder setBackgroundResource(int viewId, int drawableId) {
        View view = getView(viewId);
        view.setBackgroundResource(drawableId);
        return this;
    }

    public LauRecyclerViewHolder setBackgroundColor(int viewId, int color) {
        View view = getView(viewId);
        view.setBackgroundColor(color);
        return this;
    }

    public LauRecyclerViewHolder setAnimation(int viewId, Animation animation) {
        View view = getView(viewId);
        view.setAnimation(animation);
        return this;
    }

    public LauRecyclerViewHolder setImageResource(int viewId,int drawableId) {
        ImageView view = getView(viewId);
        view.setImageResource(drawableId);
        return this;
    }

    public LauRecyclerViewHolder setTextColorRes(int viewId, int textColorRes) {
        TextView view = getView(viewId);
        view.setTextColor(mContext.getResources().getColor(textColorRes));
        return this;
    }

    /**
     * glide版本更新,需要改变图片设置方式
     * @param viewId .
     * @param imageUrl .
     * @return .
     */
    public LauRecyclerViewHolder setImageUrl(int viewId, String imageUrl) {
        ImageView view = getView(viewId);
        Glide.with(mContext).load(imageUrl).into(view);
        return this;
    }

    public LauRecyclerViewHolder setImageUrl(int viewId, String imageUrl,int placeholder) {
        ImageView view = getView(viewId);
        Glide.with(mContext).load(imageUrl).into(view);
        return this;
    }

    public LauRecyclerViewHolder setImageUrl(int viewId, String imageUrl,int width,int height) {
        ImageView view = getView(viewId);
        Glide.with(mContext).load(imageUrl).into(view);
        return this;
    }

    public LauRecyclerViewHolder setProgress(int viewId, int progress) {
        ProgressBar view = getView(viewId);
        view.setProgress(progress);
        return this;
    }

    public LauRecyclerViewHolder setProgress(int viewId, int progress, int max) {
        ProgressBar view = getView(viewId);
        view.setMax(max);
        view.setProgress(progress);
        return this;
    }

    public LauRecyclerViewHolder setMax(int viewId, int max) {
        ProgressBar view = getView(viewId);
        view.setMax(max);
        return this;
    }

    public LauRecyclerViewHolder setOnClickListener(int viewId, View.OnClickListener listener) {
        View view = getView(viewId);
        view.setOnClickListener(listener);
        return this;
    }

    public LauRecyclerViewHolder setOnTouchListener(int viewId, View.OnTouchListener listener) {
        View view = getView(viewId);
        view.setOnTouchListener(listener);
        return this;
    }

    public LauRecyclerViewHolder setOnLongClickListener(int viewId, View.OnLongClickListener listener) {
        View view = getView(viewId);
        view.setOnLongClickListener(listener);
        return this;
    }

    public LauRecyclerViewHolder setOnItemClickListener(int viewId, AdapterView.OnItemClickListener listener) {
        AdapterView view = getView(viewId);
        view.setOnItemClickListener(listener);
        return this;
    }

    public LauRecyclerViewHolder setOnItemLongClickListener(int viewId, AdapterView.OnItemLongClickListener listener) {
        AdapterView view = getView(viewId);
        view.setOnItemLongClickListener(listener);
        return this;
    }

    public LauRecyclerViewHolder setOnItemSelectedClickListener(int viewId,AdapterView.OnItemSelectedListener listener) {
        AdapterView view = getView(viewId);
        view.setOnItemSelectedListener(listener);
        return this;
    }

    public LauRecyclerViewHolder setOnCheckedChangeListener(int viewId, CompoundButton.OnCheckedChangeListener listener) {
        CompoundButton view = getView(viewId);
        view.setOnCheckedChangeListener(listener);
        return this;
    }

    public LauRecyclerViewHolder setChecked(int viewId, boolean checked) {
        View view = getView(viewId);
        // View unable cast to Checkable
        if (view instanceof CompoundButton) {
            ((CompoundButton) view).setChecked(checked);
        } else if (view instanceof CheckedTextView) {
            ((CheckedTextView) view).setChecked(checked);
        }
        return this;
    }


}
