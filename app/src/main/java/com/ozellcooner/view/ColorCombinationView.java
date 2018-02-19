package com.ozellcooner.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.ozellcooner.R;
import com.ozellcooner.fragment.model.CommonColor;
import com.ozellcooner.webserive.ColorUtils;

/**
 * Created by Admin on 2/15/2018.
 */

public class ColorCombinationView extends RelativeLayout {

    CommonColor color1, color2, color3;
    View colorView1, colorView2, colorView3;


    public ColorCombinationView(Context context) {
        super(context);
        init(context);
    }

    public ColorCombinationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ColorCombinationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.color_combination_view_layout, null);
        addView(view);

        colorView1 = findViewById(R.id.colorlayout1);
        colorView2 = findViewById(R.id.colorlayout2);
        colorView3 = findViewById(R.id.colorlayout3);

        colorView1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        colorView2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        colorView3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (widthMeasureSpec < heightMeasureSpec) {
            super.onMeasure(widthMeasureSpec, widthMeasureSpec);
        } else {
            super.onMeasure(heightMeasureSpec, heightMeasureSpec);
        }
    }

    public void setColor1(CommonColor color) {
        this.color1 = color;
        if (color != null) {
            this.colorView1.setBackgroundColor(ColorUtils.parseRgb(color1.getRgb()));
            this.colorView1.setVisibility(View.VISIBLE);
        } else {
            this.colorView1.setVisibility(View.INVISIBLE);
        }
    }

    public void setColor2(CommonColor color) {
        this.color2 = color;
        if (color2 != null) {
            this.colorView2.setBackgroundColor(ColorUtils.parseRgb(color2.getRgb()));
            this.colorView2.setVisibility(View.VISIBLE);
        } else {
            this.colorView2.setVisibility(View.INVISIBLE);
        }
    }

    public void setColor3(CommonColor color) {
        this.color3 = color;
        if (color3 != null) {
            this.colorView3.setBackgroundColor(ColorUtils.parseRgb(color3.getRgb()));
            this.colorView3.setVisibility(View.VISIBLE);
        } else {
            this.colorView3.setVisibility(View.INVISIBLE);
        }
    }
}
