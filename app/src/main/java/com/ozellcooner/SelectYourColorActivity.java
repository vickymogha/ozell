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
import com.ozellcooner.adapter.FamilyColorAdapter;
import com.ozellcooner.fragment.model.ColorFamily;
import com.ozellcooner.fragment.model.Datum;
import com.ozellcooner.fragment.model.OzellColorModel;
import com.ozellcooner.webserive.DatabaseHandler;
import com.ozellcooner.webserive.VolleySingleton;
import com.ozellcooner.webserive.WebService;

import java.util.ArrayList;

public class SelectYourColorActivity extends AppCompatActivity {


    VolleySingleton volleySingleton;
    ProgressDialog loaderDialog;
    RecyclerView recyclerView;
    DatabaseHandler handler;

    //String json = "{\"status\":\"success\",\"color_family\":[{\"color\":\"orange\",\"desc\":\"This is the Color.\",\"rgb\":\"rgb(255,165,0)\"},{\"color\":\"red\",\"desc\":\"This is the Color.\",\"rgb\":\"rgb(255,0,0)\"},{\"color\":\"blue\",\"desc\":\"This is the Color.\",\"rgb\":\"rgb(0,0,255)\"},{\"color\":\"violet\",\"desc\":\"This is the Color.\",\"rgb\":\"rgb(238,130,238)\"},{\"color\":\"blue-green\",\"desc\":\"This is the Color.\",\"rgb\":\"rgb(13,152,186)\"},{\"color\":\"pink\",\"desc\":\"This is the Color.\",\"rgb\":\"rgb(255,192,203)\"},{\"color\":\"grey\",\"desc\":\"This is the Color.\",\"rgb\":\"rgb(128,128,128)\"},{\"color\":\"brown\",\"desc\":\"This is the Color.\",\"rgb\":\"rgb(165,42,42)\"}],\"data\":[[{\"main_rgb\":\"rgb(216, 221, 223)\",\"family\":\"blue\",\"main_color_code\":\"GA 6009\",\"main_color_name\":\"Cool Crystal-N\",\"monochromatic\":[{\"rgb\":\"rgb(216, 221, 223)\",\"color_name\":\"Cool Crystal-N\",\"color_code\":\"GA 6009\"},{\"rgb\":\"rgb(162, 180, 191)\",\"color_name\":\"Bali Blue\",\"color_code\":\"GA 6030\"},{\"rgb\":\"rgb(105, 115, 124)\",\"color_name\":\"Granite\",\"color_code\":\"GA 6003\"}],\"complimentary\":[{\"rgb\":\"rgb(247, 235, 233)\",\"color_name\":\"Soft Breeze\",\"color_code\":\"RD 5032\"},{\"rgb\":\"rgb(246, 220, 222)\",\"color_name\":\"Graceful Pink\",\"color_code\":\"J1380\"},{\"rgb\":\"rgb(216, 221, 223)\",\"color_name\":\"Cool Crystal-N\",\"color_code\":\"GA 6009\"}],\"analogous\":[{\"rgb\":\"rgb(216, 221, 223)\",\"color_name\":\"Cool Crystal-N\",\"color_code\":\"GA 6009\"},{\"rgb\":\"rgb(225, 230, 230)\",\"color_name\":\"Mirage White\",\"color_code\":\"J1420\"},{\"rgb\":\"rgb(182, 198, 207)\",\"color_name\":\"Water&#39;S Edge\",\"color_code\":\"8274\"}],\"analogous_complimentary\":[{\"rgb\":\"rgb(216, 221, 223)\",\"color_name\":\"Cool Crystal-N\",\"color_code\":\"GA 6009\"},{\"rgb\":\"rgb(255, 220, 199)\",\"color_name\":\"Daylight\",\"color_code\":\"J1341\"},{\"rgb\":\"rgb(224, 239, 237)\",\"color_name\":\"Sail Boat\",\"color_code\":\"BL 0670\"}]}],[{\"main_rgb\":\"rgb(193, 204, 209)\",\"family\":\"blue\",\"main_color_code\":\"GA 6031\",\"main_color_name\":\"Sensibility\",\"monochromatic\":[{\"rgb\":\"rgb(193, 204, 209)\",\"color_name\":\"Sensibility\",\"color_code\":\"GA 6031\"},{\"rgb\":\"rgb(144, 157, 168)\",\"color_name\":\"Brook View\",\"color_code\":\"GA 6029\"},{\"rgb\":\"rgb(82, 81, 85)\",\"color_name\":\"Thunder Cloud\",\"color_code\":\"GA 6026\"}],\"complimentary\":[{\"rgb\":\"rgb(246, 220, 222)\",\"color_name\":\"Graceful Pink\",\"color_code\":\"J1380\"},{\"rgb\":\"rgb(240, 197, 203)\",\"color_name\":\"Merrie Pink\",\"color_code\":\"J1381\"},{\"rgb\":\"rgb(193, 204, 209)\",\"color_name\":\"Sensibility\",\"color_code\":\"GA 6031\"}],\"analogous\":[{\"rgb\":\"rgb(193, 204, 209)\",\"color_name\":\"Sensibility\",\"color_code\":\"GA 6031\"},{\"rgb\":\"rgb(207, 217, 221)\",\"color_name\":\"Clear Sky\",\"color_code\":\"GA 6035\"},{\"rgb\":\"rgb(160, 180, 193)\",\"color_name\":\"Grand Bay\",\"color_code\":\"GA 6034\"}],\"analogous_complimentary\":[{\"rgb\":\"rgb(193, 204, 209)\",\"color_name\":\"Sensibility\",\"color_code\":\"GA 6031\"},{\"rgb\":\"rgb(255, 207, 183)\",\"color_name\":\"Popsicle\",\"color_code\":\"OR 4028\"},{\"rgb\":\"rgb(197, 231, 235)\",\"color_name\":\"Inner Peace\",\"color_code\":\"BL 0669\"}]}],[{\"main_rgb\":\"rgb(162, 180, 191)\",\"family\":\"blue\",\"main_color_code\":\"GA 6030\",\"main_color_name\":\"Bali Blue\",\"monochromatic\":[{\"rgb\":\"rgb(162, 180, 191)\",\"color_name\":\"Bali Blue\",\"color_code\":\"GA 6030\"},{\"rgb\":\"rgb(120, 134, 146)\",\"color_name\":\"Voltage\",\"color_code\":\"GA 6028\"},{\"rgb\":\"rgb(216, 221, 223)\",\"color_name\":\"Cool Crystal-N\",\"color_code\":\"GA 6009\"}],\"complimentary\":[{\"rgb\":\"rgb(240, 197, 203)\",\"color_name\":\"Merrie Pink\",\"color_code\":\"J1381\"},{\"rgb\":\"rgb(228, 174, 183)\",\"color_name\":\"Bed Of Roses\",\"color_code\":\"RD 5032\"},{\"rgb\":\"rgb(162, 180, 191)\",\"color_name\":\"Bali Blue\",\"color_code\":\"GA 6030\"}],\"analogous\":[{\"rgb\":\"rgb(162, 180, 191)\",\"color_name\":\"Bali Blue\",\"color_code\":\"GA 6030\"},{\"rgb\":\"rgb(182, 198, 207)\",\"color_name\":\"Water&#39;S Edge\",\"color_code\":\"8274\"},{\"rgb\":\"rgb(144, 163, 177)\",\"color_name\":\"Blue Horizon\",\"color_code\":\"GA 6033\"}],\"analogous_complimentary\":[{\"rgb\":\"rgb(162, 180, 191)\",\"color_name\":\"Bali Blue\",\"color_code\":\"GA 6030\"},{\"rgb\":\"rgb(255, 187, 159)\",\"color_name\":\"Orange Nectar\",\"color_code\":\"J1342\"},{\"rgb\":\"rgb(158, 219, 228)\",\"color_name\":\"Sea Surf\",\"color_code\":\"BL 0668\"}]}]]}";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_your_color1);
        handler = new DatabaseHandler(this);
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

