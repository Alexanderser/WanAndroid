package com.foxconn.lau.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.foxconn.lau.dialog.recyclerview.LauRecyclerAdapter;
import com.foxconn.lau.dialog.recyclerview.LauRecyclerViewHolder;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author 劉帥
 * @version 1.0
 * @des ${TODO}
 * @date 2019/5/16 下午 04:23
 */
public class BaseDialogFragment extends DialogFragment {

    private static int type = -1;
    private static String sShowTag;
    private static long sLastTime;
    boolean mDismissed;
    boolean mShownByMe;


    //bottom list
    private static List<String> bottomListData;
    private static final int RESID_BOTTOM_LIST_ID = R.layout.dialog_bottom_list;
    private String cancelText;

    //center msg
    private static final int RESID_CENTER_TIP_MSG_ID = R.layout.dialog_center_tip_message;

    //center list
    private static final int RESID_CENTER_LIST_ID = R.layout.dialog_bottom_list;

    //center toast
    private static final int RESID_CENTER_TOAST_ID = R.layout.dialog_center_toast;

    public BaseDialogFragment() {
        Bundle bundle = new Bundle();
        bundle.putInt("resId", type);
        setArguments(bundle);
    }

    public static BaseDialogFragment getInstance(int dialogType, List<String> data){
        type = dialogType;
        if (data != null) {
            bottomListData = data;
        }
        return new BaseDialogFragment();
    }

    public static BaseDialogFragment getInstance(int dialogType){
        type = dialogType;
        return new BaseDialogFragment();
    }

