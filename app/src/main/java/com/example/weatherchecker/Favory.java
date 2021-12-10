package com.example.weatherchecker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Favory extends AppCompatActivity {
    ListView liste2;
  private  DatabaseManager databaseManager;
    ArrayAdapter arrayAdapter;
    // ArrayList<String>arrayList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favory);
        liste2=findViewById(R.id.Liste2);
        //arrayAdapter =new ArrayAdapter<>(liste2.getContext(), R.layout.support_simple_spinner_dropdown_item);
        // Intent intent = getIntent();
        //arrayList=intent.getStringArrayListExtra(MainActivity.EXTRA_MESSAGE);
       databaseManager=new DatabaseManager(Favory.this);
       arrayAdapter=databaseManager.readCity();
       liste2.setAdapter(arrayAdapter);
    }
}