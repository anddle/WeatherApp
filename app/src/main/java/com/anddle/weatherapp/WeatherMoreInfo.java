package com.anddle.weatherapp;

public class WeatherMoreInfo {

    public int typeResId = 0;
    public int description;
    public String value = "";

    public WeatherMoreInfo(String keyword, String value) {
        this.value = value;

        switch (keyword) {
            case "wind_direction":
                this.typeResId = R.mipmap.ic_wind_direction;
                this.description = R.string.wind_direction;
                break;

            case "wind_level":
                this.typeResId = R.mipmap.ic_wind_level;
                this.description = R.string.wind_level;
                break;

            case "humidity_level":
                this.typeResId = R.mipmap.ic_humidity_level;
                this.description = R.string.humidity_level;
                break;

            case "air_quality":
                this.typeResId = R.mipmap.ic_air_quality;
                this.description = R.string.air_quality;
                break;

            case "sport_level":
                this.typeResId = R.mipmap.ic_sport_level;
                this.description = R.string.sport;
                break;

            case "ultraviolet_ray":
                this.typeResId = R.mipmap.ic_ultraviolet_level;
                this.description = R.string.ultraviolet_level;
                break;

        }
    }

}
