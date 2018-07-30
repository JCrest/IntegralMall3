package com.csii.integralmall.fragment.message;

import android.view.View;
import android.widget.ListView;

import com.csii.integralmall.R;
import com.csii.integralmall.adpater.MyMessageAdapter;
import com.csii.integralmall.adpater.NoticeActivityAdapter;
import com.csii.integralmall.base.BaseFragment;
import com.csii.integralmall.bean.MessageInfos;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class NoticeActivityFragment extends BaseFragment {
    @BindView(R.id.lv_notice_list)
    ListView lvNoticeList;
    Unbinder unbinder;
    private NoticeActivityAdapter noticeActivityAdapter;
    //数据集合  鉴于活动公告与“我的消息”对象相同，此处共用
    private List<MessageInfos> messageInfos;

    @Override
    protected View initView() {
        View view = View.inflate(context, R.layout.fragment_notice_activity, null);
        unbinder = ButterKnife.bind(this, view);

        //        获取数据
        getMessageInfos();
        //创建适配器
        noticeActivityAdapter = new NoticeActivityAdapter(getActivity(), messageInfos);
        //        设置适配器
        lvNoticeList.setAdapter(noticeActivityAdapter);
        return view;
    }

    private void getMessageInfos() {

        messageInfos = new ArrayList<>();
        messageInfos.add(new MessageInfos("每日签到送积分，好礼享不停！", "签到即享每日翻牌的好礼，更多积分、礼品、补签卡等你拿，记得每天签到哦～～", "2018-5-20  16:12", R.drawable.bg_hdgg_one));
        messageInfos.add(new MessageInfos("邀请好友享大礼！", "会员可以通过邀请自己的好友得到积分、礼品等，邀请不限次数，可累计更多积分～～", "2018-5-20  16:12", R.drawable.bg_hdgg_two));
        messageInfos.add(new MessageInfos("每日签到送积分，好礼享不停！", "签到即享每日翻牌的好礼，更多积分、礼品、补签卡等你拿，记得每天签到哦～～", "2018-5-20  16:12", R.drawable.bg_hdgg_one));
        messageInfos.add(new MessageInfos("邀请好友享大礼！", "会员可以通过邀请自己的好友得到积分、礼品等，邀请不限次数，可累计更多积分～～", "2018-5-20  16:12", R.drawable.bg_hdgg_two));
        messageInfos.add(new MessageInfos("每日签到送积分，好礼享不停！", "签到即享每日翻牌的好礼，更多积分、礼品、补签卡等你拿，记得每天签到哦～～", "2018-5-20  16:12", R.drawable.bg_hdgg_one));
        messageInfos.add(new MessageInfos("邀请好友享大礼！", "会员可以通过邀请自己的好友得到积分、礼品等，邀请不限次数，可累计更多积分～～", "2018-5-20  16:12", R.drawable.bg_hdgg_two));
        messageInfos.add(new MessageInfos("每日签到送积分，好礼享不停！", "签到即享每日翻牌的好礼，更多积分、礼品、补签卡等你拿，记得每天签到哦～～", "2018-5-20  16:12", R.drawable.bg_hdgg_one));
        messageInfos.add(new MessageInfos("邀请好友享大礼！", "会员可以通过邀请自己的好友得到积分、礼品等，邀请不限次数，可累计更多积分～～", "2018-5-20  16:12", R.drawable.bg_hdgg_two));
        messageInfos.add(new MessageInfos("每日签到送积分，好礼享不停！", "签到即享每日翻牌的好礼，更多积分、礼品、补签卡等你拿，记得每天签到哦～～", "2018-5-20  16:12", R.drawable.bg_hdgg_one));
        messageInfos.add(new MessageInfos("邀请好友享大礼！", "会员可以通过邀请自己的好友得到积分、礼品等，邀请不限次数，可累计更多积分～～", "2018-5-20  16:12", R.drawable.bg_hdgg_two));
        messageInfos.add(new MessageInfos("每日签到送积分，好礼享不停！", "签到即享每日翻牌的好礼，更多积分、礼品、补签卡等你拿，记得每天签到哦～～", "2018-5-20  16:12", R.drawable.bg_hdgg_one));
        messageInfos.add(new MessageInfos("邀请好友享大礼！", "会员可以通过邀请自己的好友得到积分、礼品等，邀请不限次数，可累计更多积分～～", "2018-5-20  16:12", R.drawable.bg_hdgg_two));

    }

    @Override
    public void initData() {
        super.initData();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
