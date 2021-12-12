package com.example.weatherchecker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.telephony.SmsManager;
import android.widget.TextView;
import android.widget.Toast;

public class ViewFavory extends AppCompatActivity {
    ListView liste3;
    ArrayAdapter arrayAdapter;
Button delete;
TextView t1;
    String cityName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_favory);
        WeatherData weatherData=new WeatherData(this);
        DatabaseManager databaseManager=new DatabaseManager(this);
        liste3=findViewById(R.id.listee);
        delete=findViewById(R.id.delete);
        t1=findViewById(R.id.title);
        Intent intent = getIntent();
        cityName = intent.getStringExtra(Favory.Name);
        t1.setText(cityName);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cityID = weatherData.getCityID(cityName.toString());
                //Toast.makeText(ViewFavory.this, cityID, Toast.LENGTH_SHORT).show();
                ArrayAdapter arrayAdapter=weatherData.getCityForcastById(cityID);
                //Toast.makeText(ViewFavory.this, arrayAdapter.toString(), Toast.LENGTH_SHORT).show();
                liste3.setAdapter(arrayAdapter);
            }
        });
    }

}