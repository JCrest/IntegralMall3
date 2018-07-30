package com.csii.integralmall.adpater;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.csii.integralmall.R;
import com.csii.integralmall.bean.ChannelBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChannelAdapter extends BaseAdapter {
    private final Context context;
    private final List<ChannelBean> datas;

    public ChannelAdapter(Context context, List<ChannelBean> datas) {
        this.context = context;
        this.datas = datas;

    }

    @Override
    public int getCount() {
        return datas == null ? 0 : datas.size();
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
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_channel, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tvChannel.setText(datas.get(position).getChannelText());
//        利用glide 加载资源图片
        Glide.with(context)
                .load(datas.get(position).getImageView())
                .into(viewHolder.ivChannel);
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.iv_channel)
        ImageView ivChannel;
        @BindView(R.id.tv_channel)
        TextView tvChannel;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
