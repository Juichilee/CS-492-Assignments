package com.example.android.sqliteweather;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.sqliteweather.data.FiveDayForecast;
import com.example.android.sqliteweather.data.ForecastData;
import com.example.android.sqliteweather.data.ForecastLocationEntity;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ForecastLocationAdapter extends RecyclerView.Adapter<ForecastLocationAdapter.ForecastLocationViewHolder>{
    String TAG = ForecastLocationAdapter.class.getName();

    private List<ForecastLocationEntity> forecastLocationEntities;
    private OnForecastLocationClickListener onForecastLocationClickListener;

    public interface OnForecastLocationClickListener {
        void onForecastLocationClick(ForecastLocationEntity forecastLocationEntity);
    }

    public ForecastLocationAdapter(OnForecastLocationClickListener onForecastLocationClickListener) {
        this.forecastLocationEntities = new ArrayList<>();
        this.onForecastLocationClickListener = onForecastLocationClickListener;
    }

    @NonNull
    @Override
    public ForecastLocationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.forecast_location, parent, false);
        return new ForecastLocationAdapter.ForecastLocationViewHolder(itemView);
    }

    public void updateForecastLocations(List<ForecastLocationEntity> locations){
        this.forecastLocationEntities = locations;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull ForecastLocationAdapter.ForecastLocationViewHolder holder, int position) {
        Log.d(TAG, "Binded Location Name: " + forecastLocationEntities.get(position).locationName);
        holder.bind(forecastLocationEntities.get(position).locationName);
    }

    @Override
    public int getItemCount() {
        if(this.forecastLocationEntities == null){
            return 0;
        }else{
            Log.d(TAG, "ForecastLocationSize: " + forecastLocationEntities.size());
            return forecastLocationEntities.size();
        }
    }

    class ForecastLocationViewHolder extends RecyclerView.ViewHolder {
        final private TextView locationTV;

        public ForecastLocationViewHolder(@NonNull View itemView) {
            super(itemView);
            locationTV = itemView.findViewById(R.id.tv_location_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onForecastLocationClickListener.onForecastLocationClick(
                            forecastLocationEntities.get(getAdapterPosition())
                    );
                }
            });
        }
        public void bind(String locationName) {
            locationTV.setText(locationName);
        }
    }
}
