package ng.kedco.gridix.fragments;


import android.content.Intent;
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

import java.io.Serializable;
import java.util.ArrayList;

import ng.kedco.gridix.ItemInfoActivity;
import ng.kedco.gridix.R;
import ng.kedco.gridix.adapters.CardAdapter;
import ng.kedco.gridix.adapters.ListItemAdapter;
import ng.kedco.gridix.decorators.GridSpacingItemDecoration;
import ng.kedco.gridix.enums.Status;
import ng.kedco.gridix.enums.ViewType;
import ng.kedco.gridix.enums.Voltage;
import ng.kedco.gridix.helpers.ConnectivtyHelper;
import ng.kedco.gridix.helpers.Holder;
import ng.kedco.gridix.helpers.JsonHelper;
import ng.kedco.gridix.models.PowerLine;
import ng.kedco.gridix.models.TransmissionStation;

/**
 * A simple {@link Fragment} subclass.
 */
public class FeederElevenFragment extends Fragment {
    //general setup
    ConnectivtyHelper connectivtyHelper;
    JsonHelper jsonHelper;
    Holder holder = Holder.getInstance();
    View fragView;
    ViewType viewType;
    View list,grid;
    ViewSwitcher switcher;
    ArrayList<PowerLine> powerLineList = holder.getFeeder11();
    RelativeLayout rootLayout;
    //Grid setup
    RecyclerView felGridView;
    CardAdapter gridAdapter;
    //List setup
    ListView felListView;
    ListItemAdapter felListAdapter;


    public FeederElevenFragment() {
        // Required empty public constructor


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        jsonHelper = new JsonHelper(getActivity());
        viewType = (ViewType) getArguments().getSerializable("view_type");
        fragView= inflater.inflate(R.layout.fragment_feeder11,container,false);
        connectivtyHelper = new ConnectivtyHelper(getActivity());
        rootLayout = (RelativeLayout) fragView.findViewById(R.id.fel_root);
        switcher = (ViewSwitcher) fragView.findViewById(R.id.fel_switcher);
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
        final SwipeRefreshLayout listSwipe = (SwipeRefreshLayout) felListView.getParent();
        listSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(connectivtyHelper.IsDeviceConnected()){
                    new GetFELPowerLines(listSwipe).execute();
                }else{
                    listSwipe.setRefreshing(false);
                    Toast.makeText(getActivity(),"please check connection and retry",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void setupGridSwipe(){
        final SwipeRefreshLayout gridSwipe = (SwipeRefreshLayout) felGridView.getParent();
        gridSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(connectivtyHelper.IsDeviceConnected()){
                    new GetFELPowerLines(gridSwipe).execute();
                }else{
                    gridSwipe.setRefreshing(false);
                    Toast.makeText(getActivity(),"please check connection and retry",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void setViewArrangement(ViewType vt){
        final SwipeRefreshLayout listSwipe = (SwipeRefreshLayout) felListView.getParent();
        final SwipeRefreshLayout gridSwipe = (SwipeRefreshLayout) felGridView.getParent();
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
        list = fragView.findViewById(R.id.fel_list_view);
        felListView = (ListView) list.findViewById(R.id.swipe_list_view);
        felListAdapter = new ListItemAdapter(getActivity(),powerLineList,PowerLine.class);
        felListView.setAdapter(felListAdapter);
        felListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                displayActivity(i);
            }
        });

    }

    private void initialiseGridView(){
        grid = fragView.findViewById(R.id.fel_grid_view);
        felGridView = (RecyclerView) grid.findViewById(R.id.swipe_grid_view);
        gridAdapter = new CardAdapter(getActivity(), powerLineList,PowerLine.class);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(),2);
        felGridView.setLayoutManager(mLayoutManager);
        felGridView.addItemDecoration(new GridSpacingItemDecoration(2,dpToPx(10),true));
        felGridView.setItemAnimator(new DefaultItemAnimator());
        felGridView.setAdapter(gridAdapter);

    }

    private void displayActivity(int pos){
        Intent intent = new Intent(getActivity(), ItemInfoActivity.class);
        intent.putExtra("clicked_item",pos);
        intent.putExtra("from","fel");
        startActivity(intent);


    }

    public class GetFELPowerLines extends AsyncTask<String,Void,Status> {

        SwipeRefreshLayout swl;

        GetFELPowerLines(SwipeRefreshLayout sl){this.swl = sl;}
        @Override
        protected ng.kedco.gridix.enums.Status doInBackground(String... strings) {
            ng.kedco.gridix.enums.Status stats = jsonHelper.updatePowerLinesList(PowerLine.PowerLineType.FEEDER, Voltage.MVOLTL);
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
            switch (s){
                case SUCESS:
                    felListAdapter.notifyDataSetChanged();
                    gridAdapter.notifyDataSetChanged();
                    swl.setRefreshing(false);
                    break;
                case FAILED:
                    Toast.makeText(getActivity(),"Error in Connection",Toast.LENGTH_SHORT).show();
                    swl.setRefreshing(false);
            }



        }
    }

}

