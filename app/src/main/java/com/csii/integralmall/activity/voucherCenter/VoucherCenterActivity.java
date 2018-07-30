package com.csii.integralmall.activity.voucherCenter;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.csii.integralmall.R;
import com.csii.integralmall.adpater.VoucherCenterPagerAdpater;
import com.csii.integralmall.base.BaseActivity;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VoucherCenterActivity extends BaseActivity implements TabLayout.OnTabSelectedListener {
    @BindView(R.id.tv_title_center)
    TextView tvTitleCenter;
    @BindView(R.id.ib_return)
    ImageButton ibReturn;
    @BindView(R.id.tv_title_right)
    TextView tvTitleRight;
    @BindView(R.id.cut_line)
    View cutLine;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    private VoucherCenterPagerAdpater voucherCenterPagerAdpater;

    @Override
    public void initView() {
        initTitleBar();
        ButterKnife.bind(this);

        voucherCenterPagerAdpater = new VoucherCenterPagerAdpater(getSupportFragmentManager());
        viewPager.setAdapter(voucherCenterPagerAdpater);
        viewPager.setCurrentItem(0);
        tabLayout.setupWithViewPager(viewPager);
        cutLine.setVisibility(View.VISIBLE);
    }

    @Override
    protected void initListener() {
//      对 tablayout 添加选中监听
        tabLayout.addOnTabSelectedListener(this);
    }

    @Override
    protected void initData() {
        tvTitleCenter.setText("充值中心");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_voucher_center;
    }


    @OnClick(R.id.ib_return)
    public void onViewClicked() {
        finish();
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        if (Objects.requireNonNull(tabLayout.getTabAt(0)).isSelected()) {
            tabLayout.setBackground(ContextCompat.getDrawable(this, R.drawable.voucher_center_c));
        } else {
            tabLayout.setBackground(ContextCompat.getDrawable(this, R.drawable.vocher_center));
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
        tabLayout.setBackground(ContextCompat.getDrawable(this, R.drawable.voucher_center_c));

    }
}
