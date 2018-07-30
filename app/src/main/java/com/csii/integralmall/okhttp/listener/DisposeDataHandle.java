package com.csii.integralmall.okhttp.listener;

public class DisposeDataHandle {
    private static final String TAG = DisposeDataHandle.class.getSimpleName();
    public DisposeDataListener mListener = null;
    public Class<?> mClass = null;

    public String mSource = null;


    public DisposeDataHandle(DisposeDataListener mListener) {
        this.mListener = mListener;
    }

    public DisposeDataHandle(DisposeDataListener mListener, Class<?> mClass) {
        this.mListener = mListener;
        this.mClass = mClass;
    }

    public DisposeDataHandle(DisposeDataListener mListener, String mSource) {
        this.mListener = mListener;
        this.mSource = mSource;
    }

}
