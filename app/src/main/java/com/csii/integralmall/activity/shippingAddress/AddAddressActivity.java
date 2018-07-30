package com.csii.integralmall.activity.shippingAddress;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.csii.integralmall.R;
import com.csii.integralmall.base.BaseActivity;

public class AddAddressActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = AddAddressActivity.class.getSimpleName();

    private TextView tvSaveAddress;
    private TextView tvClickSelect;
    Intent intent;

    @Override
    public void initView() {
        initTitleBar();
        tvSaveAddress = findViewById(R.id.tv_save_address);
        tvClickSelect = findViewById(R.id.tv_click_select);
        Log.e(TAG, "initView: 在这个位置打印一下");

    }

    @Override
    protected void initListener() {
        tvSaveAddress.setOnClickListener(this);

    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_ads;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_save_address:
                intent = new Intent(this, ShowMapActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_click_select:
                intent = new Intent(this, ShowMapActivity.class);
                startActivity(intent);
                break;
        }
    }
}
