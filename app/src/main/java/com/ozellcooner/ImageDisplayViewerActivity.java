package com.ozellcooner;

import android.app.ProgressDialog;
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
import com.ozellcooner.adapter.ImageDisplayAdapter;
import com.ozellcooner.fragment.model.ColorTheroryModel;
import com.ozellcooner.fragment.model.DataModel;
import com.ozellcooner.fragment.model.ImageDisplayModel;
import com.ozellcooner.webserive.VolleySingleton;
import com.ozellcooner.webserive.WebService;

import java.util.ArrayList;

public class ImageDisplayViewerActivity extends AppCompatActivity {


    RecyclerView recyclerView1;
    ArrayList<ImageDisplayModel> list;
    VolleySingleton volleySingleton;
    ProgressDialog loaderDialog;

    String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_display);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setElevation(0);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        type = getIntent().getStringExtra("type");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        loaderDialog = new ProgressDialog(this);
        loaderDialog.setMessage("Please wait...");
        loaderDialog.setCancelable(false);
        volleySingleton = VolleySingleton.getInstance(this);

        list = new ArrayList<>();
        list.add(new ImageDisplayModel());
        list.add(new ImageDisplayModel());
        recyclerView1 = (RecyclerView) findViewById(R.id.recycleView);
        RecyclerView.LayoutManager mLayoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView1.setLayoutManager(mLayoutManager2);
//        ImageDisplayAdapter adapter = new ImageDisplayAdapter(list);
//        recyclerView1.setAdapter(adapter);
        hitServiceReport();
    }

    public void hitServiceReport() {
        String Url = WebService.getImageUploadDataMethodURL(type);
        loaderDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        loaderDialog.dismiss();
                        try {

                            if (TextUtils.isEmpty(response)) {
                                Toast.makeText(ImageDisplayViewerActivity.this, "Error", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            //parsing(response);
                            Gson gson = new Gson();
                            ColorTheroryModel model = gson.fromJson(response, ColorTheroryModel.class);

                            ArrayList<DataModel> data = model.getData();
                            ImageDisplayAdapter adapter = new ImageDisplayAdapter(ImageDisplayViewerActivity.this, data);
                            recyclerView1.setAdapter(adapter);


                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(ImageDisplayViewerActivity.this, "parsing Error", Toast.LENGTH_SHORT).show();

                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                loaderDialog.dismiss();
                Toast.makeText(ImageDisplayViewerActivity.this, "Error.. Check your Internet Connection", Toast.LENGTH_SHORT).show();


            }
        });

        volleySingleton.addToRequestQueue(stringRequest);
    }


}
