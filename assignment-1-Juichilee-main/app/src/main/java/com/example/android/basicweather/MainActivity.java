package com.example.android.basicweather;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements WeatherAdapter.OnForecastListener {

    private ArrayList<WeatherForecast> weatherForecastList;

    private RecyclerView todoListRV;
    private WeatherAdapter weatherAdapter;
    private Toast forecastToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initializeForecastList(this.weatherForecastList);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.weatherAdapter = new WeatherAdapter(this);

//        mTodoEntryEditText = (EditText)findViewById(R.id.et_todo_entry_box);
        this.todoListRV = findViewById(R.id.rv_forecast_list);

        this.todoListRV.setAdapter(this.weatherAdapter);
        this.todoListRV.setLayoutManager(new LinearLayoutManager(this));
        this.todoListRV.setHasFixedSize(true);
        this.todoListRV.setItemAnimator(new DefaultItemAnimator());

        this.weatherForecastList = initializeForecastList(this.weatherForecastList);
        for(int i = 0; i < weatherForecastList.size(); i++){
            //Log.println(Log.DEBUG, null, "DATE:" + weatherForecastList.get(i).get_date());
            weatherAdapter.addTodo(weatherForecastList.get(i));
        }

        //mTodoList = new ArrayDeque<String>();
        //Button addTodoButton = (Button)findViewById(R.id.btn_add_todo);

//        addTodoButton.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v){
//                String todoText = mTodoEntryEditText.getText().toString();
//                if(!TextUtils.isEmpty(todoText)){
//                    mTodoEntryEditText.setText("");
//                    weatherAdapter.addTodo(todoText);
//                    todoListRV.scrollToPosition(0);
//                    //mTodoList.push(todoText);
//                    //mTodoListTextView.setText("");
//                    /*
//                    for(String todo : mTodoList){
//                        //mTodoListTextView.append(todo + "\n\n");
//                    }
//                     */
//                }
//            }
//        });
        //mTodoListTextView.setText("Finish my TODO app.");
    }

    private ArrayList<WeatherForecast> initializeForecastList(ArrayList<WeatherForecast> weatherForecastList){
        weatherForecastList = new ArrayList<>();
        weatherForecastList.add(new WeatherForecast("1/31/2021", 80, 66, 35.0f, "Warm", "Warmer than average and increasing. Partly cloudy and hazy. Calm/light breeze."));
        weatherForecastList.add(new WeatherForecast("1/30/2021", 62, 50, 50.0f, "Cold", "Cooler than average. Partly cloudy. Light winds."));
        weatherForecastList.add(new WeatherForecast("1/29/2021", 52, 36, 80.0f, "Cold", "Chilly outside. Very cloudy with intense precipitation. Calm."));
        weatherForecastList.add(new WeatherForecast("1/28/2021", 76, 57, 30.0f, "Warm", "Warmer than average and increasing. Cloudy with little to no precipitation. Calm/light breeze."));
        weatherForecastList.add(new WeatherForecast("1/27/2021", 70, 50, 50.0f, "Warm", "Warmer than average. Partly cloudy. Light winds."));
        weatherForecastList.add(new WeatherForecast("1/26/2021", 65, 59, 25.0f, "Average", "Average temperatures with slight warming. Scattered clouds. Calm/light breeze."));
        weatherForecastList.add(new WeatherForecast("1/25/2021", 68, 60, 20.0f, "Average", "Average temperatures. Clear skies. Calm."));
        weatherForecastList.add(new WeatherForecast("1/24/2021", 62, 50, 50.0f, "Cold", "Cooler than average. Partly cloudy. Light winds."));
        weatherForecastList.add(new WeatherForecast("1/23/2021", 52, 36, 80.0f, "Cold", "Chilly outside. Very cloudy with intense precipitation. Calm."));
        weatherForecastList.add(new WeatherForecast("1/22/2021", 62, 50, 50.0f, "Cold", "Cooler than average. Partly cloudy. Light winds."));
        return weatherForecastList;
    }

    @Override
    public void OnForecastClick(int position) {
        //Log.println(Log.DEBUG, null, "Clicked on item in pos: " + position);
        if(this.forecastToast != null){
            this.forecastToast.cancel();
        }
        String toastText = weatherForecastList.get(weatherForecastList.size() - position - 1).get_long_desc();
        this.forecastToast = Toast.makeText(this, toastText, Toast.LENGTH_LONG);
        this.forecastToast.show();
    }
}