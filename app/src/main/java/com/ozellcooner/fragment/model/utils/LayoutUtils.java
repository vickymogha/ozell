package com.ozellcooner.fragment.model.utils;

import android.app.Activity;
import android.graphics.Point;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
/**
 * @author javed
 */
public class LayoutUtils {

	public static Activity context;
	public static int SCR_WIDTH;
	public static int SCR_HEIGHT;
	
	public static int FILL_PARENT;
	public static int MATCH_PARENT;
	public static int WRAP_CONTENT;

	public static void setContext(Activity context) {
		LayoutUtils.FILL_PARENT = LayoutParams.FILL_PARENT;
		LayoutUtils.MATCH_PARENT = LayoutParams.MATCH_PARENT;
		LayoutUtils.WRAP_CONTENT = LayoutParams.WRAP_CONTENT;
		LayoutUtils.context = context;
	}

	public static int getWindowHeight() {
		if (context == null) {
			Log.e("Error in context",
					"Use setContext() before calling this method");
			return -1;
		}
		Display display = context.getWindowManager().getDefaultDisplay();
		Point size = new Point();
		
		
		if (android.os.Build.VERSION.SDK_INT >= 13) {
			display.getSize(size);
			int height = size.y;
			return height;
			
		} else {
			int height = display.getHeight();  // deprecated
			LayoutUtils.SCR_HEIGHT = height;
			return height;
		}
		
		
	}

	public static int getWindowWidth() {
		if (context == null) {
			Log.e("Error in context",
					"Use setContext() before calling this method");
			return -1;
		}
		Display display = context.getWindowManager().getDefaultDisplay();
		Point size = new Point();

		if (android.os.Build.VERSION.SDK_INT >= 13) {
			display.getSize(size);
			int width = size.x;
			return width;
			
		} else {
			int width = display.getWidth();  
			LayoutUtils.SCR_WIDTH = width;
			return width;
		}
		
	}

	public static float getPropWidth(float layoutX) {
		 return (layoutX * getWindowWidth()) / 480;
	}

	public static float getPropHeight(float layoutY) {
		return (layoutY * getWindowHeight()) / 800;
	}
	
	public static float getX(View view) {
		if (android.os.Build.VERSION.SDK_INT >= 11) {
			return view.getX();
		} else {
			
			RelativeLayout.LayoutParams params = (LayoutParams) view.getLayoutParams();
			return params.leftMargin;
		}
	}

	public static void setX(View view, float margin) {
		
		if (android.os.Build.VERSION.SDK_INT >= 11) {
			view.setX(margin);
			
		} else {
			RelativeLayout.LayoutParams params = (LayoutParams) view.getLayoutParams();
			params.leftMargin = (int) (margin); // Your Y
			// coordinate
			view.setLayoutParams(params);
		}

	}

	public static float getY(View view) {
		
		if (android.os.Build.VERSION.SDK_INT >= 11) {
			return view.getY();
		} else {
			
			RelativeLayout.LayoutParams params = (LayoutParams) view.getLayoutParams();
			return params.topMargin;
		}
	}

	public static void setY(View view, float margin) {
		if (android.os.Build.VERSION.SDK_INT >= 11) {
			view.setY(margin);
		} else {
			RelativeLayout.LayoutParams params = (LayoutParams) view.getLayoutParams();
			params.topMargin = (int) (margin); 
			view.setLayoutParams(params);
		}
	}
	
	public static void setXY(View view, float marginX, float marginY) {
		setX(view, marginX);
		setY(view, marginY);
	}
}
