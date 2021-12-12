package com.example.weatherchecker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Favory extends AppCompatActivity {
    public static final String Name= "MESSAGE";

    ListView liste2;
  private  DatabaseManager databaseManager;
    ArrayAdapter arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favory);
        liste2=findViewById(R.id.Liste2);
       databaseManager=new DatabaseManager(Favory.this);
       arrayAdapter=databaseManager.readCity();
       liste2.setAdapter(arrayAdapter);
       liste2.setClickable(true);
       liste2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
               Intent intent=new Intent(Favory.this,ViewFavory.class);
            intent.putExtra(Name,arrayAdapter.getItem(position).toString());
              // Toast.makeText(Favory.this,arrayAdapter.getItem(position).toString(),Toast.LENGTH_SHORT).show()
            ;
               startActivity(intent);
           }
       });
    }
}