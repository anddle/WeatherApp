package com.anddle.weatherapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView mWeatherMoreInfoListView;
    private List<WeatherMoreInfo> mWeatherMoreInfoList;

    private final String FAKE_DATA= "{\n" +
            "    \"error_code\": \"0\",\n" +
            "    \"data\": {\n" +
            "        \"location\": \"成都\",\n" +
            "        \"temperature\": \"23°\",\n" +
            "        \"temperature_range\": \"18℃~23℃\",\n" +
            "        \"weather_code\": \"5\",\n" +
            "        \"wind_direction\": \"东南\",\n" +
            "        \"wind_level\": \"1级\",\n" +
            "        \"humidity_level\": \"30%\",\n" +
            "        \"air_quality\": \"良\",\n" +
            "        \"sport_level\": \"适宜\",\n" +
            "        \"ultraviolet_ray\": \"弱\",\n" +
            "        \"forcast\": [\n" +
            "            {\n" +
            "                \"date\": \"明天\",\n" +
            "                \"temperature_range\": \"18℃~23℃\",\n" +
            "                \"weather_code\": \"0\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"date\": \"星期六\",\n" +
            "                \"temperature_range\": \"17℃~21℃\",\n" +
            "                \"weather_code\": \"1\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"date\": \"星期日\",\n" +
            "                \"temperature_range\": \"19℃~24℃\",\n" +
            "                \"weather_code\": \"3\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"date\": \"星期一\",\n" +
            "                \"temperature_range\": \"16℃~22℃\",\n" +
            "                \"weather_code\": \"4\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"date\": \"星期二\",\n" +
            "                \"temperature_range\": \"20℃~26℃\",\n" +
            "                \"weather_code\": \"2\"\n" +
            "            }\n" +
            "        ]\n" +
            "    }\n" +
            "}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("TEST", "Weather app launched");

        mWeatherMoreInfoListView = (ListView) findViewById(R.id.weather_more_info_list);
        mWeatherMoreInfoList = new ArrayList<>();
        WeatherMoreInfoAdapter adapter = new WeatherMoreInfoAdapter(MainActivity.this, R.layout.weather_more_info_item_layout, mWeatherMoreInfoList);
        mWeatherMoreInfoListView.setAdapter(adapter);

        try {
            Log.d("TEST","start to parse JSON content");

            JSONObject weatherResult = new JSONObject(FAKE_DATA);
            int errorCode = weatherResult.getInt("error_code");
            Log.d("TEST", "error_code = " + errorCode);
            if(errorCode == 0) {
                JSONObject data = weatherResult.getJSONObject("data");
                String location = data.getString("location");
                String temperature = data.getString("temperature");
                String temperatureRange = data.getString("temperature_range");
                int weatherCode = data.getInt("weather_code");

                Log.d("TEST","weather detail info:"+
                        " location = " + location +
                        " temperature = " + temperature +
                        " temperatureRange = " + temperatureRange +
                        " weatherCode = " + weatherCode);

                JSONArray forcast = data.getJSONArray("forcast");
                List<ForcastInfo> forcastInfoList = new ArrayList<>();
                for(int i = 0; i < forcast.length(); i++) {
                    JSONObject forcastItem = forcast.getJSONObject(i);
                    String date = forcastItem.getString("date");
                    String forcastTemperatureRange = forcastItem.getString("temperature_range");
                    int forcastWeatherCode = forcastItem.getInt("weather_code");

                    Log.d("TEST","weather forcast info:"+
                            " date = " + date +
                            " forcastTemperatureRange = " + forcastTemperatureRange +
                            " forcastWeatherCode = " + forcastWeatherCode);

                    ForcastInfo forcastInfo = new ForcastInfo(date, forcastTemperatureRange, forcastWeatherCode);
                    forcastInfoList.add(forcastInfo);
                }

                String windDirection = data.getString("wind_direction");
                String windLevel = data.getString("wind_level");
                String humidityLevel = data.getString("humidity_level");
                String airQuality = data.getString("air_quality");
                String sportLevel = data.getString("sport_level");
                String ultravioletRay = data.getString("ultraviolet_ray");

                Log.d("TEST","more weather info:"+
                        " windDirection = " + windDirection +
                        " windLevel = " + windLevel +
                        " humidityLevel = " + humidityLevel +
                        " airQuality = " + airQuality +
                        " sportLevel = " + sportLevel +
                        " ultravioletRay = " + ultravioletRay );

                List<WeatherMoreInfo> weatherMoreInfoList = new ArrayList<>();
                WeatherMoreInfo info1 = new WeatherMoreInfo("wind_direction", windDirection);
                WeatherMoreInfo info2 = new WeatherMoreInfo("wind_level", windLevel);
                WeatherMoreInfo info3 = new WeatherMoreInfo("humidity_level", humidityLevel);
                WeatherMoreInfo info4 = new WeatherMoreInfo("air_quality", airQuality);
                WeatherMoreInfo info5 = new WeatherMoreInfo("sport_level", sportLevel);
                WeatherMoreInfo info6 = new WeatherMoreInfo("ultraviolet_ray", ultravioletRay);
                weatherMoreInfoList.add(info1);
                weatherMoreInfoList.add(info2);
                weatherMoreInfoList.add(info3);
                weatherMoreInfoList.add(info4);
                weatherMoreInfoList.add(info5);
                weatherMoreInfoList.add(info6);

                Log.d("TEST","finish to parse JSON content");

                updateWeatherDetail(location, temperature, temperatureRange, weatherCode);
                updateWeatherForcast(forcastInfoList);
                updateWeatherMoreInfo(weatherMoreInfoList);
            }
            else {

            }
        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("TEST","fail to parse JSON content");
        }

    }

    private void updateWeatherDetail(String location, String temperature, String temperatureRange, int weatherCode) {

        TextView currentTemperatureView = (TextView) findViewById(R.id.current_temperature);
        TextView temperatureRangeView = (TextView) findViewById(R.id.temperature_range);
        ImageView weatherIcon = (ImageView) findViewById(R.id.weather_icon);

        currentTemperatureView.setText(temperature);
        temperatureRangeView.setText(temperatureRange);

        int iconId = R.mipmap.ic_sunny_cloudy_l;
        switch (weatherCode) {

            case 0:
                iconId = R.mipmap.ic_sunny_l;
                break;

            case 1:
                iconId = R.mipmap.ic_rainy_l;
                break;

            case 2:
                iconId = R.mipmap.ic_cloudy_l;
                break;

            case 3:
                iconId = R.mipmap.ic_fog_l;
                break;

            case 4:
                iconId = R.mipmap.ic_snow_l;
                break;

            case 5:
                iconId = R.mipmap.ic_sunny_cloudy_l;
                break;
        }
        weatherIcon.setImageResource(iconId);
    }

    private void updateWeatherForcast(List<ForcastInfo> list) {

        LinearLayout forcastItem1 = (LinearLayout) findViewById(R.id.forcast_item1);
        LinearLayout forcastItem2 = (LinearLayout) findViewById(R.id.forcast_item2);
        LinearLayout forcastItem3 = (LinearLayout) findViewById(R.id.forcast_item3);
        LinearLayout forcastItem4 = (LinearLayout) findViewById(R.id.forcast_item4);
        LinearLayout forcastItem5 = (LinearLayout) findViewById(R.id.forcast_item5);

        updateWeatherForcastItem(forcastItem1, list.get(0));
        updateWeatherForcastItem(forcastItem2, list.get(1));
        updateWeatherForcastItem(forcastItem3, list.get(2));
        updateWeatherForcastItem(forcastItem4, list.get(3));
        updateWeatherForcastItem(forcastItem5, list.get(4));
    }

    private void updateWeatherForcastItem(LinearLayout layout, ForcastInfo info) {

        TextView date = (TextView) layout.findViewById(R.id.forcast_date);
        ImageView icon = (ImageView) layout.findViewById(R.id.forcast_icon);
        TextView temperatureRage = (TextView) layout.findViewById(R.id.forcast_temperature);

        date.setText(info.date);
        icon.setImageResource(info.iconResId);
        temperatureRage.setText(info.temperatureRage);

    }

    private void updateWeatherMoreInfo(List<WeatherMoreInfo> list) {

        mWeatherMoreInfoList.clear();
        mWeatherMoreInfoList.addAll(list);

        WeatherMoreInfoAdapter adapter = (WeatherMoreInfoAdapter) mWeatherMoreInfoListView.getAdapter();
        adapter.notifyDataSetChanged();
    }
}
