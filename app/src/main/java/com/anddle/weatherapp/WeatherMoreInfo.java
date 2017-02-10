package com.anddle.weatherapp;

public class WeatherMoreInfo {

    public int typeResId = 0;
    public String description;
    public String value = "";

    public WeatherMoreInfo(String keyword, String value) {
        this.value = value;

        switch (keyword) {
            case "wind_direction":
                this.typeResId = R.mipmap.ic_wind_direction;
                this.description = "风向";
                break;

            case "wind_level":
                this.typeResId = R.mipmap.ic_wind_level;
                this.description = "风力";
                break;

            case "humidity_level":
                this.typeResId = R.mipmap.ic_humidity_level;
                this.description = "湿度";
                break;

            case "air_quality":
                this.typeResId = R.mipmap.ic_air_quality;
                this.description = "空气质量";
                break;

            case "sport_level":
                this.typeResId = R.mipmap.ic_sport_level;
                this.description = "运动";
                break;

            case "ultraviolet_ray":
                this.typeResId = R.mipmap.ic_ultraviolet_level;
                this.description = "紫外线";
                break;

        }
    }

}
