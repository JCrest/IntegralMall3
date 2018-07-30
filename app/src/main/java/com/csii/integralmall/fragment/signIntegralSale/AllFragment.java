package com.csii.integralmall.fragment.signIntegralSale;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.csii.integralmall.base.BaseFragment;

public class AllFragment extends BaseFragment {
    TextView textView;

    @Override
    protected View initView() {
        textView = new TextView(context);
        textView.setText("全部");
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.BLUE);
        return textView;
    }
}
