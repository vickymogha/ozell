package com.ozellcooner.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ozellcooner.R;
import com.ozellcooner.fragment.model.ColorTrendsDatum;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ColorTrendsMonthAdapter extends RecyclerView.Adapter<ColorTrendsMonthAdapter.MyViewHolder> {

    private ArrayList<ColorTrendsDatum> colorTrendsList;

    private ItemListener listener;
    private Context context;
    Typeface typeface;



    public class MyViewHolder extends RecyclerView.ViewHolder {

        View mainView;
        TextView textViewMonth;


        public MyViewHolder(View view) {
            super(view);
            mainView = view.findViewById(R.id.mainView);
            textViewMonth = (TextView)view.findViewById(R.id.textViewMonth);
        }
    }

    public interface ItemListener{
        void onClick(int position);
    }

    public void setOnItemListener(ItemListener listener)
    {
        this.listener = listener;
    }


    public ColorTrendsMonthAdapter(Context context, ArrayList<ColorTrendsDatum> colorTrendsList) {
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
                .inflate(R.layout.item_color_trends_month, parent, false);
        return new MyViewHolder(itemView);
    }
 
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        ColorTrendsDatum color1 = colorTrendsList.get(position);
        holder.mainView.setBackgroundColor(parse(color1.getRgb()));
        holder.mainView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null)
                {
                    listener.onClick(position);
                }
            }
        });

        holder.textViewMonth.setTypeface(typeface);
        holder.textViewMonth.setText(color1.getMonthName());

    }

    public int parse(String input)
    {
        Pattern c = Pattern.compile("rgb *\\( *([0-9]+), *([0-9]+), *([0-9]+) *\\)");
        Matcher m = c.matcher(input);

        if(m.matches())
        {

            return Color.rgb(Integer.valueOf(m.group(1)), Integer.valueOf(m.group(2)), Integer.valueOf(m.group(3)));
        }

        return -1;
    }


 
    @Override
    public int getItemCount() {
        return colorTrendsList.size();
    }
}