package com.csii.integralmall.activity.shippingAddress;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.csii.integralmall.R;
import com.csii.integralmall.adpater.AddressAdapter;
import com.csii.integralmall.base.BaseActivity;
import com.csii.integralmall.bean.AddressListInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddressListActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = AddressListActivity.class.getSimpleName();
    
    @BindView(R.id.tv_title_center)
    TextView tvTitleCenter;
    @BindView(R.id.ib_return)
    ImageButton ibReturn;
    @BindView(R.id.cut_line)
    View cutLine;
    @BindView(R.id.address_list)
    ListView addressList;


    private AddressAdapter addressAdapter;
    private List<AddressListInfo> addressListInfos;


    @Override
    public void initView() {
        initTitleBar();
        Log.e(TAG, "initView: 咋直接跑这来了？");
        ButterKnife.bind(this);

        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View footerView = inflater.inflate(R.layout.list_button, null);
        TextView addButton = footerView.findViewById(R.id.tv_add_address);
        addButton.setOnClickListener(this);
        addressList.addFooterView(footerView);
    }

    @Override
    protected void initListener() {

        tvTitleCenter.setText("收货地址");

    }

    @Override
    protected void initData() {
        addressListInfos = new ArrayList<>();
        addressListInfos.add(new AddressListInfo(1, 81,
                "张先生", "男", "18500233216",
                "北京市西一旗中关村", "软件园南路11号2栋1单元211室", "1"));
        addressListInfos.add(new AddressListInfo(2, 82,
                "王汪汪", "男", "17612345678",
                "北京市西二旗南关村", "软件园南路11号2栋1单元212室", "0"));
        addressListInfos.add(new AddressListInfo(3, 85,
                "李丽丽", "女", "17814525478",
                "北京市西三旗北关村", "软件园南路11号2栋1单元213室", "0"));
        addressListInfos.add(new AddressListInfo(1, 81,
                "张先生", "男", "18500233216",
                "北京市西一旗中关村", "软件园南路11号2栋1单元211室", "1"));
        addressListInfos.add(new AddressListInfo(2, 82,
                "王汪汪", "男", "17612345678",
                "北京市西二旗南关村", "软件园南路11号2栋1单元212室", "0"));
        addressListInfos.add(new AddressListInfo(3, 85,
                "李丽丽", "女", "17814525478",
                "北京市西三旗北关村", "软件园南路11号2栋1单元213室", "0"));
        addressAdapter = new AddressAdapter(this, addressListInfos);
        addressList.setAdapter(addressAdapter);


    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_address;
    }


    @OnClick(R.id.ib_return)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void onClick(View view) {
        showToast("hahahah");
        Intent intent = new Intent(this, AddAddressActivity.class);
        startActivity(intent);

    }
}
