package com.example.android.lifecycleweather;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.lifecycleweather.data.ForecastCity;
import com.example.android.lifecycleweather.data.ForecastData;

public class ForecastDetailActivity extends AppCompatActivity {
    public static final String EXTRA_FORECAST_DATA = "ForecastDetailActivity.ForecastData";
    public static final String EXTRA_FORECAST_CITY = "ForecastDetailActivity.ForecastCity";
    public static final String EXTRA_UNIT = "ForecastDetailActivity.Unit";

    private ForecastData forecastData = null;
    private ForecastCity forecastCity = null;
    private String unit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast_detail);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(EXTRA_FORECAST_DATA)) {
            this.forecastData = (ForecastData)intent.getSerializableExtra(EXTRA_FORECAST_DATA);
            unit = intent.getStringExtra(EXTRA_UNIT);
            /*
             * Load forecast icon into ImageView using Glide: https://bumptech.github.io/glide/
             */
            ImageView forecastIconIV = findViewById(R.id.iv_forecast_icon);
            Glide.with(this)
                    .load(this.forecastData.getIconUrl())
                    .into(forecastIconIV);

            TextView forecastDateTV = findViewById(R.id.tv_forecast_date);
            forecastDateTV.setText(getString(
                    R.string.forecast_date_time,
                    getString(R.string.forecast_date, forecastData.getDate()),
                    getString(R.string.forecast_time, forecastData.getDate())
            ));

            TextView lowTempTV = findViewById(R.id.tv_low_temp);
            lowTempTV.setText(getString(R.string.forecast_temp, forecastData.getLowTemp(), unit));

            TextView highTempTV = findViewById(R.id.tv_high_temp);
            highTempTV.setText(getString(R.string.forecast_temp, forecastData.getHighTemp(), unit));

            TextView popTV = findViewById(R.id.tv_pop);
            popTV.setText(getString(R.string.forecast_pop, forecastData.getPop()));

            TextView cloudsTV = findViewById(R.id.tv_clouds);
            cloudsTV.setText(getString(R.string.forecast_clouds, forecastData.getCloudCoverage()));

            TextView windTV = findViewById(R.id.tv_wind);
            windTV.setText(getString(R.string.forecast_wind, forecastData.getWindSpeed(), "mph"));

            ImageView windDirIV = findViewById(R.id.iv_wind_dir);
            windDirIV.setRotation(forecastData.getWindDirDeg());

            TextView forecastDescriptionTV = findViewById(R.id.tv_forecast_description);
            forecastDescriptionTV.setText(forecastData.getShortDescription());
        }

        if (intent != null && intent.hasExtra(EXTRA_FORECAST_CITY)) {
            this.forecastCity = (ForecastCity)intent.getSerializableExtra(EXTRA_FORECAST_CITY);
            TextView forecastCityTV = findViewById(R.id.tv_forecast_city);
            forecastCityTV.setText(this.forecastCity.getName());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_forecast_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_share:
                shareForecastText();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * This method uses an implicit intent to launch the Android Sharesheet to allow the user to
     * share the current forecast.
     */
    private void shareForecastText() {
        if (this.forecastData != null && this.forecastCity != null) {
            String shareText = getString(
                    R.string.share_forecast_text,
                    getString(R.string.app_name),
                    this.forecastCity.getName(),
                    getString(
                            R.string.forecast_date_time,
                            getString(R.string.forecast_date, forecastData.getDate()),
                            getString(R.string.forecast_time, forecastData.getDate())
                    ),
                    this.forecastData.getShortDescription(),
                    getString(R.string.forecast_temp, this.forecastData.getHighTemp(), unit),
                    getString(R.string.forecast_temp, this.forecastData.getLowTemp(), unit),
                    getString(R.string.forecast_pop, this.forecastData.getPop())
            );

            Intent sendIntent = new Intent(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, shareText);
            sendIntent.setType("text/plain");

            Intent chooserIntent = Intent.createChooser(sendIntent, null);
            startActivity(chooserIntent);
        }
    }
}