package com.csii.integralmall.adpater;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.csii.integralmall.R;
import com.csii.integralmall.bean.VoucherCenterBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VocherCenterAdapter extends BaseAdapter {
    private final Context context;
    private final List<VoucherCenterBean.ResultBean.ResultlistBean> datas;

    public VocherCenterAdapter(Context context, List<VoucherCenterBean.ResultBean.ResultlistBean> datas) {
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
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_voucher_center, null);
            viewHolder = new ViewHolder(convertView);
            viewHolder.llVoucherCenter = convertView.findViewById(R.id.ll_voucher_center);
            viewHolder.tvVoucherRmb = convertView.findViewById(R.id.tv_voucher_rmb);
            viewHolder.tvVoucherIntegral = convertView.findViewById(R.id.tv_voucher_integral);
            viewHolder.ivHot = convertView.findViewById(R.id.iv_hot);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        String productName = datas.get(position).getProductName();
        viewHolder.tvVoucherRmb.setText(productName.substring(0, productName.length() - 2));
        viewHolder.tvVoucherIntegral.setText(datas.get(position).getSubTitle());

        if (position == datas.size() - 1) {
            viewHolder.ivHot.setVisibility(View.VISIBLE);
        } else {
            viewHolder.ivHot.setVisibility(View.GONE);
        }

        viewHolder.llVoucherCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clickListener != null) {
                    clickListener.OnMitemsClickListener(position);
                }
            }
        });

        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.tv_voucher_rmb)
        TextView tvVoucherRmb;
        @BindView(R.id.tv_voucher_integral)
        TextView tvVoucherIntegral;
        @BindView(R.id.ll_voucher_center)
        LinearLayout llVoucherCenter;
        @BindView(R.id.iv_hot)
        ImageView ivHot;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    //    自定义接口
    public interface OnMitemsClickListener {
        public void OnMitemsClickListener(int position);
    }

    private OnMitemsClickListener clickListener;

    public void setOnMitemsClickListener(OnMitemsClickListener listener) {
        this.clickListener = listener;

    }

}
