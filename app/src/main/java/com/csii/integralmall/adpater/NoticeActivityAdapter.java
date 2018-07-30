package com.csii.integralmall.adpater;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.csii.integralmall.R;
import com.csii.integralmall.bean.MessageInfos;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NoticeActivityAdapter extends BaseAdapter {
    private final Context context;
    private final List<MessageInfos> messageInfos;

    public NoticeActivityAdapter(Context context, List<MessageInfos> messageInfos) {
        this.context = context;
        this.messageInfos = messageInfos;
    }

    @Override
    public int getCount() {
        return messageInfos == null ? 0 : messageInfos.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_notice_activity_list, null);
            viewHolder = new ViewHolder(convertView);
            viewHolder.myNoticeTitle = convertView.findViewById(R.id.my_noticeTitle);
            viewHolder.myNoticeImage = convertView.findViewById(R.id.my_noticeImage);
            viewHolder.myNoticeDes = convertView.findViewById(R.id.my_noticeDes);
            viewHolder.myNoticeData = convertView.findViewById(R.id.my_noticeData);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        MessageInfos messageInfo = messageInfos.get(position);
        viewHolder.myNoticeTitle.setText(messageInfo.getMessageTitle());
        viewHolder.myNoticeImage.setImageResource(messageInfo.getMessageImage());
        viewHolder.myNoticeDes.setText(messageInfo.getMessageDes());
        viewHolder.myNoticeData.setText(messageInfo.getMessageData());


        return convertView;
    }

    class ViewHolder {
        @BindView(R.id.my_noticeTitle)
        TextView myNoticeTitle;
        @BindView(R.id.my_noticeImage)
        ImageView myNoticeImage;
        @BindView(R.id.my_noticeDes)
        TextView myNoticeDes;
        @BindView(R.id.my_noticeData)
        TextView myNoticeData;
        @BindView(R.id.my_immediately_check)
        TextView myImmediatelyCheck;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
            myImmediatelyCheck.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "立即查看", Toast.LENGTH_SHORT).show();
                }
            });

        }
    }
}
