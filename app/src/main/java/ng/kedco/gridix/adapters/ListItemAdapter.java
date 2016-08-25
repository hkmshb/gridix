package com.shaibujnr.kedco.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.shaibujnr.kedco.R;
import com.shaibujnr.kedco.categories.Distribution;
import com.shaibujnr.kedco.categories.InjectionSubstation;
import com.shaibujnr.kedco.categories.Transmission;

import java.util.ArrayList;

/**
 * Created by shaibujnr on 8/20/16.
 */

public class ListItemAdapter extends BaseAdapter {
    Context callingContext;
    ArrayList stations;
    Class aClass;
    int bri;

    public ListItemAdapter(Context c, ArrayList sts,Class cls){
        this.callingContext = c;
        this.stations = sts;
        this.aClass = cls;
    }
    @Override
    public int getCount() {
        return stations.size();
    }

    @Override
    public Object getItem(int i) {
        return stations.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) callingContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_item,viewGroup,false);
        }
        ImageView itemImage = (ImageView) view.findViewById(R.id.list_item_img);
        ImageView infoImg = (ImageView) view.findViewById(R.id.list_item_info);
        TextView itemName = (TextView) view.findViewById(R.id.list_item_name);

        if(aClass == Transmission.class){
            Transmission t = (Transmission) stations.get(i);
            itemImage.setBackgroundResource(R.drawable.trans_sample);
            itemName.setText(t.getName());
            infoImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(callingContext,"t dots clicked",Toast.LENGTH_SHORT).show();
                }
            });
        }
        else if(aClass == InjectionSubstation.class){
            InjectionSubstation a = (InjectionSubstation) stations.get(i);
            itemImage.setBackgroundResource(R.drawable.trans_sample);
            itemName.setText(a.getName());
            infoImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(callingContext,"t dots clicked",Toast.LENGTH_SHORT).show();
                }
            });
        }
        else if(aClass == Distribution.class){
            Distribution d = (Distribution) stations.get(i);
            itemImage.setBackgroundResource(R.drawable.trans_sample);
            itemName.setText(d.getName());
            infoImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(callingContext,"t dots clicked",Toast.LENGTH_SHORT).show();
                }
            });
        }
        return view;
    }
}
