package com.csii.integralmall.adpater;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.csii.integralmall.fragment.voucherCenter.ChargeCallsFragment;
import com.csii.integralmall.fragment.voucherCenter.ChargeFlowFragment;


public class VoucherCenterPagerAdpater extends FragmentPagerAdapter {

    private String[] mTitles = new String[]{"充话费", "充流量"};

    public VoucherCenterPagerAdpater(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 1) {
            return new ChargeFlowFragment();
        }
        return new ChargeCallsFragment();
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
