package com.anddle.weatherapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
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
            JSONObject weatherResult = new JSONObject(FAKE_DATA);
            int errorCode = weatherResult.getInt("error_code");
            if(errorCode == 0) {
                JSONObject data = weatherResult.getJSONObject("data");
                String location = data.getString("location");
                String temperature = data.getString("temperature");
                String temperatureRange = data.getString("temperature_range");
                int weatherCode = data.getInt("weather_code");

                JSONArray forcast = data.getJSONArray("forcast");
                for(int i = 0; i < forcast.length(); i++) {
                    JSONObject forcastItem = forcast.getJSONObject(i);
                    String date = forcastItem.getString("date");
                    String forcastTemperatureRange = forcastItem.getString("temperature_range");
                    int forcastWeatherCode = forcastItem.getInt("weather_code");

                }

                String windDirection = data.getString("wind_direction");
                String windLevel = data.getString("wind_level");
                String humidityLevel = data.getString("humidity_level");
                String airQuality = data.getString("air_quality");
                String sportLevel = data.getString("sport_level");
                String ultravioletRay = data.getString("ultraviolet_ray");
            }
            else {

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
