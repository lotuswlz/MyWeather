package com.thoughtworks.cathywu.myweather.api;

import com.thoughtworks.cathywu.myweather.model.WeatherBasicInfo;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

public interface WeatherService {
    @GET("/sk/101110101.html")
    public void getWeatherBasicInfo(Callback<WeatherBasicInfo> callback);
}
