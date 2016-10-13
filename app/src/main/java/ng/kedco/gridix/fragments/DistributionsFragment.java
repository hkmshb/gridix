package ng.kedco.gridix.fragments;


import android.content.res.Resources;
import android.os.AsyncTask;
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
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.ViewSwitcher;


import java.util.ArrayList;

import ng.kedco.gridix.R;
import ng.kedco.gridix.adapters.CardAdapter;
import ng.kedco.gridix.adapters.ListItemAdapter;
import ng.kedco.gridix.enums.Status;
import ng.kedco.gridix.enums.ViewType;
import ng.kedco.gridix.helpers.ConnectivtyHelper;
import ng.kedco.gridix.helpers.Holder;
import ng.kedco.gridix.helpers.JsonHelper;
import ng.kedco.gridix.models.DistributionSubstation;
import ng.kedco.gridix.models.InjectionStation;
import ng.kedco.gridix.models.DistributionSubstation;
import ng.kedco.gridix.decorators.GridSpacingItemDecoration;
import ng.kedco.gridix.models.Station;

/**
 * A simple {@link Fragment} subclass.
 */
public class DistributionsFragment extends Fragment {
    //general setup
    ConnectivtyHelper connectivtyHelper;
    JsonHelper jsonHelper;
    Holder holder = Holder.getInstance();
    View fragView;
    ViewType viewType;
    View list,grid;
    ViewSwitcher switcher;
    ArrayList<DistributionSubstation> distributionStationList = holder.getDistributionSubstations();
    RelativeLayout rootLayout;
    //Grid setup
    RecyclerView distroGridView;
    CardAdapter gridAdapter;
    //List setup
    ListView distroListView;
    ListItemAdapter distroListAdapter;


    public DistributionsFragment() {
        // Required empty public constructor


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        jsonHelper = new JsonHelper(getActivity());
        viewType = (ViewType) getArguments().getSerializable("view_type");
        fragView= inflater.inflate(R.layout.fragment_distributions,container,false);
        connectivtyHelper = new ConnectivtyHelper(getActivity());
        rootLayout = (RelativeLayout) fragView.findViewById(R.id.distro_root);
        switcher = (ViewSwitcher) fragView.findViewById(R.id.distro_switcher);
        initialiseListView();
        initialiseGridView();
        switch(viewType){
            case LIST:
                setupListSwipe();
                break;



            case GRID:
                switcher.showNext();
                setupGridSwipe();
                break;


        }
        return fragView;

    }


    private int dpToPx(int dp){
        Resources r = getActivity().getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp,r.getDisplayMetrics()));
    }

    private void setupListSwipe(){
        final SwipeRefreshLayout listSwipe = (SwipeRefreshLayout) distroListView.getParent();
        listSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(connectivtyHelper.IsDeviceConnected()){
                    new GetDistributionStations(listSwipe).execute();
                }else{
                    listSwipe.setRefreshing(false);
                    Toast.makeText(getActivity(),"please check connection and retry",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void setupGridSwipe(){
        final SwipeRefreshLayout gridSwipe = (SwipeRefreshLayout) distroGridView.getParent();
        gridSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(connectivtyHelper.IsDeviceConnected()){
                    new GetDistributionStations(gridSwipe).execute();
                }else{
                    gridSwipe.setRefreshing(false);
                    Toast.makeText(getActivity(),"please check connection and retry",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void setViewArrangement(ViewType vt){
        final SwipeRefreshLayout listSwipe = (SwipeRefreshLayout) distroListView.getParent();
        final SwipeRefreshLayout gridSwipe = (SwipeRefreshLayout) distroGridView.getParent();
        switch(vt){
            case LIST:
                listSwipe.setRefreshing(false);
                gridSwipe.setRefreshing(false);
                switcher.showPrevious();
                setupListSwipe();
                break;



            case GRID:
                listSwipe.setRefreshing(false);
                gridSwipe.setRefreshing(false);
                switcher.showNext();
                setupGridSwipe();
                break;

        }

    }

    private void initialiseListView(){
        list = fragView.findViewById(R.id.distro_list_view);
        distroListView = (ListView) list.findViewById(R.id.swipe_list_view);
        distroListAdapter = new ListItemAdapter(getActivity(),distributionStationList,DistributionSubstation.class);
        distroListView.setAdapter(distroListAdapter);
        distroListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                displayActivity(distributionStationList.get(i));
            }
        });

    }

    private void initialiseGridView(){
        grid = fragView.findViewById(R.id.distro_grid_view);
        distroGridView = (RecyclerView) grid.findViewById(R.id.swipe_grid_view);
        gridAdapter = new CardAdapter(getActivity(), distributionStationList,DistributionSubstation.class);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(),2);
        distroGridView.setLayoutManager(mLayoutManager);
        distroGridView.addItemDecoration(new GridSpacingItemDecoration(2,dpToPx(10),true));
        distroGridView.setItemAnimator(new DefaultItemAnimator());
        distroGridView.setAdapter(gridAdapter);
    }

    private void displayActivity(DistributionSubstation ds){

    }

    public class GetDistributionStations extends AsyncTask<String,Void,Status> {
        SwipeRefreshLayout swl;

        GetDistributionStations(SwipeRefreshLayout sl){this.swl = sl;}
        @Override
        protected ng.kedco.gridix.enums.Status doInBackground(String... strings) {
            ng.kedco.gridix.enums.Status stats = jsonHelper.updateStationsList(Station.StationType.DISTRIBUTION);
            return stats;

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            swl.setRefreshing(true);
        }

        @Override
        protected void onPostExecute(ng.kedco.gridix.enums.Status s) {
            super.onPostExecute(s);
            switch(s){
                case SUCESS:
                    distributionStationList = holder.getDistributionSubstations();
                    distroListAdapter.notifyDataSetChanged();
                    gridAdapter.notifyDataSetChanged();
                    swl.setRefreshing(false);
                    break;
                case FAILED:
                    Toast.makeText(getActivity()," Error in connection",Toast.LENGTH_SHORT).show();
                    swl.setRefreshing(false);
            }


        }
    }

}

