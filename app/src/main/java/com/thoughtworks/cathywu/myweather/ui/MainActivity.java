package com.thoughtworks.cathywu.myweather.ui;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.cathywu.myweather.R;
import com.thoughtworks.cathywu.myweather.api.WeatherService;
import com.thoughtworks.cathywu.myweather.model.CityWeatherBasicInfo;
import com.thoughtworks.cathywu.myweather.model.WeatherBasicInfo;
import com.thoughtworks.cathywu.myweather.util.InjectorApplication;
import com.thoughtworks.cathywu.myweather.util.PreferenceUtils;

import javax.inject.Inject;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class MainActivity extends Activity {

    @Inject WeatherService weatherService;
    @Inject PreferenceUtils preferenceUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((InjectorApplication) getApplication()).inject(this);
        setContentView(R.layout.activity_main);
        showSavedData();
        getCityWeatherInfo();
    }

    private void showSavedData() {
        String weatherStr = preferenceUtils.getString(R.id.pref_weather_key, null);
        if (weatherStr != null) {
            Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
            CityWeatherBasicInfo cityWeatherBasicInfo = gson.fromJson(weatherStr, CityWeatherBasicInfo.class);
            showData(cityWeatherBasicInfo);
        }
    }

    private void getCityWeatherInfo() {
        weatherService.getWeatherBasicInfo(new Callback<WeatherBasicInfo>() {
            @Override
            public void success(WeatherBasicInfo weatherBasicInfo, Response response) {
                Log.i("MainActivity", "Data Get Success");
                CityWeatherBasicInfo currentData = weatherBasicInfo.getWeatherinfo();
                showData(currentData);
                saveToPreference(currentData);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("MainActivity", "Failure");
                Log.e("MainActivity", error.getUrl());
                Log.e("MainActivity", error.getMessage());
            }
        });
    }

    private void showData(CityWeatherBasicInfo basicInfo) {
        TextView cityName = (TextView) this.findViewById(R.id.cityname);
        cityName.setText(basicInfo.getCity());
        TextView weatherTime = (TextView) this.findViewById(R.id.weather_time);
        weatherTime.setText(basicInfo.getTime() + getResources().getString(R.string.publish_suffix));
        TextView weatherTemp = (TextView) this.findViewById(R.id.citytemp);
        weatherTemp.setText(basicInfo.getTemp() + getResources().getString(R.string.temp_unit));
        TextView windDirection = (TextView) this.findViewById(R.id.wind_direction);
        windDirection.setText(basicInfo.getWD());
        TextView windSpeed = (TextView) this.findViewById(R.id.wind_power);
        windSpeed.setText(basicInfo.getWS());
        TextView airHumidity = (TextView) this.findViewById(R.id.air_humidity);
        airHumidity.setText(basicInfo.getSD());
    }

    private void saveToPreference(CityWeatherBasicInfo basicInfo) {
        Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
        String weatherStr = gson.toJson(basicInfo);
        preferenceUtils.putString(R.id.pref_weather_key, weatherStr);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onRefresh(View view) {
        getCityWeatherInfo();
    }
}
