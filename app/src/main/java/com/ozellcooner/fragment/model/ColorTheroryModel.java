package com.ozellcooner.fragment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ColorTheroryModel {

@SerializedName("status")
@Expose
private String status;
@SerializedName("data")
@Expose
private ArrayList<DataModel> data = null;

public String getStatus() {
return status;
}

public void setStatus(String status) {
this.status = status;
}

public ArrayList<DataModel> getData() {
return data;
}

public void setData(ArrayList<DataModel> data) {
this.data = data;
}

}