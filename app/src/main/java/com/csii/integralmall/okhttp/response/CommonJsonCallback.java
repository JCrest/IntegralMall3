package com.csii.integralmall.okhttp.response;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.csii.integralmall.common.MyApplication;
import com.csii.integralmall.okhttp.exception.OkHttpException;
import com.csii.integralmall.okhttp.listener.DisposeDataHandle;
import com.csii.integralmall.okhttp.listener.DisposeDataListener;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class CommonJsonCallback implements Callback {

    private static final String TAG = CommonJsonCallback.class.getSimpleName();

    protected final String RESULT_CODE = "code"; // 有返回则对于http请求来说是成功的，但还有可能是业务逻辑上的错误
    protected final int RESULT_CODE_VALUE = 200;
    protected final String ERROR_MSG = "message";
    protected final String EMPTY_MSG = "";

    /**
     * the java layer exception, do not same to the logic error
     */
    protected final int NETWORK_ERROR = -1; // the network relative error
    protected final int JSON_ERROR = -2; // the JSON relative error
    protected final int OTHER_ERROR = -3; // the unknow error


    private Handler mDeliveryHandler;
    private DisposeDataListener mListener;
    private Class<?> mClass;

    public CommonJsonCallback(DisposeDataHandle handle) {
        this.mListener = handle.mListener;
        this.mClass = handle.mClass;
        this.mDeliveryHandler = new Handler(Looper.getMainLooper());
    }

    /**
     * 实现请求失败的接口
     *
     * @param call
     * @param e
     */
    @Override
    public void onFailure(Call call, final IOException e) {
        mDeliveryHandler.post(new Runnable() {
            @Override
            public void run() {
                mListener.onFailure(new OkHttpException(NETWORK_ERROR, e));

            }
        });
    }

    /**
     * 实现请求成功的接口
     *
     * @param call
     * @param response
     * @throws IOException
     */
    @Override
    public void onResponse(Call call, Response response) throws IOException {
        final String result = response.body().string();
        Log.e(TAG, "onResponse(返回数据结果): " + result);
        mDeliveryHandler.post(new Runnable() {
            @Override
            public void run() {
                handleResponse(result);
            }
        });


    }

    private void handleResponse(Object responseObj) {
        if (responseObj == null) {
            mListener.onFailure(new OkHttpException(NETWORK_ERROR, EMPTY_MSG));
            return;
        }
        try {
            JSONObject result = new JSONObject(responseObj.toString());
            if (result.has(RESULT_CODE)) {
                if (result.optInt(RESULT_CODE) == RESULT_CODE_VALUE) {
                    if (mClass == null) {
                        mListener.onSuccess(result);
                    } else {
                        Object obj = new Gson().fromJson(result.toString(), mClass);
                        if (obj != null) {
                            mListener.onSuccess(obj);
                        } else {
                            mListener.onFailure(new OkHttpException(JSON_ERROR, EMPTY_MSG));
                        }
                    }
                } else {
                    if (result.has(ERROR_MSG)) {
                        mListener.onFailure(
                                new OkHttpException(result.optInt(RESULT_CODE), result.optString(ERROR_MSG)));
                        Toast.makeText(MyApplication.getContext(), result.optString(ERROR_MSG) + "", Toast.LENGTH_SHORT).show();
                    } else {
                        mListener.onFailure(new OkHttpException(result.optInt(RESULT_CODE), EMPTY_MSG));
                    }
                }
            } else {
                if (result.has(ERROR_MSG)) {
                    mListener.onFailure(new OkHttpException(OTHER_ERROR, result.opt(ERROR_MSG)));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
