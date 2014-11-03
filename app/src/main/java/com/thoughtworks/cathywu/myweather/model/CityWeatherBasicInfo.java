package com.thoughtworks.cathywu.myweather.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CityWeatherBasicInfo implements Parcelable {

    private String city;
    private String cityid;
    private String temp;
    private String WD;
    private String WS;
    private String SD;
    private String WSE;
    private String time;

    public CityWeatherBasicInfo(Parcel in) {
        city = in.readString();
        cityid = in.readString();
        temp = in.readString();
        WD = in.readString();
        WS = in.readString();
        SD = in.readString();
        WSE = in.readString();
        time = in.readString();
    }

    @Override
    public String toString() {
        return "city=" + city + ";cityid=" + cityid + ";temp=" + temp + ";WD=" + WD + ";WS=" + WS + ";SD=" + SD + ";WSE=" + WSE + ";time=" + time;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(city);
        dest.writeString(cityid);
        dest.writeString(temp);
        dest.writeString(WD);
        dest.writeString(WS);
        dest.writeString(SD);
        dest.writeString(WSE);
        dest.writeString(time);
    }

    public static final Creator<CityWeatherBasicInfo> CREATOR = new Creator<CityWeatherBasicInfo>() {
        public CityWeatherBasicInfo createFromParcel(Parcel in) {
            return new CityWeatherBasicInfo(in);
        }

        public CityWeatherBasicInfo[] newArray(int size) {
            return new CityWeatherBasicInfo[size];
        }
    };

    public String getCity() {
        return city;
    }

    public String getCityid() {
        return cityid;
    }

    public String getTemp() {
        return temp;
    }

    public String getWD() {
        return WD;
    }

    public String getWS() {
        return WS;
    }

    public String getSD() {
        return SD;
    }

    public String getWSE() {
        return WSE;
    }

    public String getTime() {
        return time;
    }
    //@SuppressWarnings("RedundantIfStatement")

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || CityWeatherBasicInfo.class != o.getClass()) return false;

        CityWeatherBasicInfo that = (CityWeatherBasicInfo) o;

        if (SD != null ? !SD.equals(that.SD) : that.SD != null) return false;
        if (WD != null ? !WD.equals(that.WD) : that.WD != null) return false;
        if (WS != null ? !WS.equals(that.WS) : that.WS != null) return false;
        if (WSE != null ? !WSE.equals(that.WSE) : that.WSE != null) return false;
        if (!city.equals(that.city)) return false;
        if (!cityid.equals(that.cityid)) return false;
        if (temp != null ? !temp.equals(that.temp) : that.temp != null) return false;
        if (time != null ? !time.equals(that.time) : that.time != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = city.hashCode();
        result = 31 * result + cityid.hashCode();
        result = 31 * result + (temp != null ? temp.hashCode() : 0);
        result = 31 * result + (WD != null ? WD.hashCode() : 0);
        result = 31 * result + (WS != null ? WS.hashCode() : 0);
        result = 31 * result + (SD != null ? SD.hashCode() : 0);
        result = 31 * result + (WSE != null ? WSE.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        return result;
    }
}
