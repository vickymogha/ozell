package com.ozellcooner;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.ozellcooner.adapter.ColorTrendsMonthAdapter;
import com.ozellcooner.fragment.model.ColorTrendsModel;
import com.ozellcooner.fragment.model.ColorTrendsDatum;
import com.ozellcooner.webserive.VolleySingleton;
import com.ozellcooner.webserive.WebService;

import java.util.ArrayList;

public class ColorTrendsNextActivity extends AppCompatActivity {



    VolleySingleton volleySingleton;
    ProgressDialog loaderDialog;
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_trends_next);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setElevation(0);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    onBackPressed();
            }
        });

        recyclerView = (RecyclerView)findViewById(R.id.recycleView);
        RecyclerView.LayoutManager mLayoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager2);

        loaderDialog = new ProgressDialog(this);
        loaderDialog.setMessage("Please wait...");
        loaderDialog.setCancelable(false);
        volleySingleton = VolleySingleton.getInstance(this);

        hitColorTrendsService();
    }

    public void hitColorTrendsService() {
        String Url = WebService.getColorTrendsUrl();
        loaderDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        loaderDialog.dismiss();
                        try {

                            if (TextUtils.isEmpty(response)) {
                                Toast.makeText(ColorTrendsNextActivity.this, "Error", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            //parsing(response);
                            Gson gson = new Gson();
                            ColorTrendsModel model = gson.fromJson(response, ColorTrendsModel.class);

                           final ArrayList<ColorTrendsDatum> data = model.getData();

                            ColorTrendsMonthAdapter adapter = new ColorTrendsMonthAdapter(ColorTrendsNextActivity.this, data);
                            recyclerView.setAdapter(adapter);
                            adapter.setOnItemListener(new ColorTrendsMonthAdapter.ItemListener() {
                                @Override
                                public void onClick(int position) {
                                    Intent in  = new Intent(ColorTrendsNextActivity.this,ColorTrendsMainActivity.class);
                                    in.putExtra("data",data.get(position));
                                    startActivity(in);
                                }
                            });


                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(ColorTrendsNextActivity.this, "parsing Error", Toast.LENGTH_SHORT).show();

                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                loaderDialog.dismiss();
                Toast.makeText(ColorTrendsNextActivity.this, "Error.. Check your Internet Connection", Toast.LENGTH_SHORT).show();


            }
        });

        volleySingleton.addToRequestQueue(stringRequest);
    }

}
