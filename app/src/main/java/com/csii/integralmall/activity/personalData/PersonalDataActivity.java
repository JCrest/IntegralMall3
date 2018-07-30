package com.csii.integralmall.activity.personalData;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.csii.integralmall.BuildConfig;
import com.csii.integralmall.R;
import com.csii.integralmall.activity.personalData.activity_personal_data.changeNumber;
import com.csii.integralmall.activity.personalData.activity_personal_data.changePassword;
import com.csii.integralmall.activity.personalData.activity_personal_data.settingNickname;
import com.csii.integralmall.base.BaseActivity;
import com.csii.integralmall.bean.User;
import com.csii.integralmall.common.Api;
import com.csii.integralmall.common.MyApplication;
import com.csii.integralmall.common.SharedPreferencesHelper;
import com.csii.integralmall.headUpload.ClipImageActivity;
import com.csii.integralmall.headUpload.util.FileUtil;
import com.csii.integralmall.okhttp.CommonOkHttpClient;
import com.csii.integralmall.okhttp.listener.DisposeDataHandle;
import com.csii.integralmall.okhttp.listener.DisposeDataListener;
import com.csii.integralmall.okhttp.request.CommonRequest;
import com.csii.integralmall.okhttp.request.RequestParams;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PersonalDataActivity extends BaseActivity implements DisposeDataListener {

    private static final String TAG = PersonalDataActivity.class.getSimpleName();

    @BindView(R.id.tv_title_center)
    TextView tvTitleCenter;
    @BindView(R.id.ib_return)
    ImageButton ibReturn;
    @BindView(R.id.tv_title_right)
    TextView tvTitleRight;
    @BindView(R.id.iv_iconHead)
    ImageView ivIconHead;
    @BindView(R.id.ll_iconHead)
    LinearLayout llIconHead;
    @BindView(R.id.tv_nickName)
    TextView tvNickName;
    @BindView(R.id.ll_passwd)
    LinearLayout llPasswd;
    @BindView(R.id.ll_nickName)
    LinearLayout llNickName;
    @BindView(R.id.ll_changeNumber)
    LinearLayout llChangeNumber;
    @BindView(R.id.tv_isWechat)
    TextView tvIsWechat;
    @BindView(R.id.ll_associateWechat)
    LinearLayout llAssociateWechat;
    @BindView(R.id.tv_isWebo)
    TextView tvIsWebo;
    @BindView(R.id.ll_associateWebo)
    LinearLayout llAssociateWebo;
    Intent intent;


    //请求相机
    private static final int REQUEST_CAPTURE = 100;
    //请求相册
    private static final int REQUEST_PICK = 101;
    //请求截图
    private static final int REQUEST_CROP_PHOTO = 102;
    //请求访问外部存储
    private static final int READ_EXTERNAL_STORAGE_REQUEST_CODE = 103;
    //请求写入外部存储
    private static final int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 104;
    //调用照相机返回图片文件
    private File tempFile;
    // 1: qq 样式的圆形头像, 2: weixin 样式的圆角头像
    private int type;

    private static final int REQUEST_NICK_NAME = 105;

    private SharedPreferencesHelper sharedPreferencesHelper;


    @Override
    public void initView() {
        initTitleBar();
        ButterKnife.bind(this);
        sharedPreferencesHelper = new SharedPreferencesHelper(this, "token");
        Log.e(TAG, "打印了吗？");

    }


    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        tvTitleCenter.setText("个人资料");

        String u = (String) sharedPreferencesHelper.getSharedPreference("user", "");
        User user = new Gson().fromJson(u, User.class);//把JSON字符串转为对象
        tvNickName.setText(user.getResult().getUsername());
        initHead();
    }

    /**
     * 初始化加载头像
     * 先判断路径是否存在 然后判断图片是否存在
     * 有则加载没有则显示默认
     */
    private void initHead() {
        Uri uri = Uri.fromFile(new File(getCacheDir(), "crop_image.jpg"));
        if (uri != null) {
            String cropImagePath = FileUtil.getRealFilePathFromUri(this, uri);
            Bitmap bitmap = BitmapFactory.decodeFile(cropImagePath);
            if (bitmap != null) {
                ivIconHead.setImageBitmap(bitmap);
            }
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_personal_data;
    }


    @OnClick({R.id.ib_return, R.id.tv_title_right, R.id.ll_iconHead, R.id.ll_passwd,
            R.id.ll_nickName, R.id.ll_changeNumber, R.id.ll_associateWechat, R.id.ll_associateWebo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ib_return:
                finish();
                break;
            case R.id.tv_title_right:
                break;
            case R.id.ll_iconHead:
//                showToast("开始上传头像了！");
                type = 1;
                uploadHeadImage();
                break;
            case R.id.ll_passwd:
                intent = new Intent(this, changePassword.class);
                startActivity(intent);
                break;
            case R.id.ll_nickName:
                String nickName = (String) tvNickName.getText();
                intent = new Intent(this, settingNickname.class);
                intent.putExtra("nickName", nickName);
                startActivityForResult(intent, REQUEST_NICK_NAME);
//                showToast("昵称");
                break;
            case R.id.ll_changeNumber:
                intent = new Intent(this, changeNumber.class);
                startActivity(intent);
                break;
            case R.id.ll_associateWechat:
                break;
            case R.id.ll_associateWebo:
                break;
        }
    }

    private void uploadHeadImage() {
        //加载 PopupWindow 布局
        View view = LayoutInflater.from(this).inflate(R.layout.layout_popwin_head, null);
        //初始化控件
        TextView btnCarema = (TextView) view.findViewById(R.id.btn_camera);
        TextView btnPhoto = (TextView) view.findViewById(R.id.btn_photo);
        TextView btnCancel = (TextView) view.findViewById(R.id.btn_cancel);
        //设置 PopupWindow 的各种属性
        final PopupWindow popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        已经过时的方法
//        popupWindow.setBackgroundDrawable(getResources().getDrawable(android.R.color.transparent));
        popupWindow.setBackgroundDrawable(ContextCompat.getDrawable(this, R.color.transparent));
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        View parent = LayoutInflater.from(this).inflate(R.layout.activity_personal_data, null);
        popupWindow.showAtLocation(parent, Gravity.BOTTOM, 0, 0);
        final WindowManager.LayoutParams params = getWindow().getAttributes();
        params.alpha = 0.5f;
        getWindow().setAttributes(params);
        //监听 PopupWindow 的消失
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                params.alpha = 1.0f;
                getWindow().setAttributes(params);
            }
        });
        btnCarema.setOnClickListener(new View.OnClickListener() {
            /**
             *  相机按钮点击事件
             * @param view
             */
            @Override
            public void onClick(View view) {
                //权限判断（写入外部存储）
                if (ContextCompat.checkSelfPermission(PersonalDataActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    //申请 WRITE_EXTERNAL_STORAGE 权限
                    ActivityCompat.requestPermissions(PersonalDataActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            WRITE_EXTERNAL_STORAGE_REQUEST_CODE);

                } else {
                    //调起系统相机拍照
                    gotoCamera();
                }
                popupWindow.dismiss();
            }
        });

        btnPhoto.setOnClickListener(new View.OnClickListener() {
            /**
             *  相册按钮点击事件
             *  先判断是否有 READ_EXTERNAL_STORAGE 权限
             *  有则进行下一步，没有则申请 （申请成功进行结果监听执行下一步操作  onRequestPermissionsResult）
             * @param view
             */
            @Override
            public void onClick(View view) {
                //判断权限
                if (ContextCompat.checkSelfPermission(PersonalDataActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    //申请权限
                    ActivityCompat.requestPermissions(PersonalDataActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            READ_EXTERNAL_STORAGE_REQUEST_CODE);

                } else {
                    //跳转到系统相册 选择图片
                    gotoPhoto();
                }
                popupWindow.dismiss();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });
    }

    /**
     * 权限申请结果
     * 申请成功进行下一步
     *
     * @param requestCode  申请码
     * @param permissions  权限
     * @param grantResults 申请结果
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == WRITE_EXTERNAL_STORAGE_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //权限申请通过
                gotoCamera();
            }
        } else if (requestCode == READ_EXTERNAL_STORAGE_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //权限申请通过
                gotoPhoto();
            }
        }
    }

    /**
     * 调起系统图库 选择需要的图片
     */
    private void gotoPhoto() {
        Log.d(TAG, "gotoPhoto: 调起系统的图库");
        intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(Intent.createChooser(intent, "请选择图片"), REQUEST_PICK);
    }

    private void gotoCamera() {
        Log.d(TAG, "gotoCamera: 调起系统相机进行拍照");
        //创建文件保存路径  及文件资源名称  sdcard/image/当前时间点.jpg
        tempFile = new File(FileUtil.checkDirPath(Environment.getExternalStorageDirectory().getPath() + "/image/"), System.currentTimeMillis() + ".jpg");
        //跳转到调用系统相机
        intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //设置7.0中共享文件，分享路径定义在xml/file_paths.xml
            intent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(PersonalDataActivity.this, BuildConfig.APPLICATION_ID + ".fileProvider", tempFile);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri);
        } else {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
        }
        startActivityForResult(intent, REQUEST_CAPTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        switch (requestCode) {
            case REQUEST_CAPTURE:                    //调用系统相机返回
                if (resultCode == RESULT_OK) {        //如果返回码正确则启动切图页面
                    gotoClipActivity(Uri.fromFile(tempFile));
                }
                break;
            case REQUEST_PICK:                        //调用系统图库返回
                if (resultCode == RESULT_OK) {        //如果返回码正确则启动切图页面
                    Uri uri = intent.getData();
                    gotoClipActivity(uri);
                }
                break;
            case REQUEST_CROP_PHOTO:                        //图片裁剪结束返回
                if (resultCode == RESULT_OK) {        //如果返回码正确则启动切图页面
                    final Uri uri = intent.getData();
                    if (uri == null) {
                        return;
                    }
                    String cropImagePath = FileUtil.getRealFilePathFromUri(this, uri);
                    Bitmap bitmap = BitmapFactory.decodeFile(cropImagePath);
                    ivIconHead.setImageBitmap(bitmap);
                    postRequest(bitmap);
                }
                break;
            case REQUEST_NICK_NAME:
                if (resultCode == RESULT_OK) {
                    tvNickName.setText(intent.getStringExtra("userName"));

                }
                break;
        }


    }

    private void postRequest(Bitmap bitmap) {
        RequestParams params = new RequestParams();
        params.put("filename", "123456789.jpg");
        params.put("token", MyApplication.getToken());
        params.put("imgData", sentImage(bitmap));

        CommonOkHttpClient.post(CommonRequest.createPostRequest(Api.IMGAEUPLOAD, params),
                new DisposeDataHandle(this));

    }

    @Override
    public void onSuccess(Object responseObj) {
        Log.e(TAG, "onSuccess: " + responseObj);

    }

    @Override
    public void onFailure(Object reasonObj) {
        Log.e(TAG, "onFailure: " + reasonObj);

    }

    private String sentImage(Bitmap bitmap) {
        // 先创建 字节数组流: ByteArrayOutputStream: 可以捕获内存缓冲区的数据,转换成字节数组。
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        // 写入一个压缩版的位图到指定的输出流 (这里是字节数组流)
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        // 把 字节数组流作为字节数组返回
        byte[] bytes = stream.toByteArray();
        // 用base64 编码方式把一个字节数组编码后以一个新的字节数组返回
        return Base64.encodeToString(bytes, Base64.DEFAULT);
    }

    /**
     * 打开图片裁剪界面 去裁切图片
     *
     * @param uri
     */
    private void gotoClipActivity(Uri uri) {
        if (uri == null) {
            return;
        }
        intent = new Intent();
        intent.setClass(this, ClipImageActivity.class);
        intent.putExtra("type", type);
        intent.setData(uri);
        startActivityForResult(intent, REQUEST_CROP_PHOTO);


    }

}
