package adit.kalyani.weatherapp;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class WeatherData {
    public String Time;
    public String Temperature;
    public int Condition;
    public static int updateWeatherIcon(int condition) {

        if (condition >= 0 && condition < 300) {
            return R.drawable.tstorm1;
        } else if (condition >= 300 && condition < 500) {
            return R.drawable.light_rain;
        } else if (condition >= 500 && condition < 600) {
            return R.drawable.shower3;
        } else if (condition >= 600 && condition <= 700) {
            return R.drawable.snow4;
        } else if (condition >= 701 && condition <= 771) {
            return R.drawable.fog;
        } else if (condition >= 772 && condition < 800) {
            return R.drawable.tstorm3;
        } else if (condition == 800) {
            return R.drawable.sunny;
        } else if (condition >= 801 && condition <= 804) {
            return R.drawable.cloudy2;
        } else if (condition >= 900 && condition <= 902) {
            return R.drawable.tstorm3;
        } else if (condition == 903) {
            return R.drawable.snow5;
        } else if (condition == 904) {
            return R.drawable.sunny;
        } else if (condition >= 905 && condition <= 1000) {
            return R.drawable.tstorm3;
        }

        return R.drawable.dunno;
    }
    public  static  WeatherData fromJson(JSONObject json){
        WeatherData data = new WeatherData();
        try{
            DateTime dt = DateTime.parse(json.getString("dt_txt"));
            data.Time = String.valueOf(dt.hour) + ":" + "00";
            data.Condition = json.getJSONArray("weather").getJSONObject(0).getInt("id");
            double temp = json.getJSONObject("main").getDouble("temp");
            data.Temperature = String.valueOf((int)(temp-273.15));
        }catch (JSONException ex){
            Log.e("JSON ERROR",ex.toString());
        }
        return  data;
    }
}
