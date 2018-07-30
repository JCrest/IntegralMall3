package com.csii.integralmall.adpater;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.csii.integralmall.R;
import com.csii.integralmall.bean.ActBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActAdapter extends BaseAdapter {
    private final Context context;
    private final List<ActBean> datas;

    public ActAdapter(Context context, List<ActBean> datas) {
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
            convertView = View.inflate(context, R.layout.item_act, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tvActTitle.setText(datas.get(position).getTitle());
        viewHolder.tvActDes.setText(datas.get(position).getDescribe());
//        利用glide 加载资源图片
        Glide.with(context)
                .load(datas.get(position).getImage())
                .into(viewHolder.ivAct);
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.iv_act)
        ImageView ivAct;
        @BindView(R.id.tv_act_title)
        TextView tvActTitle;
        @BindView(R.id.tv_act_des)
        TextView tvActDes;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
