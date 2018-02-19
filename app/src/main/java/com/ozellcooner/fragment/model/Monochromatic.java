package com.ozellcooner.fragment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Monochromatic implements Serializable , CommonColor{

@SerializedName("rgb")
@Expose
private String rgb;
@SerializedName("color_name")
@Expose
private String colorName;
@SerializedName("color_code")
@Expose
private String colorCode;

public String getRgb() {
return rgb;
}

public void setRgb(String rgb) {
this.rgb = rgb;
}

public String getColorName() {
return colorName;
}

public void setColorName(String colorName) {
this.colorName = colorName;
}

public String getColorCode() {
return colorCode;
}

public void setColorCode(String colorCode) {
this.colorCode = colorCode;
}

}