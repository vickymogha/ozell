package com.ozellcooner;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.ozellcooner.fragment.model.ColorTrendsDatum;
import com.ozellcooner.webserive.ColorUtils;

public class ColorTrendsMainActivity extends AppCompatActivity {

    ColorTrendsDatum data;
    View colorCodeAndNameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_trends_main);
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


        data = (ColorTrendsDatum)getIntent().getSerializableExtra("data");

        colorCodeAndNameLayout = findViewById(R.id.colorCodeAndNameLayout);
        colorCodeAndNameLayout.setBackgroundColor(ColorUtils.parseRgb(data.getRgb()));


    }



}
