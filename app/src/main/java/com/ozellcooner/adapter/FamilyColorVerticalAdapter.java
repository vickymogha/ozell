package com.ozellcooner.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ozellcooner.R;
import com.ozellcooner.fragment.model.ColorFamily;
import com.ozellcooner.webserive.ColorUtils;

import java.util.ArrayList;

public class FamilyColorVerticalAdapter extends RecyclerView.Adapter<FamilyColorVerticalAdapter.MyViewHolder> {

    private ArrayList<ColorFamily> colorTrendsList;

    private ItemListener listener;
    private Context context;
    Typeface typeface;



    public class MyViewHolder extends RecyclerView.ViewHolder {

        View mainView,colorView;


        public MyViewHolder(View view) {
            super(view);
            mainView = view.findViewById(R.id.mainView);
            colorView = view.findViewById(R.id.colorView);
        }
    }

    public interface ItemListener{
        void onClick(int position);
    }

    public void setOnItemListener(ItemListener listener)
    {
        this.listener = listener;
    }


    public FamilyColorVerticalAdapter(Context context, ArrayList<ColorFamily> colorTrendsList) {
        this.colorTrendsList = colorTrendsList;
        this.context = context;
        typeface = Typeface.createFromAsset(context.getAssets(),  "ubuntu_r.ttf");

    }

    public void clear()
    {
        colorTrendsList.clear();
    }
 
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.family_color_item2, parent, false);
        return new MyViewHolder(itemView);
    }
 
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        ColorFamily color1 = colorTrendsList.get(position);
        holder.colorView.setBackgroundColor(ColorUtils.parseRgb(color1.getRgb()));
        holder.mainView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null)
                {
                    listener.onClick(position);
                }
            }
        });

    }


    @Override
    public int getItemCount() {
        return colorTrendsList.size();
    }
}