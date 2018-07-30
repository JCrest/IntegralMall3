package com.csii.integralmall.activity.setting;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.csii.integralmall.R;
import com.csii.integralmall.activity.setting.activity_setting.AboutUsActivity;
import com.csii.integralmall.activity.setting.activity_setting.FeedBackActivity;
import com.csii.integralmall.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.tv_title_center)
    TextView tvTitleCenter;
    @BindView(R.id.ib_return)
    ImageButton ibReturn;
    @BindView(R.id.ll_contact_customer_service)
    LinearLayout llContactCustomerService;
    @BindView(R.id.ll_feed_back)
    LinearLayout llFeedBack;
    @BindView(R.id.ll_about_us)
    LinearLayout llAboutUs;

    Intent intent;
    private View view;
    private TextView tvCallConfirm;
    private TextView tvCallCancel;
    private PopupWindow popupWindow;


    @Override
    public void initView() {
        initTitleBar();
        ButterKnife.bind(this);

        view = LayoutInflater.from(this).inflate(R.layout.layout_popwin_show_flop, null);
        tvCallConfirm = view.findViewById(R.id.tv_call_confirm);
        tvCallCancel = view.findViewById(R.id.tv_call_cancel);
    }

    @Override
    protected void initListener() {
        tvCallCancel.setOnClickListener(this);
        tvCallConfirm.setOnClickListener(this);


    }

    @Override
    protected void initData() {
        tvTitleCenter.setText("设置");

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }


    @OnClick({R.id.ib_return, R.id.ll_contact_customer_service, R.id.ll_feed_back, R.id.ll_about_us})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ib_return:
                finish();
                break;
            case R.id.ll_contact_customer_service:
//                showToast("联系客服");
                showFlop();
                break;
            case R.id.ll_feed_back:
                intent = new Intent(this, FeedBackActivity.class);
                startActivity(intent);
//                showToast("意见反馈");
                break;
            case R.id.ll_about_us:
                intent = new Intent(this, AboutUsActivity.class);
                startActivity(intent);
//                showToast("关于我们");
                break;
        }
    }

    private void showFlop() {
        popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setBackgroundDrawable(ContextCompat.getDrawable(this, R.color.transparent));
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.update();
        View parent = LayoutInflater.from(this).inflate(R.layout.activity_sign_in, null);
        popupWindow.showAtLocation(parent, Gravity.BOTTOM, 0, 0);
        final WindowManager.LayoutParams params = getWindow().getAttributes();
        params.alpha = 0.5f;
        getWindow().setAttributes(params);
        //监听 PopupWindow 的消失
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                params.alpha = 1.0f;
                getWindow().setAttributes(params);
            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_call_cancel:
                popupWindow.dismiss();
                break;
            case R.id.tv_call_confirm:
                call("18500396036");
                popupWindow.dismiss();
                break;
        }

    }

    /**
     * 调用本地拨号程序
     *
     * @param phone 电话号码
     */
    private void call(String phone) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
