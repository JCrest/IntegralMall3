package com.csii.integralmall.headUpload;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.csii.integralmall.R;
import com.csii.integralmall.base.BaseActivity;
import com.csii.integralmall.headUpload.view.ClipViewLayout;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

public class ClipImageActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "ClipImageActivity";
    private ClipViewLayout clipViewLayout1;
    private ClipViewLayout clipViewLayout2;
    private ImageView back;
    private TextView btnCancel;
    private TextView btnOk;
    //类别 1: qq 样式圆形头像, 2: weixin 样式圆角矩形头像
    private int type;

    @Override
    public void initView() {
        initTitleBar();
        type = getIntent().getIntExtra("type", 1);

        clipViewLayout1 = (ClipViewLayout) findViewById(R.id.clipViewLayout1);
        clipViewLayout2 = (ClipViewLayout) findViewById(R.id.clipViewLayout2);
        back = (ImageView) findViewById(R.id.iv_back);
        btnCancel = (TextView) findViewById(R.id.btn_cancel);
        btnOk = (TextView) findViewById(R.id.bt_ok);
        //设置点击事件监听器
        back.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        btnOk.setOnClickListener(this);

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_clip_image;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (type == 1) {
            clipViewLayout1.setVisibility(View.VISIBLE);
            clipViewLayout2.setVisibility(View.GONE);
            //设置图片资源
            clipViewLayout1.setImageSrc(getIntent().getData());
        } else {
            clipViewLayout2.setVisibility(View.VISIBLE);
            clipViewLayout1.setVisibility(View.GONE);
            //设置图片资源
            clipViewLayout2.setImageSrc(getIntent().getData());
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_cancel:
                finish();
                break;
            case R.id.bt_ok:
                generateUriAndReturn();
                break;
        }
    }

    private void generateUriAndReturn() {
        //调用返回剪切图
        Bitmap zoomedCropBitmap;
        if (type == 1) {
            zoomedCropBitmap = clipViewLayout1.clip();
        } else {
            zoomedCropBitmap = clipViewLayout2.clip();
        }
        if (zoomedCropBitmap == null) {
            Log.e("android", "zoomedCropBitmap == null");
            return;
        }
        Uri mSaveUri = Uri.fromFile(new File(getCacheDir(), "crop_image.jpg"));
        Log.e("mSaveUri", mSaveUri.toString());
        if (mSaveUri != null) {
            OutputStream outputStream = null;
            try {
                outputStream = getContentResolver().openOutputStream(mSaveUri);
                if (outputStream != null) {
                    zoomedCropBitmap.compress(Bitmap.CompressFormat.JPEG, 90, outputStream);
                }
            } catch (IOException ex) {
                Log.e("android", "Cannot open file: " + mSaveUri, ex);
            } finally {
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            Intent intent = new Intent();
            intent.setData(mSaveUri);
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}
