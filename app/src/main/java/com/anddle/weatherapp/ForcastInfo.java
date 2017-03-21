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
                break;

            case 1:
                this.iconResId =  R.mipmap.ic_rainy_s;
                break;

            case 2:
                this.iconResId =  R.mipmap.ic_cloudy_s;
                break;

            case 3:
                this.iconResId =  R.mipmap.ic_fog_s;
                break;

            case 4:
                this.iconResId =  R.mipmap.ic_snow_s;
                break;

            case 5:
                this.iconResId =  R.mipmap.ic_sunny_cloudy_s;
                break;

        }
    }
}
