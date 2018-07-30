package com.csii.integralmall.fragment.signIntegralSale;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.csii.integralmall.base.BaseFragment;

public class CallCouponFragment extends BaseFragment {
    TextView textView;
    @Override
    protected View initView() {
        textView = new TextView(getContext());
        textView.setText("话费流量券");
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.RED);
        return textView;
    }
}
