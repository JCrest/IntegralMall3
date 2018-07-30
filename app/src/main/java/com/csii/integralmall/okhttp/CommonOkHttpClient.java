package com.csii.integralmall.okhttp;

import android.util.Log;

import com.csii.integralmall.okhttp.https.HttpsUtils;
import com.csii.integralmall.okhttp.listener.DisposeDataHandle;
import com.csii.integralmall.okhttp.response.CommonJsonCallback;
import com.csii.integralmall.okhttp.tokenInterceptor.TokenInterceptor;

import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class CommonOkHttpClient {

    private static final int TIME_OUT = 30;
    private static OkHttpClient mOkHttpClient = null;

    static {
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
        okHttpClientBuilder.hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession sslSession) {
                return true;
            }
        });

//        okHttpClientBuilder.cookieJar(new SimpleCookieJar());
        okHttpClientBuilder.connectTimeout(TIME_OUT, TimeUnit.SECONDS);

        okHttpClientBuilder.readTimeout(TIME_OUT, TimeUnit.SECONDS);

        okHttpClientBuilder.writeTimeout(TIME_OUT, TimeUnit.SECONDS);
        okHttpClientBuilder.followRedirects(true);
//        okHttpClientBuilder.addInterceptor(new TokenInterceptor());
//        okHttpClientBuilder.sslSocketFactory(HttpsUtils.getSslSocketFactory());


        Log.e("mOkHttpClient--", mOkHttpClient + "");
        mOkHttpClient = okHttpClientBuilder.build();
        Log.e("mOkHttpClient++", mOkHttpClient + "");

    }

    /**
     * 指定cilent信任指定证书
     *
     * @param certificates
     */
    public static void setCertificates(InputStream... certificates) {
        mOkHttpClient.newBuilder().sslSocketFactory(HttpsUtils.getSslSocketFactory(certificates, null, null)).build();
    }

    /**
     * 指定client信任所有证书
     */
    public static void setCertificates() {
        mOkHttpClient.newBuilder().sslSocketFactory(HttpsUtils.getSslSocketFactory());
    }

    /**
     * 通过构造好的Request,Callback去发送请求
     *
     * @param request
     * @param callback
     */
    public static Call get(Request request, DisposeDataHandle handle) {
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new CommonJsonCallback(handle));
        return call;
    }

    public static Call post(Request request, DisposeDataHandle handle) {
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new CommonJsonCallback(handle));
        return call;
    }

/*    public static Call downloadFile(Request request, DisposeDataHandle handle)
    {
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new CommonFileCallback(handle));
        return call;
    }*/

}
