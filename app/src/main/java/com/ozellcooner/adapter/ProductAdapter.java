package com.ozellcooner.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ozellcooner.R;
import com.ozellcooner.fragment.model.Product;
import com.ozellcooner.fragment.model.utils.LayoutUtils;

import java.util.ArrayList;

public class ProductAdapter extends BaseAdapter {

	Context context;
	LayoutInflater inflater;
	ArrayList<Product> list;
	int size = 0;
	Typeface typeface;
	

	public ProductAdapter(Context context, ArrayList<Product> list) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.list = list;
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		int width = (LayoutUtils.getWindowWidth());
		size = width/2 - width/8 ;
		typeface = Typeface.createFromAsset(context.getAssets(),  "ubuntu_r.ttf");
		
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup arg2) {
		ViewHolderItem viewHolder;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.product_items, null);
			viewHolder = new ViewHolderItem();
			viewHolder.view1 = (ImageView) convertView.findViewById(R.id.productImageView);
			viewHolder.textview = (TextView) convertView.findViewById(R.id.product_name);
			//viewHolder.menuButtonBox = convertView.findViewById(R.id.menuButtonBox);
			ViewGroup.LayoutParams param = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, list.get(position).getMenuHeight()/3);

			convertView.setLayoutParams(param);


			ViewGroup.LayoutParams param2 = viewHolder.view1.getLayoutParams();
			param2.height = list.get(position).getMenuHeight()/5;
			param2.width = ViewGroup.LayoutParams.MATCH_PARENT;

			viewHolder.view1.setLayoutParams(param2);

			convertView.setLayoutParams(param);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolderItem) convertView.getTag();
		}
		
		viewHolder.view1.setImageResource(list.get(position).getImageId());
		
		viewHolder.textview.setText(list.get(position).getProductName());
		viewHolder.textview.setTextColor(Color.WHITE);
		viewHolder.textview.setTypeface(typeface);

		return convertView;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	class ViewHolderItem {
		ImageView view1;
		TextView textview;
		View menuButtonBox;

	}

}
