package com.shaibujnr.kedco.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.shaibujnr.kedco.R;
import com.shaibujnr.kedco.categories.Distribution;
import com.shaibujnr.kedco.categories.InjectionSubstation;
import com.shaibujnr.kedco.categories.Transmission;

import java.util.ArrayList;

/**
 * Created by shaibujnr on 8/18/16.
 */

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.MyHolder> {
    private Context callingContext;
    private ArrayList categories;
    private Class categoryType;
    private int card_background_resource;




    public CardAdapter(Context callingContext, ArrayList cats,Class type) {
        this.callingContext = callingContext;
        this.categories = cats;
        this.categoryType = type;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card,parent,false);
        return new MyHolder(v);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        if(categoryType == Transmission.class){
            Transmission transmission = (Transmission) categories.get(position);
            card_background_resource = R.drawable.trans_sample;
            holder.nameLabel.setText(transmission.getName());
            holder.card_background.setBackgroundResource(card_background_resource);
            holder.card_info_dots.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(callingContext,"t dots clicked",Toast.LENGTH_SHORT).show();
                }
            });

        }
        else if(categoryType == InjectionSubstation.class){
            InjectionSubstation injection = (InjectionSubstation) categories.get(position);
            card_background_resource = R.drawable.album2;
            holder.nameLabel.setText(injection.getName());
            holder.card_background.setBackgroundResource(card_background_resource);
            holder.card_info_dots.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(callingContext,"i dots clicked",Toast.LENGTH_SHORT).show();
                }
            });

        }
        else if(categoryType == Distribution.class){
            Distribution distribution = (Distribution) categories.get(position);
            card_background_resource = R.drawable.album3;
            holder.nameLabel.setText(distribution.getName());
            holder.card_background.setBackgroundResource(card_background_resource);
            holder.card_info_dots.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(callingContext,"d dots clicked",Toast.LENGTH_SHORT).show();
                }
            });

        }else{

        }

    }


    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder{
        public TextView nameLabel;
        ImageView card_info_dots;
        RelativeLayout card_background;
        public MyHolder(View itemView) {
            super(itemView);
            nameLabel = (TextView) itemView.findViewById(R.id.card_name);
            card_info_dots = (ImageView) itemView.findViewById(R.id.card_info);
            card_background = (RelativeLayout) itemView.findViewById(R.id.card_bg);
        }
    }
}
