package com.csii.integralmall.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.csii.integralmall.R;
import com.csii.integralmall.activity.accountLogin.AccountLoginActivity;
import com.csii.integralmall.base.BaseActivity;
import com.csii.integralmall.base.BaseFragment;
import com.csii.integralmall.common.SharedPreferencesHelper;
import com.csii.integralmall.fragment.MallFragment;
import com.csii.integralmall.fragment.PersonFragment;

import java.util.ArrayList;

import butterknife.BindView;

public class MainActivity extends BaseActivity {


    @BindView(R.id.fl_main)
    FrameLayout flMain;
    @BindView(R.id.rb_mall)
    RadioButton rbMall;
    @BindView(R.id.rb_person)
    RadioButton rbPerson;
    @BindView(R.id.rg_main)
    RadioGroup rgMain;
    @BindView(R.id.activity_main)
    LinearLayout activityMain;

    private ArrayList<BaseFragment> fragments;
    private int position = 0;
    private Fragment tempFragment;

    private SharedPreferencesHelper sharedPreferencesHelper;
    //    上一个被选中的radio button 的id
    private int preCheckedRbtnId;
    //    当前被选中的radio button 的id
    private int curCheckedRbtId;


    @Override
    public void initView() {
        fragments = new ArrayList<>();
        fragments.add(new MallFragment());
        fragments.add(new PersonFragment());
        sharedPreferencesHelper = new SharedPreferencesHelper(this, "token");
    }

    @Override
    protected void initListener() {
        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            /**
             * 监听radioGroup 选中的变化
             * @param radioGroup
             * @param checkedId
             */
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                curCheckedRbtId = checkedId;
//                Log.e("看能不能找到您", "当前的：curCheckedRbtId：" + curCheckedRbtId);
                switchFragment(checkedId);
            }
        });
    }

    private void switchFragment(int checkedId) {
        switch (checkedId) {
            case R.id.rb_mall:
//                Log.e("看能不能找到您  Mall", "switchFragment: " + rgMain.getCheckedRadioButtonId());
                preCheckedRbtnId = checkedId;
                position = 0;
                break;
            case R.id.rb_person:
                if (!(Boolean) sharedPreferencesHelper.getSharedPreference("isLogin", false)) {
                    Intent intent = new Intent(this, AccountLoginActivity.class);
                    intent.putExtra("preCheckedRbtnId", preCheckedRbtnId);
                    intent.putExtra("curCheckedRbtId", curCheckedRbtId);
                    int requestCode = 1;
                    startActivityForResult(intent, requestCode);
                    rgMain.check(preCheckedRbtnId);
//                    Log.e("看能不能找到您", "上一个preCheckedRbtnId: " + preCheckedRbtnId);
//                    暂时给个1（后台暂时无数据）
                    position = 1;
                    break;
                } else {
                    position = 1;
                    break;
                }
        }
        Fragment currentFragment = fragments.get(position);
        selectFragment(currentFragment);
    }

    private void selectFragment(Fragment currentFragment) {
        if (currentFragment != tempFragment) {//不是同一个
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            if (!currentFragment.isAdded()) {
                //把之前的隐藏
                if (tempFragment != null) {
                    ft.hide(tempFragment);
                }
                //把现在的添加
                ft.add(R.id.fl_main, currentFragment);
//                Log.e("TAG", "switchFragment: " + currentFragment);
            } else {
                //把之前的隐藏
                if (tempFragment != null) {
                    ft.hide(tempFragment);
                }
                //把当前的显示
                ft.show(currentFragment);
            }
            //提交
            ft.commit();
            tempFragment = currentFragment;
        }
    }

    @Override
    protected void initData() {
        switchFragment(R.id.rb_mall);
        rgMain.check(R.id.rb_mall);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    /**
     * 接收返回的意图的结果
     *
     * @param requestCode
     * @param resultCode
     * @param intent
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            int resultCheckedId = intent.getIntExtra("resultCheckedId", 0);
//            Log.e("看能不能找到您", "目标: " + resultCheckedId);
            rgMain.check(resultCheckedId);
        }
    }
}
