package ng.kedco.gridix.fragments.list;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ng.kedco.gridix.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FeederListFragment extends Fragment {


    public FeederListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feeder_list, container, false);
    }

}
