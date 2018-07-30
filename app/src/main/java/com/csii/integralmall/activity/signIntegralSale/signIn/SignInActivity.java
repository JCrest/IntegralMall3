package com.csii.integralmall.activity.signIntegralSale.signIn;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.csii.integralmall.R;
import com.csii.integralmall.base.BaseActivity;
import com.csii.integralmall.bean.CheckTheResult;
import com.csii.integralmall.bean.SignWinBean;
import com.csii.integralmall.common.Api;
import com.csii.integralmall.common.MyApplication;
import com.csii.integralmall.fragment.signIntegralSale.SignFragment;
import com.csii.integralmall.okhttp.CommonOkHttpClient;
import com.csii.integralmall.okhttp.listener.DisposeDataHandle;
import com.csii.integralmall.okhttp.listener.DisposeDataListener;
import com.csii.integralmall.okhttp.request.CommonRequest;
import com.csii.integralmall.okhttp.request.RequestParams;
import com.csii.integralmall.utils.DateUtil;
import com.csii.integralmall.view.CustomCalendar;
import com.csii.integralmall.view.ObservableScrollView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static android.text.TextUtils.isEmpty;

public class SignInActivity extends BaseActivity implements ObservableScrollView.ScrollViewListener,
        DisposeDataListener {
    private static final String TAG = SignInActivity.class.getSimpleName();
    @BindView(R.id.ib_return)
    ImageButton ibReturn;
    @BindView(R.id.tv_title_right)
    ImageView tvTitleRight;
    @BindView(R.id.mall_title)
    RelativeLayout mallTitle;
    @BindView(R.id.cal)
    CustomCalendar cal;
    @BindView(R.id.tv_signed)
    TextView tvSigned;
    @BindView(R.id.tv_disable_signed)
    TextView tvDisableSigned;
    @BindView(R.id.rl_sign_head)
    RelativeLayout rlSignHead;
    @BindView(R.id.scrollview)
    ObservableScrollView scrollView;
    @BindView(R.id.fl_title)
    FrameLayout flTitle;
    @BindView(R.id.tv_sign_num)
    TextView tvSignNum;
    @BindView(R.id.tv_sign_days)
    TextView tvSignDays;

    private int headHeight;
    private final ArrayList<DayFinish> list = new ArrayList<>();

    private PopupWindow popupWindow;

    private android.os.Handler mHandler;

    private View view;
    private WebView mWebView;

    private static final String token = MyApplication.getToken();

    private SignFragment diagWebView;

    @Override
    public void initView() {
        Log.d("mJsInterface", "initView() called");
/*        mHandler = new android.os.Handler();

        view = LayoutInflater.from(this).inflate(R.layout.layout_popwin_show_flop, null);
        mWebView = (WebView) view.findViewById(R.id.wv_flop);*/


    }

    @Override
    protected void initListener() {

        ViewTreeObserver vto = rlSignHead.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                rlSignHead.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                headHeight = rlSignHead.getHeight();
                scrollView.setScrollViewListener(SignInActivity.this);
            }
        });
    }

    @Override
    protected void initData() {


//ATTENDENCE_PREPARE
        DayFinish dayFinish = null;

        for (int i = 1; i < 32; i++) {
            dayFinish = new DayFinish();
            dayFinish.setDay(i);
            dayFinish.setFinish(0);
            if (i % 7 == 0) {
                dayFinish.setAll(1);
            } else {
                dayFinish.setAll(0);
            }
            list.add(dayFinish);
        }

/*
        list.add(new DayFinish(1, 1, 2));
        list.add(new DayFinish(2, 1, 2));
        list.add(new DayFinish(3, 1, 2));
        list.add(new DayFinish(4, 1, 2));
        list.add(new DayFinish(5, -1, 2));
        list.add(new DayFinish(6, 1, 2));
        list.add(new DayFinish(7, 2, 2));
        list.add(new DayFinish(8, 1, 2));
        list.add(new DayFinish(9, 1, 2));
        list.add(new DayFinish(10, -1, 2));
        list.add(new DayFinish(11, 1, 2));
        list.add(new DayFinish(12, -1, 2));
        list.add(new DayFinish(13, 1, 2));
        list.add(new DayFinish(14, 2, 2));
        list.add(new DayFinish(15, 1, 2));
        list.add(new DayFinish(16, 0, 2));
        list.add(new DayFinish(17, 0, 2));
        list.add(new DayFinish(18, 0, 2));
        list.add(new DayFinish(19, 0, 2));
        list.add(new DayFinish(20, 0, 2));
        list.add(new DayFinish(21, 2, 2));
        list.add(new DayFinish(22, 0, 2));
        list.add(new DayFinish(23, 0, 2));
        list.add(new DayFinish(24, 0, 2));
        list.add(new DayFinish(25, 0, 2));
        list.add(new DayFinish(26, 0, 2));
        list.add(new DayFinish(27, 0, 2));
        list.add(new DayFinish(28, 2, 2));
        list.add(new DayFinish(29, 0, 2));
        list.add(new DayFinish(30, 0, 2));
        list.add(new DayFinish(31, 0, 2));*/

        postTestRequest();

        Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH) + 1;// 获取当前月份
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        Log.e("当前日期------------------------>", mYear + "年" + mMonth + "月" + mDay + "日");


