package com.csii.integralmall.okhttp.request;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * 负责创建各种请求
 */
public class CommonRequest {
    private static final String TAG = CommonRequest.class.getSimpleName();


    /**
     * 创建无参的get请求
     *
     * @param url
     * @return
     */
    public static Request createGetRequest(String url) {
        StringBuilder urlBuilder = new StringBuilder(url);
        Log.e("也不知道这个是个啥？", "createGetRequest: " + new Request.Builder()
                .url(urlBuilder.substring(0, urlBuilder.length())).get().build());
        return new Request.Builder().url(urlBuilder.substring(0, urlBuilder.length())).get().build();
    }

    /**
     * 创建get请求
     *
     * @param url    地址
     * @param params 参数
     * @return
     */
    public static Request createGetRequest(String url, RequestParams params) {
        StringBuilder urlBuilder = new StringBuilder(url).append("?");
        if (params != null) {
            for (Map.Entry<String, String> entry : params.urlParams.entrySet()) {
                urlBuilder.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }
        }
        Log.d(TAG, "createGetRequest(有参): "+urlBuilder.substring(0, urlBuilder.length() - 1));
        return new Request.Builder().url(urlBuilder.substring(0, urlBuilder.length() - 1)).get().build();
    }


    private static final MediaType PARAMS_TYPE = MediaType.parse("application/json");

    /**
     * 创建post 请求（这里将参数转换成json 格式的参数对象传递给服务器）
     *
     * @param url    服务器的请求地址
     * @param params 需要上传的参数（最终转换成json 格式）
     * @return
     */
    public static Request createPostRequest(String url, RequestParams params) {
        JSONObject jsonObject = null;
        if (params != null) {
            jsonObject = new JSONObject();
            for (Map.Entry<String, String> entry : params.urlParams.entrySet()) {
                try {
                    jsonObject.put(entry.getKey(), entry.getValue());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        RequestBody requestPostBody = RequestBody.create(PARAMS_TYPE, jsonObject.toString());
        Log.e(TAG, "createPostRequest: url---"+url);
        Log.e(TAG, "createPostRequest: requestPostBody---"+jsonObject.toString() );
        return new Request.Builder().url(url).post(requestPostBody).build();
    }

    public static Request createFormPostRequest(String url, RequestParams params) {
        FormBody.Builder mFormBodyBuild = new FormBody.Builder();
        if (params != null) {
            for (Map.Entry<String, String> entry : params.urlParams.entrySet()) {
                mFormBodyBuild.add(entry.getKey(), entry.getValue());
            }
        }
        FormBody mFormBody = mFormBodyBuild.build();
        return new Request.Builder().url(url).post(mFormBody).build();
    }


    /**
     * 文件上传请求
     *
     * @param url 请求地址
     * @param params 请求参数
     * @return
     */
/*
    private static final MediaType FILE_TYPE = MediaType.parse("application/octet-stream");

    public static Request createMultiPostRequest(String url, RequestParams params) {

        MultipartBody.Builder requestBody = new MultipartBody.Builder();
        requestBody.setType(MultipartBody.FORM);
        if (params != null) {

            for (Map.Entry<String, Object> entry : params.fileParams.entrySet()) {
                if (entry.getValue() instanceof File) {
                    requestBody.addPart(
                            Headers.of("Content-Disposition", "form-data; name=\"" + entry.getKey() + "\""),
                            RequestBody.create(FILE_TYPE, (File) entry.getValue())
                    );

                } else if (entry.getValue() instanceof String) {

                    requestBody.addPart(
                            Headers.of("Content-Disposition", "form-data; name=\"" + entry.getKey() + "\""),
                            RequestBody.create(null, (String) entry.getValue())
                    );
                }
            }
        }
        return new Request.Builder().url(url).post(requestBody.build()).build();
    }*/


}
