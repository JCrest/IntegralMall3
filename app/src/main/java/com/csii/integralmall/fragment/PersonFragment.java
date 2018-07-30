package com.csii.integralmall.fragment;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.csii.integralmall.R;
import com.csii.integralmall.activity.InvitingFriends.InvitingFriendsActivity;
import com.csii.integralmall.activity.accountLogin.AccountLoginActivity;
import com.csii.integralmall.activity.message.MessageActivity;
import com.csii.integralmall.activity.personalData.PersonalDataActivity;
import com.csii.integralmall.activity.setting.SettingActivity;
import com.csii.integralmall.activity.shippingAddress.AddressListActivity;
import com.csii.integralmall.activity.signIntegralSale.myIntegral.MyIntegralActivity;
import com.csii.integralmall.activity.signIntegralSale.mySale.MySaleActivity;
import com.csii.integralmall.activity.signIntegralSale.signIn.SignInActivity;
import com.csii.integralmall.base.BaseFragment;
import com.csii.integralmall.bean.User;
import com.csii.integralmall.common.SharedPreferencesHelper;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class PersonFragment extends BaseFragment {
    private static final String TAG = PersonFragment.class.getSimpleName();//"PersonFragment"
    @BindView(R.id.ib_messageCenter)
    ImageButton ibMessageCenter;
    @BindView(R.id.iv_iconHead)
    ImageView ivIconHead;
    @BindView(R.id.tv_nickName)
    TextView tvNickName;
    @BindView(R.id.ll_personalData)
    LinearLayout llPersonalData;
    @BindView(R.id.ll_myOrder)
    LinearLayout llMyOrder;
    @BindView(R.id.ll_inviteFriends)
    LinearLayout llInviteFriends;
    @BindView(R.id.ll_shippingAddress)
    LinearLayout llShippingAddress;
    @BindView(R.id.ll_centerHelp)
    LinearLayout llCenterHelp;
    @BindView(R.id.ll_setting)
    LinearLayout llSetting;
    @BindView(R.id.btn_quit)
    Button btnQuit;
    @BindView(R.id.btn_signIn)
    Button btnSignIn;
    @BindView(R.id.btn_myIntegral)
    Button btnMyIntegral;
    @BindView(R.id.btn_myDiscount)
    Button btnMyDiscount;
    Unbinder unbinder;
    @BindView(R.id.btn_login)
    Button btnLogin;

    Intent intent;
    private SharedPreferencesHelper sharedPreferencesHelper;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        sharedPreferencesHelper = new SharedPreferencesHelper(getContext(), "token");
/*        if (!(Boolean) sharedPreferencesHelper.getSharedPreference("isLogin", false)) {
            intent = new Intent(getActivity(), AccountLoginActivity.class);
            startActivity(intent);
        }*/
    }

    @Override
    protected View initView() {
        Log.e(TAG, "初始化主页控件...");
        View view = View.inflate(context, R.layout.fragment_person, null);
        unbinder = ButterKnife.bind(this, view);
//        showToast(MyApplication.getToken());
        return view;
    }

    @Override
    public void initData() {
        super.initData();
//        Log.e(TAG, "绑定数据到控件上...");
//        setData();
    }

    private void setData() {
        //这边的这个false只是占位用的（以实际获取到的为准）
        if ((Boolean) sharedPreferencesHelper.getSharedPreference("isLogin", false)) {
            String u = (String) sharedPreferencesHelper.getSharedPreference("user", "");
            User user = new Gson().fromJson(u, User.class);//把JSON字符串转为对象
            tvNickName.setText(user.getResult().getUsername());
            btnQuit.setVisibility(View.VISIBLE);
            btnLogin.setVisibility(View.GONE);
        } else {
//            tvNickName.setText("");
            btnQuit.setVisibility(View.GONE);
            btnLogin.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
//        initView();
        setData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        Log.e("看看能不能把您销毁", "销毁了");
        unbinder.unbind();
    }

    @OnClick({R.id.ib_messageCenter, R.id.ll_personalData, R.id.ll_myOrder,
            R.id.ll_inviteFriends, R.id.ll_shippingAddress, R.id.ll_centerHelp,
            R.id.ll_setting, R.id.btn_quit, R.id.btn_signIn, R.id.btn_myIntegral,
            R.id.btn_myDiscount, R.id.btn_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ib_messageCenter:
                showToast("消息中心");
                intent = new Intent(getActivity(), MessageActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_personalData:
//                showToast("个人资料");
                intent = new Intent(getActivity(), PersonalDataActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_myOrder:
                showToast("我的订单");
                break;
            case R.id.ll_inviteFriends:
                showToast("邀请好友");
                intent = new Intent(getActivity(), InvitingFriendsActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_shippingAddress:
                intent = new Intent(getActivity(), AddressListActivity.class);
                startActivity(intent);
                showToast("收货地址");
                break;
            case R.id.ll_centerHelp:
                showToast("帮助中心");
                break;
            case R.id.ll_setting:
                intent = new Intent(getActivity(), SettingActivity.class);
                startActivity(intent);
//                showToast("设置");
                break;
            case R.id.btn_quit:
                showToast("退出登录");
//                退出登录的时候移除登录状态
                sharedPreferencesHelper.remove("isLogin");
                setData();
                break;
            case R.id.btn_signIn:
//                showToast("签到");
                intent = new Intent(getActivity(), SignInActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_myIntegral:
                showToast("我的积分");
                intent = new Intent(getActivity(), MyIntegralActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_myDiscount:
                showToast("我的优惠");
                intent = new Intent(getActivity(), MySaleActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_login:
                intent = new Intent(getActivity(), AccountLoginActivity.class);
                startActivity(intent);

//                showToast("登录");
                break;
        }
    }

}
