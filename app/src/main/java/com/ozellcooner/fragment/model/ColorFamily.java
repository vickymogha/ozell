
package com.ozellcooner.fragment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ColorFamily {

    @SerializedName("color")
    @Expose
    private String color;
    @SerializedName("desc")
    @Expose
    private String desc;
    @SerializedName("rgb")
    @Expose
    private String rgb;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getRgb() {
        return rgb;
    }

    public void setRgb(String rgb) {
        this.rgb = rgb;
    }

}
