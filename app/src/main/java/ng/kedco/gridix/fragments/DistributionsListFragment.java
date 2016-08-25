package com.shaibujnr.kedco.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.shaibujnr.kedco.R;
import com.shaibujnr.kedco.adapters.ListItemAdapter;
import com.shaibujnr.kedco.categories.Distribution;
import com.shaibujnr.kedco.categories.InjectionSubstation;
import com.shaibujnr.kedco.categories.Transmission;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class DistributionsListFragment extends Fragment {
    ArrayList<Distribution> distros = new ArrayList<Distribution>();

    public DistributionsListFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        for(int i=0; i<=10;i++){
            Distribution dist = new Distribution("Distribution "+i,
                    new InjectionSubstation("akd",new ArrayList<Distribution>(),
                            new Transmission("dsl",new ArrayList<InjectionSubstation>())));
            distros.add(dist);
        }
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_distributions_list, container, false);
        ListView lv = (ListView) v.findViewById(R.id.distro_list_fragment);
        ListItemAdapter adapter = new ListItemAdapter(getActivity(),distros, Distribution.class);
        lv.setAdapter(adapter);
        return v;
    }

}
