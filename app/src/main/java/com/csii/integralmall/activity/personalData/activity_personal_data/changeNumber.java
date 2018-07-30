package com.csii.integralmall.activity.personalData.activity_personal_data;

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
import com.csii.integralmall.common.MyApplication;
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

public class changeNumber extends BaseActivity implements SoftKeyBoardListener.OnSoftKeyBoardChangeListener,
        DisposeDataListener, View.OnFocusChangeListener {

    private static final String TAG = changeNumber.class.getSimpleName();
    @BindView(R.id.tv_title_center)
    TextView tvTitleCenter;
    @BindView(R.id.ib_return)
    ImageButton ibReturn;
    @BindView(R.id.et_newPhoneNum)
    EditText etNewPhoneNum;
    @BindView(R.id.et_identifyingCode)
    EditText etIdentifyingCode;
    @BindView(R.id.btn_sendSMS)
    Button btnSendSMS;
    @BindView(R.id.btn_confirm)
    Button btnConfirm;
    @BindView(R.id.btn_disconfirm)
    Button btnDisConfirm;

    String checkCode;//验证码
    String phone;//注册手机号

    private LinearLayout ll_account_register;

    @Override
    public void initView() {
        initTitleBar();
        Log.i(TAG, "initView: 修改手机号");
        ll_account_register = findViewById(R.id.ll_account_register);
        ButterKnife.bind(this);
    }

    @Override
    protected void initListener() {
        etNewPhoneNum.setOnFocusChangeListener(this);
        etIdentifyingCode.setOnFocusChangeListener(this);

        SoftKeyBoardListener.setListener(this, this);

    }

    @Override
    protected void initData() {
        tvTitleCenter.setText("修改手机号");

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_change_number;
    }


    @OnClick({R.id.ib_return, R.id.btn_sendSMS, R.id.btn_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ib_return:
                finish();
                break;
            case R.id.btn_sendSMS:
                showToast("成功发送短信，请注意查收！");
                break;
            case R.id.btn_confirm:
//                showToast("手机修改成功");
                postRequest();
                break;
        }
    }

    private void postRequest() {
        RequestParams params = new RequestParams();
        String token = MyApplication.getToken();
        params.put("token", token);
        if (Utils.isChinaPhoneLegal(phone)) {
            params.put("phone", phone);
        } else {
            showToast("请输入正确的手机号！");
        }
        params.put("checkcode", checkCode);
        CommonOkHttpClient.post(CommonRequest.createPostRequest(Api.MODIFYPHONE, params),
                new DisposeDataHandle(this));
    }

    @Override
    public void onSuccess(Object responseObj) {
        Log.e(TAG, "onSuccess: " + responseObj.toString());
        showToast("手机号修改成功！");
        finish();
    }

    @Override
    public void onFailure(Object reasonObj) {
        Log.e(TAG, "onFailure: " + reasonObj.toString());
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

    @Override
    public void onFocusChange(View view, boolean hasFocus) {
        switch (view.getId()) {
            case R.id.et_newPhoneNum:
                showRegisterBtn();
                break;
            case R.id.et_identifyingCode:
                showRegisterBtn();
                break;
        }

    }

    private void showRegisterBtn() {
        getEtVal();
        if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(checkCode)) {
            btnDisConfirm.setVisibility(View.VISIBLE);
            btnConfirm.setVisibility(View.GONE);
        } else {
            btnDisConfirm.setVisibility(View.GONE);
            btnConfirm.setVisibility(View.VISIBLE);
        }
    }

    private void getEtVal() {
        phone = etNewPhoneNum.getText().toString().trim();
        checkCode = etIdentifyingCode.getText().toString().trim();
    }
}
