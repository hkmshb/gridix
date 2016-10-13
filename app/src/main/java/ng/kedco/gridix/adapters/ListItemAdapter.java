package ng.kedco.gridix.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import ng.kedco.gridix.R;
import ng.kedco.gridix.models.DistributionSubstation;
import ng.kedco.gridix.models.InjectionStation;
import ng.kedco.gridix.models.PowerLine;
import ng.kedco.gridix.models.TransmissionStation;

/**
 * Created by shaibujnr on 8/20/16.
 */

public class ListItemAdapter extends BaseAdapter {
    Context callingContext;
    ArrayList entities;
    Class aClass;
    int bri;

    public ListItemAdapter(Context c, ArrayList sts,Class cls){
        this.callingContext = c;
        this.entities = sts;
        this.aClass = cls;
    }
    @Override
    public int getCount() {
        return entities.size();
    }

    @Override
    public Object getItem(int i) {
        return entities.get(i);
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

        if(aClass == TransmissionStation.class){
            TransmissionStation t = (TransmissionStation) entities.get(i);
            itemImage.setBackgroundResource(R.drawable.trans_sample);
            itemName.setText(t.getName());
            infoImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(callingContext,"t dots clicked",Toast.LENGTH_SHORT).show();
                }
            });
        }
        else if(aClass == InjectionStation.class){
            InjectionStation a = (InjectionStation) entities.get(i);
            itemImage.setBackgroundResource(R.drawable.album2);
            itemName.setText(a.getName());
            infoImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(callingContext,"t dots clicked",Toast.LENGTH_SHORT).show();
                }
            });
        }
        else if(aClass == DistributionSubstation.class){
            DistributionSubstation d = (DistributionSubstation) entities.get(i);
            itemImage.setBackgroundResource(R.drawable.album3);
            itemName.setText(d.getName());
            infoImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(callingContext,"t dots clicked",Toast.LENGTH_SHORT).show();
                }
            });
        }
        else if(aClass == PowerLine.class){
            PowerLine pl = (PowerLine) entities.get(i);
            itemImage.setBackgroundResource(R.drawable.album7);
            itemName.setText(pl.getName());
            infoImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(callingContext,"f33 dots clicked ",Toast.LENGTH_SHORT).show();
                }
            });
        }
        return view;
    }
}
