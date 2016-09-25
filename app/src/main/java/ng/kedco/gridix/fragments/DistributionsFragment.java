package ng.kedco.gridix.fragments;


import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;


import java.io.Serializable;
import java.util.ArrayList;

import ng.kedco.gridix.R;
import ng.kedco.gridix.adapters.CardAdapter;
import ng.kedco.gridix.adapters.ListItemAdapter;
import ng.kedco.gridix.enums.ViewType;
import ng.kedco.gridix.models.DistributionSubstation;
import ng.kedco.gridix.models.InjectionStation;
import ng.kedco.gridix.models.TransmissionStation;
import ng.kedco.gridix.decorators.GridSpacingItemDecoration;

/**
 * A simple {@link Fragment} subclass.
 */
public class DistributionsFragment extends Fragment implements Serializable{
    private ArrayList<DistributionSubstation> distributionSubstationList = new ArrayList<DistributionSubstation>();
    private ViewType viewType;
    //Grid setup
    private RecyclerView recyclerView;
    private ng.kedco.gridix.adapters.CardAdapter gridAdapter;
    //list setup
    private ListView distroListView;
    private ListItemAdapter distroListAdapter;


    public DistributionsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewType = (ViewType) getArguments().getSerializable("view_type");
        View fragView = inflater.inflate(R.layout.fragment_distributions,container,false);
        SwipeRefreshLayout rootLayout = (SwipeRefreshLayout) fragView.findViewById(R.id.distro_root);
        rootLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(getActivity(),"Distro refreshed",Toast.LENGTH_SHORT).show();
            }
        });
        SwipeRefreshLayout.LayoutParams lp = new SwipeRefreshLayout.LayoutParams(SwipeRefreshLayout.LayoutParams.MATCH_PARENT,
                SwipeRefreshLayout.LayoutParams.MATCH_PARENT);
        switch(viewType){
            case LIST:
                distroListView = new ListView(getActivity());
                distroListAdapter = new ListItemAdapter(getActivity(),distributionSubstationList,DistributionSubstation.class);
                distroListView.setAdapter(distroListAdapter);
                rootLayout.removeAllViews();
                rootLayout.addView(distroListView,lp);
                prepareStations();
                distroListAdapter.notifyDataSetChanged();
                break;
            case GRID:
                recyclerView = new RecyclerView(getActivity());
                gridAdapter = new CardAdapter(getActivity(), distributionSubstationList,DistributionSubstation.class);
                RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(),2);
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.addItemDecoration(new GridSpacingItemDecoration(2,dpToPx(10),true));
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(gridAdapter);
                rootLayout.removeAllViews();
                rootLayout.addView(recyclerView,lp);
                prepareStations();
                gridAdapter.notifyDataSetChanged();
                break;
        }


        return fragView;
    }

    private void prepareStations() {
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



    }

    private int dpToPx(int dp){
        Resources r = getActivity().getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp,r.getDisplayMetrics()));
    }

    public void setViewArrangement(ViewType vt){

    }

}
