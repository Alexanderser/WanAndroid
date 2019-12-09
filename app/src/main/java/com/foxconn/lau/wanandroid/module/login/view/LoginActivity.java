package com.foxconn.lau.wanandroid.module.login.view;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.foxconn.lau.dialog.LauDialogFactory;
import com.foxconn.lau.wanandroid.R;
import com.foxconn.lau.wanandroid.module.base.BaseActivity;
import com.foxconn.lau.wanandroid.module.customview.PercentImageView;
import com.foxconn.lau.wanandroid.module.customview.TitleBar;
import com.foxconn.lau.wanandroid.module.login.contract.LoginContract;
import com.foxconn.lau.wanandroid.module.login.presenter.LoginPresenter;
import com.foxconn.lau.wanandroid.utils.ArouteConstants;
import com.foxconn.lau.wanandroid.utils.ToastUtil;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Random;

import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnFocusChange;

/**
 * @author 劉帥
 * @version 1.0
 * @des ${TODO}
 * @date 2019/11/28 上午 11:44
 */

@Route(path = ArouteConstants.LOGIN_ACTIVIYT)
public class LoginActivity extends BaseActivity<LoginContract.ILoginView, LoginPresenter> implements LoginContract.ILoginView {


    @BindView(R.id.login_user)
    TextInputEditText mLoginUserEt;
    @BindView(R.id.login_user_layout)
    TextInputLayout mLoginUserLayout;
    @BindView(R.id.login_pw)
    TextInputEditText mLoginPwEt;
    @BindView(R.id.login_pw_layout)
    TextInputLayout mLoginPwLayout;
    @BindView(R.id.login_btn_login)
    Button mLoginBtnLogin;
    @BindView(R.id.login_tv_register)
    TextView mLoginTvRegister;
    @BindView(R.id.login_title_bar)
    TitleBar mLoginTitleBar;
    @BindView(R.id.iv_circle_1)
    PercentImageView mIvCircle1;
    @BindView(R.id.iv_circle_2)
    PercentImageView mIvCircle2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mLoginTitleBar.setOnReturnClickListener(v -> finish());
    }

    @Override
    public LoginPresenter initPresenter() {
        return new LoginPresenter();
    }

    @OnClick({R.id.login_btn_login, R.id.login_tv_register})
    public void click(View v) {
        switch (v.getId()) {
            case R.id.login_btn_login:
                presenter.login();
                break;
            case R.id.login_tv_register:
                register();
                break;
            default:
                break;
        }

    }

    @OnFocusChange(R.id.login_pw)
    public void focusChange(View v, boolean hasFocus) {
        if (v.getId() == R.id.login_pw) {
            if (hasFocus) {
                mLoginPwLayout.setCounterEnabled(true);
                mLoginPwLayout.setCounterMaxLength(12);
            } else {
                mLoginPwLayout.setCounterEnabled(false);
            }
        }
    }

    @Override
    public String getUserName() {
        return mLoginUserEt.getText() == null ? "" : mLoginUserEt.getText().toString();
    }

    @Override
    public String getPassWord() {
        return mLoginPwEt.getText() == null ? "" : mLoginPwEt.getText().toString();
    }

    @Override
    public void setUserError(String content) {
        mLoginUserLayout.setError(content);
    }

    @Override
    public void setPwError(String content) {
        mLoginPwLayout.setError(content);
    }

    @Override
    public void showToast(String content) {
        ToastUtil.shortToast(LoginActivity.this, content);
    }

    @Override
    public void showErrorDialog(String error) {
        LauDialogFactory.createErrorToastDialog(LoginActivity.this, error, Toast.LENGTH_SHORT);
    }

    @Override
    public void success() {
        ArouteConstants.enterActivity(ArouteConstants.MAIN_ACTIVITY);
    }

    @Override
    public void register() {
        ArouteConstants.enterActivity(ArouteConstants.REGISTER_ACTIVITY);
    }

    private boolean isRunning = false;
    private AnimatorSet mSet1;
    private AnimatorSet mSet2;

    @Override
    protected void onStart() {
        isRunning = true;
        mSet1 = startCircleAnim(mIvCircle1);
        mSet2 = startCircleAnim(mIvCircle2);
        super.onStart();
    }

    private void stopCircleAnim() {
        if (mSet1 != null) {
            mSet1.cancel();
            mSet1 = null;
        }
        if (mSet2 != null) {
            mSet2.cancel();
            mSet2 = null;
        }
    }

    @Override
    protected void onStop() {
        isRunning = false;
        stopCircleAnim();
        super.onStop();
    }

    private AnimatorSet startCircleAnim(View target) {
        if (target == null) {
            return null;
        }
        float[] xy = calculateRandomXY();
        AnimatorSet set = createTranslationAnimator(target, xy[0], xy[1]);
        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (isRunning) {
                    startCircleAnim(target);
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
        set.start();
        return set;
    }

    private final long mMaxMoveDuration = 20000L;
    private final int mMaxMoveDistanceX = 200;
    private final int mMaxMoveDistanceY = 20;

    private AnimatorSet createTranslationAnimator(View target, float toX, float toY) {
        float fromX = target.getTranslationX();
        float fromY = target.getTranslationY();
        long duration = calculateDuration(fromX, fromY, toX, toY);
        ObjectAnimator animatorX = ObjectAnimator.ofFloat(target, "translationX", fromX, toX);
        animatorX.setDuration(duration);
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(target, "translationY", fromY, toY);
        animatorY.setDuration(duration);
        AnimatorSet set = new AnimatorSet();
        set.playTogether(animatorX, animatorY);
        return set;
    }

    private Random mRandom = new Random();

    private float[] calculateRandomXY() {
        float x = mRandom.nextInt(mMaxMoveDistanceX) - (mMaxMoveDistanceX * 0.5F);
        float y = mRandom.nextInt(mMaxMoveDistanceY) - (mMaxMoveDistanceY * 0.5F);
        return new float[]{x, y};
    }

    private long calculateDuration(float x1, float y1, float x2, float y2) {
        float distance = (float) Math.abs(Math.sqrt(Math.pow(Math.abs((x1 - x2)), 2) + Math.pow(Math.abs((y1 - y2)), 2)));
        float maxDistance = (float) Math.abs(Math.sqrt(Math.pow(mMaxMoveDistanceX, 2) + Math.pow(mMaxMoveDistanceY, 2)));
        long duration = (long) (mMaxMoveDuration * (distance / maxDistance));
        return duration;
    }
}
