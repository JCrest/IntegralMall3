package com.csii.integralmall.adpater;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.csii.integralmall.R;

public class MyPagerContainerAdapter extends PagerAdapter {


    private final Context context;
    private final int[] covers;




    public MyPagerContainerAdapter(Context context, int[] covers) {
        this.context = context;
        this.covers = covers;

    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_view_pager_avtivity, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.image_cover);
//        ImageView imageView = new ImageView(context);
        imageView.setBackgroundResource(covers[position % covers.length]);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "" + position, Toast.LENGTH_SHORT).show();
            }

        });

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == object);
    }




}
