package com.example.android.basicweather;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ForecastViewHolder> {
    private ArrayList<WeatherForecast> weatherForecastList;
    private OnForecastListener onForecastListener;

    public WeatherAdapter(OnForecastListener onForecastListener){
        this.weatherForecastList = new ArrayList<>();
        this.onForecastListener = onForecastListener;
    }

    @NonNull
    @Override
    public ForecastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.todo_list_item, parent, false);
        return new ForecastViewHolder(itemView, onForecastListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ForecastViewHolder holder, int position) {
        WeatherForecast forecast = this.weatherForecastList.get(weatherForecastList.size() - position - 1);
        holder.bind(forecast);
    }

    public void addTodo(WeatherForecast forecast){
        this.weatherForecastList.add(forecast);
        //notifyItemInserted(0);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount(){
        return this.weatherForecastList.size();
    }

    class ForecastViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView dateTV;
        private TextView temp_highTV;
        private TextView temp_lowTV;
        private TextView prob_precipTV;
        private TextView short_descTV;
        private TextView long_descTV;
        private OnForecastListener onForecastListener;

        public ForecastViewHolder(@NonNull View itemView, OnForecastListener onForecastListener){
            super(itemView);
            this.dateTV = itemView.findViewById(R.id.tv_date);
            this.temp_highTV = itemView.findViewById(R.id.tv_temp_high);
            this.temp_lowTV = itemView.findViewById(R.id.tv_temp_low);
            this.prob_precipTV = itemView.findViewById(R.id.tv_prob_precip);
            this.short_descTV = itemView.findViewById(R.id.tv_short_desc);
            this.onForecastListener = onForecastListener;
            itemView.setOnClickListener(this);
        }

        void bind(WeatherForecast forecast){
            this.dateTV.setText(forecast.get_date() + "");
            this.temp_highTV.setText(forecast.get_temp_high() + "F°");
            this.temp_lowTV.setText(forecast.get_temp_low() + "F°");
            this.prob_precipTV.setText(forecast.get_prob_precipitation() + "%");
            this.short_descTV.setText(forecast.get_short_desc());
        }

        @Override
        public void onClick(View v) {
            onForecastListener.OnForecastClick(getAdapterPosition());
        }
    }

    public interface OnForecastListener {
        void OnForecastClick(int position);
    }
}
