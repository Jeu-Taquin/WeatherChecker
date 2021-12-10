package com.example.weatherchecker;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
  //  public static final String EXTRA_MESSAGE = "WEATHER.MESSAGE";

    Button btn_viewWeather,btn_addFavory,btn_viewFavory;
EditText cityName;
ListView liste;
    private DatabaseManager databaseManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("DATABASE", "onCreate invoked");

        btn_addFavory=findViewById(R.id.addFavory);
        btn_viewFavory=findViewById(R.id.viewFavory);
        btn_viewWeather=findViewById(R.id.viewWeather);
        cityName=findViewById(R.id.nameCity);
        liste=findViewById(R.id.listView1);
        WeatherData weatherData=new WeatherData(MainActivity.this);
         databaseManager=new DatabaseManager(this);

        btn_viewWeather.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
               String cityID = weatherData.getCityID(cityName.getText().toString());
                weatherData.getCityForcastById(cityID);

                ArrayAdapter arrayAdapter=weatherData.getCityForcastById(cityID);
           liste.setAdapter(arrayAdapter);
            }});


        btn_viewFavory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayAdapter arrayAdapter=databaseManager.readCity();
                liste.setAdapter(arrayAdapter);
           //     Intent intent=new Intent(MainActivity.this,Favory.class);
           //     ArrayList<String> arrayAdapter1=databaseManager.readCity();
              //  intent.putExtra(EXTRA_MESSAGE,arrayAdapter1);
             //   startActivity(intent);

            }
        });
        btn_addFavory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseManager.insertCity(cityName.getText().toString());

            }
        });

    }
}