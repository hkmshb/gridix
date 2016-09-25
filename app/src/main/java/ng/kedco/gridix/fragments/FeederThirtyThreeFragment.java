package ng.kedco.gridix.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.Serializable;

import ng.kedco.gridix.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FeederThirtyThreeFragment extends Fragment implements Serializable{


    public FeederThirtyThreeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feeder33, container, false);
    }

}
