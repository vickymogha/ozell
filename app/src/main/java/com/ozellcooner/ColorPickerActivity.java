package com.ozellcooner;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ozellcooner.adapter.OzellColorAdapter;
import com.ozellcooner.model.OzellColor;

import java.util.ArrayList;
import java.util.List;

public class ColorPickerActivity extends AppCompatActivity {
    //List<Palette.Swatch> list;
    ArrayList<OzellColor> colorsList;
    RecyclerView recyclerView;
    MyView myView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.color_picker_activityt);
        myView = (MyView)findViewById(R.id.myView);
        recyclerView = (RecyclerView)findViewById(R.id.recycleView);
        RecyclerView.LayoutManager mLayoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(mLayoutManager2);
        colorsList = new ArrayList<>();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.baby);
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                List<Palette.Swatch> list =  palette.getSwatches();
                for(int i=0;i<list.size();i++)
                {
                    OzellColor color1 = new OzellColor();
                    Palette.Swatch swatch = list.get(i);
                    color1.setColor(swatch.getRgb());
                    colorsList.add(color1);

                }
                OzellColorAdapter adapter = new OzellColorAdapter(colorsList);
                recyclerView.setAdapter(adapter);
                myView.setColorsList(colorsList);
                myView.invalidate();

            }
        });


    }
}
