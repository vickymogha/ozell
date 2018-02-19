package com.ozellcooner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

public class BenefitOfLatexActivity extends AppCompatActivity {


    ImageView nextButton;
    ImageView imageView;

    int drawableArrayList[] = {R.drawable.ds_benefit_of_latex};
    int currentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_therory);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setElevation(0);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentIndex >= 1) {
                    currentIndex--;
                    imageView.setImageResource(drawableArrayList[currentIndex]);
                } else {
                    onBackPressed();
                }
            }
        });

        imageView = (ImageView) findViewById(R.id.imageView1);
        nextButton = (ImageView) findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentIndex < drawableArrayList.length - 1) {
                    currentIndex++;
                    imageView.setImageResource(drawableArrayList[currentIndex]);
                } else {
                    Intent in = new Intent(BenefitOfLatexActivity.this, ImageDisplayViewerActivity.class);
                    in.putExtra("type","3");
                    startActivity(in);
                }
            }
        });

        imageView.setImageResource(drawableArrayList[currentIndex]);


    }

}
