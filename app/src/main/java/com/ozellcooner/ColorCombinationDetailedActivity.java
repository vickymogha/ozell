package com.ozellcooner;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.ozellcooner.adapter.ColorCombinationTwoHorizontalAdapter;
import com.ozellcooner.adapter.FamilyColorVerticalAdapter;
import com.ozellcooner.fragment.model.ColorFamily;
import com.ozellcooner.fragment.model.CommonColor;
import com.ozellcooner.fragment.model.Datum;
import com.ozellcooner.fragment.model.OzellColorModel;
import com.ozellcooner.view.ColorCombinationView;
import com.ozellcooner.webserive.ColorUtils;
import com.ozellcooner.webserive.DatabaseHandler;
import com.ozellcooner.webserive.VolleySingleton;
import com.ozellcooner.webserive.WebService;

import java.util.ArrayList;

public class ColorCombinationDetailedActivity extends AppCompatActivity {


    VolleySingleton volleySingleton;
    ProgressDialog loaderDialog;
    RecyclerView recyclerViewFamily, recyclerViewColor, recyclerViewBottomMenuColor;
    DatabaseHandler handler;
    TextView menuTextViewColorCode, menuTextViewColorName;
    View menuView, topbarMenu,topbarColorCombiationDialog, colorCombinationViewDialog, colorCombinationBtn, colorPreviewBtn, liveVisulaiserBtn;
    ColorCombinationView monoaticColorCombinationView, anologusColorCombinationView, compliColorCombinationView, analogusCompliColorCombinationView;
    Datum selectedColorFromMenu;

    TextView familyLayoutTextview;
    View familyLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_combination_main);
        handler = new DatabaseHandler(this);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setElevation(0);
//        getSupportActionBar().setDisplayShowTitleEnabled(false);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onBackPressed();
//            }
//        });

        familyLayout = findViewById(R.id.familylayout);
        familyLayoutTextview = (TextView)findViewById(R.id.familylayoutTextView);

        recyclerViewFamily = (RecyclerView) findViewById(R.id.recycleView);
        RecyclerView.LayoutManager mLayoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewFamily.setLayoutManager(mLayoutManager2);

        loaderDialog = new ProgressDialog(this);
        loaderDialog.setMessage("Please wait...");
        loaderDialog.setCancelable(false);


        final ArrayList<ColorFamily> listfamily = handler.getAllColorFamily();
        FamilyColorVerticalAdapter adapter = new FamilyColorVerticalAdapter(this, listfamily);
        recyclerViewFamily.setAdapter(adapter);
        adapter.setOnItemListener(new FamilyColorVerticalAdapter.ItemListener() {
            @Override
            public void onClick(int position) {
                final ArrayList<Datum> list = handler.getAllColors(listfamily.get(position));
                ColorCombinationTwoHorizontalAdapter adapter1 = new ColorCombinationTwoHorizontalAdapter(ColorCombinationDetailedActivity.this, list);
                recyclerViewColor.setAdapter(adapter1);
                adapter1.setOnItemListener(new ColorCombinationTwoHorizontalAdapter.ItemListener() {
                    @Override
                    public void onClick(int position) {
                        showColorCombination(list.get(position));
                    }
                });
                familyLayout.setBackgroundColor(ColorUtils.parseRgb(listfamily.get(position).getRgb()));
                familyLayoutTextview.setText(""+listfamily.get(position).getColor());
                if(list.size()>0) {
                    adapter1.setSelectedIndex(0);
                    showColorCombination(list.get(0));
                }
            }
        });

        recyclerViewColor = (RecyclerView) findViewById(R.id.recycleViewColor);
        GridLayoutManager gridLayout = new GridLayoutManager(ColorCombinationDetailedActivity.this, 2,GridLayoutManager.HORIZONTAL,false);
        recyclerViewColor.setLayoutManager(gridLayout);





       // initMenuOption();
        initColorCombination();
        //dismissMenuOptionBottom();
        //showColorCombinationBottom(list);

    }


    void initColorCombination() {
        monoaticColorCombinationView = (ColorCombinationView) findViewById(R.id.monomaticCombinationView);
        compliColorCombinationView = (ColorCombinationView) findViewById(R.id.complimentyCombinationView);
        analogusCompliColorCombinationView = (ColorCombinationView) findViewById(R.id.anologusCompliCombinationView);
        anologusColorCombinationView = (ColorCombinationView) findViewById(R.id.anologusCombinationView);
    }

    public void showColorCombination(Datum selectedColor) {
        setColorCombination(monoaticColorCombinationView, selectedColor.getMonochromatic());
        setColorCombination(anologusColorCombinationView, selectedColor.getAnalogous());
        setColorCombination(analogusCompliColorCombinationView, selectedColor.getAnalogousComplimentary());
        setColorCombination(compliColorCombinationView, selectedColor.getComplimentary());
    }



    private void setColorCombination(ColorCombinationView view, ArrayList<?> list) {
        ArrayList<CommonColor> li = (ArrayList<CommonColor>) list;
        view.setColor1(li.get(0));
        view.setColor2(li.get(1));
        view.setColor3(li.get(2));
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
                                Toast.makeText(ColorCombinationDetailedActivity.this, "Error", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            //parsing(response);
                            Gson gson = new Gson();
                            OzellColorModel model = gson.fromJson(response, OzellColorModel.class);


                            ArrayList<ColorFamily> dataFamily = model.getColorFamily();
                            // ArrayList<Datum> colorList = model.getData();

//
//                            ColorTrendsMonthAdapter adapter = new ColorTrendsMonthAdapter(SelectYourColorActivity.this, data);
//                            recyclerView.setAdapter(adapter);
//                            adapter.setOnItemListener(new ColorTrendsMonthAdapter.ItemListener() {
//                                @Override
//                                public void onClick(int position) {
//                                    Intent in  = new Intent(SelectYourColorActivity.this,ColorTrendsMainActivity.class);
//                                    in.putExtra("data",data.get(position));
//                                    startActivity(in);
//                                }
//                            });


                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(ColorCombinationDetailedActivity.this, "parsing Error", Toast.LENGTH_SHORT).show();

                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                loaderDialog.dismiss();
                Toast.makeText(ColorCombinationDetailedActivity.this, "Error.. Check your Internet Connection", Toast.LENGTH_SHORT).show();


            }
        });

        volleySingleton.addToRequestQueue(stringRequest);
    }

}
