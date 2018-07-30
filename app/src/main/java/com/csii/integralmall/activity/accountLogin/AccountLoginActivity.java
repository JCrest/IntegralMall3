package com.csii.integralmall.activity.accountLogin;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.csii.integralmall.R;
import com.csii.integralmall.base.BaseActivity;
import com.csii.integralmall.bean.User;
import com.csii.integralmall.common.Api;
import com.csii.integralmall.common.MyApplication;
import com.csii.integralmall.common.SharedPreferencesHelper;
import com.csii.integralmall.okhttp.CommonOkHttpClient;
import com.csii.integralmall.okhttp.listener.DisposeDataHandle;
import com.csii.integralmall.okhttp.listener.DisposeDataListener;
import com.csii.integralmall.okhttp.request.CommonRequest;
import com.csii.integralmall.okhttp.request.RequestParams;
import com.csii.integralmall.utils.Utils;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AccountLoginActivity extends BaseActivity implements DisposeDataListener {

    private final static String TAG = AccountRegisterActivity.class.getSimpleName();

    @BindView(R.id.tv_title_center)
    TextView tvTitleCenter;
    @BindView(R.id.ib_return)
    ImageButton ibReturn;
    @BindView(R.id.tv_title_right)
    TextView tvTitleRight;
    @BindView(R.id.et_phoneNum)
    EditText etPhoneNum;
    @BindView(R.id.Ibtn_delete)
    ImageButton IbtnDelete;
    @BindView(R.id.et_EnterPassword)
    EditText etEnterPassword;
    @BindView(R.id.tv_forget_login_passwd)
    TextView tvForgetLoginPasswd;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.btn_disable_login)
    Button btnDisableLogin;
    private ImageView ivLoading;
    private LinearLayout llLoginParent;


    @BindView(R.id.get_Test)
    TextView getTest;

    private SharedPreferencesHelper sharedPreferencesHelper;

    private String userStr;


    @Override
    public void initView() {
        initTitleBar();
        ivLoading = (ImageView) findViewById(R.id.iv_loading);
        llLoginParent = (LinearLayout) findViewById(R.id.ll_login_parent);
//        用来存储token
        sharedPreferencesHelper = new SharedPreferencesHelper(this, "token");
        ButterKnife.bind(this);
        ivLoading.setFocusable(true);
//        btnLogin.setEnabled(false);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        tvTitleCenter.setText("账户登录");
        tvTitleRight.setText("注册");
        tvTitleRight.setTextColor(ContextCompat.getColor(this, R.color.titleTextRight));
        tvTitleRight.setVisibility(View.VISIBLE);

        userStr = (String) sharedPreferencesHelper.getSharedPreference("user", "");
        if (!(TextUtils.isEmpty(userStr))) {
            User user = new Gson().fromJson(userStr, User.class);//把JSON字符串转为对象
            etPhoneNum.setText(user.getResult().getPhone());
        }

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_account_login;
    }


    @OnClick({R.id.ib_return, R.id.tv_title_right, R.id.tv_forget_login_passwd, R.id.btn_login, R.id.Ibtn_delete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ib_return:
                finish();
                break;
            case R.id.tv_title_right:
                String phone = etPhoneNum.getText().toString().trim();
                Intent intent = new Intent(this, AccountRegisterActivity.class);
                intent.putExtra("phone", phone);
                int requestCode = 1;
                startActivityForResult(intent, requestCode);
                break;
            case R.id.tv_forget_login_passwd:
                intent = new Intent(this, FindPasswordActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_login:
//                getRequest();
                postRequest();
//                showToast("点不了吗？");
                break;
            case R.id.Ibtn_delete:
                etPhoneNum.setText("");
                break;
        }
    }


    /**
     * 重写此方法用来获得由上个页面传过来的数据
     *
     * @param requestCode 请求码
     * @param resultCode  返回码
     * @param data        携带数据的意图
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            String resultPhone = data.getStringExtra("resultPhone");
            etPhoneNum.setText(resultPhone);
        }
    }

    /**
     * post 联网请求数据（登陆）
     */
    private void postRequest() {
        RequestParams params = new RequestParams();
        String phone = etPhoneNum.getText().toString().trim();
        String userPwd = etEnterPassword.getText().toString().trim();
        params.put("userPwd", userPwd);
        if (Utils.isChinaPhoneLegal(phone)) {
            params.put("phone", phone);
        } else {
            showToast("请输入正确的手机号！");
        }

        Log.e("params", params + "");

        CommonOkHttpClient.post(CommonRequest.createPostRequest(Api.LOGIN, params),
                new DisposeDataHandle(this, User.class));
    }


    /**
     * 数据请求成功
     *
     * @param responseObj 成功的对象
     */
    @Override
    public void onSuccess(Object responseObj) {
        getTest.setText(responseObj.toString());
//        sharedPreferencesHelper.remove("token");
 /*       sharedPreferencesHelper.put("username", (User) responseObj);
        sharedPreferencesHelper.getAll();*/
        User user = (User) responseObj;
        sharedPreferencesHelper.put("token", user.getResult().getToken());
        sharedPreferencesHelper.put("isLogin", true);

        String jsonStr = new Gson().toJson(user); //将对象转换成Json
        sharedPreferencesHelper.put("user", jsonStr);
        userStr = (String) sharedPreferencesHelper.getSharedPreference("user", "");
        Toast.makeText(AccountLoginActivity.this, userStr, Toast.LENGTH_SHORT).show();
        MyApplication.setToken(user.getResult().getToken());
        Log.e("看看返回的数据", user.getResult() + "");
        Log.e("看看存上的token", sharedPreferencesHelper.getSharedPreference("token", "").toString());
        Log.d(TAG, "onSuccess() called with: responseObj = [" + responseObj + "]");

        Intent intent = getIntent();
        int resultCheckedId = getIntent().getIntExtra("curCheckedRbtId", 0);
        intent.putExtra("resultCheckedId", resultCheckedId);
        Log.e("看能不能找到您", "onSuccess: " + resultCheckedId);
        setResult(RESULT_OK, intent);
        /*************在此处测试联网结果*****************/

        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(10000);//休眠3秒
                    postTestRequest();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        finish();
    }

    /**
     * 数据请求失败
     *
     * @param reasonObj 失败的对象
     */
    @Override
    public void onFailure(Object reasonObj) {
        Log.e("这里需要打印一下返回的信息", reasonObj.toString());
        Log.d(TAG, "onFailure() called with: reasonObj = [" + reasonObj + "]");
    }

    /**
     * 此乃get 请求的模板
     */
    private void getRequest() {
        RequestParams params = new RequestParams();
        params.put("productId", "738388");
       /* params.put("phone", "17644444455");
        params.put("invitephone", "17512525252");
        params.put("checkcode", "1234");*/
        CommonOkHttpClient.get(CommonRequest.createGetRequest("http://192.168.1.198:7777/goods/productDet", params),
                new DisposeDataHandle(new DisposeDataListener() {
                    @Override
                    public void onSuccess(Object responseObj) {
                        getTest.setText(responseObj.toString());
                        Log.e("不知道是个啥：", responseObj.toString());
                    }

                    @Override
                    public void onFailure(Object reasonObj) {
                        Log.e("不知道是个啥？？：", reasonObj + "");
                    }
                }));
    }

    private void postTestRequest() {
        RequestParams params = new RequestParams();
        params.put("token", MyApplication.getToken());
   /*     params.put("page", "1");
        params.put("size", "20");*/
        Log.e("不知道是个啥:", "postTestRequest: " + MyApplication.getToken());
//        params.put("searchMonth", "20180710");

//        http://192.168.1.198:7777/game/attendence     签到按钮
//        http://192.168.1.198:7777/game/attendence_prepare    签到页面

        CommonOkHttpClient.post(CommonRequest.createPostRequest(Api.ADDRESSLIST, params),
                new DisposeDataHandle(new DisposeDataListener() {
                    @Override
                    public void onSuccess(Object responseObj) {
                        Log.e("不知道是个啥：", "onSuccess" + responseObj.toString());
                        Log.d(TAG, "onSuccess() called with: responseObj = [" + responseObj + "]");
                    }

                    @Override
                    public void onFailure(Object reasonObj) {
                        Log.e("不知道是个啥：", "onFailure" + reasonObj.toString());
                    }
                }));


    }


}
