package com.csii.integralmall.adpater;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.csii.integralmall.fragment.signIntegralSale.AllFragment;
import com.csii.integralmall.fragment.signIntegralSale.CallCouponFragment;
import com.csii.integralmall.fragment.signIntegralSale.CashCouponFragment;
import com.csii.integralmall.fragment.signIntegralSale.VIPCardFragment;

public class MySaleAdapter extends FragmentPagerAdapter {
    private String[] mTitles = new String[]{"全部", "话费流量券", "现金折扣券", "会员卡"};

    public MySaleAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 1:
                Log.e("MySaleAdapter", "getItem: CallCouponFragment");
                return new CallCouponFragment();
            case 2:
                Log.e("MySaleAdapter", "getItem: CashCouponFragment");
                return new CashCouponFragment();
            case 3:
                Log.e("MySaleAdapter", "getItem: VIPCardFragment");
                return new VIPCardFragment();
        }
        Log.e("MySaleAdapter", "getItem: AllFragment");
        return new AllFragment();
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    //ViewPager与TabLayout绑定后，这里获取到PageTitle就是Tab的Text
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
