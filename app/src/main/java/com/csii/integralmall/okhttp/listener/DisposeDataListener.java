package com.csii.integralmall.okhttp.listener;

public interface DisposeDataListener {


    /**
     * 请求成功事件处理
     *
     * @param responseObj 成功的对象
     */
    public void onSuccess(Object responseObj);

    /**
     * 请求失败事件处理
     *
     * @param reasonObj 失败的对象
     */
    public void onFailure(Object reasonObj);


}
