package com.csii.integralmall.activity.earnIntegral;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.csii.integralmall.R;
import com.csii.integralmall.adpater.EarnIntegralAdapter;
import com.csii.integralmall.base.BaseActivity;
import com.csii.integralmall.bean.EarnIntegralBean;

import java.util.ArrayList;

public class EarnIntegralActivity extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private static final String TAG = EarnIntegralActivity.class.getSimpleName();
    private TextView tvTitleCenter;
    private ImageButton ibReturn;
    private ListView mListView;
    private EarnIntegralAdapter mAdapter;

    @Override
    public void initView() {
        initTitleBar();
        tvTitleCenter = findViewById(R.id.tv_title_center);
        ibReturn = findViewById(R.id.ib_return);
        mListView = findViewById(R.id.lv_earn_integral);

        @SuppressLint("InflateParams") View headerView = LayoutInflater.from(this).inflate
                (R.layout.header_earn_integral, null);
        mListView.addHeaderView(headerView);

    }

    @Override
    protected void initListener() {
        ibReturn.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        tvTitleCenter.setText("赚积分");


        ArrayList<EarnIntegralBean> datas = new ArrayList<>();
        datas.add(new EarnIntegralBean(R.drawable.icon_xfgw, "消费购物",
                "每消费20元累计1积分"));
        datas.add(new EarnIntegralBean(R.drawable.icon_yqhy, "邀请好友",
                "每邀请一个好友，更多大礼等你拿"));
        datas.add(new EarnIntegralBean(R.drawable.icon_fxtj, "分享推荐",
                "每分享一次可获得一次抽奖机会"));
        datas.add(new EarnIntegralBean(R.drawable.icon_hytq, "会员权益",
                "根据您的等级，制定权益"));
        datas.add(new EarnIntegralBean(R.drawable.icon_qtjfhd, "其他积分活动",
                "积极参与活动，有惊喜哦"));

        mAdapter = new EarnIntegralAdapter(this, datas);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(this);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.earn_integral_activity;
    }

    @Override
    public void onClick(View view) {
        finish();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        showToast("" + position);
    }
}
