package ng.kedco.gridix.fragments.grid;


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



import java.util.ArrayList;

import ng.kedco.gridix.R;
import ng.kedco.gridix.adapters.CardAdapter;
import ng.kedco.gridix.models.DistributionSubstation;
import ng.kedco.gridix.models.InjectionStation;
import ng.kedco.gridix.models.TransmissionStation;
import ng.kedco.gridix.decorators.GridSpacingItemDecoration;

/**
 * A simple {@link Fragment} subclass.
 */
public class DistributionsFragment extends Fragment {
    RecyclerView recyclerView;
    ArrayList<DistributionSubstation> distributionSubstationList;
    ng.kedco.gridix.adapters.CardAdapter adapter;


    public DistributionsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_injections, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.injection_recycler);
        distributionSubstationList = new ArrayList<DistributionSubstation>();
        adapter = new CardAdapter(getActivity(), distributionSubstationList,DistributionSubstation.class);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(),2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2,dpToPx(10),true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        prepareTransmissions();

        return v;
    }

    private void prepareTransmissions() {
        DistributionSubstation a = new DistributionSubstation();
        a.setName("Distribution One");
        distributionSubstationList.add(a);

        DistributionSubstation b = new DistributionSubstation();
        b.setName("Distribution Two");
        distributionSubstationList.add(b);

        DistributionSubstation c = new DistributionSubstation();
        c.setName("Distribution Three");
        distributionSubstationList.add(c);

        DistributionSubstation d = new DistributionSubstation();
        d.setName("Distribution Four");
        distributionSubstationList.add(d);

        DistributionSubstation e = new DistributionSubstation();
        e.setName("Distribution Five");
        distributionSubstationList.add(e);

        adapter.notifyDataSetChanged();

    }

    private int dpToPx(int dp){
        Resources r = getActivity().getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp,r.getDisplayMetrics()));
    }

}
