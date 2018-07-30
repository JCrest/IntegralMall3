package com.csii.integralmall.activity.accountLogin;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 *
 */
public class AccountRegisterActivity extends BaseActivity implements View.OnFocusChangeListener,
        SoftKeyBoardListener.OnSoftKeyBoardChangeListener, DisposeDataListener {

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
    @BindView(R.id.et_InvitationCode)
    EditText etInvitationCode;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.btn_disable_login)
    Button btnDisableLogin;

    String phone;//注册手机号
    String userPwd;//用户密码
    String enterUserPwd;//确认密码
    String checkCode;//验证码
    String invitephone;//邀请码

    private LinearLayout ll_account_register;

    SoftKeyBoardListener softKeyBoardListener;

    @Override
    public void initView() {
        initTitleBar();
        ll_account_register = findViewById(R.id.ll_account_register);
        ButterKnife.bind(this);
    }

    @Override
    protected void initListener() {
        //以下几个是监听 edittext 焦点变化
        etPhoneNum.setOnFocusChangeListener(this);
        etEnterNewPassword.setOnFocusChangeListener(this);
        etEnterPassword.setOnFocusChangeListener(this);
        etIdentifyingCode.setOnFocusChangeListener(this);
        etInvitationCode.setOnFocusChangeListener(this);

        //监听软键盘的收起与展示
        SoftKeyBoardListener.setListener(this, this);

    }

    @Override
    protected void initData() {
        tvTitleCenter.setText(this.getResources().getString(R.string.account_reg));
//         获取由登陆界面传来的手机号码（可全可缺可空）
        phone = getIntent().getStringExtra("phone");
//        将登陆界面传来的值设置到手机号位置上
        etPhoneNum.setText(phone);

    }

    /**
     * 显示注册（能点击）按钮
     * 当当手机号、验证码、登陆密码、确认登陆密码同时填写上的时候显示可以点击的注册按钮
     */
    private void showRegisterBtn() {
        getEtVal();
        if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(userPwd) || TextUtils.isEmpty(enterUserPwd) || TextUtils.isEmpty(checkCode)) {
            btnDisableLogin.setVisibility(View.VISIBLE);
            btnLogin.setVisibility(View.GONE);
        } else {
            btnDisableLogin.setVisibility(View.GONE);
            btnLogin.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 获取用户输入到 editext 中的值
     */
    private void getEtVal() {
        phone = etPhoneNum.getText().toString().trim();
        userPwd = etEnterNewPassword.getText().toString().trim();
        enterUserPwd = etEnterPassword.getText().toString().trim();
        checkCode = etIdentifyingCode.getText().toString().trim();
        invitephone = etInvitationCode.getText().toString().trim();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_account_register;
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
                showToast("发送验证码");
                break;
            case R.id.btn_login:
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
        if (!TextUtils.isEmpty(enterUserPwd) && !TextUtils.isEmpty(userPwd) && userPwd.equals(enterUserPwd)) {
            params.put("userPwd", userPwd);
        } else {
            showToast("请检查您输入的密码！");
        }
        params.put("checkcode", "1234");
        params.put("invitephone", invitephone);

        CommonOkHttpClient.post(CommonRequest.createPostRequest(Api.REGISTER, params),
                new DisposeDataHandle(this));
    }

    /**
     * 联网请求成功
     *
     * @param responseObj 成功的对象
     */
    @Override
    public void onSuccess(Object responseObj) {
        //此处调用该方法确定号码的正确性
        dealIntent(phone);
        if (responseObj != null) {
            finish();
            showToast("恭喜您，成功注册了哦！");
        }
        String jsonStr = new Gson().toJson(responseObj); //将对象转换成Json
        Log.e("成功了", jsonStr);

    }

    /**
     * 联网请求失败
     *
     * @param reasonObj 失败的对象
     */
    @Override
    public void onFailure(Object reasonObj) {
        String jsonStr = new Gson().toJson(reasonObj); //将对象转换成Json
        Log.e("失败了", jsonStr);

    }

    /**
     * 获取由登陆页面传来的含有参数的意图并将新的参数写入
     *
     * @param string 新的参数（此处为最终注册的电话号码）
     */
    private void dealIntent(String string) {
        Intent intent = getIntent();
        intent.putExtra("resultPhone", string);
        setResult(RESULT_OK, intent);
    }

    /**
     * 各组件焦点变化的监控
     *
     * @param view     控件
     * @param hasFocus 是否有焦点
     */
    @Override
    public void onFocusChange(View view, boolean hasFocus) {
        switch (view.getId()) {
            case R.id.et_phoneNum:
                if (!hasFocus) {
                    showRegisterBtn();
                } else {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
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
            case R.id.et_identifyingCode:
                if (!hasFocus) {
                    showRegisterBtn();
                }
                break;
        }
    }


    /**
     * 软键盘的展示
     *
     * @param height
     */
    @Override
    public void keyBoardShow(int height) {

    }

    /**
     * 软键盘的隐藏
     * 软键盘隐藏的时候将所有的 edittext 设置为失去焦点
     *
     * @param height
     */
    @Override
    public void keyBoardHide(int height) {
        ll_account_register.setFocusable(true);
        ll_account_register.setFocusableInTouchMode(true);
        ll_account_register.requestFocus();
    }


}
