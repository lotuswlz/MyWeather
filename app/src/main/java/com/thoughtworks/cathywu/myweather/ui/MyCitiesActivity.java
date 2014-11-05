package com.thoughtworks.cathywu.myweather.ui;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thoughtworks.cathywu.myweather.R;
import com.thoughtworks.cathywu.myweather.api.WeatherService;
import com.thoughtworks.cathywu.myweather.manager.CityManager;
import com.thoughtworks.cathywu.myweather.model.City;
import com.thoughtworks.cathywu.myweather.util.InjectorApplication;
import com.thoughtworks.cathywu.myweather.util.PreferenceUtils;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

/**
 * Created by lzwu on 11/3/14.
 */
public class MyCitiesActivity extends Activity {

    @Inject
    CityManager cityManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((InjectorApplication) getApplication()).inject(this);
        setContentView(R.layout.activity_search_city_dialog);
    }

    @Override
    protected void onResume() {
        super.onResume();
        prepareSearchBar();
    }

    private void prepareSearchBar() {
        EditText searchText = (EditText) findViewById(R.id.search_city_name);
        searchText.setOnKeyListener(new View.OnKeyListener() {

            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (KeyEvent.KEYCODE_ENTER == keyCode && event.getAction() == KeyEvent.ACTION_DOWN) {
                    showSearchResult();
                    return true;
                }
                return false;

            }
        });
    }

    private void showSearchResult() {
        EditText searchText = (EditText) findViewById(R.id.search_city_name);
        String keyword = searchText.getText().toString();
        final List<City> cities = cityManager.searchCities(keyword);
        List<Map<String, String>> datas = Lists.transform(cities, new Function<City, Map<String, String>>() {
            @Override
            public Map<String, String> apply(City city) {
                Map<String, String> itemMap = Maps.newHashMap();
                itemMap.put("city_list_item", city.getName());
                return itemMap;
            }
        });
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, datas, R.layout.city_view, new String[]{"city_list_item"}, new int[]{R.id.city_list_item});
        ListView searchResultView = (ListView) findViewById(R.id.search_result);
        searchResultView.setAdapter(simpleAdapter);
    }
}
