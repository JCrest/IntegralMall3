package com.csii.integralmall.activity.enjoyBigBrand;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.csii.integralmall.R;
import com.csii.integralmall.adpater.EnjoyBBrandAdapter;
import com.csii.integralmall.base.BaseActivity;
import com.csii.integralmall.bean.BrandSortBean;
import com.csii.integralmall.bean.EnjoyHeadBean;
import com.csii.integralmall.common.Api;
import com.csii.integralmall.okhttp.CommonOkHttpClient;
import com.csii.integralmall.okhttp.listener.DisposeDataHandle;
import com.csii.integralmall.okhttp.listener.DisposeDataListener;
import com.csii.integralmall.okhttp.request.CommonRequest;
import com.csii.integralmall.okhttp.request.RequestParams;

public class EnhoyBBrandActivity extends BaseActivity implements View.OnClickListener,
        DisposeDataListener {

    private TextView tvTitleCenter;
    private ImageButton ibReturn;
    private RecyclerView rvEnjoyBigBrand;
    private EnjoyBBrandAdapter enjoyBBrandAdapter;
    private LinearLayoutManager layoutManager;

    @Override
    public void initView() {
        initTitleBar();
        tvTitleCenter = (TextView) findViewById(R.id.tv_title_center);
        ibReturn = (ImageButton) findViewById(R.id.ib_return);
        rvEnjoyBigBrand = (RecyclerView) findViewById(R.id.rv_enjoy_big_brand);
    }

    @Override
    protected void initListener() {
        ibReturn.setOnClickListener(this);

    }

    @Override
    protected void initData() {
        tvTitleCenter.setText("享大牌");
        getEnjoybigBrandRequest();


    }

    /**
     * 获取 享大牌头部分的数据（3个按钮）
     */
    private void getEnjoybigBrandRequest() {
        RequestParams params = new RequestParams();
        params.put("parentid", "1222");
        CommonOkHttpClient.get(CommonRequest.createGetRequest(Api.GETCATBYPARENTID, params),
                new DisposeDataHandle(this, EnjoyHeadBean.class));
    }

    @Override
    public void onSuccess(Object responseObj) {
        Log.e("打印以下这个数据", "onSuccess: " + responseObj);
        enjoyBBrandAdapter = new EnjoyBBrandAdapter(this, responseObj);
        layoutManager = new LinearLayoutManager(this);
        rvEnjoyBigBrand.setLayoutManager(layoutManager);
        rvEnjoyBigBrand.setAdapter(enjoyBBrandAdapter);
        getBrandSortRequest(enjoyBBrandAdapter);
    }


    /**
     * 第二次联网请求品牌产品的具体内容
     *
     * @param enjoyBBrandAdapter
     */
    private void getBrandSortRequest(final EnjoyBBrandAdapter enjoyBBrandAdapter) {
        RequestParams params = new RequestParams();
        params.put("parentid", "1222");
        CommonOkHttpClient.get(CommonRequest.createGetRequest(Api.GETCATANDPRODUCTBYPARENTID, params),
                new DisposeDataHandle(new DisposeDataListener() {
                    @Override
                    public void onSuccess(Object responseObj) {
                        enjoyBBrandAdapter.setBrandSort(responseObj);
                        enjoyBBrandAdapter.setOnTabsClickListener(new EnjoyBBrandAdapter.OnTabsClickListener() {
                            @Override
                            public void onTabsClick(int position) {
                                enjoyBBrandAdapter.notifyDataSetChanged();
                                rvEnjoyBigBrand.smoothScrollToPosition(position);
                            }
                        });
                    }

                    @Override
                    public void onFailure(Object reasonObj) {

                    }
                }, BrandSortBean.class));

    }

    @Override
    public void onFailure(Object reasonObj) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_enjoy_b_brand;
    }

    @Override
    public void onClick(View view) {
        finish();
    }
}
