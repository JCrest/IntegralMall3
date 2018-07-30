package com.csii.integralmall.adpater;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.csii.integralmall.R;
import com.csii.integralmall.bean.WonderfulActivityInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WonderfulActivityAdpater extends BaseAdapter {
    private final Context context;
    private final List<WonderfulActivityInfo> wonderfulActivityInfos;


    public WonderfulActivityAdpater(Context context, List<WonderfulActivityInfo> wonderfulActivityInfos) {
        this.context = context;
        this.wonderfulActivityInfos = wonderfulActivityInfos;
    }

    @Override
    public int getCount() {
        return wonderfulActivityInfos == null ? 0 : wonderfulActivityInfos.size();
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
            convertView = View.inflate(context, R.layout.item_wondeful_activit, null);
            viewHolder = new ViewHolder(convertView);
            viewHolder.ivGoodsImage = convertView.findViewById(R.id.iv_goods_image);
            viewHolder.ivPromotionImgurl = convertView.findViewById(R.id.iv_promotion_imgurl);
            viewHolder.tvGoodsName = convertView.findViewById(R.id.tv_goods_name);
            viewHolder.tvGvIntegralPrice = convertView.findViewById(R.id.tv_gv_integral_price);
            viewHolder.tvGvIntegralNum = convertView.findViewById(R.id.tv_gv_integral_num);
            viewHolder.tvImmediatelyChange = convertView.findViewById(R.id.tv_immediately_change);
            viewHolder.tvDisableChange = convertView.findViewById(R.id.tv_disable_change);


            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();

        }
        WonderfulActivityInfo wonderfulActivityInfo = wonderfulActivityInfos.get(position);
        viewHolder.ivGoodsImage.setImageResource(wonderfulActivityInfo.getProductImage());
        if (wonderfulActivityInfo.isHot() == false) {
            viewHolder.ivPromotionImgurl.setVisibility(View.GONE);
        }
        viewHolder.tvGoodsName.setText(wonderfulActivityInfo.getProductName() + "\n" + wonderfulActivityInfo.getProductDes());
        viewHolder.tvGvIntegralPrice.setText(wonderfulActivityInfo.getIntegralPrice() + "");
        viewHolder.tvGvIntegralNum.setText(wonderfulActivityInfo.getSurplusNum() + "");
        if (wonderfulActivityInfo.getSurplusNum() == 0) {
            viewHolder.tvImmediatelyChange.setVisibility(View.GONE);
            viewHolder.tvDisableChange.setVisibility(View.VISIBLE);
        } else {
            viewHolder.tvImmediatelyChange.setVisibility(View.VISIBLE);
            viewHolder.tvDisableChange.setVisibility(View.GONE);
        }
        return convertView;
    }

    class ViewHolder {
        @BindView(R.id.iv_goods_image)
        ImageView ivGoodsImage;
        @BindView(R.id.iv_promotion_imgurl)
        ImageView ivPromotionImgurl;
        @BindView(R.id.tv_goods_name)
        TextView tvGoodsName;
        @BindView(R.id.tv_gv_integral_price)
        TextView tvGvIntegralPrice;
        @BindView(R.id.tv_gv_integral_num)
        TextView tvGvIntegralNum;
        @BindView(R.id.tv_immediately_change)
        TextView tvImmediatelyChange;
        @BindView(R.id.tv_disable_change)
        TextView tvDisableChange;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
            tvImmediatelyChange.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "恭喜您已成功兑换该商品！", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