//        cal.setRenwu(2018 + "年" + 10 + "月" + 30 + "日", list);
        /*cal.setOnClickListener(new CustomCalendar.onClickListener() {
            @Override
            public void onLeftRowClick() {
                showToast("点击减箭头");
                cal.monthChange(-1);
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                            SignInActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    cal.setRenwu(list);
                                }
                            });
                        } catch (Exception e) {
                        }
                    }
                }.start();


            }

            @Override
            public void onRightRowClick() {
                showToast("点击加箭头");
                cal.monthChange(1);
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                            SignInActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    cal.setRenwu(list);
                                }
                            });
                        } catch (Exception e) {
                        }
                    }
                }.start();
            }

            @Override
            public void onTitleClick(String monthStr, Date month) {
                showToast("点击了标题：" + monthStr);
            }

            @Override
            public void onWeekClick(int weekIndex, String weekStr) {
                showToast("点击了星期：" + weekStr);
            }

            @Override
            public void onDayClick(int day, String dayStr, DayFinish finish) {
                showToast("点击了日期：" + dayStr);
                Log.w("", "点击了日期:" + dayStr);
            }
        });*/

    }

    private void postTestRequest() {
        RequestParams params = new RequestParams();
        params.put("token", MyApplication.getToken());
        CommonOkHttpClient.post(CommonRequest.createPostRequest(Api.ATTENDENCE_PREPARE, params),
                new DisposeDataHandle(this, CheckTheResult.class));
    }

    @SuppressLint({"SimpleDateFormat", "SetTextI18n"})
    @Override
    public void onSuccess(Object responseObj) {
        CheckTheResult checkTheResult = (CheckTheResult) responseObj;
        String mData = DateUtil.getDateToString(checkTheResult.getResult().getNowtimestamp(), "yyyy年MM月dd日");
        List<CheckTheResult.ResultBean.TbslistBean> tbslistBeanList = new ArrayList<>();
        tbslistBeanList.addAll(checkTheResult.getResult().getTbslist());
        try {
            String str = new SimpleDateFormat("dd").format(new SimpleDateFormat("yyyy年MM月dd日").parse(mData));
            Log.e("获取用户哪天签过到", "onSuccess: " + str);
            for (int i = 1; i < Integer.parseInt(str); i++) {
                list.get(i - 1).setFinish(-1);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < tbslistBeanList.size(); i++) {
            int j = Integer.parseInt(tbslistBeanList.get(i).getCreatetime().substring(8, 10));
            Log.e("获取用户哪天签过到", "onSuccess: " + j);
            list.get(j - 1).setFinish(1);
            Log.e("获取用户哪天签过到", "onSuccess: " + list.get(j - 1).getFinish());
        }

        cal.setRenwu(mData, list);

        for (int i = 0; i < list.size(); i++) {
            Log.e("获取用户哪天签过到", "onSuccess: " + list.get(i).toString() + "///");
        }

        if (checkTheResult.getResult().getIssigntoday() == 1) {
            tvSigned.setVisibility(View.GONE);
            tvDisableSigned.setVisibility(View.VISIBLE);
        } else {
            tvSigned.setVisibility(View.VISIBLE);
            tvDisableSigned.setVisibility(View.GONE);
        }


        tvSignNum.setText("186900" + checkTheResult.getResult().getSignincount());
        if (!isEmpty("" + checkTheResult.getResult().getTbslist().size())) {
            tvSignDays.setText("本月已累计签到" + checkTheResult.getResult().getTbslist().size() + "天");
        } else {
            tvSignDays.setText("您本月还未签到，快去签到吧！");
        }


    }

    @Override
    public void onFailure(Object reasonObj) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_sign_in;
    }


    @OnClick({R.id.ib_return, R.id.tv_title_right, R.id.tv_signed})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ib_return:
                finish();
                break;
            case R.id.tv_title_right:
                break;
            case R.id.tv_signed:
                showToast("签到成功！！");
