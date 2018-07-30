package com.csii.integralmall.activity.InvitingFriends;

import android.annotation.SuppressLint;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.csii.integralmall.R;
import com.csii.integralmall.base.BaseActivity;

public class InvitingFriendsActivity extends BaseActivity {
    private static final String TAG = InvitingFriendsActivity.class.getSimpleName();
    private WebView mWebView;
    private TextView tvImmediatelyInvited;

    @Override
    public void initView() {
        initTitleBar();
        mWebView = findViewById(R.id.wv_flop);
        tvImmediatelyInvited = findViewById(R.id.tv_immediately_invited);
        initWebView();
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initWebView() {
        //获取webSettings
        WebSettings settings = mWebView.getSettings();
        loadHtml("file:///android_asset/invite.html");
        //让webView支持JS
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);

        settings.setAllowFileAccessFromFileURLs(true);
        settings.setAllowFileAccess(true);
        settings.setAllowUniversalAccessFromFileURLs(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true);
        }

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
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_inviting_friends;
    }
}
