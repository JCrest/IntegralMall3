package com.csii.integralmall.activity.personalData.activity_personal_data;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.csii.integralmall.R;
import com.csii.integralmall.activity.accountLogin.FindPasswordActivity;
import com.csii.integralmall.base.BaseActivity;
import com.csii.integralmall.common.Api;
import com.csii.integralmall.common.MyApplication;
import com.csii.integralmall.okhttp.CommonOkHttpClient;
import com.csii.integralmall.okhttp.listener.DisposeDataHandle;
import com.csii.integralmall.okhttp.listener.DisposeDataListener;
import com.csii.integralmall.okhttp.request.CommonRequest;
import com.csii.integralmall.okhttp.request.RequestParams;
import com.csii.integralmall.utils.SoftKeyBoardListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class changePassword extends BaseActivity implements View.OnFocusChangeListener,
        SoftKeyBoardListener.OnSoftKeyBoardChangeListener, DisposeDataListener {
    private static final String TAG = changePassword.class.getSimpleName();
    @BindView(R.id.tv_title_center)
    TextView tvTitleCenter;
    @BindView(R.id.ib_return)
    ImageButton ibReturn;
    @BindView(R.id.et_oldPassword)
    EditText etOldPassword;
    @BindView(R.id.et_newPassword)
    EditText etNewPassword;
    @BindView(R.id.et_confirmNewPassword)
    EditText etConfirmNewPassword;
    @BindView(R.id.btn_confirm)
    Button btnConfirm;
    @BindView(R.id.btn_disconfirm)
    Button btnDisConfirm;
    @BindView(R.id.tv_forget_password)
    TextView tvForgetPassword;
    @BindView(R.id.ll_change_pwd)
    LinearLayout llChangePwd;

    Intent intent;

    String oldPwd;
    String newPwd;
    String conPwd;

    SoftKeyBoardListener softKeyBoardListener;

    @Override
    public void initView() {
        Log.i(TAG, "initView: 修改密码");
        ButterKnife.bind(this);
        initTitleBar();
    }

    @Override
    protected void initListener() {

        etOldPassword.setOnFocusChangeListener(this);
        etNewPassword.setOnFocusChangeListener(this);
        etConfirmNewPassword.setOnFocusChangeListener(this);

        softKeyBoardListener.setListener(this, this);

    }

    @Override
    protected void initData() {
        tvTitleCenter.setText(this.getResources().getString(R.string.change_pwd));

    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_change_password;
    }

    @OnClick({R.id.ib_return, R.id.btn_confirm, R.id.tv_forget_password})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ib_return:
                finish();
                break;
            case R.id.btn_confirm:
//                showToast("这是确认按钮");
                postRequest();
                break;
            case R.id.tv_forget_password:
                intent = new Intent(this, FindPasswordActivity.class);
                startActivity(intent);
//                showToast("你忘了么");
                break;
        }
    }

    /**
     * 请求数据
     */
    private void postRequest() {
        String token = MyApplication.getToken();
        RequestParams params = new RequestParams();
        params.put("token", token);
        if (!TextUtils.isEmpty(oldPwd)) {
            params.put("userPwd", oldPwd);
        } else {
            showToast("请输入密码！");
        }
        if (!TextUtils.isEmpty(newPwd) && !TextUtils.isEmpty(conPwd) && newPwd.equals(conPwd)) {
            params.put("newuserPwd", newPwd);
        } else {
            showToast("请确认您输入的新密码！");
        }
        CommonOkHttpClient.post(CommonRequest.createPostRequest(Api.MODIFYPASSWORD, params),
                new DisposeDataHandle(this));
    }

    /**
     * @param responseObj 成功的对象
     */
    @Override
    public void onSuccess(Object responseObj) {
        showToast("密码修改成功！");
    }

    /**
     * @param reasonObj 失败的对象
     */
    @Override
    public void onFailure(Object reasonObj) {

    }

    /**
     * 监听编辑框焦点变化
     *
     * @param view     控件
     * @param hasFocus 是否有焦点
     */
    @Override
    public void onFocusChange(View view, boolean hasFocus) {
        switch (view.getId()) {
            case R.id.et_oldPassword:
                if (!hasFocus) {
                    showConfirmBtn();
                }
                break;
            case R.id.et_newPassword:
                if (!hasFocus) {
                    showConfirmBtn();
                }
                break;
            case R.id.et_confirmNewPassword:
                if (!hasFocus) {
                    showConfirmBtn();
                }
                break;
        }

    }


    /**
     * 显示确认按钮（使之可以按）
     */
    private void showConfirmBtn() {
        getPwd();
        if (TextUtils.isEmpty(oldPwd) || TextUtils.isEmpty(newPwd) || TextUtils.isEmpty(conPwd)) {
            btnDisConfirm.setVisibility(View.VISIBLE);
            btnConfirm.setVisibility(View.GONE);
        } else {
            btnDisConfirm.setVisibility(View.GONE);
            btnConfirm.setVisibility(View.VISIBLE);
        }

    }

    /**
     * 获取编辑框的内容
     */
    private void getPwd() {
        oldPwd = etOldPassword.getText().toString().trim();
        newPwd = etNewPassword.getText().toString().trim();
        conPwd = etConfirmNewPassword.getText().toString().trim();
    }


    @Override
    public void keyBoardShow(int height) {

    }

    /**
     * 监控软件盘隐藏
     *
     * @param height
     */
    @Override
    public void keyBoardHide(int height) {
        llChangePwd.setFocusable(true);
        llChangePwd.setFocusableInTouchMode(true);
        llChangePwd.requestFocus();
    }


}
