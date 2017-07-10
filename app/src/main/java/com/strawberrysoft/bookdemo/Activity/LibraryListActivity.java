package com.strawberrysoft.bookdemo.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.strawberrysoft.bookdemo.Adapter.LibraryListAdapter;
import com.strawberrysoft.bookdemo.R;
import java.util.List;

public class LibraryListActivity extends AppCompatActivity implements AMapLocationListener, PoiSearch.OnPoiSearchListener {
    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;
    private TextView tv_city;
    private RecyclerView rv_list;
    private LatLng myPlace;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library_lsit);
        initView();
        initAmap();
        //启动定位
        mLocationClient.startLocation();

    }

    private void initView() {
        tv_city = (TextView) findViewById(R.id.tv_library_city);
        rv_list = (RecyclerView) findViewById(R.id.rv_library_list);
        rv_list.setLayoutManager(new LinearLayoutManager(this));
        findViewById(R.id.iv_librarylist_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    //    AMapUtils.calculateLineDistance(LatLng startLatlng, LatLng endLatlng) 来计算两点距离，单位：米。
    private void initLocationSearch(String keyWord,String cityCode) {
        PoiSearch.Query query = new PoiSearch.Query(keyWord, "", cityCode);
        query.setPageSize(20);// 设置每页最多返回多少条poiitem
        query.setPageNum(0);//设置查询页码
        PoiSearch poiSearch = new PoiSearch(this,query);//初始化poiSearch对象
        poiSearch.setOnPoiSearchListener(this);//设置回调数据的监听器
        poiSearch.searchPOIAsyn();//开始搜索
    }

    private void initAmap() {
        //初始化定位
        mLocationClient = new AMapLocationClient(getApplicationContext());
        //设置定位回调监听
        mLocationClient.setLocationListener(this);
        //初始化AMapLocationClientOption对象
        mLocationOption = new AMapLocationClientOption();
        //设置定位模式为AMapLocationMode.Battery_Saving，低功耗模式。
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Battery_Saving);
        //获取一次定位结果：
        //该方法默认为false。
        mLocationOption.setOnceLocation(true);
        //获取最近3s内精度最高的一次定位结果：
        //设置setOnceLocationLatest(boolean b)接口为true，启动定位时SDK会返回最近3s内精度最高的一次定位结果。如果设置其为true，setOnceLocation(boolean b)接口也会被设置为true，反之不会，默认为false。
        mLocationOption.setOnceLocationLatest(true);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
    }

    //监听结果
    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation != null) {
            if (aMapLocation.getErrorCode() == 0) {
                myPlace = new LatLng(aMapLocation.getLatitude(),aMapLocation.getLongitude());
                String province = aMapLocation.getProvince();//省信息
                String city = aMapLocation.getCity();//城市信息
                String part = aMapLocation.getDistrict();//城区信息
                String aoi = aMapLocation.getAoiName();//获取当前定位点的AOI信息
                String cityCode = aMapLocation.getCityCode();//城市编码
                String adCode = aMapLocation.getAdCode();//地区编码
                tv_city.setText(city);
                System.out.println(province+","+city+","+part+","+aoi+cityCode);
                initLocationSearch("图书馆",adCode);
            }else {
                //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                Log.e("AmapError","location Error, ErrCode:" + aMapLocation.getErrorCode() + ", errInfo:" + aMapLocation.getErrorInfo());
            }

            mLocationClient.stopLocation();//停止定位后，本地定位服务并不会被销毁
        }
    }

    @Override
    public void onPoiSearched(PoiResult poiResult, int i) {
        List<PoiItem> pois = poiResult.getPois();
        rv_list.setAdapter(new LibraryListAdapter(this,pois,myPlace));
    }

    @Override
    public void onPoiItemSearched(PoiItem poiItem, int i) {

    }
}
