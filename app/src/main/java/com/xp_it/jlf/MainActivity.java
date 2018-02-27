package com.xp_it.jlf;

import android.app.DownloadManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.gsm.GsmCellLocation;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String ENDPOINT = "http://api.ferralis.fr/api/news.json";

    private RequestQueue requestQueue;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestQueue = Volley.newRequestQueue(getApplicationContext());
        GsonBuilder gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.create();

        fetchNews();
    }

    private void fetchNews() {
        StringRequest request = new StringRequest(Request.Method.GET, ENDPOINT, onNewsLoaded, onNewsError);
        requestQueue.add(request);
    }

    private final Response.Listener<String> onNewsLoaded = new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            Log.i(MainActivity.class.getSimpleName(), response);

            List<News> newsList = Arrays.asList(gson.fromJson(response, News[].class));
            Log.i(MainActivity.class.getSimpleName(), newsList.size() + " posts loaded.");

            for (News news : newsList) {
                Log.i(MainActivity.class.getSimpleName(), news.ID + ": " + news.title);
            }
        }
    };

    private final Response.ErrorListener onNewsError = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Log.e(MainActivity.class.getSimpleName(), error.toString());
        }
    };
}
