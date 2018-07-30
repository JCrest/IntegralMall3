package com.csii.integralmall.adpater;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.csii.integralmall.R;
import com.csii.integralmall.activity.earnIntegral.EarnIntegralActivity;
import com.csii.integralmall.activity.enjoyBigBrand.EnhoyBBrandActivity;
import com.csii.integralmall.activity.signIntegralSale.signIn.SignInActivity;
import com.csii.integralmall.activity.voucherCenter.VoucherCenterActivity;
import com.csii.integralmall.bean.ActBean;
import com.csii.integralmall.bean.BannerBean;
import com.csii.integralmall.bean.ChannelBean;
import com.csii.integralmall.bean.HomePage;
import com.csii.integralmall.common.extendurl;
import com.csii.integralmall.utils.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter {


    private final Context context;
    private final Object datas;
    private final List<HomePage.ResultBean> sortDatas = new ArrayList<>();

    /**
     * 类型一：banner
     */
    public static final int BANNER = 0;

    /**
     * 类型二：频道
     */
    public static final int CHANNEL = 1;
    /**
     * 活动
     */
    public static final int ACT = 2;

    /**
     * 各种产品种类
     */
    public static final int PRODU_SORT = 3;

    /**
     * 活动广告
     */
    public static final int ACT_AD = 5;

    /**
     * 当前类型
     */
    public int currentType = BANNER;


    private LayoutInflater inflater;

    public HomeAdapter(Context context, Object datas) {
        this.context = context;
        this.datas = datas;
        inflater = LayoutInflater.from(context);

    }

    /**
     * 获取到banner的数据后在加载后面的数据
     *
     * @param item
     */
    public void setSort(Object item) {
        HomePage homePage = (HomePage) item;
        List<HomePage.ResultBean> items = homePage.getResult();
        sortDatas.addAll(items);
        notifyDataSetChanged();
    }

    /**
     * 根据不同的item所在的位置不同选择不同的类型
     *
     * @param position 位置
     * @return 返回当前的类型
     */
    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            currentType = BANNER;
        } else if (position == 1) {
            currentType = CHANNEL;
        } else if (position == 2) {
            currentType = ACT;
        } else if (position == 3 || position == 4 || position == 6 || position == 7 || position == 9 || position == 10) {
            currentType = PRODU_SORT;
        } else if (position == 5 || position == 8) {
            currentType = ACT_AD;
        }
        return currentType;
    }

    /**
     * 展示类型的个数
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return 11;
    }

    /**
     * 加载ViewHodle
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == BANNER) {
            return new BannerViewHolder(context, inflater.inflate(R.layout.banner_viewpager, null));
        } else if (viewType == CHANNEL) {
            return new ChannelViewHolder(context, inflater.inflate(R.layout.channel_item, null));
        } else if (viewType == ACT) {
            return new ActViewHolder(context, inflater.inflate(R.layout.act_item, null));
        } else if (viewType == PRODU_SORT) {
            return new SortViewHolder(context, inflater.inflate(R.layout.produ_sort_item, null));
        } else if (viewType == ACT_AD) {
            return new AdViewHolder(context, inflater.inflate(R.layout.act_ad_item, null));
        }
        return null;
    }

    /**
     * 数据绑定
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == BANNER) {
            BannerBean bannerBean = (BannerBean) datas;
            List<BannerBean.ResultBannerBean> mydatas = bannerBean.getResult();
            BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
            bannerViewHolder.setData(mydatas);
        } else if (getItemViewType(position) == CHANNEL) {
            ChannelViewHolder channelViewHolder = (ChannelViewHolder) holder;
            //绑定数据
            channelViewHolder.setData();
        } else if (getItemViewType(position) == ACT) {
            ActViewHolder actViewHolder = (ActViewHolder) holder;
            actViewHolder.setData();
        } else if (getItemViewType(position) == PRODU_SORT) {
            SortViewHolder sortViewHolder = (SortViewHolder) holder;
            if (sortDatas != null && !sortDatas.isEmpty()) {
                sortViewHolder.setData(sortDatas, position);
            }
        } else if (getItemViewType(position) == ACT_AD) {
            AdViewHolder adViewHolder = (AdViewHolder) holder;
            if (sortDatas != null && !sortDatas.isEmpty()) {
                adViewHolder.setData(sortDatas, position);
            }
        }

    }

    /*****************************************----------BannerViewHolder start-------------***************************************************/

    class BannerViewHolder extends RecyclerView.ViewHolder {
        private final Context context;
        private Banner banner;

        public BannerViewHolder(Context context, View itemView) {
            super(itemView);
            this.context = context;
            banner = (Banner) itemView.findViewById(R.id.cvp_home_banner);
        }

        public void setData(final List<BannerBean.ResultBannerBean> datas) {
            //设置Banner 数据
            List<String> images = new ArrayList<>();
            for (int i = 0; i < datas.size(); i++) {
                images.add(datas.get(i).getPicUrl());
            }

            banner.setImages(images)
                    .setDelayTime(4000)
                    .setImageLoader(new GlideImageLoader())
                    .setOnBannerListener(new OnBannerListener() {
                        @Override
                        public void OnBannerClick(int position) {
                            Toast.makeText(context, "恭喜您，成功了！", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .start();
        }
    }

    /*****************************************----------ChannelViewHolder start-------------***************************************************/
    class ChannelViewHolder extends RecyclerView.ViewHolder {
        private final Context context;
        private GridView gv;

        public ChannelViewHolder(Context context, View itemView) {
            super(itemView);
            this.context = context;
            gv = (GridView) itemView.findViewById(R.id.gv_index);

        }

        public void setData() {
            //构造channel部分数据
            final List<ChannelBean> list = new ArrayList<>();
            list.add(new ChannelBean(R.drawable.home_menu_zjf, R.string.home_menu_zjf));
            list.add(new ChannelBean(R.drawable.home_menu_czzx, R.string.home_menu_czzx));
            list.add(new ChannelBean(R.drawable.home_menu_xdp, R.string.home_menu_xdp));
            list.add(new ChannelBean(R.drawable.home_menu_mrqd, R.string.home_menu_mrqd));
            list.add(new ChannelBean(R.drawable.home_menu_fl, R.string.home_menu_fl));
            //设置适配器
            ChannelAdapter adapter = new ChannelAdapter(context, list);
            gv.setAdapter(adapter);

            final Intent[] intent = new Intent[5];
            intent[0] = new Intent(context, EarnIntegralActivity.class);
            intent[1] = new Intent(context, VoucherCenterActivity.class);
            intent[2] = new Intent(context, EnhoyBBrandActivity.class);
            intent[3] = new Intent(context, SignInActivity.class);

            gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    switch (position) {
                        case 0:
//                            Toast.makeText(context, "您点击了赚积分！", Toast.LENGTH_SHORT).show();

                            context.startActivity(intent[0]);
                            break;
                        case 1:
//                            Toast.makeText(context, "您点击了充值中心！", Toast.LENGTH_SHORT).show();

                            context.startActivity(intent[1]);
                            break;
                        case 2:
//                            Toast.makeText(context, "您点击了享大牌！", Toast.LENGTH_SHORT).show();

                            context.startActivity(intent[2]);
                            break;
                        case 3:
//                            Toast.makeText(context, "您点击了每日签到！", Toast.LENGTH_SHORT).show();

                            context.startActivity(intent[3]);
                            break;
                        case 4:
                            Toast.makeText(context, "您点击了分类！", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            });


        }
    }

    /*****************************************----------ActViewHolder start-------------***************************************************/
    class ActViewHolder extends RecyclerView.ViewHolder {

        private final Context context;
        GridView gvIndexAct;

        public ActViewHolder(Context context, View itemView) {
            super(itemView);
            this.context = context;
            gvIndexAct = (GridView) itemView.findViewById(R.id.gv_index_act);
        }

        public void setData() {
            //构造channel部分数据
            final List<ActBean> list = new ArrayList<>();
            list.add(new ActBean(R.drawable.home_icon_libao, R.string.home_libao_title, R.string.home_libao_des));
            list.add(new ActBean(R.drawable.home_icon_jiufenquan, R.string.home_jfqu_title, R.string.home_jfqu_des));

            //设置适配器
            ActAdapter adapter = new ActAdapter(context, list);
            gvIndexAct.setAdapter(adapter);
            gvIndexAct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(context, Integer.toString(position), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    /*****************************************----------SortViewHolder start-------------***************************************************/
    class SortViewHolder extends RecyclerView.ViewHolder {
        private final Context context;

        private ImageView ivProPortTitle;

        private LinearLayout llSortHead;
        private TextView tvSortHeadTitle;
        private TextView tvSortHeadDes;
        private ImageView ivSortHeadImage;

        private LinearLayout llSortLeft;
        private TextView tvSortLeftTitle;
        private TextView tvSortLeftDes;
        private ImageView ivSortLeftImage;

        private LinearLayout llSortCenter;
        private TextView tvSortCenterTitle;
        private TextView tvSortCenterDes;
        private ImageView ivSortCenterImage;

        private LinearLayout llSortRight;
        private TextView tvSortRightTitle;
        private TextView tvSortRightDes;
        private ImageView ivSortRightImage;


        SortViewHolder(Context context, View itemView) {
            super(itemView);
            this.context = context;

            ivProPortTitle = (ImageView) itemView.findViewById(R.id.iv_pro_sort_title);

            llSortHead = (LinearLayout) itemView.findViewById(R.id.ll_sort_head);
            tvSortHeadTitle = (TextView) itemView.findViewById(R.id.tv_sort_head_title);
            tvSortHeadDes = (TextView) itemView.findViewById(R.id.tv_sort_head_des);
            ivSortHeadImage = (ImageView) itemView.findViewById(R.id.iv_sort_head_image);

            llSortLeft = (LinearLayout) itemView.findViewById(R.id.ll_sort_left);
            tvSortLeftTitle = (TextView) itemView.findViewById(R.id.tv_sort_left_title);
            tvSortLeftDes = (TextView) itemView.findViewById(R.id.tv_sort_left_des);
            ivSortLeftImage = (ImageView) itemView.findViewById(R.id.iv_sort_left_image);

            llSortCenter = (LinearLayout) itemView.findViewById(R.id.ll_sort_center);
            tvSortCenterTitle = (TextView) itemView.findViewById(R.id.tv_sort_center_title);
            tvSortCenterDes = (TextView) itemView.findViewById(R.id.tv_sort_center_des);
            ivSortCenterImage = (ImageView) itemView.findViewById(R.id.iv_sort_center_image);

            llSortRight = (LinearLayout) itemView.findViewById(R.id.ll_sort_right);
            tvSortRightTitle = (TextView) itemView.findViewById(R.id.tv_sort_right_title);
            tvSortRightDes = (TextView) itemView.findViewById(R.id.tv_sort_right_des);
            ivSortRightImage = (ImageView) itemView.findViewById(R.id.iv_sort_right_image);
        }

        /**
         * 根据位置的不同设置相同的布局结构，但数据也不是相同的
         *
         * @param sortDatas 数据
         * @param position  位置
         */
        public void setData(List<HomePage.ResultBean> sortDatas, int position) {
            //        利用glide 加载资源图片   (正式使用showicon获取顶部分类的图片资源，鉴于服务器并没有放置资源所以用此代替)
            Glide.with(context)
                    .load(R.drawable.home_one_top)
                    .into(ivProPortTitle);
            int i, j;
            switch (position) {
                case 3:
                    i = j = 0;
                    setLayoutByposition(sortDatas, i, j);
                    break;
                case 4:
                    i = 0;
                    j = 1;
                    setLayoutByposition(sortDatas, i, j);
                    break;
                case 6:
                    i = 1;
                    j = 0;
                    setLayoutByposition(sortDatas, i, j);
                    break;
                case 7:
                    i = j = 1;
                    setLayoutByposition(sortDatas, i, j);
                    break;
                case 9:
                    i = 2;
                    j = 0;
                    setLayoutByposition(sortDatas, i, j);
                    break;
                case 10:
                    i = 2;
                    j = 1;
                    setLayoutByposition(sortDatas, i, j);
                    break;
            }


        }

        /**
         * 为每个布局添加数据
         *
         * @param sortDatas 数据
         * @param i
         * @param j
         */
        private void setLayoutByposition(final List<HomePage.ResultBean> sortDatas, final int i, final int j) {
//            顶部标题部分  由于没有资源所以暂时注释掉
//            Glide.with(context).load(sortDatas.get(i).getTicllist().get(j).getShowicon()).into(ivProPortTitle);

//            大图部分
            tvSortHeadTitle.setText(sortDatas.get(i).getTicllist().get(j).getTbItemCatList().get(0).getName());
            tvSortHeadDes.setText(sortDatas.get(i).getTicllist().get(j).getTbItemCatList().get(0).getRemark());
            Glide.with(context).load(sortDatas.get(i).getTicllist().get(j).getTbItemCatList().get(0).getIcon()).into(ivSortHeadImage);
            llSortHead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setTarget(sortDatas, i, j, 0);
                }
            });

//            下面3个小图部分
//            鉴于服务器现在位置为4的部分没有上传资源所以此处判断不加载
            if (sortDatas.get(i).getTicllist().get(j).getTbItemCatList().size() > 1) {
                tvSortLeftTitle.setText(sortDatas.get(i).getTicllist().get(j).getTbItemCatList().get(1).getName());
                tvSortLeftDes.setText(sortDatas.get(i).getTicllist().get(j).getTbItemCatList().get(1).getRemark());
                Glide.with(context).load(sortDatas.get(i).getTicllist().get(j).getTbItemCatList().get(1).getIcon()).into(ivSortLeftImage);
                llSortLeft.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        setTarget(sortDatas, i, j, 1);
                    }
                });

                tvSortCenterTitle.setText(sortDatas.get(i).getTicllist().get(j).getTbItemCatList().get(2).getName());
                tvSortCenterDes.setText(sortDatas.get(i).getTicllist().get(j).getTbItemCatList().get(2).getRemark());
                Glide.with(context).load(sortDatas.get(i).getTicllist().get(j).getTbItemCatList().get(2).getIcon()).into(ivSortCenterImage);
                llSortCenter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        setTarget(sortDatas, i, j, 2);
                    }
                });

                tvSortRightTitle.setText(sortDatas.get(i).getTicllist().get(j).getTbItemCatList().get(3).getName());
                tvSortRightDes.setText(sortDatas.get(i).getTicllist().get(j).getTbItemCatList().get(3).getRemark());
                Glide.with(context).load(sortDatas.get(i).getTicllist().get(j).getTbItemCatList().get(3).getIcon()).into(ivSortRightImage);
                llSortRight.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        setTarget(sortDatas, i, j, 3);
                    }
                });

            }
        }

        private void setTarget(List<HomePage.ResultBean> sortDatas, int i, int j, int index) {
            if (sortDatas.get(i).getTicllist().get(j).getTbItemCatList().get(index).getExtendurl().contains("http")) {
                Toast.makeText(context, "数据不全暂时不做处理", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "" + sortDatas.get(i).getTicllist().get(j)
                                .getTbItemCatList().get(index).getExtendurl() + "---->"
                                + extendurl.getValue(sortDatas.get(i).getTicllist().get(j)
                                .getTbItemCatList().get(index).getExtendurl())
                        , Toast.LENGTH_SHORT).show();
                try {
                    String JFSC = sortDatas.get(i).getTicllist().get(j).getTbItemCatList()
                            .get(index).getExtendurl();
                    if (extendurl.getValue(JFSC) != null && !extendurl.getValue(JFSC).isEmpty()) {
                        Class clazz = Class.forName(extendurl.getValue(JFSC));
                        Intent intent = new Intent(context, clazz);
                        intent.putExtra("index", index);
                        context.startActivity(intent);
                    } else {
                        Toast.makeText(context, "没有数据！", Toast.LENGTH_SHORT).show();
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }


    }


    /*****************************************----------AdViewHolder start-------------***************************************************/
    class AdViewHolder extends RecyclerView.ViewHolder {
        private final Context context;
        private ImageView ivActAd;

        AdViewHolder(Context context, View itemView) {
            super(itemView);
            this.context = context;
            ivActAd = (ImageView) itemView.findViewById(R.id.iv_act_ad);
        }

        public void setData(List<HomePage.ResultBean> sortDatas, int position) {
            int i;
            switch (position) {
                case 5:
                    i = 0;
                    Glide.with(context)
                            .load(sortDatas.get(i).getItemCatSingle().getIcon())
                            .into(ivActAd);
                    break;
                case 8:
                    i = 1;
                    Glide.with(context)
                            .load(sortDatas.get(i).getItemCatSingle().getIcon())
                            .into(ivActAd);
                    break;
            }
        }
    }

}