    public static final int TYPE_TOAST_ERROR=-1;
    public static final int TYPE_TOAST_FINISH = 0;
    public static final int TYPE_TOAST_WARN = 1;
    private static int toast;
    public static BaseDialogFragment getInstance(int dialogType,int toastType){
        type = dialogType;
        toast = toastType;
        return new BaseDialogFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (type == TYPE_CENTER_TOAST_DIALOG) {
            setStyle(DialogFragment.STYLE_NORMAL, R.style.TransparentDialogStyle);
        } else {
            setStyle(DialogFragment.STYLE_NORMAL, R.style.BaseDialogStyle);
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int type = -1;
        if (getArguments() != null && getArguments().containsKey("resId")) {
            type = getArguments().getInt("resId");
        } else {
            Log.e("Mr.Liu","创建dialog 无法获取resid信息");
        }
        View view = inflater.inflate(getResIdByType(type), container, false);
        if (type == TYPE_BOTTOM_LIST_DIALOG) {
            dealBottomView(view);
        } else if (type == TYPE_CENTER_MSG_DIALOG) {
            dealCenterMsgView(view);
        } else if (type == TYPE_CENTER_LIST_DIALOG) {
            dealBottomView(view);
        } else if (type == TYPE_CENTER_TOAST_DIALOG) {
            dealToastView(view);
        }
        return view;
    }

    @Override
    public void onResume() {
        WindowManager.LayoutParams params = Objects.requireNonNull(getDialog().getWindow()).getAttributes();
        if (type == TYPE_BOTTOM_LIST_DIALOG) {
            params.width = WindowManager.LayoutParams.MATCH_PARENT;
            params.windowAnimations = R.style.BottomAnimStyle;
            Objects.requireNonNull(getDialog().getWindow()).setAttributes(params);
            getDialog().getWindow().setGravity(Gravity.BOTTOM);
        } else if (type == TYPE_CENTER_MSG_DIALOG) {
            params.width = WindowManager.LayoutParams.MATCH_PARENT;
            params.windowAnimations = R.style.IOSAnimStyle;
            Objects.requireNonNull(getDialog().getWindow()).setAttributes(params);
            getDialog().getWindow().setGravity(Gravity.CENTER);
        } else if (type == TYPE_CENTER_LIST_DIALOG) {
            params.width = WindowManager.LayoutParams.MATCH_PARENT;
            params.windowAnimations = R.style.IOSAnimStyle;
            Objects.requireNonNull(getDialog().getWindow()).setAttributes(params);
            getDialog().getWindow().setGravity(Gravity.CENTER);
        } else if (type == TYPE_CENTER_TOAST_DIALOG) {
            params.width = WindowManager.LayoutParams.WRAP_CONTENT;
            params.windowAnimations = R.style.Animation_Toast;
            getDialog().getWindow().setAttributes(params);
            getDialog().getWindow().setGravity(Gravity.CENTER);
            if (mDuration == 0) {
                mDuration = 2000;
            }
            new Handler().postDelayed(() -> {
                if (getDialog() != null &&
                        getDialog().isShowing()) {
                    dismiss();
                }
            }, mDuration);
        }
        super.onResume();
    }

    public static final int TYPE_CENTER_MSG_DIALOG = 0;
    public static final int TYPE_BOTTOM_LIST_DIALOG = 1;
    public static final int TYPE_CENTER_LIST_DIALOG = 2;
    public static final int TYPE_CENTER_TOAST_DIALOG = 3;

    private int getResIdByType(int type){
        int res ;
        switch(type){
            case TYPE_CENTER_MSG_DIALOG:
                res = RESID_CENTER_TIP_MSG_ID;
                break;
            case TYPE_BOTTOM_LIST_DIALOG:
                res = RESID_BOTTOM_LIST_ID;
                break;
            case TYPE_CENTER_LIST_DIALOG:
                res = RESID_CENTER_LIST_ID;
                break;
            case TYPE_CENTER_TOAST_DIALOG:
                res = RESID_CENTER_TOAST_ID;
                break;
            default:
                res = RESID_CENTER_TIP_MSG_ID;
                break;
        }
        return res;
    }

    /*                  Toast消息弹出框                                      */

    private String tip;
    private long mDuration;

    private void dealToastView(View view) {
        ImageView iv = view.findViewById(R.id.dialog_center_toast_img);
        TextView tv = view.findViewById(R.id.dialog_center_toast_tv);
        switch(toast){
            case TYPE_TOAST_FINISH:
                iv.setImageResource(R.drawable.ic_dialog_finish);
                if (tip == null) {
                    tv.setText("完成");
                } else {
                    tv.setText(tip);
                }
                break;
            case TYPE_TOAST_ERROR:
                iv.setImageResource(R.drawable.ic_dialog_error);
                if (tip == null) {
                    tv.setText("失败");
                } else {
                    tv.setText(tip);
                }
                break;
            case TYPE_TOAST_WARN:
                iv.setImageResource(R.drawable.ic_dialog_warning);
                if (tip == null) {
                    tv.setText("警告");
                } else {
                    tv.setText(tip);
                }
                break;
            default:
                break;
        }
    }

    public BaseDialogFragment setDuration(long duration) {
        mDuration = duration;
        return this;
    }

    public BaseDialogFragment setToastTip(String tip) {
        this.tip = tip;
        return this;
    }

    /*                  Toast消息弹出框                                      */


    /*                  提示消息弹出框                                      */
    private String confirmMsg,centerTipMsg,centerTipTitle;
    private CenterTipMsgConfirmListener mCenterTipMsgconfirmListener;
    private CenterTipMsgCancelListener mCenterTipMsgCancelListener;

    private void dealCenterMsgView(View view) {
        TextView cancelTv= view.findViewById(R.id.dialog_center_tip_msg_cancel);
        TextView confirmTv = view.findViewById(R.id.dialog_center_tip_msg_confirm);
        if (centerTipTitle != null) {
            ((TextView)view.findViewById(R.id.dialog_center_tip_msg_title)).setText(centerTipTitle);
        }
        if (centerTipMsg != null) {
            ((TextView)view.findViewById(R.id.dialog_center_tip_msg_content)).setText(centerTipMsg);
        }
        if (cancelText != null) {
            cancelTv.setText(cancelText);
        }
        if (confirmMsg != null) {
            confirmTv.setText(confirmMsg);
        }
        if (mCenterTipMsgconfirmListener == null) {
            confirmTv.setOnClickListener(v -> this.dismiss());
        } else {
            confirmTv.setOnClickListener(v -> {
                mCenterTipMsgconfirmListener.onConfirm(v);
                this.dismiss();
            });
        }
        if (mCenterTipMsgCancelListener == null) {
            cancelTv.setOnClickListener(v -> this.dismiss());
        } else {
            cancelTv.setOnClickListener(v -> {
                mCenterTipMsgCancelListener.onCancel(v);
                this.dismiss();
            });
        }
    }

    public interface CenterTipMsgConfirmListener{
        void onConfirm(View v);
    }
    public interface CenterTipMsgCancelListener{

        void onCancel(View v);
    }

    public BaseDialogFragment setOnConfirmListener(CenterTipMsgConfirmListener centerTipMsgconfirmListener) {
        mCenterTipMsgconfirmListener = centerTipMsgconfirmListener;
        return this;
    }

    public BaseDialogFragment setOnCancelListener(CenterTipMsgCancelListener centerTipMsgCancelListener) {
        mCenterTipMsgCancelListener = centerTipMsgCancelListener;
        return this;
    }


    public BaseDialogFragment setConfirmBtn(String confirmMsg) {
        this.confirmMsg = confirmMsg;
        return this;
    }

    public BaseDialogFragment setCenterTipMsg(String centerTipMsg) {
        this.centerTipMsg = centerTipMsg;
        return this;
    }

    public BaseDialogFragment setCenterTipTitle(String centerTipTitle) {
        this.centerTipTitle = centerTipTitle;
        return this;
    }
    /*                  提示消息弹出框 结束                                     */

    /*                  底部list弹出框                         */
    private ListDialogClickListener mBottomListener;
    private BottomDialogCancleListener mBottomDialogCancleListener;

    private void dealBottomView(View view) {
        RecyclerView bottomListView = view.findViewById(R.id.dialog_bottom_rv);
        TextView tv = view.findViewById(R.id.dialog_bottom_cancel_tv);
        if (cancelText == null) {
            tv.setVisibility(View.GONE);
        } else {
            tv.setText(cancelText);
        }
        tv.setOnClickListener(v -> this.dismiss());
        BottomListAdapter adapter = new BottomListAdapter(getContext(), bottomListData, R.layout.item_dialog_menu);
        adapter.setOnClickListener((viewHolder, i) -> this.dismiss());
        if (mBottomListener != null) {
            adapter.setOnClickListener((viewHolder, i) -> {
                this.dismiss();
                mBottomListener.click(i);
            });
        } else {
            adapter.setOnClickListener((viewHolder, i) -> this.dismiss());
        }
        if (mBottomDialogCancleListener == null) {
            tv.setOnClickListener(v -> this.dismiss());
        } else {
            tv.setOnClickListener(v -> {
                this.dismiss();
                mBottomDialogCancleListener.cancel(v);
            });
        }
        bottomListView.setAdapter(adapter);
        bottomListView.setLayoutManager(new LinearLayoutManager(getContext()));
    }


    public BaseDialogFragment setOnItemClickListener(ListDialogClickListener bottomListener) {
        mBottomListener = bottomListener;
        return this;
    }

    public BaseDialogFragment setOnCancelClickListener(BottomDialogCancleListener cancelClickListener) {
        mBottomDialogCancleListener = cancelClickListener;
        return this;
    }


    public interface ListDialogClickListener {
        void click(int i);
    }

    public interface BottomDialogCancleListener{
        void cancel(View view);
    }

    private class BottomListAdapter extends LauRecyclerAdapter {

        public BottomListAdapter(Context mContext, List mList, int layoutId) {
            super(mContext, mList, layoutId);
        }

        @Override
        public void bindData(LauRecyclerViewHolder viewHolder, int i) {
            viewHolder.setText(R.id.tv_dialog_menu_name, bottomListData.get(i));
            if (i == 0) {
                // 当前是否只有一个条目
                if (getItemCount() == 1) {
                    viewHolder.getItemView().setBackgroundResource(R.drawable.dialog_menu_item);
                    viewHolder.setVisible(R.id.v_dialog_menu_line,View.GONE);
                }else {
                    viewHolder.getItemView().setBackgroundResource(R.drawable.dialog_menu_item_top);
                    viewHolder.setVisible(R.id.v_dialog_menu_line,View.VISIBLE);
                }
            }else if (i == getItemCount() - 1) {
                viewHolder.getItemView().setBackgroundResource(R.drawable.dialog_menu_item_bottom);
                viewHolder.setVisible(R.id.v_dialog_menu_line,View.GONE);
            }else {
                viewHolder.getItemView().setBackgroundResource(R.drawable.dialog_menu_item_middle);
                viewHolder.setVisible(R.id.v_dialog_menu_line,View.VISIBLE);
            }

        }
    }

    /*               底部list对话框结束                    */

    public void show(Fragment fragment) {
        show(fragment.getFragmentManager(), fragment.getClass().getName());
    }

    public void show(FragmentActivity activity) {
        show(activity.getSupportFragmentManager(), activity.getClass().getName());
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        if (!isRepeatedShow(tag)) {
            try {
                Class c=Class.forName("android.support.v4.app.DialogFragment");
                Constructor con = c.getConstructor();
                Object obj = con.newInstance();
                Field dismissed = c.getDeclaredField(" mDismissed");
                dismissed.setAccessible(true);
                dismissed.set(obj,false);
                Field shownByMe = c.getDeclaredField("mShownByMe");
                shownByMe.setAccessible(true);
                shownByMe.set(obj,false);
            } catch (Exception e) {
                e.printStackTrace();
            }
            FragmentTransaction ft = manager.beginTransaction();
            ft.add(this, tag);
            ft.commitAllowingStateLoss();
        }
    }


    //    @Override
//    public void show(FragmentManager manager, String tag) {
//        if (!isRepeatedShow(tag)) {
//            super.show(manager, tag);
//        }
//    }

    @Override
    public int show(FragmentTransaction transaction, String tag) {
        if (!isRepeatedShow(tag)) {
            return super.show(transaction, tag);
        }
        return -1;
    }

    /**
     * 根据 tag 判断这个 Dialog 是否重复显示了
     *
     * @param tag           Tag标记
     */
    protected boolean isRepeatedShow(String tag) {
        boolean result = tag.equals(sShowTag) && SystemClock.uptimeMillis() - sLastTime < 500;
        sShowTag = tag;
        sLastTime = SystemClock.uptimeMillis();
        return result;
    }

    public BaseDialogFragment setCancel(boolean cancel){
        super.setCancelable(cancel);
        return this;
    }

    public BaseDialogFragment setCancelBtn(String content){
        cancelText = content;
        return this;
    }
}
