package com.csii.integralmall.activity.signIntegralSale.mySale;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.csii.integralmall.R;
import com.csii.integralmall.adpater.MySaleAdapter;
import com.csii.integralmall.base.BaseActivity;

import java.lang.reflect.Field;

@SuppressLint("Registered")
public class MySaleActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = MySaleActivity.class.getSimpleName();
    private TextView tvTitleCenter;
    private ImageButton ibReturn;
    private View cutLine;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    public void initView() {
        initTitleBar();
        tvTitleCenter = findViewById(R.id.tv_title_center);
        ibReturn = findViewById(R.id.ib_return);
        cutLine = findViewById(R.id.cut_line);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);


    }

    @Override
    protected void initListener() {
        ibReturn.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        tvTitleCenter.setText("我的优惠");

        MySaleAdapter mySaleAdapter = new MySaleAdapter(getSupportFragmentManager());

        viewPager.setAdapter(mySaleAdapter);
        viewPager.setCurrentItem(0);
        tabLayout.setupWithViewPager(viewPager);
        cutLine.setVisibility(View.GONE);
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                setIndicator(tabLayout, 10, 10);
            }
        });

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_sale;
    }


    @Override
    public void onClick(View view) {
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