        recyclerView = (RecyclerView) findViewById(R.id.recycleView);
        RecyclerView.LayoutManager mLayoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager2);

        loaderDialog = new ProgressDialog(this);
        loaderDialog.setMessage("Please wait...");
        loaderDialog.setCancelable(false);
        volleySingleton = VolleySingleton.getInstance(this);

        //hitListOfAllTypeColorService();

//        Gson gson = new Gson();
//        OzellColorModel model = gson.fromJson(json, OzellColorModel.class);
//
//
//
//        ArrayList<ArrayList<Datum>> colorList = model.getData();
//        ArrayList<ColorFamily> dataFamily = model.getColorFamily();
//        for(int i=0;i<colorList.size();i++)
//        {
//
//            handler.addColor(colorList.get(i).get(0));
//        }
//        for(int i=0;i<dataFamily.size();i++)
//        {
//            handler.addFamilyColor(dataFamily.get(i));
//        }


        ArrayList<ColorFamily> listfamily = handler.getAllColorFamily();

        FamilyColorAdapter adapter = new FamilyColorAdapter(SelectYourColorActivity.this, listfamily);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemListener(new FamilyColorAdapter.ItemListener() {
            @Override
            public void onClick(int position) {
                Intent in = new Intent(SelectYourColorActivity.this, SelectYourColorDetailedActivity.class);
                startActivity(in);
            }
        });

        if (listfamily.size() == 0) {
            hitListOfAllTypeColorService();
        }

        //volleySingleton = VolleySingleton.getInstance(this);

    }

    public void hitListOfAllTypeColorService() {
        String Url = WebService.getListOfAllTypeColorUrl();
        loaderDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        loaderDialog.dismiss();
                        try {

                            if (TextUtils.isEmpty(response)) {
                                Toast.makeText(SelectYourColorActivity.this, "Error", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            //parsing(response);
                            Gson gson = new Gson();
                            OzellColorModel model = gson.fromJson(response, OzellColorModel.class);
                            ArrayList<ArrayList<Datum>> colorList = model.getData();
                            ArrayList<ColorFamily> dataFamily = model.getColorFamily();
                            for (int i = 0; i < colorList.size(); i++) {

                                handler.addColor(colorList.get(i).get(0));
                            }
                            for (int i = 0; i < dataFamily.size(); i++) {
                                handler.addFamilyColor(dataFamily.get(i));
                            }

                            ArrayList<ColorFamily> listfamily = handler.getAllColorFamily();


                            FamilyColorAdapter adapter = new FamilyColorAdapter(SelectYourColorActivity.this, listfamily);
                            recyclerView.setAdapter(adapter);
                            adapter.setOnItemListener(new FamilyColorAdapter.ItemListener() {
                                @Override
                                public void onClick(int position) {
                                    Intent in = new Intent(SelectYourColorActivity.this, SelectYourColorDetailedActivity.class);
                                    startActivity(in);
                                }
                            });


                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(SelectYourColorActivity.this, "parsing Error", Toast.LENGTH_SHORT).show();

                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                loaderDialog.dismiss();
                Toast.makeText(SelectYourColorActivity.this, "Error.. Check your Internet Connection", Toast.LENGTH_SHORT).show();


            }
        });

        volleySingleton.addToRequestQueue(stringRequest);
    }

}
