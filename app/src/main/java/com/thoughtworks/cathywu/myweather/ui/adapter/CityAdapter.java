package com.thoughtworks.cathywu.myweather.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.common.base.Strings;
import com.thoughtworks.cathywu.myweather.R;
import com.thoughtworks.cathywu.myweather.model.City;
import com.thoughtworks.cathywu.myweather.ui.CitySearchActivity;
import com.thoughtworks.cathywu.myweather.util.InjectorApplication;
import com.thoughtworks.cathywu.myweather.util.PreferenceUtils;

import java.util.List;

import javax.inject.Inject;

public class CityAdapter extends ArrayAdapter<City> {

    private final int layout;
    private final LayoutInflater inflater;
    private List<City> cities;
    private Activity activity;
    @Inject PreferenceUtils preferenceUtils;

    public CityAdapter(Activity activity, int layout, List<City> cities) {
        super(activity, layout, cities);
        this.layout = layout;
        this.cities = cities;
        this.activity = activity;
        this.inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ((InjectorApplication) activity.getApplication()).inject(this);
    }

    @Override
    public int getCount() {
        return cities.size();
    }

    @Override
    public City getItem(int position) {
        return cities.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View view = convertView == null ? inflater.inflate(layout, parent, false) : convertView;
        final City city = getItem(position);
        if (!Strings.isNullOrEmpty(city.getCityId())) {
            TextView cityNameView = (TextView) view.findViewById(R.id.city_list_item);
            cityNameView.setText(city.getName());
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!Strings.isNullOrEmpty(city.getCityId())) {
                        addCity(city.getCityId());
                    }
                    activity.finish();
                }
            });
        }
        return view;
    }

    private void addCity(String cityId) {
        String currentCities = preferenceUtils.getString((R.string.my_cities_key), "");
        if (!Strings.isNullOrEmpty(currentCities)) {
            currentCities += ",";
        }
        currentCities += cityId;
        preferenceUtils.putString(R.string.my_cities_key, currentCities);
    }
}
