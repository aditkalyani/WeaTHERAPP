package adit.kalyani.weatherapp;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomRecyclerViewAdapter extends RecyclerView.Adapter<CustomRecyclerViewAdapter.RecyclerViewHolder> {
    ArrayList<Day> days = new ArrayList<>();
    public Context ctx;

    public CustomRecyclerViewAdapter(Context ctx, ArrayList<Day> days) {
        this.days = days;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.card_view,parent,false);
        return new RecyclerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerViewHolder holder, int position) {
        String s = String.valueOf(days.get(position).time.day) + " " + String.valueOf(days.get(position).time.getMonth()) + " " +String.valueOf(days.get(position).time.year);
        holder.date_.setText(s);
        LinearLayoutManager manager = new LinearLayoutManager(ctx);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        holder.viewPager.setHasFixedSize(true);
        holder.viewPager.setLayoutManager(manager);
        holder.viewPager.setAdapter(new ViewPagerAdapter(days.get(position).weatherData,ctx));
    }

    @Override
    public int getItemCount() {
        return days.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder{
        public TextView date_;
        public RecyclerView viewPager;
        public RecyclerViewHolder(View itemView) {
            super(itemView);
            date_ = (TextView) itemView.findViewById(R.id.day_of_week);
            viewPager = (RecyclerView)itemView.findViewById(R.id.view_pager);
        }
    }
}
