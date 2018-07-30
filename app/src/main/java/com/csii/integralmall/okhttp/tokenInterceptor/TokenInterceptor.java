package com.csii.integralmall.okhttp.tokenInterceptor;

import android.util.Log;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class TokenInterceptor implements Interceptor {
    private static final String TAG = TokenInterceptor.class.getSimpleName();


    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        Response response = chain.proceed(request);

        Log.d(TAG, "response.code=" + response);

        //根据和服务端的约定判断token过期
        if (isTokenExpired(response)) {
            Log.d(TAG, "自动刷新Token,然后重新请求数据");
            //同步请求方式，获取最新的Token
            String newToken = getNewToken();
            //使用新的Token，创建新的请求
            Request newRequest = chain.request()
                    .newBuilder()
                    .header("Authorization", "Basic " + newToken)
                    .build();
            //重新请求
            return chain.proceed(newRequest);
        }
        return response;
    }


    private static final MediaType PARAMS_TYPE = MediaType.parse("application/json");
    private String getNewToken() {
        OkHttpClient httpClient = new OkHttpClient();
        RequestBody requestPostBody = RequestBody.create(PARAMS_TYPE, "{'phone':'17612345678','userPwd':'111111'}");
        Request request = new Request.Builder().url("http://192.168.1.198:7777/member/login").post(requestPostBody).build();
        Call call = httpClient.newCall(request);
        try {
            Response response = call.execute();
            Log.e(TAG, "getNewToken: "+response);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
//        return newToken;

    }

    private boolean isTokenExpired(Response response) {
        if (response.code() == 401) {
            return true;
        }

        return false;
    }


}
