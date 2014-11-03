package com.thoughtworks.cathywu.myweather.ui;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

import com.thoughtworks.cathywu.myweather.R;
import com.thoughtworks.cathywu.myweather.api.WeatherService;
import com.thoughtworks.cathywu.myweather.manager.CityManager;
import com.thoughtworks.cathywu.myweather.model.City;
import com.thoughtworks.cathywu.myweather.util.InjectorApplication;
import com.thoughtworks.cathywu.myweather.util.PreferenceUtils;

import java.util.List;

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
        List<City> cities = cityManager.searchCities(keyword);
        Log.i("MyCitiesActivity", String.valueOf(cities));
    }
}
