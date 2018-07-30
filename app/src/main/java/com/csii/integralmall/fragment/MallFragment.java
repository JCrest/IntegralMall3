package com.csii.integralmall.fragment;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.csii.integralmall.R;
import com.csii.integralmall.adpater.HomeAdapter;
import com.csii.integralmall.base.BaseFragment;
import com.csii.integralmall.bean.BannerBean;
import com.csii.integralmall.bean.HomePage;
import com.csii.integralmall.common.Api;
import com.csii.integralmall.okhttp.CommonOkHttpClient;
import com.csii.integralmall.okhttp.listener.DisposeDataHandle;
import com.csii.integralmall.okhttp.listener.DisposeDataListener;
import com.csii.integralmall.okhttp.request.CommonRequest;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MallFragment extends BaseFragment implements DisposeDataListener {

    private static final String TAG = MallFragment.class.getSimpleName();//"MallFragment"
    @BindView(R.id.rv_home_mall)
    RecyclerView rvHomeMall;
    @BindView(R.id.iv_location)
    ImageView ivLocation;
    @BindView(R.id.tv_location)
    TextView tvLocation;
    @BindView(R.id.iv_icon_logction)
    ImageView ivIconLogction;
    @BindView(R.id.ll_location)
    LinearLayout llLocation;
    @BindView(R.id.iv_search_goods)
    ImageView ivSearchGoods;
    @BindView(R.id.et_search_goods)
    EditText etSearchGoods;
    @BindView(R.id.ll_search_goods)
    LinearLayout llSearchGoods;
    @BindView(R.id.iv_message)
    ImageView ivMessage;
    @BindView(R.id.ll_message)
    LinearLayout llMessage;
    Unbinder unbinder;

    //首页的适配器
    private HomeAdapter adapter;

    @Override
    protected View initView() {
        View view = View.inflate(context, R.layout.fragment_mall, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initData() {
        getBannerRequest();


    }

    /**
     * 获取第二部分的数据（成功后，刷新适配器）
     *
     * @param adapter
     */
    private void getPageRequest(final HomeAdapter adapter) {
        CommonOkHttpClient.get(CommonRequest.createGetRequest(Api.HOMEPAGE),
                new DisposeDataHandle(new DisposeDataListener() {
                    @Override
                    public void onSuccess(Object responseObj) {
                        HomePage homePage = (HomePage) responseObj;
                        Log.e(TAG, "onSuccess: " + homePage.getResult());
//                        刷新适配器
                        adapter.setSort(responseObj);
                    }

                    @Override
                    public void onFailure(Object reasonObj) {
                        Log.e(TAG, "onFailure: " + reasonObj);

                    }
                }, HomePage.class));
    }

    private void getBannerRequest() {
        CommonOkHttpClient.get(CommonRequest.createGetRequest(Api.INDEXBANNER),
                new DisposeDataHandle(this, BannerBean.class));

    }

    @Override
    public void onSuccess(Object responseObj) {
        //设置适配器
        adapter = new HomeAdapter(context, responseObj);
        rvHomeMall.setAdapter(adapter);
        //设置布局管理器
        GridLayoutManager liner = new GridLayoutManager(context, 1);
        setActtionBar(liner);
        rvHomeMall.setLayoutManager(liner);
//        获取第二部分的数据（homepage 部分的数据）
        getPageRequest(adapter);
    }

    //暂时应用此方法设置顶部
    private void setActtionBar(GridLayoutManager liner) {
        liner.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position <= 4) {
                    ivLocation.setBackgroundResource(R.drawable.home_nav_add);
                    tvLocation.setTextColor(ContextCompat.getColor(context, R.color.home_tvLoca));
                    ivIconLogction.setBackgroundResource(R.drawable.home_nav_jiantou_xia);
                    ivSearchGoods.setBackgroundResource(R.drawable.map_search_);
                    etSearchGoods.setTextColor(ContextCompat.getColor(context, R.color.home_et_search));
                    etSearchGoods.setHintTextColor(ContextCompat.getColor(context, R.color.home_et_search));
                    ivMessage.setBackgroundResource(R.drawable.home_nav_news_);
                    llSearchGoods.setBackground(ContextCompat.getDrawable(context, R.drawable.home_nav_search));
                } else {
                    ivLocation.setBackgroundResource(R.drawable.home_nav_add_hui);
                    tvLocation.setTextColor(ContextCompat.getColor(context, R.color.home_tvLoca_gray));
                    ivIconLogction.setBackgroundResource(R.drawable.home_nav_jt_xia);
                    ivSearchGoods.setBackgroundResource(R.drawable.map_search);
                    etSearchGoods.setTextColor(ContextCompat.getColor(context, R.color.home_tvLoca_gray));
                    etSearchGoods.setHintTextColor(ContextCompat.getColor(context, R.color.home_tvLoca_gray));
                    ivMessage.setBackgroundResource(R.drawable.home_nav_news_hui);
                    llSearchGoods.setBackground(ContextCompat.getDrawable(context, R.drawable.home_nav_search_gray));
                }
                return 1;
            }
        });
    }

    @Override
    public void onFailure(Object reasonObj) {

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
