package com.ozellcooner.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ozellcooner.R;
import com.ozellcooner.fragment.model.DataModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ImageDisplayAdapter extends RecyclerView.Adapter<ImageDisplayAdapter.MyViewHolder> {

    private ArrayList<DataModel> dataModels;

    private ItemListener listener;
    Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;


        public MyViewHolder(View view) {
            super(view);
            imageView = (ImageView) view.findViewById(R.id.imageView);

        }
    }

    public interface ItemListener {
        void onClick(int position);
    }

    public void setOnItemListener(ItemListener listener) {
        this.listener = listener;
    }


    public ImageDisplayAdapter(Context context, ArrayList<DataModel> data ) {
        this.dataModels = data;
        this.context = context;
    }

    public void clear() {
        dataModels.clear();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.image_display_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        DataModel model = dataModels.get(position);
        if(model.getImageName()!=null) {
            if(!model.getImageName().equalsIgnoreCase("")) {
                Picasso.with(context)
                        .load(model.getImageName())
                        .into(holder.imageView, new com.squareup.picasso.Callback() {
                            @Override
                            public void onSuccess() {

                            }

                            @Override
                            public void onError() {

                            }
                        });
            }
        }
    }

    @Override
    public int getItemCount() {
        return dataModels.size();
    }
}