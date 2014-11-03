package com.thoughtworks.cathywu.myweather.model;

import android.os.Parcel;
import android.os.Parcelable;

public class WeatherBasicInfo implements Parcelable {

    private CityWeatherBasicInfo weatherinfo;

    public WeatherBasicInfo(CityWeatherBasicInfo cityWeatherBasicInfo) {
        this.weatherinfo = cityWeatherBasicInfo;
    }

    public WeatherBasicInfo(Parcel in) {
        this.weatherinfo = new CityWeatherBasicInfo(in);
    }

    public CityWeatherBasicInfo getWeatherinfo() {
        return weatherinfo;
    }

    @Override
    public String toString() {
        return weatherinfo.toString();
    }

    @Override public int describeContents() {
        return 0;
    }

    @Override public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(weatherinfo, PARCELABLE_WRITE_RETURN_VALUE);
    }

    public static final Creator<WeatherBasicInfo> CREATOR = new Creator<WeatherBasicInfo>() {
        public WeatherBasicInfo createFromParcel(Parcel in) {
            return new WeatherBasicInfo(in);
        }

        public WeatherBasicInfo[] newArray(int size) {
            return new WeatherBasicInfo[size];
        }
    };
}
