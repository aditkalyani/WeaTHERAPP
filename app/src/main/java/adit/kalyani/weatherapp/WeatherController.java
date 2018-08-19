package adit.kalyani.weatherapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class WeatherController extends AppCompatActivity  {

    final String WEATHER_URL = "http://api.openweathermap.org/data/2.5/forecast";
    final String APP_ID = "842cbebbc0945b0666bfe4c2e409de5f";
    RecyclerView rv;
    ArrayList<Day> days;
    TextView textView;
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        String city = getIntent().getStringExtra("city");
        textView = (TextView)findViewById(R.id.city_name);
        days = getWeatherForNewCity(city);
    }

    public ArrayList<Day> getWeatherForNewCity(final String city) {
        RequestParams params = new RequestParams();
        params.put("q",city);
        params.put("appid",APP_ID);
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<Day> days = new ArrayList<>();
        client.get(WEATHER_URL, params, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    textView.setText(city);

                    DateTime prevDay = new DateTime(0, 0, 0, 0, 0, 0);
                    int dayIndex = -1;
                    for (int i = 0; i < response.getJSONArray("list").length(); i++) {
                        DateTime d = DateTime.parse(response.getJSONArray("list").getJSONObject(i).getString("dt_txt"));
                        if(DateTime.CompareDay(d,prevDay)){
                            days.get(dayIndex).weatherData.add(WeatherData.fromJson(response.getJSONArray("list").getJSONObject(i)));
                            Log.i("LOG",response.getJSONArray("list").getJSONObject(i).toString());
                        }else{
                            Day dd = new Day();
                            dd.time = d;
                            days.add(dd);
                            dayIndex = days.size()-1;
                            prevDay = d;
                            days.get(dayIndex).weatherData.add(WeatherData.fromJson(response.getJSONArray("list").getJSONObject(i)));
                        }
                    }
                    rv = findViewById(R.id.recycler_view);
                    RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplicationContext());
                    rv.setLayoutManager(manager);
                    rv.setAdapter(new CustomRecyclerViewAdapter(getApplicationContext(),days));
                    Log.i("SOME JSON",response.toString());
                }catch (JSONException ex){

                }

            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable e, JSONObject response){
                Toast.makeText(WeatherController.this, "Request Failed", Toast.LENGTH_SHORT).show();
            }
        });
        return days;
    }
}
