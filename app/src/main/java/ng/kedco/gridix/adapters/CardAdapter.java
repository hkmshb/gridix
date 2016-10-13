package ng.kedco.gridix.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;

import ng.kedco.gridix.MainActivity;
import ng.kedco.gridix.R;
import ng.kedco.gridix.models.DistributionSubstation;
import ng.kedco.gridix.models.InjectionStation;
import ng.kedco.gridix.models.PowerLine;
import ng.kedco.gridix.models.TransmissionStation;


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
    public void onBindViewHolder(MyHolder holder, final int position) {
        if(categoryType == TransmissionStation.class){
            TransmissionStation transmissionStation = (TransmissionStation) categories.get(position);
            card_background_resource = R.drawable.trans_sample;
            holder.nameLabel.setText(transmissionStation.getName());
            holder.card_background.setBackgroundResource(card_background_resource);
            holder.card_background.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MainActivity callingActivity = (MainActivity) callingContext;
                    callingActivity.displayAcitivity(position,"trans");
                }
            });
            holder.card_info_dots.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(callingContext,"t dots clicked",Toast.LENGTH_SHORT).show();

                }
            });

        }
        else if(categoryType == InjectionStation.class){
            InjectionStation injection = (InjectionStation) categories.get(position);
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
        else if(categoryType == DistributionSubstation.class){
            DistributionSubstation distributionSubstation = (DistributionSubstation) categories.get(position);
            card_background_resource = R.drawable.album3;
            holder.nameLabel.setText(distributionSubstation.getName());
            holder.card_background.setBackgroundResource(card_background_resource);
            holder.card_info_dots.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(callingContext,"d dots clicked",Toast.LENGTH_SHORT).show();
                }
            });

        }else if(categoryType == PowerLine.class){
            PowerLine pl = (PowerLine) categories.get(position);
            card_background_resource = R.drawable.album7;
            holder.nameLabel.setText(pl.getName());
            holder.card_background.setBackgroundResource(card_background_resource);
            holder.card_info_dots.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(callingContext, "f33 grid dots clicked",Toast.LENGTH_SHORT).show();
                }
            });

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
