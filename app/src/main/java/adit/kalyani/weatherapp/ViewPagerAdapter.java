package adit.kalyani.weatherapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ViewPagerAdapter extends RecyclerView.Adapter<ViewPagerAdapter.ViewPageHolder> {
    public ArrayList<WeatherData> weatherData = new ArrayList<>();
    private  Context ctx;
    public ViewPagerAdapter(ArrayList<WeatherData> weatherData, Context ctx) {
        this.weatherData = weatherData;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public ViewPageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v =inflater.inflate(R.layout.weatherdata_view,parent,false);
        return  new ViewPageHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewPageHolder holder, int position) {
        holder.imageView.setImageResource(WeatherData.updateWeatherIcon(weatherData.get(position).Condition));
        holder.timeView.setText(weatherData.get(position).Time);
        holder.tempView.setText(weatherData.get(position).Temperature);
    }

    @Override
    public int getItemCount() {
        return weatherData.size();
    }

    public  class ViewPageHolder extends  RecyclerView.ViewHolder{
        public  ImageView imageView;
        public  TextView tempView;
        public  TextView timeView;
       public ViewPageHolder(View itemView) {
           super(itemView);
           imageView = itemView.findViewById(R.id.img_view);
           tempView = itemView.findViewById(R.id.temperature);
           timeView = itemView.findViewById(R.id.time);
       }
   }
}
