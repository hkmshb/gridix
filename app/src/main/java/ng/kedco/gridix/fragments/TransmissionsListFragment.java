package com.shaibujnr.kedco.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shaibujnr.kedco.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TransmissionsListFragment extends Fragment {


    public TransmissionsListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_transmissions_list, container, false);
    }

}
