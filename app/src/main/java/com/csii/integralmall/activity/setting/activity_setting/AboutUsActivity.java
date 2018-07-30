package com.csii.integralmall.activity.setting.activity_setting;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.csii.integralmall.R;
import com.csii.integralmall.base.BaseActivity;
import com.csii.integralmall.utils.APKVersionCodeUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AboutUsActivity extends BaseActivity {
    @BindView(R.id.tv_title_center)
    TextView tvTitleCenter;
    @BindView(R.id.ib_return)
    ImageButton ibReturn;
    @BindView(R.id.iv_icon_logo)
    ImageView ivIconLogo;
    @BindView(R.id.tv_version_name)
    TextView tvVersionName;
    @BindView(R.id.ll_edition_statement)
    LinearLayout llEditionStatement;
    @BindView(R.id.ll_to_score)
    LinearLayout llToScore;
    @BindView(R.id.ll_service_agreement)
    LinearLayout llServiceAgreement;

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
        tvTitleCenter.setText("关于我们");
        String versionName = APKVersionCodeUtils.getVerName(this);
        tvVersionName.setText("版本号： " + versionName);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_about_us;
    }


    @OnClick({R.id.ib_return, R.id.ll_edition_statement, R.id.ll_to_score, R.id.ll_service_agreement})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ib_return:
                finish();
                break;
            case R.id.ll_edition_statement:
                showToast("版本说明");
                break;
            case R.id.ll_to_score:
                showToast("去评分");
                break;
            case R.id.ll_service_agreement:
                showToast("服务协议");
                break;
        }
    }
}
