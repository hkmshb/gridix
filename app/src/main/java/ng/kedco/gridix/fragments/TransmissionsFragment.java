package com.shaibujnr.kedco.fragments;


import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shaibujnr.kedco.R;
import com.shaibujnr.kedco.adapters.CardAdapter;
import com.shaibujnr.kedco.categories.InjectionSubstation;
import com.shaibujnr.kedco.categories.Transmission;
import com.shaibujnr.kedco.decorators.GridSpacingItemDecoration;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TransmissionsFragment extends Fragment {
    RecyclerView recyclerView;
    ArrayList<Transmission> transmissionList;
    CardAdapter adapter;


    public TransmissionsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_transmissions, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.trans_recycler);
        transmissionList = new ArrayList<Transmission>();
        adapter = new CardAdapter(getActivity(),transmissionList,Transmission.class);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(),2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2,dpToPx(10),true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        prepareTransmissions();

        return v;
    }

    private void prepareTransmissions() {
        Transmission a = new Transmission("Transmission One gxcvxcvxcvzxcvzxc",new ArrayList<InjectionSubstation>());
        transmissionList.add(a);

        Transmission b = new Transmission("Transmission One",new ArrayList<InjectionSubstation>());
        transmissionList.add(b);

        Transmission c = new Transmission("Transmission One",new ArrayList<InjectionSubstation>());
        transmissionList.add(c);

        Transmission d = new Transmission("Transmission One",new ArrayList<InjectionSubstation>());
        transmissionList.add(d);

        Transmission e = new Transmission("Transmission One",new ArrayList<InjectionSubstation>());
        transmissionList.add(e);

        adapter.notifyDataSetChanged();

    }

    private int dpToPx(int dp){
        Resources r = getActivity().getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp,r.getDisplayMetrics()));
    }

}
