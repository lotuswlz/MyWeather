package com.thoughtworks.cathywu.myweather.manager;

import android.content.Context;
import android.util.Log;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.thoughtworks.cathywu.myweather.R;
import com.thoughtworks.cathywu.myweather.model.City;
import com.thoughtworks.cathywu.myweather.util.InjectorApplication;
import com.thoughtworks.cathywu.myweather.util.PreferenceUtils;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

public class CityManager {

    private List<City> cities;

    public CityManager(Context context) {
        List<String> cityRecords = Arrays.asList(context.getResources().getStringArray(R.array.cities));
        cities = Lists.transform(cityRecords, new Function<String, City>() {

            @Override
            public City apply(String cityStr) {
                String[] cityProps = cityStr.split("=");
                return new City(cityProps[0], cityProps[1]);
            }
        });
    }

    public List<City> searchCities(final String keyword) {
        return Lists.newArrayList(Iterables.filter(cities, new Predicate<City>() {
            @Override
            public boolean apply(City city) {
                if (city.getName().contains(keyword)) {
                    return true;
                } else {
                    return false;
                }
            }
        }));
    }

    public City getCity(final String cityId){
        try {
            return Iterables.find(cities, new Predicate<City>() {
                @Override
                public boolean apply(City city) {
                    return city.getCityId().equals(cityId);
                }
            });
        } catch (Exception e) {
            Log.e("get city", e.getMessage());
        }
        return null;
    }

}
