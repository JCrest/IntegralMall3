package com.csii.integralmall.common;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {
    private static Context context;

    private static String token;

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        MyApplication.token = token;
    }

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }
}
