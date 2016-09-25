package ng.kedco.gridix.fragments;


import android.content.res.Resources;
import android.database.DataSetObserver;
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
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;


import java.util.ArrayList;

import ng.kedco.gridix.R;
import ng.kedco.gridix.adapters.CardAdapter;
import ng.kedco.gridix.adapters.ListItemAdapter;
import ng.kedco.gridix.enums.ViewType;
import ng.kedco.gridix.models.InjectionStation;
import ng.kedco.gridix.models.TransmissionStation;
import ng.kedco.gridix.decorators.GridSpacingItemDecoration;

/**
 * A simple {@link Fragment} subclass.
 */
public class TransmissionsFragment extends Fragment {
    ViewType viewType;
    ArrayList<TransmissionStation> transmissionStationList = new ArrayList<TransmissionStation>();
    SwipeRefreshLayout rootLayout;
    //Grid setup
    RecyclerView recyclerView;
    CardAdapter gridAdapter;
    //List setup
    ListView transListView;
    ListItemAdapter transListAdapter;


    public TransmissionsFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewType = (ViewType) getArguments().getSerializable("view_type");
        View fragView= inflater.inflate(R.layout.fragment_transmissions,container,false);
        rootLayout = (SwipeRefreshLayout) fragView.findViewById(R.id.transmission_root);
        transListView = (ListView) fragView.findViewById(R.id.trans_list_view);
        recyclerView = (RecyclerView) fragView.findViewById(R.id.trans_grid_view);
        switch(viewType){
            case LIST:
                recyclerView.setVisibility(View.GONE);
                transListView.setVisibility(View.VISIBLE);
                transListAdapter = new ListItemAdapter(getActivity(),transmissionStationList,TransmissionStation.class);
                transListView.setAdapter(transListAdapter);
                prepareTransmissions();
                transListAdapter.notifyDataSetChanged();
                break;



            case GRID:
                transListView.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                gridAdapter = new CardAdapter(getActivity(), transmissionStationList,TransmissionStation.class);
                RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(),2);
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.addItemDecoration(new GridSpacingItemDecoration(2,dpToPx(10),true));
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(gridAdapter);
                prepareTransmissions();
                gridAdapter.notifyDataSetChanged();
                break;


        }
        return fragView;

    }

    private void prepareTransmissions() {
        TransmissionStation a = new TransmissionStation();
        a.setName("TransmissionStation One");
        transmissionStationList.add(a);

        TransmissionStation b = new TransmissionStation();
        b.setName("TransmissionStation Two");
        transmissionStationList.add(b);

        TransmissionStation c = new TransmissionStation();
        c.setName("TransmissionStation Three");
        transmissionStationList.add(c);

        TransmissionStation d = new TransmissionStation();
        d.setName("TransmissionStation Four");
        transmissionStationList.add(d);

        TransmissionStation e = new TransmissionStation();
        e.setName("TransmissionStation Five");
        transmissionStationList.add(e);



    }

    private int dpToPx(int dp){
        Resources r = getActivity().getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp,r.getDisplayMetrics()));
    }

    public void setViewArrangement(ViewType vt){
        rootLayout.setRefreshing(false);
        switch(vt){
            case LIST:
                Toast.makeText(getActivity(),"List",Toast.LENGTH_SHORT).show();
                recyclerView.setVisibility(View.GONE);
                transListView.setVisibility(View.VISIBLE);
                transListAdapter = new ListItemAdapter(getActivity(),transmissionStationList,TransmissionStation.class);
                transListView.setAdapter(transListAdapter);
                prepareTransmissions();
                transListAdapter.notifyDataSetChanged();
                break;



            case GRID:
                Toast.makeText(getActivity(),"Grid",Toast.LENGTH_SHORT).show();
                transListView.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                gridAdapter = new CardAdapter(getActivity(), transmissionStationList,TransmissionStation.class);
                RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(),2);
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.addItemDecoration(new GridSpacingItemDecoration(2,dpToPx(10),true));
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(gridAdapter);
                prepareTransmissions();
                gridAdapter.notifyDataSetChanged();
                break;

        }

    }


}
