package com.csii.integralmall.activity.shippingAddress;

import android.Manifest;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.maps.model.animation.Animation;
import com.amap.api.maps.model.animation.TranslateAnimation;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeAddress;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.amap.api.services.geocoder.RegeocodeRoad;
import com.amap.api.services.geocoder.StreetNumber;
import com.csii.integralmall.R;
import com.csii.integralmall.activity.shippingAddress.permissions.PermissionsActivity;
import com.csii.integralmall.activity.shippingAddress.permissions.PermissionsChecker;
import com.csii.integralmall.adpater.NearByAdapter;
import com.csii.integralmall.fragment.shippingAdress.mCityPickerFragment;

import java.util.List;

public class ShowMapActivity extends AppCompatActivity implements LocationSource, AMapLocationListener,
        AMap.OnCameraChangeListener, View.OnClickListener, GeocodeSearch.OnGeocodeSearchListener {

    private static final String TAG = ShowMapActivity.class.getSimpleName();
    //容器对象
    private MapView defaltMapView;
    //AMap是地图对象
    private AMap aMap;
    //声明mLocationOption对象，定位参数
    public AMapLocationClientOption mLocationOption = null;
    //声明mListener对象，定位监听器
    private OnLocationChangedListener mListener = null;
    //标识，用于判断是否只显示一次定位信息和用户重新定位
    private boolean isFirstLoc = true;

    //用户位置的显示
    private Marker marker = null;

    private LinearLayout llAdministrativeCity;
    private TextView tvTargetCities;

    //定位需要的动态权限
    private static final int REQUEST_CODE = 0;
    private String[] strPermissions = {
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_NETWORK_STATE,
            Manifest.permission.ACCESS_WIFI_STATE,
            Manifest.permission.CHANGE_WIFI_STATE,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private PermissionsChecker checker; //权限检测器


    private LatLng point;
    private List<PoiItem> pois;
    private NearByAdapter nearByAdapter;
    private ListView nearByRecycler;

    private AMapLocationClient mlocationClient;


    private TextView tv_title_header, tv_address_header;
    private LinearLayout ll_item_header;

    private mCityPickerFragment mCityPickerFragment;

    private String currentCity;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_map);
        initTitleBar();
        tvTargetCities = (TextView) findViewById(R.id.tv_target_cities);
        llAdministrativeCity = (LinearLayout) findViewById(R.id.ll_administrative_city);
        nearByRecycler = (ListView) findViewById(R.id.nearby_recycle);
        //获取地图控件引用
        defaltMapView = findViewById(R.id.map_defalt);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，实现地图生命周期管理
        defaltMapView.onCreate(savedInstanceState);
        initMap();

        checker = new PermissionsChecker(this);//检测是否已经获取权限

        initLocationPermission();//初始化定位权限

        llAdministrativeCity.setOnClickListener(this);

        View headerView = LayoutInflater.from(this).inflate(R.layout.item_near_header,
                null);
        tv_title_header = headerView.findViewById(R.id.tv_title_header);
        tv_address_header = headerView.findViewById(R.id.tv_address_header);
        ll_item_header = headerView.findViewById(R.id.ll_item_header);
        nearByRecycler.addHeaderView(headerView);

    }

    private void initLocationPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checker.lacksPermissions(strPermissions)) {
                permissionActivity();
            }
        }
    }

    private void permissionActivity() {
        PermissionsActivity.startActivityForResult(this, REQUEST_CODE, strPermissions);
    }

    private void initMap() {
        if (aMap == null) {
            aMap = defaltMapView.getMap();
        }
//        aMap.moveCamera(CameraUpdateFactory.zoomTo(17));
        initLocation();

    }

    private void initLocation() {
        if (aMap != null) {
            MyLocationStyle locationStyle = new MyLocationStyle();
            locationStyle.myLocationIcon(BitmapDescriptorFactory.fromResource(R.drawable.map_add_muself3));
            locationStyle.strokeColor(Color.argb(0, 0, 0, 0));// 自定义精度范围的圆形边框颜色
            locationStyle.radiusFillColor(Color.argb(0, 0, 0, 0));//圆圈的颜色,设为透明的时候就可以去掉园区区域了

            aMap.setMyLocationStyle(locationStyle);
            aMap.setLocationSource(this);
            aMap.getUiSettings().setMyLocationButtonEnabled(true);
            aMap.setMyLocationEnabled(true);
            aMap.setOnCameraChangeListener(this);

            Log.e(TAG, "initLocation: 地图缩放级别" + aMap.getCameraPosition().zoom);


        }
    }


    public void initTitleBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            this.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

    }


    public void startJumpAnimation() {
        if (marker != null) {
            //根据屏幕距离计算需要移动的目标点
            final LatLng latLng = marker.getPosition();
            Point point = aMap.getProjection().toScreenLocation(latLng);
            //dpValue在屏幕上跳动的高度
            point.y -= dip2px(this, 20);
            LatLng target = aMap.getProjection()
                    .fromScreenLocation(point);
            //使用TranslateAnimation,填写一个需要移动的目标点
            Animation animation = new TranslateAnimation(target);
            animation.setInterpolator(new Interpolator() {
                @Override
                public float getInterpolation(float input) {
                    // 模拟重加速度的interpolator
                    if (input <= 0.5) {
                        return (float) (0.5f - 2 * (0.5 - input) * (0.5 - input));
                    } else {
                        return (float) (0.5f - Math.sqrt((input - 0.5f) * (1.5f - input)));
                    }
                }
            });
            //整个移动所需要的时间
            animation.setDuration(600);
            //设置动画
            marker.setAnimation(animation);
            //开始动画
            marker.startAnimation();
        } else {
            Log.e("amap", "marker is null");
        }
    }

    //dip和px转换
    private static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {

                marker = aMap.addMarker(new MarkerOptions().draggable(true)
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.map_add_location3)));
                marker.setPositionByPixels((int) (defaltMapView.getWidth() / 2),
                        (int) (defaltMapView.getHeight() / 2));
                marker.showInfoWindow();

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        defaltMapView.onDestroy();
/*        mLocationClient.stopLocation();//停止定位
        mLocationClient.onDestroy();//销毁定位客户端。*/
        Log.e(TAG, "onDestroy: 没有被销毁吧？？");
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，实现地图生命周期管理
        defaltMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，实现地图生命周期管理
        defaltMapView.onPause();
        Log.e(TAG, "onPause: 看看停了没有！");

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，实现地图生命周期管理
        defaltMapView.onSaveInstanceState(outState);
    }

    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {
        mListener = onLocationChangedListener;
        if (mlocationClient == null) {
            mlocationClient = new AMapLocationClient(this.getApplicationContext());
            mLocationOption = new AMapLocationClientOption();
            mlocationClient.setLocationListener(this);
            mLocationOption.setOnceLocation(true); //只定位一次
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            mlocationClient.setLocationOption(mLocationOption);
            mlocationClient.startLocation();


        }

    }

    @Override
    public void deactivate() {
        mListener = null;


        if (mlocationClient != null) {
            mlocationClient.stopLocation();
            mlocationClient.onDestroy();
        }
        mlocationClient = null;
        mLocationOption = null;
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation != null && mListener != null) {
            if (aMapLocation.getErrorCode() == 0) {
                Double weidu = aMapLocation.getLatitude();//获取纬度
                Double jingdu = aMapLocation.getLongitude();//获取经度
                if (isFirstLoc) {
                    //将地图移动到定位点
                    aMap.moveCamera(CameraUpdateFactory.changeLatLng(new LatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude())));
                    //设置缩放级别
                    aMap.moveCamera(CameraUpdateFactory.zoomTo(17));
                    //点击定位按钮 能够将地图的中心移动到定位点
                    mListener.onLocationChanged(aMapLocation);
                    tvTargetCities.setText(aMapLocation.getCity());
                    currentCity = aMapLocation.getCity();
                    isFirstLoc = false;
                }
                regeocodeSearch(weidu, jingdu, 2000);
            } else {
                String errorText = "faild to located" + aMapLocation.getErrorCode() + ":" + aMapLocation.getErrorInfo();
                Log.d("ceshi", errorText);
            }
        }

    }

    private void regeocodeSearch(Double weidu, Double jingdu, final int distance) {
        LatLonPoint point = new LatLonPoint(weidu, jingdu);
        GeocodeSearch geocodeSearch = new GeocodeSearch(this);
        RegeocodeQuery regeocodeQuery = new RegeocodeQuery(point, distance, geocodeSearch.AMAP);
        geocodeSearch.getFromLocationAsyn(regeocodeQuery);
        Log.e(TAG, "onRegeocodeSearched1: 为啥不进来呢？");
        geocodeSearch.setOnGeocodeSearchListener(this);
        Log.e(TAG, "onRegeocodeSearched3: 为啥不进来呢？");

    }

    @Override
    public void onCameraChange(CameraPosition cameraPosition) {
        //发生变化时获取到经纬度传递逆地理编码获取周边数据
        regeocodeSearch(cameraPosition.target.latitude, cameraPosition.target.longitude, 2000);
        point = new LatLng(cameraPosition.target.latitude, cameraPosition.target.longitude);
//        marker.remove();//将上一次描绘的mark清除
    }

    @Override
    public void onCameraChangeFinish(CameraPosition cameraPosition) {
        startJumpAnimation();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_administrative_city:
                Toast.makeText(ShowMapActivity.this, "可以改变城市", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, DialogActivity.class);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(ShowMapActivity.this).toBundle());
                break;
        }
    }

    @Override
    public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int rCode) {
        String preAdd = "";//地址前缀

        if (1000 == rCode) {
            final RegeocodeAddress address = regeocodeResult.getRegeocodeAddress();
            StringBuffer stringBuffer = new StringBuffer();
            String area = address.getProvince();//省或者直辖市
            String loc = address.getCity();//地级市或者直辖市
            String subLoc = address.getDistrict();//区或县或县级市
            String ts = address.getTownship();//乡镇
            String thf = null;//道路
            List<RegeocodeRoad> regeocodeRoads = address.getRoads();//道路列表

            if (regeocodeRoads != null && regeocodeRoads.size() > 0) {
                RegeocodeRoad regeocodeRoad = regeocodeRoads.get(0);
                if (regeocodeRoad != null) {
                    thf = regeocodeRoad.getName();
                }
            }
            String subthf = null;//门牌号
            StreetNumber streetNumber = address.getStreetNumber();
            if (streetNumber != null) {
                subthf = streetNumber.getNumber();
            }
            String fn = address.getBuilding();//标志性建筑,当道路为null时显示
            if (area != null) {
                stringBuffer.append(area);
                preAdd += area;
            }
            if (loc != null && !area.equals(loc)) {
                stringBuffer.append(loc);
                preAdd += loc;
            }
            if (subLoc != null) {
                stringBuffer.append(subLoc);
                preAdd += subLoc;
            }
            if (ts != null)
                stringBuffer.append(ts);
            if (thf != null)
                stringBuffer.append(thf);
            if (subthf != null)
                stringBuffer.append(subthf);
            if ((thf == null && subthf == null) && fn != null && !subLoc.equals(fn))
                stringBuffer.append(fn + "附近");
            pois = address.getPois();//获取周围兴趣点
            nearByAdapter = new NearByAdapter(ShowMapActivity.this, pois);
//                    nearByRecycler.setItemAnimator(new DefaultItemAnimator());
            nearByRecycler.setAdapter(nearByAdapter);

            tv_title_header.setTextColor(ContextCompat.getColor(ShowMapActivity.this, R.color.F5562C));
            tv_title_header.setText(pois.get(0).getTitle());
            tv_address_header.setText(pois.get(0).getSnippet());
            ll_item_header.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(ShowMapActivity.this, "postion:header地址："
                            + pois.get(0).getTitle(), Toast.LENGTH_SHORT).show();
                }
            });
            Log.e(TAG, "onRegeocodeSearched2: 为啥不进来呢？");

            nearByAdapter.setItemClickListener(new NearByAdapter.ItemClickListener() {
                @Override
                public void onItemClick(View view, int pos) {
                    Toast.makeText(ShowMapActivity.this, "postion:" + pos + "地址："
                            + pois.get(pos).getTitle(), Toast.LENGTH_SHORT).show();

                }

            });

        }
    }

    @Override
    public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {

    }
}
