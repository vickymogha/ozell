package com.ozellcooner.fragment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ColorTrendsDatum implements Serializable{

@SerializedName("month_name")
@Expose
private String monthName;
@SerializedName("rgb")
@Expose
private String rgb;
@SerializedName("image")
@Expose
private String image;
@SerializedName("monochromatic")
@Expose
private List<Monochromatic> monochromatic = null;
@SerializedName("complimentary")
@Expose
private List<Complimentary> complimentary = null;
@SerializedName("analogous")
@Expose
private List<Analogou> analogous = null;
@SerializedName("analogous_complimentary")
@Expose
private List<AnalogousComplimentary> analogousComplimentary = null;

public String getMonthName() {
return monthName;
}

public void setMonthName(String monthName) {
this.monthName = monthName;
}

public String getRgb() {
return rgb;
}

public void setRgb(String rgb) {
this.rgb = rgb;
}

public String getImage() {
return image;
}

public void setImage(String image) {
this.image = image;
}

public List<Monochromatic> getMonochromatic() {
return monochromatic;
}

public void setMonochromatic(List<Monochromatic> monochromatic) {
this.monochromatic = monochromatic;
}

public List<Complimentary> getComplimentary() {
return complimentary;
}

public void setComplimentary(List<Complimentary> complimentary) {
this.complimentary = complimentary;
}

public List<Analogou> getAnalogous() {
return analogous;
}

public void setAnalogous(List<Analogou> analogous) {
this.analogous = analogous;
}

public List<AnalogousComplimentary> getAnalogousComplimentary() {
return analogousComplimentary;
}

public void setAnalogousComplimentary(List<AnalogousComplimentary> analogousComplimentary) {
this.analogousComplimentary = analogousComplimentary;
}

}