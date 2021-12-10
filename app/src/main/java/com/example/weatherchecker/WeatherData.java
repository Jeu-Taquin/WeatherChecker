package com.example.weatherchecker;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class WeatherData {
        public static final String URL ="https://www.metaweather.com/api/location/search/?query=";
        public static final String URL_report ="https://www.metaweather.com/api/location/";
        Context context;
String cityID;
        public WeatherData(Context context) {
                this.context = context;
        }

        public String getCityID(String cityName){
                RequestQueue queue = Volley.newRequestQueue(context);
                String url=URL+cityName;
                JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET,url,null, new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                                cityID = "";
                                try {
                                        JSONObject cityinfo= response.getJSONObject(0);
                                        cityID=cityinfo.getString("woeid");
                                } catch (JSONException e) {
                                        e.printStackTrace();
                                }
                               //Toast.makeText(context,response.toString(),Toast.LENGTH_SHORT).show();
                        }
                }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                                Toast.makeText(context,"Something wrong",Toast.LENGTH_SHORT).show();
                        }
                });
                MySingleton.getInstance(context).addToRequestQueue(request);
                return cityID;
        }

public ArrayAdapter getCityForcastById(String cityID) {
        List<WeatherReportModel> report = new ArrayList<>();
        ArrayAdapter arrayAdapter=new ArrayAdapter(context,R.layout.support_simple_spinner_dropdown_item);

        String url = URL_report + cityID+"/";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject response) {
                        try {


                                JSONArray consolidate_weather_list=response.getJSONArray("consolidated_weather");
                                WeatherReportModel oneDay = new WeatherReportModel();

for (int i=0;i<consolidate_weather_list.length();i++) {
        JSONObject first_day_from_api= (JSONObject) consolidate_weather_list.get(i);
        oneDay.setId(first_day_from_api.getInt("id"));
        oneDay.setWeather_state_abbr(first_day_from_api.getString("weather_state_abbr"));
        oneDay.setWeather_state_name(first_day_from_api.getString("weather_state_name"));
        oneDay.setWind_direction_compass(first_day_from_api.getString("wind_direction_compass"));
        oneDay.setCreated(first_day_from_api.getString("created"));
        oneDay.setApplicable_date(first_day_from_api.getString("applicable_date"));
        oneDay.setMin_temp(first_day_from_api.getLong("min_temp"));
        oneDay.setMax_temp(first_day_from_api.getLong("max_temp"));
        oneDay.setThe_temp(first_day_from_api.getLong("wind_speed"));
        oneDay.setWind_direction(first_day_from_api.getLong("wind_direction"));
        oneDay.setAir_pressure(first_day_from_api.getLong("air_pressure"));
        oneDay.setHumidity(first_day_from_api.getLong("humidity"));
        oneDay.setVisibility(first_day_from_api.getLong("visibility"));
        oneDay.setPredictability(first_day_from_api.getLong("predictability"));
        arrayAdapter.add(oneDay.toString());
}
                              //  Toast.makeText(context, response.toString(),Toast.LENGTH_SHORT).show();
                                Toast.makeText(context, oneDay.toString(),Toast.LENGTH_SHORT).show();



                        } catch (JSONException e) {
                                e.printStackTrace();
                        }
                }
        }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
        });
        MySingleton.getInstance(context).addToRequestQueue(request);

        //  public List<WeatherReportModel> getCityForcastByName(String cityName){}
        return arrayAdapter;
}

}

