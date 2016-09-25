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
import android.widget.ViewSwitcher;


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
    //general setup
    View fragView;
    ViewType viewType;
    View list,grid;
    ViewSwitcher switcher;
    ArrayList<TransmissionStation> transmissionStationList = new ArrayList<TransmissionStation>();
    RelativeLayout rootLayout;
    //Grid setup
    RecyclerView transGridView;
    CardAdapter gridAdapter;
    //List setup
    ListView transListView;
    ListItemAdapter transListAdapter;


    public TransmissionsFragment() {
        // Required empty public constructor
        prepareTransmissions();

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewType = (ViewType) getArguments().getSerializable("view_type");
        fragView= inflater.inflate(R.layout.fragment_transmissions,container,false);
        rootLayout = (RelativeLayout) fragView.findViewById(R.id.transmission_root);
        switcher = (ViewSwitcher) fragView.findViewById(R.id.trans_switcher);
        initialiseListView();
        initialiseGridView();
        switch(viewType){
            case LIST:
                break;



            case GRID:
                switcher.showNext();
                break;


        }
        return fragView;

    }

    private void prepareTransmissions() {
        for(int i=0;i<100;i++){
            TransmissionStation t = new TransmissionStation();
            t.setName("Transmission "+i);
            transmissionStationList.add(t);
        }



    }

    private int dpToPx(int dp){
        Resources r = getActivity().getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp,r.getDisplayMetrics()));
    }

    public void setViewArrangement(ViewType vt){
        SwipeRefreshLayout listSwipe = (SwipeRefreshLayout) transListView.getParent();
        SwipeRefreshLayout gridSwipe = (SwipeRefreshLayout) transGridView.getParent();
        switch(vt){
            case LIST:
                listSwipe.setRefreshing(false);
                gridSwipe.setRefreshing(false);
                Toast.makeText(getActivity(),"List",Toast.LENGTH_SHORT).show();
                switcher.showPrevious();
                break;



            case GRID:
                listSwipe.setRefreshing(false);
                gridSwipe.setRefreshing(false);
                Toast.makeText(getActivity(),"Grid",Toast.LENGTH_SHORT).show();
                switcher.showNext();
                break;

        }

    }

    private void initialiseListView(){
        list = fragView.findViewById(R.id.transmission_list_view);
        transListView = (ListView) list.findViewById(R.id.swipe_list_view);
        transListAdapter = new ListItemAdapter(getActivity(),transmissionStationList,TransmissionStation.class);
        transListView.setAdapter(transListAdapter);

    }

    private void initialiseGridView(){
        grid = fragView.findViewById(R.id.transmission_grid_view);
        transGridView = (RecyclerView) grid.findViewById(R.id.swipe_grid_view);

        gridAdapter = new CardAdapter(getActivity(), transmissionStationList,TransmissionStation.class);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(),2);
        transGridView.setLayoutManager(mLayoutManager);
        transGridView.addItemDecoration(new GridSpacingItemDecoration(2,dpToPx(10),true));
        transGridView.setItemAnimator(new DefaultItemAnimator());
        transGridView.setAdapter(gridAdapter);
    }

}
