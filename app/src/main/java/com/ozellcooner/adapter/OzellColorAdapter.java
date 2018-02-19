package com.ozellcooner.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ozellcooner.R;
import com.ozellcooner.model.OzellColor;

import java.util.ArrayList;

public class OzellColorAdapter extends RecyclerView.Adapter<OzellColorAdapter.MyViewHolder> {

    private ArrayList<OzellColor> reportArrayListList;

    private ItemListener listener;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        View mainView;

        public MyViewHolder(View view) {
            super(view);
            mainView = view.findViewById(R.id.mainView);
        }
    }

    public interface ItemListener{
        void onClick(int position);
    }

    public void setOnItemListener(ItemListener listener)
    {
        this.listener = listener;
    }


    public OzellColorAdapter(ArrayList<OzellColor> reportArrayListList) {
        this.reportArrayListList = reportArrayListList;
    }

    public void clear()
    {
        reportArrayListList.clear();
    }
 
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_color, parent, false);
 
        return new MyViewHolder(itemView);
    }
 
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        OzellColor color1 = reportArrayListList.get(position);
        holder.mainView.setBackgroundColor(color1.getColor());
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
        return reportArrayListList.size();
    }
}