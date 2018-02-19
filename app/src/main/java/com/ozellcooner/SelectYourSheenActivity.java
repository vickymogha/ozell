package com.ozellcooner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

public class SelectYourSheenActivity extends AppCompatActivity {


    ImageView nextButton;
    ImageView imageView;

    int drawableArrayList[] = {R.drawable.color_therory_icon};
    int currentIndex = 0;
    View textViewLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_your_sheen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setElevation(0);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //back();
                onBackPressed();
            }
        });

        textViewLayout = findViewById(R.id.textViewLayout);
        imageView = (ImageView) findViewById(R.id.imageView1);
        nextButton = (ImageView) findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(textViewLayout.getVisibility()==View.VISIBLE)
                {
                    Intent in = new Intent(SelectYourSheenActivity.this, ImageDisplayViewerSheenActivity.class);
                    in.putExtra("type","1");
                    startActivity(in);
                }else {

                    if (currentIndex < drawableArrayList.length - 1) {
                        currentIndex++;
                        imageView.setImageResource(drawableArrayList[currentIndex]);
                        textViewLayout.setVisibility(View.INVISIBLE);
                    } else {
                        imageView.setVisibility(View.INVISIBLE);
                        textViewLayout.setVisibility(View.VISIBLE);
                    }
                }
            }
        });

        //imageView.setImageResource(drawableArrayList[currentIndex]);


    }

    public void back()
    {
        if(textViewLayout.getVisibility()==View.INVISIBLE) {
            if (currentIndex >= 1) {
                currentIndex--;
                imageView.setImageResource(drawableArrayList[currentIndex]);
            } else {
                super.onBackPressed();
            }
        }else
        {
            imageView.setVisibility(View.VISIBLE);
            textViewLayout.setVisibility(View.INVISIBLE);
        }
    }
}
