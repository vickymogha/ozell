package com.ozellcooner.model;

import android.graphics.Color;
import android.graphics.Point;

/**
 * Created by Admin on 1/16/2018.
 */

public class OzellColor {

    int red,green,blue;
    int color;

    private Point point;
    private int distance = 10000;

    public int getDistance() {
        return distance;
    }



    public void setDistance(int distance) {
            this.distance = distance;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public int getGreen() {
        return green;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
        setBlue(Color.blue(color));
        setGreen(Color.green(color));
        setRed(Color.red(color));
    }
}
