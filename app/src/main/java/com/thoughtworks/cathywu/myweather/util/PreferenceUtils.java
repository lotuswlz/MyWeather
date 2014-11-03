package com.thoughtworks.cathywu.myweather.util;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceUtils {
    private final Context context;
    private final SharedPreferences sharedPreferences;

    public PreferenceUtils(Context context, int prefFileKeyRes) {
        this.context = context;
        this.sharedPreferences = context.getSharedPreferences(
                context.getString(prefFileKeyRes), Context.MODE_PRIVATE);
    }

    /**
     * Gets a String value from the SharedPreferences
     *
     * @param prefKeyRes   String resource ID of the preference key
     * @param defaultValue Value to be returned if the preference has not been set
     * @return SharedPreferences value or default
     */
    public String getString(int prefKeyRes, String defaultValue) {
        return sharedPreferences.getString(context.getString(prefKeyRes), defaultValue);
    }

    /**
     * Gets a boolean value from the SharedPreferences
     *
     * @param prefKeyRes   String resource ID of the preference key
     * @param defaultValue Value to be returned if the preference has not been set
     * @return SharedPreferences value or default
     */
    public boolean getBoolean(int prefKeyRes, boolean defaultValue) {
        return sharedPreferences.getBoolean(context.getString(prefKeyRes), defaultValue);
    }

    /**
     * Gets a integer value from the SharedPreferences
     *
     * @param prefKeyRes   String resource ID of the preference key
     * @param defaultValue Value to be returned if the preference has not been set
     * @return SharedPreferences value or default
     */
    public Integer getInt(int prefKeyRes, int defaultValue) {
        return sharedPreferences.getInt(context.getString(prefKeyRes), defaultValue);
    }

    /**
     * Sets a String value in the SharedPreferences
     *
     * @param prefKeyRes String resource ID of the preference key
     */
    public void putString(int prefKeyRes, String value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getString(prefKeyRes), value);
        editor.apply();
    }

    /**
     * Sets a boolean value in the SharedPreferences
     *
     * @param prefKeyRes String resource ID of the preference key
     */
    public void putBoolean(int prefKeyRes, boolean value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(context.getString(prefKeyRes), value);
        editor.apply();
    }

    /**
     * Sets an integer value in the SharedPreferences
     *
     * @param prefKeyRes String resource ID of the preference key
     */
    public void putInt(int prefKeyRes, int value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(context.getString(prefKeyRes), value);
        editor.apply();
    }
}
