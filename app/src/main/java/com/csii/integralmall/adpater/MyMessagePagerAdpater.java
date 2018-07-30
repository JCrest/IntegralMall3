package com.csii.integralmall.adpater;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.csii.integralmall.fragment.message.MyMessageFragment;
import com.csii.integralmall.fragment.message.NoticeActivityFragment;

public class MyMessagePagerAdpater extends FragmentPagerAdapter {
    private String[] mTitles = new String[]{"我的消息", "活动公告"};


    public MyMessagePagerAdpater(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 1) {
            return new NoticeActivityFragment();
        }
        return new MyMessageFragment();

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
