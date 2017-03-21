package com.anddle.weatherapp;

import android.os.AsyncTask;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mWeatherMoreInfoListView;
    private List<WeatherMoreInfo> mWeatherMoreInfoList;
    private UpdateTask mUpdateTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("TEST", "Weather app launched");

        Toolbar toolbar = (Toolbar) findViewById(R.id.app_toolbar);
        setSupportActionBar(toolbar);

        mWeatherMoreInfoListView = (RecyclerView) findViewById(R.id.weather_more_info_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        mWeatherMoreInfoListView.setLayoutManager(layoutManager);

        mWeatherMoreInfoList = new ArrayList<>();
        WeatherMoreInfoAdapter adapter = new WeatherMoreInfoAdapter(mWeatherMoreInfoList);
        mWeatherMoreInfoListView.setAdapter(adapter);

        mUpdateTask = new UpdateTask();
        mUpdateTask.execute();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if((mUpdateTask != null) && (mUpdateTask.getStatus() == AsyncTask.Status.RUNNING)) {
            mUpdateTask.cancel(true);
        }

        mUpdateTask = null;
    }

    String getWeatherInformationFromNetwork(String apiUrl) {

        URL url = null;
        String weatherRes = "";
        try {
            url = new URL(apiUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            byte[] buffer = new byte[2048];
            int readBytes = 0;
            StringBuilder stringBuilder = new StringBuilder();
            while((readBytes = in.read(buffer)) > 0){
                stringBuilder.append(new String(buffer, 0, readBytes));
            }

            urlConnection.disconnect();

            weatherRes = stringBuilder.toString();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return weatherRes;
    }

    WeatherParsedData parseWeatherInfo(String jsonString) {

        WeatherParsedData result = new WeatherParsedData();
        result.errorCode = -1;

        Log.d("TEST","start to parse JSON content");

        try {
            JSONObject weatherResult = new JSONObject(jsonString);
            result.errorCode = weatherResult.getInt("error_code");
            Log.d("TEST", "error_code = " + result.errorCode);
            if(result.errorCode == 0) {
                JSONObject data = weatherResult.getJSONObject("data");
                result.location = data.getString("location");
                result.temperature = data.getString("temperature");
                result.temperatureRange = data.getString("temperature_range");
                result.weatherCode = data.getInt("weather_code");

                Log.d("TEST","weather detail info:"+
                        " location = " + result.location +
                        " temperature = " + result.temperature +
                        " temperatureRange = " + result.temperatureRange +
                        " weatherCode = " + result.weatherCode);

                JSONArray forcast = data.getJSONArray("forcast");
                result.forcastList = new ArrayList<>();
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
                    result.forcastList.add(forcastInfo);
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

                result.weatherMoreInfoList = new ArrayList<>();
                WeatherMoreInfo info1 = new WeatherMoreInfo("wind_direction", windDirection);
                WeatherMoreInfo info2 = new WeatherMoreInfo("wind_level", windLevel);
                WeatherMoreInfo info3 = new WeatherMoreInfo("humidity_level", humidityLevel);
                WeatherMoreInfo info4 = new WeatherMoreInfo("air_quality", airQuality);
                WeatherMoreInfo info5 = new WeatherMoreInfo("sport_level", sportLevel);
                WeatherMoreInfo info6 = new WeatherMoreInfo("ultraviolet_ray", ultravioletRay);
                result.weatherMoreInfoList.add(info1);
                result.weatherMoreInfoList.add(info2);
                result.weatherMoreInfoList.add(info3);
                result.weatherMoreInfoList.add(info4);
                result.weatherMoreInfoList.add(info5);
                result.weatherMoreInfoList.add(info6);

            }

            else {

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d("TEST","finish to parse JSON content");

        return result;
    }

    class WeatherParsedData {

        public int errorCode;

        public String location;

        public String temperature;

        public String temperatureRange;

        public int weatherCode;

        public List<ForcastInfo> forcastList;

        public List<WeatherMoreInfo> weatherMoreInfoList;
    }

    private class UpdateTask extends AsyncTask<Void, Void, WeatherParsedData> {

        @Override
        protected WeatherParsedData doInBackground(Void... params) {

            Log.d("TEST","UpdateTask doInBackground - ThreadId = " + Thread.currentThread().getId());

            String url = "http://book.anddle.com/api/query_weather";
            String dataString = getWeatherInformationFromNetwork(url);

            WeatherParsedData weatherParsedData = parseWeatherInfo(dataString);

            return weatherParsedData;
        }

        @Override
        protected void onPostExecute(WeatherParsedData data) {

            Log.d("TEST","UpdateTask onPostExecute - ThreadId = " + Thread.currentThread().getId());

            if(data.errorCode != 0) {
                Snackbar.make(findViewById(R.id.root_view), R.string.load_weather_info_fail, Snackbar.LENGTH_LONG).show();
                return;
            }

            updateWeatherDetail(data.location, data.temperature, data.temperatureRange, data.weatherCode);
            updateWeatherForcast(data.forcastList);
            updateWeatherMoreInfo(data.weatherMoreInfoList);
        }

        @Override
        protected void onCancelled() {

        }

    }

    private void updateWeatherDetail(String location, String temperature, String temperatureRange, int weatherCode) {

        TextView currentTemperatureView = (TextView) findViewById(R.id.current_temperature);
        TextView temperatureRangeView = (TextView) findViewById(R.id.temperature_range);
        ImageView weatherIcon = (ImageView) findViewById(R.id.weather_icon);

        currentTemperatureView.setText(temperature);
        temperatureRangeView.setText(temperatureRange);

        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_layout);
        collapsingToolbar.setTitle(location);

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
