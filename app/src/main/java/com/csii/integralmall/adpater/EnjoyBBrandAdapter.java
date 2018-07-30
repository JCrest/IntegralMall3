package com.csii.integralmall.adpater;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.csii.integralmall.R;
import com.csii.integralmall.bean.BrandSortBean;
import com.csii.integralmall.bean.EnjoyHeadBean;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class EnjoyBBrandAdapter extends RecyclerView.Adapter {
    private final Context context;
    private final Object objData;

    private final List<BrandSortBean.ResultBean.ResultlistBean> sortDatas = new ArrayList<>();

    // 第一种类型 顶部图片一张
    public static final int HEAD_IMAGE = 0;
    //    第二种类型  三个按钮
    public static final int TABS = 1;
    //    第三种类型  titleImage
    public static final int TITLEIMAGE = 2;
    //    第四种类型  sortDetails
    public static final int SORTDETAILS_L = 3;
    //    第五种类型  sortDetails
    public static final int SORTDETAILS_R = 4;
    //    第六种类型  viewMore
    public static final int VIEWMORE = 6;
    //    第七种类型  footer
    public static final int FOOTER = 17;
    //当前类型
    public int currentType = HEAD_IMAGE;

    private LayoutInflater inflater;

    public EnjoyBBrandAdapter(Context context, Object objData) {
        this.context = context;
        this.objData = objData;
        inflater = LayoutInflater.from(context);
    }

    /**
     * 适配器刷新的方法
     *
     * @param item 数据对象
     */
    public void setBrandSort(Object item) {
        BrandSortBean brandSortBean = (BrandSortBean) item;
        List<BrandSortBean.ResultBean.ResultlistBean> items = brandSortBean.getResult().getResultlist();
        sortDatas.addAll(items);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            currentType = HEAD_IMAGE;
        } else if (position == 1) {
            currentType = TABS;
        } else if (position == 2 || position == 7 || position == 12|| position == 17) {
            currentType = TITLEIMAGE;
        } else if (position == 3 || position == 5 || position == 8 || position == 10 ||
                position == 13 || position == 15|| position == 18 || position == 20) {
            currentType = SORTDETAILS_L;
        } else if (position == 4 || position == 9 || position == 14|| position == 19) {
            currentType = SORTDETAILS_R;
        } else if (position == 6 || position == 11 || position == 16|| position == 21) {
            currentType = VIEWMORE;
        } else if (position == 22) {
            currentType = FOOTER;
        }
        return currentType;
    }

    @Override
    public int getItemCount() {
        return 23;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == HEAD_IMAGE) {
            return new HeadImageViewHolder(context, inflater.inflate(R.layout.enjoy_brand_head, null));
        } else if (viewType == TABS) {
            return new TabsViewHolder(context, inflater.inflate(R.layout.enjoy_brand_tabs, null));
        } else if (viewType == TITLEIMAGE) {
            return new TitleImageViewHolder(context, inflater.inflate(R.layout.enjoy_brand_title, null));
        } else if (viewType == SORTDETAILS_L) {
            return new SortDetails_lViewHolder(context, inflater.inflate(R.layout.enjoy_brand_sortl, null));
        } else if (viewType == SORTDETAILS_R) {
            return new SortDetails_rViewHolder(context, inflater.inflate(R.layout.enjoy_brand_sortr, null));
        } else if (viewType == VIEWMORE) {
            return new ViewMoreViewHolder(context, inflater.inflate(R.layout.enjoy_brand_view_more, null));
        } else if (viewType == FOOTER) {
            return new FooterViewHolder(context, inflater.inflate(R.layout.enjoy_brand_footer, null));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == HEAD_IMAGE) {
            HeadImageViewHolder headImageViewHolder = (HeadImageViewHolder) holder;
            headImageViewHolder.setData();
        } else if (getItemViewType(position) == TABS) {
            EnjoyHeadBean enjoyHeadBean = (EnjoyHeadBean) objData;
            List<EnjoyHeadBean.ResultBean> mydatas = enjoyHeadBean.getResult();
            TabsViewHolder tabsViewHolder = (TabsViewHolder) holder;
            tabsViewHolder.setData(mydatas, position);
        } else if (getItemViewType(position) == TITLEIMAGE) {
            TitleImageViewHolder titleImageViewHolder = (TitleImageViewHolder) holder;
            if (sortDatas != null && !sortDatas.isEmpty()) {
                titleImageViewHolder.setData(sortDatas, position);
            }
        } else if (getItemViewType(position) == SORTDETAILS_L) {
            SortDetails_lViewHolder sortDetails_lViewHolder = (SortDetails_lViewHolder) holder;
            if (sortDatas != null && !sortDatas.isEmpty()) {
                sortDetails_lViewHolder.setData(sortDatas, position);
            }
        } else if (getItemViewType(position) == SORTDETAILS_R) {
            SortDetails_rViewHolder sortDetails_rViewHolder = (SortDetails_rViewHolder) holder;
            if (sortDatas != null && !sortDatas.isEmpty()) {
                sortDetails_rViewHolder.setData(sortDatas, position);
            }
        } else if (getItemViewType(position) == VIEWMORE) {
            ViewMoreViewHolder viewMoreViewHolder = (ViewMoreViewHolder) holder;
            if (sortDatas != null && !sortDatas.isEmpty()) {
                viewMoreViewHolder.setData(sortDatas, position);
            }
        } else if (getItemViewType(position) == FOOTER) {
            FooterViewHolder footerViewHolder = (FooterViewHolder) holder;
        }
    }

    /***************************------------------- 顶部图片 ViewHoder ---------------------**************************/
    class HeadImageViewHolder extends RecyclerView.ViewHolder {
        private final Context context;
        private ImageView ivEnjoyHeadImage;

        HeadImageViewHolder(Context context, View view) {
            super(view);
            this.context = context;
            ivEnjoyHeadImage = (ImageView) view.findViewById(R.id.iv_enjoy_head_image);
        }

        public void setData() {
            Glide.with(context).load(R.drawable.xiangjifen_bg).into(ivEnjoyHeadImage);
        }
    }

    /***************************------------------- 3个按钮 ViewHoder ---------------------**************************/
    class TabsViewHolder extends RecyclerView.ViewHolder {
        private final Context context;

        private TextView tvEnjoyTab1;
        private TextView tvEnjoyTab2;
        private TextView tvEnjoyTab3;
        private TextView tvEnjoyTab4;

        TabsViewHolder(Context context, View view) {
            super(view);
            this.context = context;
            tvEnjoyTab1 = (TextView) view.findViewById(R.id.tv_enjoy_tab1);
            tvEnjoyTab2 = (TextView) view.findViewById(R.id.tv_enjoy_tab2);
            tvEnjoyTab3 = (TextView) view.findViewById(R.id.tv_enjoy_tab3);
            tvEnjoyTab4 = (TextView) view.findViewById(R.id.tv_enjoy_tab4);
        }

        void setData(final List<EnjoyHeadBean.ResultBean> mydatas, final int position) {
            for (int i = 0; i < mydatas.size(); i++) {
                if (i == 0) {
                    setTabs(mydatas, tvEnjoyTab1, i, 5);
                } else if (i == 1) {
                    setTabs(mydatas, tvEnjoyTab2, i, 10);
                } else if (i == 2) {
                    setTabs(mydatas, tvEnjoyTab3, i, 15);
                } else if (i == 3) {
                    setTabs(mydatas, tvEnjoyTab4, i, 20);
                }
            }
        }

        /**
         * 设置 tab 的值并 实现自定义接口
         *
         * @param mydatas  数据
         * @param textView 控件
         * @param i        数据下标
         * @param position 目标位置
         */
        private void setTabs(List<EnjoyHeadBean.ResultBean> mydatas, TextView textView, int i, final int position) {
            textView.setText(mydatas.get(i).getName());
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (clickListener != null) {
                        clickListener.onTabsClick(position);
                    }
                }
            });
        }
    }

    //    自定义接口
    public interface OnTabsClickListener {
        public void onTabsClick(int position);
    }

    private OnTabsClickListener clickListener;

    public void setOnTabsClickListener(OnTabsClickListener listener) {
        this.clickListener = listener;

    }


    /***************************------------------- 种类名称 ViewHoder ---------------------**************************/
    class TitleImageViewHolder extends RecyclerView.ViewHolder {
        private final Context context;
        private ImageView ivEnjoyBrandTitle;

        TitleImageViewHolder(Context context, View view) {
            super(view);
            this.context = context;
            ivEnjoyBrandTitle = (ImageView) view.findViewById(R.id.iv_enjoy_brand_title);
        }

        public void setData(List<BrandSortBean.ResultBean.ResultlistBean> datas, int position) {
            switch (position) {
                case 2:
                    Glide.with(context).load(datas.get(0).getTbItemCat().getIcon()).into(ivEnjoyBrandTitle);
                    break;
                case 7:
                    Glide.with(context).load(datas.get(1).getTbItemCat().getIcon()).into(ivEnjoyBrandTitle);
                    break;
                case 12:
                    Glide.with(context).load(datas.get(2).getTbItemCat().getIcon()).into(ivEnjoyBrandTitle);
                    break;
                case 17:
                    Glide.with(context).load(datas.get(3).getTbItemCat().getIcon()).into(ivEnjoyBrandTitle);
                    break;
            }

        }
    }

    /***************************------------------- 产品 图片位于左侧 ViewHoder ---------------------**************************/
    class SortDetails_lViewHolder extends RecyclerView.ViewHolder {

        private final Context context;

        private LinearLayout llSortLeft;
        private TextView tvDetailCurrentPrice;
        private TextView tvDetailOriginalPrice;
        private TextView tvDetail;
        private TextView tvDetailName;
        private ImageView ivDetailImage;

        SortDetails_lViewHolder(Context context, View view) {
            super(view);
            this.context = context;
            llSortLeft = (LinearLayout) view.findViewById(R.id.ll_sort_left);
            tvDetailCurrentPrice = (TextView) view.findViewById(R.id.tv_detail_current_price);
            tvDetailOriginalPrice = (TextView) view.findViewById(R.id.tv_detail_original_price);
            tvDetail = (TextView) view.findViewById(R.id.tv_detail_);
            tvDetailName = (TextView) view.findViewById(R.id.tv_detail_name);
            ivDetailImage = (ImageView) view.findViewById(R.id.iv_detail_image);
        }

        void setData(List<BrandSortBean.ResultBean.ResultlistBean> sortDatas, int position) {
            int i, j;
            switch (position) {
                //                    由于服务器返回数据不够会造成数组下标越界导致程序崩溃  暂时注释掉
/*                case 3:
                    i = 0;
                    j = 1;
                    setCss(sortDatas, i, j, R.drawable.sort_brand_detail, R.color.sort_color_1,
                            R.color.sort_color_opacity_1, R.drawable.tv_price, llSortLeft, ivDetailImage,
                            tvDetailName, tvDetailOriginalPrice, tvDetail, tvDetailCurrentPrice);
                    break;
                case 5:
                    i = 0;
                    j = 2;
                    setCss(sortDatas, i, j, R.drawable.sort_brand_detail, R.color.sort_color_1,
                            R.color.sort_color_opacity_1, R.drawable.tv_price, llSortLeft, ivDetailImage,
                            tvDetailName, tvDetailOriginalPrice, tvDetail, tvDetailCurrentPrice);
                    break;*/
                case 8:
                    i = 1;
                    j = 1;
                    setCss(sortDatas, i, j, R.drawable.sort_brand_detail2, R.color.sort_color_2,
                            R.color.sort_color_opacity_2, R.drawable.tv_price2, llSortLeft, ivDetailImage,
                            tvDetailName, tvDetailOriginalPrice, tvDetail, tvDetailCurrentPrice);
                    break;
                case 10:
                    i = 1;
                    j = 2;
                    setCss(sortDatas, i, j, R.drawable.sort_brand_detail2, R.color.sort_color_2,
                            R.color.sort_color_opacity_2, R.drawable.tv_price2, llSortLeft, ivDetailImage,
                            tvDetailName, tvDetailOriginalPrice, tvDetail, tvDetailCurrentPrice);
                    break;
//                    由于服务器返回数据不够会造成数组下标越界导致程序崩溃  暂时注释掉
                case 13:
                    i = 2;
                    j = 1;
                    setCss(sortDatas, i, j, R.drawable.sort_brand_detail, R.color.sort_color_1,
                            R.color.sort_color_opacity_1, R.drawable.tv_price, llSortLeft, ivDetailImage,
                            tvDetailName, tvDetailOriginalPrice, tvDetail, tvDetailCurrentPrice);
                    break;
                case 15:
                    i = 2;
                    j = 2;
                    setCss(sortDatas, i, j, R.drawable.sort_brand_detail, R.color.sort_color_1,
                            R.color.sort_color_opacity_1, R.drawable.tv_price, llSortLeft, ivDetailImage,
                            tvDetailName, tvDetailOriginalPrice, tvDetail, tvDetailCurrentPrice);
                    break;
                case 18:
                    i = 3;
                    j = 1;
                    setCss(sortDatas, i, j, R.drawable.sort_brand_detail, R.color.sort_color_1,
                            R.color.sort_color_opacity_1, R.drawable.tv_price, llSortLeft, ivDetailImage,
                            tvDetailName, tvDetailOriginalPrice, tvDetail, tvDetailCurrentPrice);
                    break;
/*                case 20:
                    i = 3;
                    j = 2;
                    setCss(sortDatas, i, j, R.drawable.sort_brand_detail, R.color.sort_color_1,
                            R.color.sort_color_opacity_1, R.drawable.tv_price, llSortLeft, ivDetailImage,
                            tvDetailName, tvDetailOriginalPrice, tvDetail, tvDetailCurrentPrice);
                    break;*/
            }

        }


    }

    /***************************------------------- 产品 图片位于右侧 ViewHoder ---------------------**************************/
    class SortDetails_rViewHolder extends RecyclerView.ViewHolder {
        private final Context context;
        private LinearLayout llSortLeft;
        private TextView tvDetailCurrentPrice;
        private TextView tvDetailOriginalPrice;
        private TextView tvDetail;
        private TextView tvDetailName;
        private ImageView ivDetailImage;

        SortDetails_rViewHolder(Context context, View view) {
            super(view);
            this.context = context;
            llSortLeft = (LinearLayout) view.findViewById(R.id.ll_sort_left);
            tvDetailCurrentPrice = (TextView) view.findViewById(R.id.tv_detail_current_price);
            tvDetailOriginalPrice = (TextView) view.findViewById(R.id.tv_detail_original_price);
            tvDetail = (TextView) view.findViewById(R.id.tv_detail_);
            tvDetailName = (TextView) view.findViewById(R.id.tv_detail_name);
            ivDetailImage = (ImageView) view.findViewById(R.id.iv_detail_image);
        }

        public void setData(List<BrandSortBean.ResultBean.ResultlistBean> sortDatas, int position) {
            int i;
            int j = 0;
            switch (position) {
                case 4:
                    i = 0;
                    setCss(sortDatas, i, j, R.drawable.sort_brand_detail, R.color.sort_color_1,
                            R.color.sort_color_opacity_1, R.drawable.tv_price, llSortLeft, ivDetailImage,
                            tvDetailName, tvDetailOriginalPrice, tvDetail, tvDetailCurrentPrice);
                    break;
                case 9:
                    i = 1;
                    setCss(sortDatas, i, j, R.drawable.sort_brand_detail2, R.color.sort_color_2,
                            R.color.sort_color_opacity_2, R.drawable.tv_price2, llSortLeft, ivDetailImage,
                            tvDetailName, tvDetailOriginalPrice, tvDetail, tvDetailCurrentPrice);
                    break;
                case 14:
                    i = 2;
                    setCss(sortDatas, i, j, R.drawable.sort_brand_detail3, R.color.sort_color_3,
                            R.color.sort_color_opacity_3, R.drawable.tv_price3, llSortLeft, ivDetailImage,
                            tvDetailName, tvDetailOriginalPrice, tvDetail, tvDetailCurrentPrice);
                    break;
                case 19:
                    i = 3;
                    setCss(sortDatas, i, j, R.drawable.sort_brand_detail3, R.color.sort_color_3,
                            R.color.sort_color_opacity_3, R.drawable.tv_price3, llSortLeft, ivDetailImage,
                            tvDetailName, tvDetailOriginalPrice, tvDetail, tvDetailCurrentPrice);
                    break;
            }
        }
    }

    /**
     * 设置单个产品的item的样式
     *
     * @param sortDatas             联网请求获取到的数据
     * @param i                     服务器数据集合的第一层下标
     * @param j                     服务器数据集合的第二层下标
     * @param wireframe             不同位置的产品应用不同的线框背景
     * @param noOpacity             没有透明度设置的文字的颜色
     * @param opacity               设置有透明度的文字的颜色
     * @param priceCss              当前的价格
     * @param llSortLeft            外层线性布局
     * @param ivDetailImage         产品图片
     * @param tvDetailName          产品名称
     * @param tvDetailOriginalPrice 产品原始价格
     * @param tvDetail              （市场价格）
     * @param tvDetailCurrentPrice  当前价格
     */
    @SuppressLint("SetTextI18n")
    public void setCss(final List<BrandSortBean.ResultBean.ResultlistBean> sortDatas, final int i, final int j, int wireframe, int noOpacity, int opacity, int priceCss, View llSortLeft, ImageView ivDetailImage, TextView tvDetailName, TextView tvDetailOriginalPrice, TextView tvDetail, TextView tvDetailCurrentPrice) {
        llSortLeft.setBackground(ContextCompat.getDrawable(context, wireframe));
        sortClick(sortDatas, i, j, llSortLeft);

        Glide.with(context).load(sortDatas.get(i).getTbItemList().get(j).getProductImageBig()).into(ivDetailImage);

        tvDetailName.setText(sortDatas.get(i).getTbItemList().get(j).getProductName());
        tvDetailName.setTextColor(ContextCompat.getColor(context, noOpacity));


        tvDetailOriginalPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        tvDetailOriginalPrice.setText(doubleToString(sortDatas.get(i).getTbItemList().get(j).getMarketprice()));
        tvDetailOriginalPrice.setTextColor(ContextCompat.getColor(context, opacity));
        tvDetail.setTextColor(ContextCompat.getColor(context, opacity));

        tvDetailCurrentPrice.setText("99积分+" + doubleToString(sortDatas.get(i).getTbItemList().get(j).getSalePrice()) + "元");
        tvDetailCurrentPrice.setBackground(ContextCompat.getDrawable(context, priceCss));
    }

    /**
     * 产品线性布局的点击事件
     *
     * @param sortDatas  数据
     * @param i          服务器数据集合的第一层下标
     * @param j          服务器数据集合的第二层下标
     * @param llSortLeft 控件
     */
    private void sortClick(final List<BrandSortBean.ResultBean.ResultlistBean> sortDatas, final int i, final int j, View llSortLeft) {
        llSortLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "" + sortDatas.get(i).getTbItemList().get(j).getProductId(), Toast.LENGTH_SHORT).show();
            }
        });
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

    /***************************------------------- 查看更多 ViewHoder ---------------------**************************/
    private class ViewMoreViewHolder extends RecyclerView.ViewHolder {

        private final Context context;
        private TextView tvViewMore;

        public ViewMoreViewHolder(Context context, View view) {
            super(view);
            this.context = context;
            tvViewMore = (TextView) view.findViewById(R.id.tv_view_more);
        }

        public void setData(final List<BrandSortBean.ResultBean.ResultlistBean> sortDatas, int position) {
            int i;
            switch (position) {
                case 6:
                    i = 0;
                    viewMoreClick(sortDatas, i);
                    break;
                case 11:
                    i = 1;
                    viewMoreClick(sortDatas, i);
                    break;
                case 16:
                    i = 2;
                    viewMoreClick(sortDatas, i);
                    break;
                case 21:
                    i = 3;
                    viewMoreClick(sortDatas, i);
                    break;
            }
        }

        /**
         * 查看更多的点击事件
         *
         * @param sortDatas 服务器返回的数据
         * @param i         数据集合第一层的下标
         */
        private void viewMoreClick(final List<BrandSortBean.ResultBean.ResultlistBean> sortDatas, final int i) {
            tvViewMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "" + sortDatas.get(i).getTbItemCat().getParentId() + "/"
                            + sortDatas.get(i).getTbItemCat().getId() + ":"
                            + sortDatas.get(i).getTbItemCat().getName(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    // 为底部添加24像素的距离
    private class FooterViewHolder extends RecyclerView.ViewHolder {
        private final Context context;
        private View vFooter;

        public FooterViewHolder(Context context, View view) {
            super(view);
            this.context = context;
            vFooter = (View) view.findViewById(R.id.v_footer);
        }
    }
}
