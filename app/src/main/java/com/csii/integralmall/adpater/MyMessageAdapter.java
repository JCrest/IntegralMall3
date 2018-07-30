package com.csii.integralmall.adpater;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.csii.integralmall.R;
import com.csii.integralmall.bean.MessageInfos;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyMessageAdapter extends BaseAdapter {


    private final Context context;
    private final List<MessageInfos> messageInfos;

    public MyMessageAdapter(Context context, List<MessageInfos> messageInfos) {
        this.context = context;
        this.messageInfos = messageInfos;

    }

    @Override
    public int getCount() {
        return messageInfos == null ? 0 : messageInfos.size();
    }

    @Override
    public Object getItem(int position) {
        return messageInfos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;

        if (convertView == null) {
            //1.加载布局文件（item Layout）,转换成View
            convertView = View.inflate(context, R.layout.item_message_list, null);
            //创建ViewHolder
            viewHolder = new ViewHolder(convertView);//
            viewHolder.myMessageTitle = (TextView) convertView.findViewById(R.id.my_messageTitle);
            viewHolder.myMessageDes = (TextView) convertView.findViewById(R.id.my_messageDes);
            viewHolder.myMessageData = (TextView) convertView.findViewById(R.id.my_messageData);
            viewHolder.myMessageImage = (ImageView) convertView.findViewById(R.id.my_messageImage);


            //使用View里面的字段Tag,把ViewHolder当成Tag保存起来
            convertView.setTag(viewHolder);


        } else {
            Log.e("TAG", "position==" + position + "---复用convertView==" + convertView);
            viewHolder = (ViewHolder) convertView.getTag();

        }


        //3.根据位置索引从集合中获取数据：MessageInfos
        MessageInfos messageInfo = messageInfos.get(position);

        //4.把得到的数据设置到子View上
        viewHolder.myMessageTitle.setText(messageInfo.getMessageTitle());
        viewHolder.myMessageDes.setText(messageInfo.getMessageDes());
        viewHolder.myMessageData.setText(messageInfo.getMessageData());
        viewHolder.myMessageImage.setImageResource(messageInfo.getMessageImage());

        //5.返回item Layout 对应的View
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.my_messageTitle)
        TextView myMessageTitle;
        @BindView(R.id.my_messageDes)
        TextView myMessageDes;
        @BindView(R.id.my_messageData)
        TextView myMessageData;
        @BindView(R.id.my_messageImage)
        ImageView myMessageImage;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
