package com.anddle.weatherapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

public class WeatherMoreInfoAdapter extends ArrayAdapter<WeatherMoreInfo> {

    private final LayoutInflater mInflater;
    private final int mResource;

    public WeatherMoreInfoAdapter(Context context, int resource, List<WeatherMoreInfo> objects) {
        super(context, resource, objects);

        mInflater = LayoutInflater.from(context);
        mResource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = mInflater.inflate(mResource, parent, false);
        }

        WeatherMoreInfo item = getItem(position);

        ImageView icon = (ImageView) convertView.findViewById(R.id.weather_more_info_icon);
        icon.setImageResource(item.typeResId);

        TextView description = (TextView) convertView.findViewById(R.id.weather_more_info_description);
        description.setText(item.description);

        TextView value = (TextView) convertView.findViewById(R.id.weather_more_info_value);
        value.setText(item.value);

        return convertView;

    }
}
