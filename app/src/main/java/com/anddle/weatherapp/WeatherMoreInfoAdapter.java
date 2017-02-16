package com.anddle.weatherapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

public class WeatherMoreInfoAdapter extends RecyclerView.Adapter {

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView icon;
        public TextView description;
        public TextView value;

        public ViewHolder(View v) {
            super(v);

            this.icon = (ImageView) v.findViewById(R.id.weather_more_info_icon);
            this.description = (TextView) v.findViewById(R.id.weather_more_info_description);
            this.value = (TextView) v.findViewById(R.id.weather_more_info_value);
        }
    }

    private List<WeatherMoreInfo> mData;

    public WeatherMoreInfoAdapter(List<WeatherMoreInfo> data) {
        this.mData = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.weather_more_info_item_layout, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        WeatherMoreInfo info = mData.get(position);
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.icon.setImageResource(info.typeResId);
        viewHolder.description.setText(info.description);
        viewHolder.value.setText(info.value);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
