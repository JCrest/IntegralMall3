package com.csii.integralmall.adpater;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.csii.integralmall.R;
import com.csii.integralmall.bean.BrandSortBean;

import java.text.DecimalFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BigBrandAdapter extends BaseAdapter {

    private final Context context;
    private final List<BrandSortBean.ResultBean.ResultlistBean.TbItemListBean> datas;

    public BigBrandAdapter(Context context,
                           List<BrandSortBean.ResultBean.ResultlistBean.TbItemListBean> datas) {
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
    public long getItemId(int i) {
        return 0;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;

        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_big_brand_sur_sta_vevel, null);
            viewHolder = new ViewHolder(convertView);
            viewHolder.ivBigBrandIcon = convertView.findViewById(R.id.iv_big_brand_icon);
            viewHolder.ivBigBrandDash = convertView.findViewById(R.id.iv_big_brand_dash);
            viewHolder.tvBigBrandName = convertView.findViewById(R.id.tv_big_brand_name);
            viewHolder.tvBigBrandIntegral = convertView.findViewById(R.id.tv_big_brand_integral);
            viewHolder.tvIntegral = convertView.findViewById(R.id.tv_integral);
            viewHolder.tvBigBrandYuan = convertView.findViewById(R.id.tv_big_brand_yuan);
            viewHolder.tvYuan = convertView.findViewById(R.id.tv_yuan);
            viewHolder.tvBigBrandMarketPrice = convertView.findViewById(R.id.tv_big_brand_market_price);
            viewHolder.tvNumberSold = convertView.findViewById(R.id.tv_number_sold);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        BrandSortBean.ResultBean.ResultlistBean.TbItemListBean data = datas.get(position);
        Glide.with(context).load(data.getProductImageBig()).into(viewHolder.ivBigBrandIcon);
        viewHolder.tvBigBrandName.setText(data.getProductName());
        viewHolder.tvBigBrandIntegral.setText("99");
        viewHolder.tvIntegral.setText("积分");
        viewHolder.tvBigBrandYuan.setText(doubleToString(data.getSalePrice()));
        viewHolder.tvYuan.setText("元");
        viewHolder.tvBigBrandMarketPrice.setText("市场参考价：" + data.getMarketprice() + "元");
//        已售:1999件
        viewHolder.tvNumberSold.setText("已售:" + "不知道" + "件");

        return convertView;
    }

    /**
     * double 转 String (这里将有小数位的都去掉)
     *
     * @param num
     * @return
     */
    public String doubleToString(double num) {
        //使用0.00不足位补0，#.##仅保留有效位
//            return new DecimalFormat("0.00").format(num);
        return new DecimalFormat("0").format(num);
    }

    static class ViewHolder {
        @BindView(R.id.iv_big_brand_icon)
        ImageView ivBigBrandIcon;
        @BindView(R.id.iv_big_brand_dash)
        ImageView ivBigBrandDash;
        @BindView(R.id.tv_big_brand_name)
        TextView tvBigBrandName;
        @BindView(R.id.tv_big_brand_integral)
        TextView tvBigBrandIntegral;
        @BindView(R.id.tv_integral)
        TextView tvIntegral;
        @BindView(R.id.tv_big_brand_yuan)
        TextView tvBigBrandYuan;
        @BindView(R.id.tv_yuan)
        TextView tvYuan;
        @BindView(R.id.tv_big_brand_market_price)
        TextView tvBigBrandMarketPrice;
        @BindView(R.id.tv_number_sold)
        TextView tvNumberSold;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
