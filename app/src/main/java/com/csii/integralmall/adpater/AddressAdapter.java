package com.csii.integralmall.adpater;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.csii.integralmall.R;
import com.csii.integralmall.bean.AddressListInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddressAdapter extends BaseAdapter {

    private final Context context;
    private final List<AddressListInfo> addressListInfos;

    public AddressAdapter(Context context, List<AddressListInfo> addressListInfos) {
        this.context = context;
        this.addressListInfos = addressListInfos;
    }

    @Override
    public int getCount() {
        return addressListInfos == null ? 0 : addressListInfos.size();
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
            convertView = View.inflate(context, R.layout.item_address_list, null);
            viewHolder = new ViewHolder(convertView);


            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.tv_contact_person)
        TextView tvContactPerson;
        @BindView(R.id.tv_phone_num)
        TextView tvPhoneNum;
        @BindView(R.id.tv_address)
        TextView tvAddress;
        @BindView(R.id.default_add)
        CheckBox defaultAdd;
        @BindView(R.id.iv_edit)
        ImageView ivEdit;
        @BindView(R.id.rl_edit)
        RelativeLayout rlEdit;
        @BindView(R.id.iv_delete)
        ImageView ivDelete;
        @BindView(R.id.rl_delete)
        RelativeLayout rlDelete;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
