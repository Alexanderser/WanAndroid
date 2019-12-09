package com.foxconn.lau.dialog;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

/**
 * @author 劉帥
 * @version 1.0
 * @des ${多样dialog 创建工厂}
 * @date 2019/5/17 上午 11:00
 */
public class LauDialogFactory {

    /**
     *
     * 创建对话框,底部弹出,list形式
     * @param activity 当前activity
     * @param mList item选项
     * @param cancelStr 取消按钮文本(不设置不显示)
     * @param isCancel  点击其他位置是否可取消
     * @param listener1 list item listener
     * @param listener2 取消 listener
     */
    public static void createBottomListDialog
            (AppCompatActivity activity, List<String> mList, String cancelStr, boolean isCancel,
             BaseDialogFragment.ListDialogClickListener listener1,BaseDialogFragment.BottomDialogCancleListener listener2){
        BaseDialogFragment.getInstance(BaseDialogFragment.TYPE_BOTTOM_LIST_DIALOG,mList)
                        .setCancel(isCancel)
                        .setCancelBtn(cancelStr)
                        .setOnItemClickListener(listener1)
                        .setOnCancelClickListener(listener2).show(activity);
    }

    public static void createBottomListDialog
            (AppCompatActivity activity, List<String> mList, String cancelStr, boolean isCancel,
             BaseDialogFragment.ListDialogClickListener listener1){
        createBottomListDialog(activity,mList,cancelStr,isCancel,listener1,null);
    }

    public static void createBottomListDialog
            (AppCompatActivity activity, List<String> mList, String cancelStr,
             BaseDialogFragment.ListDialogClickListener listener1){
        createBottomListDialog(activity,mList,cancelStr,false,listener1);
    }

    public static void createBottomListDialog
            (AppCompatActivity activity, List<String> mList, BaseDialogFragment.ListDialogClickListener listener1){
        createBottomListDialog(activity,mList,"取消",listener1);
    }

    /**
     * 创建普通提示消息对话框,中间弹出
     * @param activity  activit
     * @param title     标题
     * @param msg       提示信息
     * @param cancelStr 取消按钮文本(不设置默认:取消)
     * @param confirmStr    确定按钮文本(不设置默认:确定)
     * @param isCancel  点击其他位置是否消失
     */
    public static void createCenterMsgDialog
            (FragmentActivity activity, String title, String msg, String cancelStr, String confirmStr, boolean isCancel,
             BaseDialogFragment.CenterTipMsgConfirmListener listener1, BaseDialogFragment.CenterTipMsgCancelListener listener2){
        BaseDialogFragment.getInstance(BaseDialogFragment.TYPE_CENTER_MSG_DIALOG)
                .setCancel(isCancel)
                .setCancelBtn(cancelStr)
                .setConfirmBtn(confirmStr)
                .setCenterTipTitle(title)
                .setCenterTipMsg(msg)
                .setOnConfirmListener(listener1)
                .setOnCancelListener(listener2).show(activity);
    }

    public static void createCenterMsgDialog
            (FragmentActivity activity, String title, String msg,  String cancelStr, String confirmStr, boolean isCancel,
             BaseDialogFragment.CenterTipMsgConfirmListener listener1){
        createCenterMsgDialog(activity, title, msg, cancelStr, confirmStr, isCancel, listener1,null);
    }

    public static void createCenterMsgDialog
            (FragmentActivity activity, String title, String msg,   boolean isCancel,
             BaseDialogFragment.CenterTipMsgConfirmListener listener1){
        createCenterMsgDialog(activity, title, msg, "取消","确定", isCancel, listener1);
    }

    public static void createCenterMsgDialog
            (FragmentActivity activity, String title, String msg, BaseDialogFragment.CenterTipMsgConfirmListener listener1){
        createCenterMsgDialog(activity, title, msg, false, listener1);
    }

    /**
     * 创建对话框,中间弹出,list形式
     * @param activity 当前activity
     * @param mList item选项
     * @param isCancel  点击其他位置是否可取消
     * @param listener  listener
     */
    public static void createCenterListDialog
            (FragmentActivity activity, List<String> mList, boolean isCancel, BaseDialogFragment.ListDialogClickListener listener){
        BaseDialogFragment.getInstance(BaseDialogFragment.TYPE_CENTER_LIST_DIALOG,mList)
                .setCancel(isCancel)
                .setOnItemClickListener(listener).show(activity);
    }

    /**
     *
     * 创建toast--- finish
     * @param activity activity
     * @param text 显示文本
     * @param duration 显示时间
     */
    public static void createFinishToastDialog(FragmentActivity activity,String text,long duration){
        BaseDialogFragment.getInstance(BaseDialogFragment.TYPE_CENTER_TOAST_DIALOG,BaseDialogFragment.TYPE_TOAST_FINISH)
                .setToastTip(text)
                .setDuration(duration).show(activity);
    }

    /**
     * 创建toast--- error
     * @param activity activity
     * @param text 显示文本
     * @param duration 显示时间
     */
    public static void createErrorToastDialog(FragmentActivity activity,String text,long duration){
        BaseDialogFragment.getInstance(BaseDialogFragment.TYPE_CENTER_TOAST_DIALOG,BaseDialogFragment.TYPE_TOAST_ERROR)
                .setToastTip(text).setDuration(duration).show(activity);
    }

    /**
     * 创建toast--- warn
     * @param activity activity
     * @param text 显示文本
     * @param duration 显示时间
     */
    public static void createWarnToastDialog(FragmentActivity activity, String text, long duration){
        BaseDialogFragment.getInstance(BaseDialogFragment.TYPE_CENTER_TOAST_DIALOG,BaseDialogFragment.TYPE_TOAST_WARN)
                .setToastTip(text).setDuration(duration).show(activity);
    }
}
