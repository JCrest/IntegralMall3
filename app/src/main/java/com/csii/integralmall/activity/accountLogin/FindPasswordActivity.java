package com.csii.integralmall.activity.accountLogin;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.csii.integralmall.R;
import com.csii.integralmall.base.BaseActivity;
import com.csii.integralmall.common.Api;
import com.csii.integralmall.okhttp.CommonOkHttpClient;
import com.csii.integralmall.okhttp.listener.DisposeDataHandle;
import com.csii.integralmall.okhttp.listener.DisposeDataListener;
import com.csii.integralmall.okhttp.request.CommonRequest;
import com.csii.integralmall.okhttp.request.RequestParams;
import com.csii.integralmall.utils.SoftKeyBoardListener;
import com.csii.integralmall.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FindPasswordActivity extends BaseActivity implements DisposeDataListener,
        View.OnFocusChangeListener, SoftKeyBoardListener.OnSoftKeyBoardChangeListener {
    private static final String TAG = FindPasswordActivity.class.getSimpleName();

    @BindView(R.id.tv_title_center)
    TextView tvTitleCenter;
    @BindView(R.id.ib_return)
    ImageButton ibReturn;
    @BindView(R.id.et_phoneNum)
    EditText etPhoneNum;
    @BindView(R.id.Ibtn_delete)
    ImageButton IbtnDelete;
    @BindView(R.id.et_identifyingCode)
    EditText etIdentifyingCode;
    @BindView(R.id.btn_sendSMS)
    Button btnSendSMS;
    @BindView(R.id.et_EnterNewPassword)
    EditText etEnterNewPassword;
    @BindView(R.id.et_EnterPassword)
    EditText etEnterPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.btn_disable_login)
    Button btnDisableLogin;

    String checkCode;//验证码
    String phone;//注册手机号
    String newPwd;//新密码
    String confirmPwd;//确认密码

    private LinearLayout ll_account_register;

    @Override
    public void initView() {
        Log.i(TAG, "initView: 找回密码");
        initTitleBar();
        ll_account_register = findViewById(R.id.ll_account_register);
        ButterKnife.bind(this);
    }

    @Override
    protected void initListener() {
        etPhoneNum.setOnFocusChangeListener(this);
        etIdentifyingCode.setOnFocusChangeListener(this);
        etEnterNewPassword.setOnFocusChangeListener(this);
        etEnterPassword.setOnFocusChangeListener(this);

        SoftKeyBoardListener.setListener(this, this);

    }

    @Override
    protected void initData() {
        tvTitleCenter.setText(this.getResources().getString(R.string.find_password));

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_find_password;
    }


    @OnClick({R.id.ib_return, R.id.Ibtn_delete, R.id.btn_sendSMS, R.id.btn_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ib_return:
                finish();
                break;
            case R.id.Ibtn_delete:
                etPhoneNum.setText("");
                break;
            case R.id.btn_sendSMS:
                showToast("发送短信成功");
                break;
            case R.id.btn_login:
//                showToast("确认按钮点了");
                postRequest();
                break;
        }
    }

    private void postRequest() {
        RequestParams params = new RequestParams();

        if (Utils.isChinaPhoneLegal(phone)) {
            params.put("phone", phone);
        } else {
            showToast("请输入正确的手机号！");
        }
        params.put("checkcode", checkCode);
        params.put("newuserPwd", newPwd);
        CommonOkHttpClient.post(CommonRequest.createPostRequest(Api.USERFINDPASSWORD, params),
                new DisposeDataHandle(this));
    }

    @Override
    public void onSuccess(final Object responseObj) {
        Log.e(TAG, "onSuccess: " + responseObj.toString());
        showToast("密码修改成功！");
        finish();
    }

    @Override
    public void onFailure(Object reasonObj) {
        Log.e(TAG, "onFailure: " + reasonObj.toString());

    }

    @Override
    public void onFocusChange(View view, boolean hasFocus) {
        switch (view.getId()) {
            case R.id.et_phoneNum:
                if (!hasFocus) {
                    showRegisterBtn();
                }
                break;
            case R.id.et_identifyingCode:
                if (!hasFocus) {
                    showRegisterBtn();
                }
                break;
            case R.id.et_EnterNewPassword:
                if (!hasFocus) {
                    showRegisterBtn();
                }
                break;
            case R.id.et_EnterPassword:
                if (!hasFocus) {
                    showRegisterBtn();
                }
                break;
        }

    }

    private void showRegisterBtn() {
        getEtVal();
        if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(newPwd) || TextUtils.isEmpty(confirmPwd) || TextUtils.isEmpty(checkCode)) {
            btnDisableLogin.setVisibility(View.VISIBLE);
            btnLogin.setVisibility(View.GONE);
        } else {
            btnDisableLogin.setVisibility(View.GONE);
            btnLogin.setVisibility(View.VISIBLE);
        }
    }

    private void getEtVal() {
        phone = etPhoneNum.getText().toString().trim();
        checkCode = etIdentifyingCode.getText().toString().trim();
        newPwd = etEnterNewPassword.getText().toString().trim();
        confirmPwd = etEnterPassword.getText().toString().trim();
    }

    @Override
    public void keyBoardShow(int height) {

    }

    @Override
    public void keyBoardHide(int height) {
        ll_account_register.setFocusable(true);
        ll_account_register.setFocusableInTouchMode(true);
        ll_account_register.requestFocus();
    }
}
