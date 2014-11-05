package com.thoughtworks.cathywu.myweather.ui.adapter;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.thoughtworks.cathywu.myweather.model.City;

import java.util.List;

/**
 * Created by lzwu on 11/3/14.
 */
public class CityAdapter extends ArrayAdapter<City> {

    private List<City> cities;

    public CityAdapter(Context context, int resource, List<City> cities) {
        super(context, resource, cities);
        this.cities = cities;
    }
}
