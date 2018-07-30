package com.csii.integralmall.activity.personalData.activity_personal_data;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class settingNickname extends BaseActivity implements DisposeDataListener {
    final private String TAG = getClass().getSimpleName();
    @BindView(R.id.tv_title_center)
    TextView tvTitleCenter;
    @BindView(R.id.ib_return)
    ImageButton ibReturn;
    @BindView(R.id.tv_title_right)
    TextView tvTitleRight;
    @BindView(R.id.et_nickname)
    EditText etNickname;


    @Override
    public void initView() {
        initTitleBar();
        ButterKnife.bind(this);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        tvTitleCenter.setText("设置昵称");
        tvTitleRight.setVisibility(View.VISIBLE);
        etNickname.setText(getIntent().getStringExtra("nickName"));


    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting_nickname;
    }

    @OnClick({R.id.ib_return, R.id.tv_title_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ib_return:
                finish();
                break;
            case R.id.tv_title_right:
                showToast("保存");
                postRequest();
                break;
        }
    }

    /**
     * post 联网请求数据（登陆）
     */

    private String userName;

    private void postRequest() {
        RequestParams params = new RequestParams();
        userName = etNickname.getText().toString().trim();
        params.put("userName", userName);
        params.put("token", MyApplication.getToken());
        CommonOkHttpClient.post(CommonRequest.createPostRequest(Api.NICKNAMESET, params),
                new DisposeDataHandle(this));
    }

    @Override
    public void onSuccess(Object responseObj) {
        String str = responseObj.toString();
        String getSignInfo = str.substring(str.indexOf(":") + 1, str.indexOf(","));
        if (getSignInfo.equals("200")) {
            Log.e(TAG, "onSuccess: " + getSignInfo);
            getIntent().putExtra("userName", userName);
            setResult(RESULT_OK, getIntent());
            finish();
        }
    }

    @Override
    public void onFailure(Object reasonObj) {

    }
}
