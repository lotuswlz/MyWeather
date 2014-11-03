package com.thoughtworks.cathywu.myweather.util;

import android.app.Application;

import com.thoughtworks.cathywu.myweather.R;
import com.thoughtworks.cathywu.myweather.api.WeatherService;
import com.thoughtworks.cathywu.myweather.ui.MainActivity;

import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;

@Module(
        injects = {
                MainActivity.class,
                WeatherService.class
        }
)
@SuppressWarnings("unused")
public class MWModule {
    private Application application;

    public MWModule(Application application) {
        this.application = application;
    }

    @Provides
    WeatherService provideWeatherData() {
        return new RestAdapter.Builder()
                .setEndpoint(application.getString(R.string.weather_endpoint))
                .build()
                .create(WeatherService.class);
    }

    @Provides PreferenceUtils providePreferenceUtils() {
        return new PreferenceUtils(application, R.string.preference_file_key);
    }


}