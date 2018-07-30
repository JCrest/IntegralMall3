package com.csii.integralmall.activity.signIntegralSale.myIntegral;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.csii.integralmall.R;
import com.csii.integralmall.activity.earnIntegral.EarnIntegralActivity;
import com.csii.integralmall.adpater.MyPagerContainerAdapter;
import com.csii.integralmall.adpater.WonderfulActivityAdpater;
import com.csii.integralmall.base.BaseActivity;
import com.csii.integralmall.bean.WonderfulActivityInfo;
import com.csii.integralmall.utils.CoverFlow;
import com.csii.integralmall.view.CustomViewPager;
import com.csii.integralmall.view.MyGridView;
import com.csii.integralmall.view.MyIntegralPagerContainer;
import com.csii.integralmall.view.ObservableScrollView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MyIntegralActivity extends BaseActivity implements
        ObservableScrollView.ScrollViewListener {

    @BindView(R.id.ib_return)
    ImageButton ibReturn;
    @BindView(R.id.iv_iconHead)
    ImageView ivIconHead;
    @BindView(R.id.tv_signed_and_integral)
    TextView tvSignedAndIntegral;
    @BindView(R.id.tv_integral_rule)
    TextView tvIntegralRule;
    @BindView(R.id.btn_earn_points)
    Button btnEarnPoints;
    @BindView(R.id.btn_lottery)
    Button btnLottery;
    @BindView(R.id.btn_against_the_present)
    Button btnAgainstThePresent;
    @BindView(R.id.btn_integral_bills)
    Button btnIntegralBills;
    @BindView(R.id.tv_marquee)
    TextView tvMarquee;
    @BindView(R.id.ll_point_group)
    LinearLayout ll_point_group;
    @BindView(R.id.gv_wonderful_activity)
    MyGridView gvWonderfulActivity;
    @BindView(R.id.pager_container)
    MyIntegralPagerContainer pagerContainer;

    @BindView(R.id.rl_sign_head)
    RelativeLayout rlSignHead;
    @BindView(R.id.scrollview)
    ObservableScrollView scrollView;
    @BindView(R.id.fl_title)
    FrameLayout flTitle;

    @BindView(R.id.ll_total_integral)
    LinearLayout llTotalIntegral;

    private int headHeight;

    private CustomViewPager pager;

    private List<WonderfulActivityInfo> wonderfulActivityInfos;

    private int prePosition;

    private Intent intent;


    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int itemposition = pager.getCurrentItem() + 1;
            pager.setCurrentItem(itemposition);
            sendEmptyMessageDelayed(0, 2000);
        }
    };


    //图片集合
    private int[] covers = {R.drawable.wdjf_yqyl, R.drawable.bg_act_one, R.drawable.wdjf_yqyl,
            R.drawable.bg_act_one, R.drawable.wdjf_yqyl, R.drawable.bg_act_one};

    @Override
    public void initView() {

        tvMarquee.setSelected(true);//跑马灯（貌似无用回头在研究）

        //取消GridView默认获取焦点，使ScrollView的顶部显示，而是GridView的部分。
        gvWonderfulActivity.setFocusable(false);
        getWonderfulActivityInfos();
        WonderfulActivityAdpater madpater = new WonderfulActivityAdpater(this, wonderfulActivityInfos);
        gvWonderfulActivity.setAdapter(madpater);


        pager = (CustomViewPager) pagerContainer.getViewPager();
        MyPagerContainerAdapter myPagerContainerAdapter = new MyPagerContainerAdapter(this, covers);
        pager.setAdapter(myPagerContainerAdapter);
        pager.addOnPageChangeListener(new MyOnPageChangeListener());
        pager.setClipChildren(false);
        pager.setOffscreenPageLimit(15);

        new CoverFlow.Builder()
                .with(pager)
                .scale(0.3f)
                .pagerMargin(getResources().getDimensionPixelSize(R.dimen.pager_margin))
                .spaceSize(0f)
                .build();


        //根据图片集合的元素的数量设置指示点
        for (int i = 0; i < covers.length; i++) {
            //指示点在android中本质上是ImageView（创建一个ImageView）
            ImageView imageView = new ImageView(this);

            //在布局文件中已经设置了小指示点但还需要在代码中稍微调整一下，如果已经调好
            // 则可不需要这段（以下3行）
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-2,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            params.leftMargin = 10;
            imageView.setLayoutParams(params);

            //给小指示点设置背景（这里用selector+shape方法）
            imageView.setBackgroundResource(R.drawable.point_selector);
            //这段代码类似于设置第一条文本，让其一打开app便可选择第一个点
            if (i == 0) {
                imageView.setSelected(true);
            } else {
                imageView.setSelected(false);
            }
            ll_point_group.addView(imageView);
        }

        int itemposition = Integer.MAX_VALUE / 2 - Integer.MAX_VALUE / 2 % covers.length;
        pager.setCurrentItem(itemposition);
        //一打开就让其发送消息
        handler.sendEmptyMessageDelayed(0, 2000);


    }

    private void getWonderfulActivityInfos() {
        wonderfulActivityInfos = new ArrayList<>();
        wonderfulActivityInfos.add(new WonderfulActivityInfo(true, "SK-II 护肤精华露",
                "（神仙水®）", 10000, 20, R.drawable.bg_goods));
        wonderfulActivityInfos.add(new WonderfulActivityInfo(false, "苏泊尔(SUPOR)电饭煲",
                "（3.0钛球釜内胆）", 15000, 10, R.drawable.bg_goods_two));
        wonderfulActivityInfos.add(new WonderfulActivityInfo(false, "SK-II 护肤精华露",
                "（神仙水®）", 10000, 0, R.drawable.bg_goods));
        wonderfulActivityInfos.add(new WonderfulActivityInfo(true, "SK-II 护肤精华露",
                "（神仙水®）", 10000, 0, R.drawable.bg_goods_two));


    }

    @Override
    protected void initListener() {
        ViewTreeObserver vto = rlSignHead.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                rlSignHead.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                headHeight = rlSignHead.getHeight();
                scrollView.setScrollViewListener(MyIntegralActivity.this);


            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_integral;
    }


    @SuppressLint("CommitTransaction")
    @OnClick({R.id.ib_return, R.id.tv_signed_and_integral, R.id.tv_integral_rule, R.id.btn_earn_points,
            R.id.btn_lottery, R.id.btn_against_the_present, R.id.btn_integral_bills, R.id.ll_total_integral})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ib_return:
                finish();
                break;
            case R.id.tv_signed_and_integral:
                break;
            case R.id.tv_integral_rule:
                break;
            case R.id.btn_earn_points:
                showToast("赚积分");
                intent = new Intent(this, EarnIntegralActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_lottery:
                break;
            case R.id.btn_against_the_present:
                break;
            case R.id.btn_integral_bills:
                break;
            case R.id.ll_total_integral:
                showToast("会员权益");
                intent = new Intent(this, MemberRightsActivity.class);
                intent.putExtra("webUrl", "file:///android_asset/VIP.html");
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {
        if (y <= 0) {
            flTitle.setAlpha(0);
        } else if (y > 0 && y <= headHeight) {
            float scale = (float) y / headHeight;
            flTitle.setAlpha(scale);
        }
        flTitle.setBackgroundResource(R.drawable.obs_scr_wdjf_bg);
    }


    private class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            int realPosition = position % covers.length;
            //设置页面滑动时指示点也随着变化(当选中当前的页面的时候之前的画面就变成false不在显示颜色)
            ll_point_group.getChildAt(prePosition).setSelected(false);
            ll_point_group.getChildAt(realPosition).setSelected(true);
            prePosition = realPosition;
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            if (state == ViewPager.SCROLL_STATE_IDLE) {
                handler.removeCallbacksAndMessages(null);
                handler.sendEmptyMessageDelayed(0, 2000);

            } else if (state == ViewPager.SCROLL_STATE_DRAGGING) {
                handler.removeCallbacksAndMessages(null);
            } else if (state == ViewPager.SCROLL_STATE_SETTLING) {

            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }
}
