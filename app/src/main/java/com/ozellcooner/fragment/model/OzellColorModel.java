
package com.ozellcooner.fragment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class OzellColorModel {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("color_family")
    @Expose
    private ArrayList<ColorFamily> colorFamily = null;
    @SerializedName("data")
    @Expose
    private ArrayList<ArrayList<Datum>> data = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<ColorFamily> getColorFamily() {
        return colorFamily;
    }

    public void setColorFamily(ArrayList<ColorFamily> colorFamily) {
        this.colorFamily = colorFamily;
    }

    public ArrayList<ArrayList<Datum>> getData() {
        return data;
    }

    public void setData(ArrayList<ArrayList<Datum>> data) {
        this.data = data;
    }

}
