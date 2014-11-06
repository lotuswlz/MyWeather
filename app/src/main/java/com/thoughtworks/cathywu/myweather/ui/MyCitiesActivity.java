package com.thoughtworks.cathywu.myweather.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.google.common.base.Function;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thoughtworks.cathywu.myweather.R;
import com.thoughtworks.cathywu.myweather.manager.CityManager;
import com.thoughtworks.cathywu.myweather.model.City;
import com.thoughtworks.cathywu.myweather.util.InjectorApplication;
import com.thoughtworks.cathywu.myweather.util.PreferenceUtils;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

/**
 * Created by lzwu on 11/5/14.
 */
public class MyCitiesActivity extends Activity {

    @Inject
    PreferenceUtils preferenceUtils;
    @Inject
    CityManager cityManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((InjectorApplication) getApplication()).inject(this);

        setContentView(R.layout.my_city_list);


    }

    @Override
    protected void onResume() {
        super.onResume();
        createCityListView();
    }

    private void createCityListView(){
        String myCities = preferenceUtils.getString(R.string.my_cities_key, null);
        List<Map<String, String>> datas = Lists.newArrayList();
        if (!Strings.isNullOrEmpty(myCities)) {

            List<City> cities = Lists.transform(Lists.newArrayList(Splitter.on(",").split(myCities)), new Function<String, City>() {
                @Override
                public City apply(String cityId) {
                    return cityManager.getCity((cityId));
                }
            });

            datas.addAll(Lists.transform(cities, new Function<City, Map<String, String>>() {
                @Override
                public Map<String, String> apply(City city) {
                    Map<String, String> itemMap = Maps.newHashMap();
                    itemMap.put("my_city_list_item", city.getName());
                    return itemMap;
                }
            }));
        }

        Map<String, String> addCityButton = Maps.newHashMap();
        addCityButton.put("my_city_list_item", getResources().getString(R.string.add_city_button_text));
        datas.add(0, addCityButton);

        SimpleAdapter simpleAdapter = new SimpleAdapter(this, datas, R.layout.my_city_view, new String[]{"my_city_list_item"}, new int[]{R.id.my_city_list_item});
        ListView myCityView = (ListView) findViewById(R.id.city_list);
        myCityView.setAdapter(simpleAdapter);
    }

    public void showSearchCitiesActivity(View view) {
        Log.i("My City", "showSearchCitiesActivity");
        Intent intent = new Intent(this, CitySearchActivity.class);
        String action = getIntent().getAction();
        intent.setAction(action);
        intent.setFlags(Intent.FLAG_ACTIVITY_FORWARD_RESULT);
        startActivity(intent);
    }
}
