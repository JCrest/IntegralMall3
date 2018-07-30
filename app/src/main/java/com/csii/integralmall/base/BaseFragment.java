package com.csii.integralmall.base;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public abstract class BaseFragment extends Fragment {
    public Context context;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e("fragment生命周期之", "onAttach");
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
        Log.e("fragment生命周期之", "onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e("fragment生命周期之", "onCreateView");
        return initView();
    }

    protected abstract View initView();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        Log.e("fragment生命周期之", "onActivityCreated");
    }

    public void initData() {
    }

    public void initTitleBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                    View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

    }

    public void showToast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("fragment生命周期之", "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("fragment生命周期之", "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("fragment生命周期之", "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("fragment生命周期之", "onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e("fragment生命周期之", "onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("fragment生命周期之", "onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e("fragment生命周期之", "onDetach");
    }
}
