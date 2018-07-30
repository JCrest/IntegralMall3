package com.csii.integralmall.fragment.message;

import android.view.View;
import android.widget.ListView;

import com.csii.integralmall.R;
import com.csii.integralmall.adpater.MyMessageAdapter;
import com.csii.integralmall.base.BaseFragment;
import com.csii.integralmall.bean.MessageInfos;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MyMessageFragment extends BaseFragment {

    @BindView(R.id.lv_message_list)
    ListView lvMessageList;
    Unbinder unbinder;

    private MyMessageAdapter messageAdapter;
    //数据集合
    private List<MessageInfos> messageInfos;

    @Override
    protected View initView() {

        View view = View.inflate(context, R.layout.fragment_my_message, null);


        unbinder = ButterKnife.bind(this, view);
        //        获取数据
        getMessageInfos();
        //创建适配器
        messageAdapter = new MyMessageAdapter(getActivity(), messageInfos);
        //        设置适配器
        lvMessageList.setAdapter(messageAdapter);
        return view;
    }

    @Override
    public void initData() {
        super.initData();


    }

    private void getMessageInfos() {
        messageInfos = new ArrayList<>();
        messageInfos.add(new MessageInfos("礼品派送！", "恭喜，您参见大转盘活动赢取的保温杯已派送！记得每日来签到哦～～", "2018-5-20  16:12", R.drawable.bg_wdxx));
        messageInfos.add(new MessageInfos("邀请享大礼！！", "修改一下看看能否正常显示？记得每日来签到哦～～", "2018-5-20  16:12", R.drawable.bg_wdxx));
        messageInfos.add(new MessageInfos("礼品派送！", "恭喜，您参见大转盘活动赢取的保温杯已派送！记得每日来签到哦～～", "2018-5-20  16:12", R.drawable.bg_wdxx));
        messageInfos.add(new MessageInfos("邀请享大礼！！", "修改一下看看能否正常显示？记得每日来签到哦～～", "2018-5-20  16:12", R.drawable.bg_wdxx));
        messageInfos.add(new MessageInfos("礼品派送！", "恭喜，您参见大转盘活动赢取的保温杯已派送！记得每日来签到哦～～", "2018-5-20  16:12", R.drawable.bg_wdxx));
        messageInfos.add(new MessageInfos("邀请享大礼！！", "修改一下看看能否正常显示？记得每日来签到哦～～", "2018-5-20  16:12", R.drawable.bg_wdxx));
        messageInfos.add(new MessageInfos("礼品派送！", "恭喜，您参见大转盘活动赢取的保温杯已派送！记得每日来签到哦～～", "2018-5-20  16:12", R.drawable.bg_wdxx));
        messageInfos.add(new MessageInfos("邀请享大礼！！", "修改一下看看能否正常显示？记得每日来签到哦～～", "2018-5-20  16:12", R.drawable.bg_wdxx));
        messageInfos.add(new MessageInfos("礼品派送！", "恭喜，您参见大转盘活动赢取的保温杯已派送！记得每日来签到哦～～", "2018-5-20  16:12", R.drawable.bg_wdxx));
        messageInfos.add(new MessageInfos("邀请享大礼！！", "修改一下看看能否正常显示？记得每日来签到哦～～", "2018-5-20  16:12", R.drawable.bg_wdxx));
        messageInfos.add(new MessageInfos("礼品派送！", "恭喜，您参见大转盘活动赢取的保温杯已派送！记得每日来签到哦～～", "2018-5-20  16:12", R.drawable.bg_wdxx));
        messageInfos.add(new MessageInfos("邀请享大礼！！", "修改一下看看能否正常显示？记得每日来签到哦～～", "2018-5-20  16:12", R.drawable.bg_wdxx));
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
