package com.csii.integralmall.activity.enjoyBigBrand;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.csii.integralmall.R;
import com.csii.integralmall.adpater.BigBrandAdapter;
import com.csii.integralmall.base.BaseActivity;
import com.csii.integralmall.bean.BrandSortBean;
import com.csii.integralmall.common.Api;
import com.csii.integralmall.okhttp.CommonOkHttpClient;
import com.csii.integralmall.okhttp.listener.DisposeDataHandle;
import com.csii.integralmall.okhttp.listener.DisposeDataListener;
import com.csii.integralmall.okhttp.request.CommonRequest;
import com.csii.integralmall.okhttp.request.RequestParams;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


@SuppressLint("Registered")
public class BigBrandActivity extends BaseActivity implements View.OnClickListener,
        DisposeDataListener, AdapterView.OnItemClickListener {
    private static final String TAG = BigBrandActivity.class.getSimpleName();

    @BindView(R.id.tv_title_center)
    TextView tvTitleCenter;
    @BindView(R.id.ib_return)
    ImageButton ibReturn;
    @BindView(R.id.cut_line)
    View cutLine;
    @BindView(R.id.lv_earn_integral)
    ListView mListView;

    private List<BrandSortBean.ResultBean.ResultlistBean> datas;
    //    上一个页面传过来的下标
    private int i;

    @Override
    public void initView() {
        initTitleBar();
        i = getIntent().getIntExtra("index", 0);

/*
        View headerView = LayoutInflater.from(this).inflate(R.layout.item_sur_sta_revel_header,
                null);
        mListView.addHeaderView(headerView);
*/

    }

    @Override
    protected void initListener() {
        ibReturn.setOnClickListener(this);
        mListView.setOnItemClickListener(this);
    }

    @Override
    protected void initData() {

//  暂时使用 这个接口
        getEnjoybigBrandRequest();
    }

    /**
     * 获取 享大牌头部分的数据（3个按钮）
     */
    private void getEnjoybigBrandRequest() {
        RequestParams params = new RequestParams();
        params.put("parentid", "1222");
        CommonOkHttpClient.get(CommonRequest.createGetRequest(Api.GETCATANDPRODUCTBYPARENTID, params),
                new DisposeDataHandle(this, BrandSortBean.class));
    }


    @Override
    public void onSuccess(Object responseObj) {


        BrandSortBean brandSortBean = (BrandSortBean) responseObj;

        datas = brandSortBean.getResult().getResultlist();

        BigBrandAdapter mAdapter = new BigBrandAdapter(this, datas.get(i).getTbItemList());
        mListView.setAdapter(mAdapter);
        tvTitleCenter.setText(datas.get(i).getTbItemCat().getName());
        Log.e(TAG, "onSuccess: " + datas.get(i).getTbItemCat().getName());

    }

    @Override
    public void onFailure(Object reasonObj) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_big_brand;
    }


    @OnClick(R.id.ib_return)
    public void onViewClicked() {
    }

    @Override
    public void onClick(View view) {
        finish();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        showToast(datas.get(i).getTbItemList().get(position).getProductId() + "");
    }
}
