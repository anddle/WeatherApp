package com.anddle.weatherapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView mWeatherMoreInfoListView;
    private List<WeatherMoreInfo> mWeatherMoreInfoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("TEST", "Weather app launched");

        mWeatherMoreInfoListView = (ListView) findViewById(R.id.weather_more_info_list);
        mWeatherMoreInfoList = new ArrayList<>();
        WeatherMoreInfoAdapter adapter = new WeatherMoreInfoAdapter(MainActivity.this, R.layout.weather_more_info_item_layout, mWeatherMoreInfoList);
        mWeatherMoreInfoListView.setAdapter(adapter);

        WeatherMoreInfo data1 = new WeatherMoreInfo();
        data1.typeResId = R.mipmap.ic_wind_level;
        data1.description = "风力";
        data1.value = "3级";
        mWeatherMoreInfoList.add(data1);

        WeatherMoreInfo data2 = new WeatherMoreInfo();
        data2.typeResId = R.mipmap.ic_wind_direction;
        data2.description = "风向";
        data2.value = "东南";
        mWeatherMoreInfoList.add(data2);

        WeatherMoreInfo data3 = new WeatherMoreInfo();
        data3.typeResId = R.mipmap.ic_humidity_level;
        data3.description = "湿度";
        data3.value = "60%";
        mWeatherMoreInfoList.add(data3);

        WeatherMoreInfo data4 = new WeatherMoreInfo();
        data4.typeResId = R.mipmap.ic_air_quality;
        data4.description = "空气质量";
        data4.value = "重污染";
        mWeatherMoreInfoList.add(data4);

        WeatherMoreInfo data5 = new WeatherMoreInfo();
        data5.typeResId = R.mipmap.ic_sport_level;
        data5.description = "运动";
        data5.value = "不合适";
        mWeatherMoreInfoList.add(data5);

        WeatherMoreInfo data6 = new WeatherMoreInfo();
        data6.typeResId = R.mipmap.ic_ultraviolet_level;
        data6.description = "紫外线";
        data6.value = "强";
        mWeatherMoreInfoList.add(data6);

        adapter.notifyDataSetChanged();

    }
}
