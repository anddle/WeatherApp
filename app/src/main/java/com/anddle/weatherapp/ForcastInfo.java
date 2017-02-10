package com.anddle.weatherapp;

public class ForcastInfo {

    public String date;
    public String temperatureRage;
    public int iconResId;

    public ForcastInfo(String date, String temperatureRage, int weatherCode) {

        this.date = date;
        this.temperatureRage = temperatureRage;

        this.iconResId = R.mipmap.ic_sunny_cloudy_s;
        switch (weatherCode) {

            case 0:
                this.iconResId = R.mipmap.ic_sunny_s;

            case 1:
                this.iconResId =  R.mipmap.ic_rainy_s;

            case 2:
                this.iconResId =  R.mipmap.ic_cloudy_s;

            case 3:
                this.iconResId =  R.mipmap.ic_fog_s;

            case 4:
                this.iconResId =  R.mipmap.ic_snow_s;

            case 5:
                this.iconResId =  R.mipmap.ic_sunny_cloudy_s;

        }
    }
}
