package com.example.android.lifecycleweather;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.lifecycleweather.data.FiveDayForecast;
import com.example.android.lifecycleweather.data.ForecastData;
import com.example.android.lifecycleweather.utils.OpenWeatherUtils;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ForecastItemViewHolder> {
    private List<ForecastData> forecastDataList;
    private String currentUnit;
    private OnForecastItemClickListener onForecastItemClickListener;

    public interface OnForecastItemClickListener {
        void onForecastItemClick(ForecastData forecastData, String unit);
    }

    public ForecastAdapter(OnForecastItemClickListener onForecastItemClickListener) {
        forecastDataList = new ArrayList<>();
        this.onForecastItemClickListener = onForecastItemClickListener;
    }

    @NonNull
    @Override
    public ForecastItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.forecast_list_item, parent, false);
        return new ForecastItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ForecastItemViewHolder holder, int position) {
        holder.bind(this.forecastDataList.get(position), this.currentUnit);
    }

    public void updateForecastData(List<ForecastData> forecastDataList, String unit) {
        this.forecastDataList = forecastDataList;
        this.currentUnit = unit;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return this.forecastDataList.size();
    }

    class ForecastItemViewHolder extends RecyclerView.ViewHolder {
        final private TextView dateTV;
        final private TextView timeTV;
        final private TextView highTempTV;
        final private TextView lowTempTV;
        final private TextView popTV;
        final private ImageView iconIV;

        public ForecastItemViewHolder(@NonNull View itemView) {
            super(itemView);
            dateTV = itemView.findViewById(R.id.tv_date);
            timeTV = itemView.findViewById(R.id.tv_time);
            highTempTV = itemView.findViewById(R.id.tv_high_temp);
            lowTempTV = itemView.findViewById(R.id.tv_low_temp);
            popTV = itemView.findViewById(R.id.tv_pop);
            iconIV = itemView.findViewById(R.id.iv_forecast_icon);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onForecastItemClickListener.onForecastItemClick(forecastDataList.get(getAdapterPosition()), currentUnit);
                }
            });
        }

        public void bind(ForecastData forecastData, String units) {
            Context ctx = this.itemView.getContext();
            dateTV.setText(ctx.getString(R.string.forecast_date, forecastData.getDate()));
            timeTV.setText(ctx.getString(R.string.forecast_time, forecastData.getDate()));
            highTempTV.setText(ctx.getString(R.string.forecast_temp, forecastData.getHighTemp(), units));
            lowTempTV.setText(ctx.getString(R.string.forecast_temp, forecastData.getLowTemp(), units));
            popTV.setText(ctx.getString(R.string.forecast_pop, forecastData.getPop()));

            /*
             * Load forecast icon into ImageView using Glide: https://bumptech.github.io/glide/
             */
            Glide.with(ctx)
                    .load(forecastData.getIconUrl())
                    .into(iconIV);
        }

    }
}
