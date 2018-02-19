package com.ozellcooner;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.ozellcooner.model.OzellColor;

import java.util.ArrayList;

/**
 * Created by Admin on 1/16/2018.
 */

public class MyView extends ImageView {
    Paint paint,stockPaint;
    ArrayList<OzellColor> colorsList;
    Bitmap bitmap;

    public MyView(Context context) {
        super(context);
        init();
    }

    public void setColorsList(ArrayList<OzellColor> colorsList)
    {
        this.colorsList = colorsList;
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.RED);


        stockPaint = new Paint();
        stockPaint.setStyle(Paint.Style.STROKE);
        stockPaint.setColor(Color.BLACK);

        stockPaint.setStrokeWidth(5);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        if(bitmap==null)
        {
            bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.baby);
            bitmap = Bitmap.createScaledBitmap(bitmap,canvas.getWidth() ,canvas.getHeight(), false);
        }
        if(colorsList!=null) {
            for(int i=0;i<colorsList.size();i++) {
                getPoint(colorsList.get(i));
            }

            for(int i=0;i<colorsList.size();i++) {
                Point p = colorsList.get(i).getPoint();
                if(p!=null) {
                    paint.setColor(colorsList.get(i).getColor());
                    canvas.drawCircle(p.x, p.y, 20, paint);
                    canvas.drawCircle(p.x, p.y, 20, stockPaint);

                }
            }
        }
    }

    public void getPoint(OzellColor colorOzell)
    {
        if(colorsList!=null) {
            for(int i=0;i<bitmap.getHeight();i=i+20)
            {
                for(int j=0;j<bitmap.getWidth();j=j+20)
                {
                       int color1 = bitmap.getPixel(j,i);
                        int color = colorOzell.getColor();
                        int distance = colorDistance(color1,color);
                       if(distance<colorOzell.getDistance())
                       {
                           Point point= colorOzell.getPoint();
                           if(colorOzell.getPoint()==null) {
                              point =  new Point();
                           }
                            point.x = j;
                            point.y = i;
                           colorOzell.setDistance(distance);
                           colorOzell.setPoint(point);
                       }
                }
            }
        }

    }

    OzellColor color1 = new OzellColor();
    OzellColor color2 = new OzellColor();

    public int colorDistance(int c, int c2) {
        color1.setColor(c);
        color2.setColor(c2);

        return Math.abs(color1.getRed() - color2.getRed()) + Math.abs(color1.getGreen() - color2.getGreen()) + Math.abs(color1.getBlue() - color2.getBlue());
    }


}
