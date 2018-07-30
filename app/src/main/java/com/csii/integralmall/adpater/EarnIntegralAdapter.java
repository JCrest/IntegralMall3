package com.csii.integralmall.adpater;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.csii.integralmall.R;
import com.csii.integralmall.bean.EarnIntegralBean;

import java.util.ArrayList;

public class EarnIntegralAdapter extends BaseAdapter {
    private final Context context;
    private final ArrayList<EarnIntegralBean> datas;

    public EarnIntegralAdapter(Context context, ArrayList<EarnIntegralBean> datas) {
        this.context = context;
        this.datas = datas;

    }

    @Override
    public int getCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_earn_ingral, null);
            holder = new ViewHolder(convertView);

            holder.ivEarnIcon = convertView.findViewById(R.id.iv_earn_icon);
            holder.tvEarnTitle = convertView.findViewById(R.id.tv_earn_title);
            holder.tvEarnDesc = convertView.findViewById(R.id.tv_earn_desc);

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        EarnIntegralBean earnIntegralBean = datas.get(position);
        Glide.with(context).load(earnIntegralBean.getIcon()).into(holder.ivEarnIcon);
        holder.tvEarnTitle.setText(earnIntegralBean.getTitle());
        holder.tvEarnDesc.setText(earnIntegralBean.getDesc());

        return convertView;
    }

    private class ViewHolder {
        private ImageView ivEarnIcon;
        private TextView tvEarnTitle;
        private TextView tvEarnDesc;

        public ViewHolder(View view) {
            ivEarnIcon = view.findViewById(R.id.iv_earn_icon);
            tvEarnTitle = view.findViewById(R.id.tv_earn_title);
            tvEarnDesc = view.findViewById(R.id.tv_earn_desc);
        }
    }
}
