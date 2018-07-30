package com.csii.integralmall.activity.signIntegralSale.myIntegral;

import android.annotation.SuppressLint;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.TextView;

import com.csii.integralmall.R;
import com.csii.integralmall.base.BaseActivity;

public class MemberRightsActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = MemberRightsActivity.class.getSimpleName();
    private WebView mWebView;
    private TextView tvTitleCenter;
    private ImageButton ibReturn;


    @Override
    public void initView() {
        initTitleBar();
        mWebView = findViewById(R.id.wv_flop);
        tvTitleCenter = findViewById(R.id.tv_title_center);
        ibReturn = findViewById(R.id.ib_return);
        ibReturn.setOnClickListener(this);
        initWebView();
    }

    @Override
    protected void initListener() {

    }


    @SuppressLint("SetJavaScriptEnabled")
    private void initWebView() {
        //获取webSettings
        WebSettings settings = mWebView.getSettings();
        loadHtml(getIntent().getExtras().getString("webUrl"));
        //让webView支持JS
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);

        settings.setAllowFileAccessFromFileURLs(true);
        settings.setAllowFileAccess(true);
        settings.setAllowUniversalAccessFromFileURLs(true);

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

        mWebView.setWebChromeClient(new WebChromeClient());

        mWebView.setWebViewClient(new WebViewClient() {
            @SuppressLint("NewApi")
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(request.getUrl().toString());
                return true;
            }

            /**  页面加载结束后调用JS方法传值
             * @param view
             * @param url
             */
            @Override
            public void onPageFinished(WebView view, String url) {
//                loadHtml("javascript:getToken({\"token\":'" + token + "'})");
            }
        });

    }
    private void loadHtml(String s) {
        mWebView.loadUrl(s);
        Log.d(TAG, "loadHtml() called with: s = [" + s + "]");
    }

    @Override
    public void initData() {
        tvTitleCenter.setText("会员权益");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_member_rights;
    }

    @Override
    public void onClick(View view) {
        finish();
        showToast("关了！");
    }
}