//                showFlop();
//                diagWebView.show(getSupportFragmentManager(), "");
                postWinSignRequest();
                break;
        }
    }

    private void postWinSignRequest() {
        RequestParams params = new RequestParams();
        params.put("token", MyApplication.getToken());
        CommonOkHttpClient.post(CommonRequest.createPostRequest(Api.ATTENDENCE, params),
                new DisposeDataHandle(new DisposeDataListener() {
                    @Override
                    public void onSuccess(Object responseObj) {
                        Log.e(TAG, "onSuccess: " + responseObj);
                        SignWinBean signWinBean = (SignWinBean) responseObj;

                        diagWebView = new SignFragment();
                        Bundle bundle = new Bundle();
                        bundle.putString("webUrl", signWinBean.getResult());
                        diagWebView.setArguments(bundle);
                        tvSigned.setVisibility(View.GONE);
                        tvDisableSigned.setVisibility(View.VISIBLE);
                        diagWebView.show(getSupportFragmentManager(), "");
                    }

                    @Override
                    public void onFailure(Object reasonObj) {
                        Log.e(TAG, "onFailure: " + reasonObj);

                    }
                }, SignWinBean.class));

    }


    /*
    @SuppressLint({"AddJavascriptInterface", "SetJavaScriptEnabled"})
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
                showToast(token);
                getWindow().setAttributes(params);
            }
        });

        //获取webSettings
        WebSettings settings = mWebView.getSettings();
        loH5("file:///android_asset/fanpai.html");
//        loH5("file:///android_asset/index.html");
//        mWebView.loadUrl("http://192.168.1.147:8082/fanpai/fanpai.html?_ijt=l64fepc5opdrc4e4p6sjke8v1a");
        //让webView支持JS
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);

//        mWebView.loadUrl("javascript:getToken()");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true);
        }

        mWebView.setBackgroundColor(0); // 设置背景色
        mWebView.getBackground().setAlpha(0); // 设置填充透明度 范围：0-255
        //不能横向滚动
        mWebView.setHorizontalScrollBarEnabled(false);
        //不能纵向滚动
        mWebView.setVerticalScrollBarEnabled(false);
        //允许截图
        mWebView.setDrawingCacheEnabled(true);
        //屏蔽长按事件
        mWebView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });

        mWebView.setWebViewClient(new WebViewClient() {
            @SuppressLint("NewApi")
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(request.getUrl().toString());
                return true;
            }
        });
        mWebView.addJavascriptInterface(new mJsInterface(this), "mLauncher");

        loH5("javascript:getToken2({\"token\":'" + token + "'})");


    }

    private void loH5(String s) {
        mWebView.loadUrl(s);
        Log.d("mJsInterface", "loH5() called with: s = [" + s + "]");
    }

    @Override
    public void setWebViewClose() {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                popupWindow.dismiss();
                Log.d("mJsInterface", "run() called");
            }
        });
    }*/


    @Override
    public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {

        if (y <= 0) {
            flTitle.setAlpha(0);
        } else if (y > 0 && y <= headHeight) {
            float scale = (float) y / headHeight;
            flTitle.setAlpha(scale);
        }
        flTitle.setBackgroundResource(R.drawable.obs_scr_vie_bg);
    }


    public class DayFinish {
        public int day;
        public int all;
        public int finish;

        public DayFinish(int day, int finish, int all) {
            this.day = day;
            this.all = all;
            this.finish = finish;
        }

        public DayFinish() {
        }

        public int getDay() {
            return day;
        }

        public void setDay(int day) {
            this.day = day;
        }

        public int getAll() {
            return all;
        }

        public void setAll(int all) {
            this.all = all;
        }

        public int getFinish() {
            return finish;
        }

        public void setFinish(int finish) {
            this.finish = finish;
        }

        @Override
        public String toString() {
            return "DayFinish{" +
                    "day=" + day +
                    ", all=" + all +
                    ", finish=" + finish +
                    '}';
        }
    }
}
