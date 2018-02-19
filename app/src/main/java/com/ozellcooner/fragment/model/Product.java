package com.ozellcooner.fragment.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Product implements Parcelable {

	int imageId;
    String productName;
	int id;
	int menuHeight;

	public Product(int imageId, String productName) {
		// TODO Auto-generated constructor stub
		this.imageId = imageId;
		this.productName = productName;
		this.id = id;

	}
	public Product(int imageId, String productName, int id, int menuHeight) {
		// TODO Auto-generated constructor stub
		this.imageId = imageId;
		this.productName = productName;
		this.id = id;
		this.menuHeight = menuHeight;
	}

	protected Product(Parcel in) {
		imageId = in.readInt();
		productName = in.readString();
		id = in.readInt();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(imageId);
		dest.writeString(productName);
		dest.writeInt(id);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public static final Creator<Product> CREATOR = new Creator<Product>() {
		@Override
		public Product createFromParcel(Parcel in) {
			return new Product(in);
		}

		@Override
		public Product[] newArray(int size) {
			return new Product[size];
		}
	};

	public int getId() {
		return id;
	}

	public int getMenuHeight() {
		return menuHeight;
	}

	public void setMenuHeight(int menuHeight) {
		this.menuHeight = menuHeight;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getImageId() {
		return imageId;
	}

	public String getProductName() {
		return productName;
	}

	public void setImageId(int imageId) {
		this.imageId = imageId;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

}