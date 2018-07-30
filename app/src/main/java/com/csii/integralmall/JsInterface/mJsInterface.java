package com.csii.integralmall.JsInterface;

import android.util.Log;
import android.webkit.JavascriptInterface;

public class mJsInterface {
    private static final String TAG = mJsInterface.class.getSimpleName();

    private JsBridge jsBridge;

    public mJsInterface(JsBridge jsBridge) {
        this.jsBridge = jsBridge;
    }

    /**
     * 该方法不在主线程执行
     */
    @JavascriptInterface
    public void setWebViewClose() {
        Log.d(TAG, "setWebViewClose() called");
        jsBridge.setWebViewClose();
    }

}
