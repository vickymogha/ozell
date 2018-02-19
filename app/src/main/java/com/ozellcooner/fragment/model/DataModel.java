package com.ozellcooner.fragment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataModel {

@SerializedName("screen_type")
@Expose
private String screenType;
@SerializedName("image_name")
@Expose
private String imageName;
@SerializedName("image_sequence")
@Expose
private String imageSequence;

public String getScreenType() {
return screenType;
}

public void setScreenType(String screenType) {
this.screenType = screenType;
}

public String getImageName() {
return imageName;
}

public void setImageName(String imageName) {
this.imageName = imageName;
}

public String getImageSequence() {
return imageSequence;
}

public void setImageSequence(String imageSequence) {
this.imageSequence = imageSequence;
}

}