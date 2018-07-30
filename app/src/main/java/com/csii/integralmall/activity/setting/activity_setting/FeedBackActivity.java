package com.csii.integralmall.activity.setting.activity_setting;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FeedBackActivity extends BaseActivity implements View.OnFocusChangeListener,
        SoftKeyBoardListener.OnSoftKeyBoardChangeListener, DisposeDataListener {

    private static final String TAG = FeedBackActivity.class.getSimpleName();
    @BindView(R.id.tv_title_center)
    TextView tvTitleCenter;
    @BindView(R.id.ib_return)
    ImageButton ibReturn;
    @BindView(R.id.tv_title_right)
    TextView tvTitleRight;
    @BindView(R.id.tv_title_dis_right)
    TextView tvTitleDisRight;
    @BindView(R.id.et_gather_opinions)
    EditText etGatherOpinions;
    @BindView(R.id.et_email_num)
    EditText etEmailNum;

    String responseMsg;
    String contactWay;

    private LinearLayout ll_account_register;

    @Override
    public void initView() {
        Log.i(TAG, "initView: 意见反馈");
        initTitleBar();
        ll_account_register = findViewById(R.id.ll_account_register);
        ButterKnife.bind(this);
    }

    @Override
    protected void initListener() {
        etGatherOpinions.setOnFocusChangeListener(this);
        etEmailNum.setOnFocusChangeListener(this);

        SoftKeyBoardListener.setListener(this, this);

    }

    @Override
    protected void initData() {
        tvTitleCenter.setText("意见反馈");
        tvTitleDisRight.setText("提交");
        tvTitleRight.setText("提交");
        tvTitleDisRight.setVisibility(View.VISIBLE);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_feed_back;
    }


    @OnClick({R.id.ib_return, R.id.tv_title_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ib_return:
                finish();
                break;
            case R.id.tv_title_right:
//                showToast("您的意见已提交");
                postRequest();
                break;
        }
    }

    private void postRequest() {
        RequestParams params = new RequestParams();
        String token = MyApplication.getToken();
        params.put("token", token);
        params.put("responseMsg", responseMsg);
        params.put("contactWay", contactWay);


        CommonOkHttpClient.post(CommonRequest.createPostRequest(Api.SUGGESTION, params),
                new DisposeDataHandle(this));
    }

    @Override
    public void onSuccess(Object responseObj) {
        Log.e(TAG, "onSuccess: " + responseObj.toString());
        showToast("系统已经收到您的意见！");
        finish();
    }

    @Override
    public void onFailure(Object reasonObj) {

    }

    @Override
    public void onFocusChange(View view, boolean hasFocus) {
        switch (view.getId()) {
            case R.id.et_gather_opinions:
                if (!hasFocus) {
                    showRegisterBtn();
                }
                break;
            case R.id.et_email_num:
                if (!hasFocus) {
                    showRegisterBtn();
                }
                break;
        }

    }

    private void showRegisterBtn() {
        getEtVal();
        if (TextUtils.isEmpty(responseMsg) || TextUtils.isEmpty(contactWay)) {
            tvTitleDisRight.setVisibility(View.VISIBLE);
            tvTitleRight.setVisibility(View.GONE);
        } else {
            tvTitleRight.setVisibility(View.VISIBLE);
            tvTitleDisRight.setVisibility(View.GONE);
        }
    }

    private void getEtVal() {
        responseMsg = etGatherOpinions.getText().toString().trim();
        contactWay = etEmailNum.getText().toString().trim();
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
