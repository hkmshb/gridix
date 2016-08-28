package ng.kedco.gridix.fragments.list;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;



import java.util.ArrayList;

import ng.kedco.gridix.R;
import ng.kedco.gridix.adapters.ListItemAdapter;
import ng.kedco.gridix.models.DistributionSubstation;
import ng.kedco.gridix.models.InjectionStation;
import ng.kedco.gridix.models.TransmissionStation;

/**
 * A simple {@link Fragment} subclass.
 */
public class DistributionsListFragment extends Fragment {
    ArrayList<DistributionSubstation> distros = new ArrayList<DistributionSubstation>();

    public DistributionsListFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        for(int i=0; i<=10;i++){
            DistributionSubstation dist = new DistributionSubstation();
            dist.setName("DistributionStation "+i);
            distros.add(dist);
        }
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_distributions_list, container, false);
        ListView lv = (ListView) v.findViewById(R.id.distro_list_fragment);
        ListItemAdapter adapter = new ListItemAdapter(getActivity(),distros, DistributionSubstation.class);
        lv.setAdapter(adapter);
        return v;
    }

}
