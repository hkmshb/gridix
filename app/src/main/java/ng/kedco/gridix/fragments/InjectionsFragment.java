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
import android.widget.ViewSwitcher;


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
public class InjectionsFragment extends Fragment {
    //general setup
    View fragView;
    ViewType viewType;
    View list,grid;
    ViewSwitcher switcher;
    ArrayList<InjectionStation> injectionStationList = new ArrayList<InjectionStation>();
    RelativeLayout rootLayout;
    //Grid setup
    RecyclerView injectionsGridView;
    CardAdapter gridAdapter;
    //List setup
    ListView injectionsListView;
    ListItemAdapter injectionsListAdapter;


    public InjectionsFragment() {
        // Required empty public constructor
        prepareStations();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewType = (ViewType) getArguments().getSerializable("view_type");
        fragView= inflater.inflate(R.layout.fragment_injections,container,false);
        rootLayout = (RelativeLayout) fragView.findViewById(R.id.injections_root);
        switcher = (ViewSwitcher) fragView.findViewById(R.id.injections_switcher);
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

    private void initialiseListView() {
        list = fragView.findViewById(R.id.injections_list_view);
        injectionsListView = (ListView) list.findViewById(R.id.swipe_list_view);
        injectionsListAdapter = new ListItemAdapter(getActivity(),injectionStationList,InjectionStation.class);
        injectionsListView.setAdapter(injectionsListAdapter);
    }
    private void initialiseGridView(){
        grid = fragView.findViewById(R.id.injections_grid_view);
        injectionsGridView = (RecyclerView) grid.findViewById(R.id.swipe_grid_view);
        gridAdapter = new CardAdapter(getActivity(), injectionStationList,InjectionStation.class);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(),2);
        injectionsGridView.setLayoutManager(mLayoutManager);
        injectionsGridView.addItemDecoration(new GridSpacingItemDecoration(2,dpToPx(10),true));
        injectionsGridView.setItemAnimator(new DefaultItemAnimator());
        injectionsGridView.setAdapter(gridAdapter);

    }

    private void prepareStations() {
        for(int i=0;i<100;i++){
            InjectionStation inj = new InjectionStation();
            inj.setName("Injection Station "+i);
            injectionStationList.add(inj);
        }





    }

    private int dpToPx(int dp){
        Resources r = getActivity().getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp,r.getDisplayMetrics()));
    }

    public void setViewArrangement(ViewType vt){
        SwipeRefreshLayout listSwipe = (SwipeRefreshLayout) injectionsListView.getParent();
        SwipeRefreshLayout gridSwipe = (SwipeRefreshLayout) injectionsGridView.getParent();
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

}
