package com.csii.integralmall.activity.message;

import android.content.res.Resources;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.csii.integralmall.R;
import com.csii.integralmall.adpater.MyMessagePagerAdpater;
import com.csii.integralmall.base.BaseActivity;

import java.lang.reflect.Field;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MessageActivity extends BaseActivity {
    @BindView(R.id.tv_title_center)
    TextView tvTitleCenter;
    @BindView(R.id.ib_return)
    ImageButton ibReturn;
    @BindView(R.id.tv_title_right)
    TextView tvTitleRight;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.cut_line)
    View cutLine;

    @Override
    public void initView() {
        initTitleBar();

        ButterKnife.bind(this);
        MyMessagePagerAdpater messagePagerAdpater = new MyMessagePagerAdpater(getSupportFragmentManager());
        viewPager.setAdapter(messagePagerAdpater);
        viewPager.setCurrentItem(0);
        tabLayout.setupWithViewPager(viewPager);
        cutLine.setVisibility(View.GONE);


        tabLayout.post(new Runnable() {

            @Override

            public void run() {
                setIndicator(tabLayout,60,60);
            }
        });



    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        tvTitleCenter.setText("消息");

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_message;
    }


    @OnClick(R.id.ib_return)
    public void onViewClicked() {
        finish();
    }



    public void setIndicator(TabLayout tabs, int leftDip, int rightDip) {

        Class<?> tabLayout = tabs.getClass();

        Field tabStrip = null;

        try {

            tabStrip = tabLayout.getDeclaredField("mTabStrip");

        } catch (NoSuchFieldException e) {

            e.printStackTrace();

        }



        tabStrip.setAccessible(true);

        LinearLayout llTab = null;

        try {

            llTab = (LinearLayout) tabStrip.get(tabs);

        } catch (IllegalAccessException e) {

            e.printStackTrace();

        }



        int left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, leftDip, Resources.getSystem().getDisplayMetrics());

        int right = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rightDip, Resources.getSystem().getDisplayMetrics());



        for (int i = 0; i < llTab.getChildCount(); i++) {

            View child = llTab.getChildAt(i);

            child.setPadding(0, 0, 0, 0);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);

            params.leftMargin = left;

            params.rightMargin = right;

            child.setLayoutParams(params);

            child.invalidate();

        }

    }

}
