package com.csii.integralmall.fragment.signIntegralSale;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.csii.integralmall.JsInterface.JsBridge;
import com.csii.integralmall.JsInterface.mJsInterface;
import com.csii.integralmall.R;
import com.csii.integralmall.common.MyApplication;

public class SignFragment extends DialogFragment implements JsBridge {
    private int screenWidth;// 屏幕的宽度
    private Window window;
    private WebView mWebView;

    private Handler mhandler;

    private static final String token = MyApplication.getToken();

    private static final String TAG = SignFragment.class.getSimpleName();


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Dialog dialog = new Dialog(getActivity(), R.style.CustomDatePickerDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // must be called before set content
        dialog.setContentView(R.layout.dialog_webview);
        dialog.setCanceledOnTouchOutside(true);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        // 设置宽度为屏宽、靠近屏幕底部。
        window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
        wlp.height = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(wlp);
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_webview, container);
        mWebView = view.findViewById(R.id.wv_flop);

        //获取屏幕的宽度
        DisplayMetrics displayMetrics = new DisplayMetrics();
        window.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        screenWidth = displayMetrics.widthPixels;

        mhandler = new Handler();

        initWebView();
        return view;

    }

    @SuppressLint({"SetJavaScriptEnabled", "AddJavascriptInterface"})
    private void initWebView() {
        //获取webSettings
        WebSettings settings = mWebView.getSettings();
        loadHtml(getArguments().getString("webUrl"));
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
                loadHtml("javascript:getToken({\"token\":'" + token + "'})");
            }
        });
        mWebView.addJavascriptInterface(new mJsInterface(this), "mLauncher");


    }

    private void loadHtml(String s) {
        mWebView.loadUrl(s);
        Log.d(TAG, "loadHtml() called with: s = [" + s + "]");
    }


    @Override
    public void setWebViewClose() {
        mhandler.post(new Runnable() {
            @Override
            public void run() {
                dismiss();
            }
        });
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        try {
            super.show(manager, tag);
        } catch (IllegalStateException ignore) {
        }
    }

}
