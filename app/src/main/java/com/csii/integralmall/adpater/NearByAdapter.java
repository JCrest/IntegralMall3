package com.csii.integralmall.adpater;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amap.api.services.core.PoiItem;
import com.csii.integralmall.R;

import java.util.List;

public class NearByAdapter extends BaseAdapter {
    private static final String TAG = NearByAdapter.class.getSimpleName();
    private final Context context;
    private final List<PoiItem> poiItemList;


    public NearByAdapter(Context context, List<PoiItem> poiItemList) {
        this.context = context;
        this.poiItemList = poiItemList;
        Log.e(TAG, "getItemCount: " + poiItemList.size());

    }


    public ItemClickListener mItemClickListener;

    public void setItemClickListener(ItemClickListener listener) {
        mItemClickListener = listener;
    }


    @Override
    public int getCount() {
        return poiItemList == null ? 0 : poiItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_showaddress, null);
            holder = new ViewHolder(convertView);
            holder.mItem = convertView.findViewById(R.id.rl_item);
            holder.mtitle = convertView.findViewById(R.id.tv_title);
            holder.mdesc = convertView.findViewById(R.id.tv_address);
            holder.mMarker = convertView.findViewById(R.id.iv_address_marker_def);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Log.e(TAG, "getView: " + parent.getChildCount());
        holder.mtitle.setText(poiItemList.get(position).getTitle());
        holder.mdesc.setText(poiItemList.get(position).getSnippet());

        holder.mItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mItemClickListener != null) {
                    mItemClickListener.onItemClick(view, position);

                }
            }
        });
        return convertView;
    }


    public interface ItemClickListener {
        public void onItemClick(View view, int pos);
    }

    public static class ViewHolder {
        TextView mtitle, mdesc;
        LinearLayout mItem;
        ImageView mMarker;


        public ViewHolder(View view) {
            mtitle = view.findViewById(R.id.tv_title);
            mdesc = view.findViewById(R.id.tv_address);
            mMarker = view.findViewWithTag(R.id.iv_address_marker_def);
            mItem = view.findViewById(R.id.rl_item);


        }
    }
}
