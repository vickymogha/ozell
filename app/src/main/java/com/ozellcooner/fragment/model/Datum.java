
package com.ozellcooner.fragment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Datum {

    @SerializedName("main_rgb")
    @Expose
    private String mainRgb;
    @SerializedName("family")
    @Expose
    private String family;
    @SerializedName("main_color_code")
    @Expose
    private String mainColorCode;
    @SerializedName("main_color_name")
    @Expose
    private String mainColorName;
    @SerializedName("monochromatic")
    @Expose
    private ArrayList<Monochromatic> monochromatic = null;
    @SerializedName("complimentary")
    @Expose
    private ArrayList<Complimentary> complimentary = null;
    @SerializedName("analogous")
    @Expose
    private ArrayList<Analogou> analogous = null;
    @SerializedName("analogous_complimentary")
    @Expose
    private ArrayList<AnalogousComplimentary> analogousComplimentary = null;

    public String getMainRgb() {
        return mainRgb;
    }

    public void setMainRgb(String mainRgb) {
        this.mainRgb = mainRgb;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getMainColorCode() {
        return mainColorCode;
    }

    public void setMainColorCode(String mainColorCode) {
        this.mainColorCode = mainColorCode;
    }

    public String getMainColorName() {
        return mainColorName;
    }

    public void setMainColorName(String mainColorName) {
        this.mainColorName = mainColorName;
    }

    public ArrayList<Monochromatic> getMonochromatic() {
        return monochromatic;
    }

    public void setMonochromatic(ArrayList<Monochromatic> monochromatic) {
        this.monochromatic = monochromatic;
    }

    public ArrayList<Complimentary> getComplimentary() {
        return complimentary;
    }

    public void setComplimentary(ArrayList<Complimentary> complimentary) {
        this.complimentary = complimentary;
    }

    public ArrayList<Analogou> getAnalogous() {
        return analogous;
    }

    public void setAnalogous(ArrayList<Analogou> analogous) {
        this.analogous = analogous;
    }

    public ArrayList<AnalogousComplimentary> getAnalogousComplimentary() {
        return analogousComplimentary;
    }

    public void setAnalogousComplimentary(ArrayList<AnalogousComplimentary> analogousComplimentary) {
        this.analogousComplimentary = analogousComplimentary;
    }

}
