package com.thoughtworks.cathywu.myweather.model;

/**
 * Created by lzwu on 11/3/14.
 */
public class City {
    private String cityId;
    private String name;

    public City(String cityId, String name) {
        this.cityId = cityId;
        this.name = name;
    }

    public String getCityId() {
        return cityId;
    }

    public String getName() {
        return name;
    }
}
