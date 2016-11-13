package com.gds.materialdesign.model;

import android.content.Context;

import com.gds.materialdesign.modelimpl.NewsModelImpl;
import com.gds.materialdesign.modelimpl.WeatherModelImpl;

/**

 */
public interface WeatherModel {

    void loadWeatherData(String cityName, WeatherModelImpl.LoadWeatherListener listener);

    void loadLocation(Context context, WeatherModelImpl.LoadLocationListener listener);

}
