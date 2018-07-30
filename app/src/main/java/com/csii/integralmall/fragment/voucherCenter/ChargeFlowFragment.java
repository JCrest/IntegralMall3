package com.csii.integralmall.fragment.voucherCenter;

import android.view.View;
import android.widget.EditText;
import android.widget.GridView;

import com.csii.integralmall.R;
import com.csii.integralmall.adpater.VocherCenterAdapter;
import com.csii.integralmall.base.BaseFragment;
import com.csii.integralmall.bean.VoucherCenterBean;
import com.csii.integralmall.common.Api;
import com.csii.integralmall.okhttp.CommonOkHttpClient;
import com.csii.integralmall.okhttp.listener.DisposeDataHandle;
import com.csii.integralmall.okhttp.listener.DisposeDataListener;
import com.csii.integralmall.okhttp.request.CommonRequest;
import com.csii.integralmall.okhttp.request.RequestParams;

public class ChargeFlowFragment extends BaseFragment implements DisposeDataListener {
    private EditText etVocherCenterPhone;
    private GridView gvVocherCenter;
    private VocherCenterAdapter vocherCenterAdapter;

    @Override
    protected View initView() {
        View view = View.inflate(context, R.layout.fragment_charge_calls_flow, null);
        etVocherCenterPhone = view.findViewById(R.id.et_vocher_center_phone);
        gvVocherCenter = view.findViewById(R.id.gv_vocher_center);
        gvVocherCenter.setFocusable(false);
        getChargeCallsRequest();
        return view;
    }

    private void getChargeCallsRequest() {
        RequestParams params = new RequestParams();
        params.put("selfid", "1235");
        CommonOkHttpClient.get(CommonRequest.createGetRequest(Api.GETPRODUCTSBYSELFID, params),
                new DisposeDataHandle(this, VoucherCenterBean.class));
    }

    @Override
    public void onSuccess(Object responseObj) {
        final VoucherCenterBean chargeCallsBean = (VoucherCenterBean) responseObj;
        vocherCenterAdapter = new VocherCenterAdapter(getActivity(), chargeCallsBean.getResult().getResultlist());
        gvVocherCenter.setAdapter(vocherCenterAdapter);
        vocherCenterAdapter.setOnMitemsClickListener(new VocherCenterAdapter.OnMitemsClickListener() {
            @Override
            public void OnMitemsClickListener(int position) {
                showToast("恭喜您,成功兑换" + chargeCallsBean.getResult().getResultlist().get(position).getProductName());
            }
        });
    }

    @Override
    public void onFailure(Object reasonObj) {

    }
}
