package com.ozellcooner.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ozellcooner.R;
import com.ozellcooner.fragment.model.Datum;
import com.ozellcooner.webserive.ColorUtils;

import java.util.ArrayList;

public class ColorAdapter extends RecyclerView.Adapter<ColorAdapter.MyViewHolder> {

    private ArrayList<Datum> colorList;
    private ItemListener listener;
    private Context context;
    Typeface typeface;
    int selectedIndex = -1;





    public class MyViewHolder extends RecyclerView.ViewHolder {

        View mainView,colorView;
        TextView textColorName,textDescription;
        GradientDrawable shape;


        public MyViewHolder(View view) {
            super(view);
            mainView = view.findViewById(R.id.mainView);
            textColorName = (TextView)view.findViewById(R.id.textcolorname);
            textDescription = (TextView)view.findViewById(R.id.textDescription);
            colorView = view.findViewById(R.id.colorView);
            shape =  new GradientDrawable();
        }
    }

    public interface ItemListener{
        void onClick(int position);
    }

    public void setOnItemListener(ItemListener listener)
    {
        this.listener = listener;
    }


    public ColorAdapter(Context context, ArrayList<Datum> colorList) {
        this.colorList = colorList;
        this.context = context;
        typeface = Typeface.createFromAsset(context.getAssets(),  "ubuntu_r.ttf");

    }

    public void clear()
    {
        colorList.clear();
    }
 
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.color_item, parent, false);
        return new MyViewHolder(itemView);
    }
 
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        Datum color1 = colorList.get(position);
        //holder.colorView.setBackgroundColor(ColorUtils.parseRgb(color1.getMainRgb()));
        holder.mainView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null)
                {
                    listener.onClick(position);

                }

                selectedIndex = position;
                notifyDataSetChanged();
            }
        });

        //holder.colorView.setBackgroundResource(R.drawable.round_color_white_bg);
        holder.shape.setCornerRadius(10);
        holder.shape.setColor(ColorUtils.parseRgb(color1.getMainRgb()));
        if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            holder.colorView.setBackgroundDrawable(holder.shape);
        } else {
            holder.colorView.setBackground(holder.shape);
        }

        if(selectedIndex==position)
        {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams)holder.mainView.getLayoutParams();
            int size = (int)context.getResources().getDimension(R.dimen.cell_size_selected);
            layoutParams.setMargins(size,size,size,size);
            holder.mainView.setLayoutParams(layoutParams);
            //holder.mainView.setBackgroundColor(R.drawable.round_color_black_bg);
            holder.mainView.setBackgroundResource(R.drawable.round_color_black_bg);
        }else
        {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams)holder.mainView.getLayoutParams();
            int size = (int)context.getResources().getDimension(R.dimen.cell_size);
            layoutParams.setMargins(size,size,size,size);
            holder.mainView.setLayoutParams(layoutParams);
            holder.mainView.setBackgroundResource(R.drawable.round_color_white_bg);
        }

    }


    @Override
    public int getItemCount() {
        return colorList.size();
    }
}