package ng.kedco.gridix.fragments;


import android.app.ProgressDialog;
import android.content.res.Resources;
import android.database.DataSetObserver;
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
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import ng.kedco.gridix.GridixApplication;
import ng.kedco.gridix.R;
import ng.kedco.gridix.adapters.CardAdapter;
import ng.kedco.gridix.adapters.ListItemAdapter;
import ng.kedco.gridix.enums.ViewType;
import ng.kedco.gridix.helpers.ConnectivtyHelper;
import ng.kedco.gridix.helpers.Holder;
import ng.kedco.gridix.helpers.JsonHelper;
import ng.kedco.gridix.models.DistributionSubstation;
import ng.kedco.gridix.models.InjectionStation;
import ng.kedco.gridix.models.Station;
import ng.kedco.gridix.models.TransmissionStation;
import ng.kedco.gridix.decorators.GridSpacingItemDecoration;

/**
 * A simple {@link Fragment} subclass.
 */
public class TransmissionsFragment extends Fragment {
    //general setup
    ConnectivtyHelper connectivtyHelper;
    JsonHelper jsonHelper= new JsonHelper();
    Holder holder = Holder.getInstance();
    View fragView;
    ViewType viewType;
    View list,grid;
    ViewSwitcher switcher;
    ArrayList<TransmissionStation> transmissionStationList = holder.getTransmissionStations();
    RelativeLayout rootLayout;
    //Grid setup
    RecyclerView transGridView;
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
        fragView= inflater.inflate(R.layout.fragment_transmissions,container,false);
        connectivtyHelper = new ConnectivtyHelper(getActivity());
        rootLayout = (RelativeLayout) fragView.findViewById(R.id.transmission_root);
        switcher = (ViewSwitcher) fragView.findViewById(R.id.trans_switcher);
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

//    private void prepareTransmissions() {
//        for(int i=0;i<100;i++){
//            TransmissionStation t = new TransmissionStation();
//            t.setName("Transmission "+i);
//            transmissionStationList.add(t);
//        }
//
//
//
//    }

    private int dpToPx(int dp){
        Resources r = getActivity().getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp,r.getDisplayMetrics()));
    }

    private void setupListSwipe(){
        final SwipeRefreshLayout listSwipe = (SwipeRefreshLayout) transListView.getParent();
        listSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(connectivtyHelper.IsDeviceConnected()){
                    new GetStations(listSwipe).execute();
                }else{
                    listSwipe.setRefreshing(false);
                    Toast.makeText(getActivity(),"please check connection and retry",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void setupGridSwipe(){
        final SwipeRefreshLayout gridSwipe = (SwipeRefreshLayout) transGridView.getParent();
        gridSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(connectivtyHelper.IsDeviceConnected()){
                    new GetStations(gridSwipe).execute();
                }else{
                    gridSwipe.setRefreshing(false);
                    Toast.makeText(getActivity(),"please check connection and retry",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void setViewArrangement(ViewType vt){
        final SwipeRefreshLayout listSwipe = (SwipeRefreshLayout) transListView.getParent();
        final SwipeRefreshLayout gridSwipe = (SwipeRefreshLayout) transGridView.getParent();
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
        list = fragView.findViewById(R.id.transmission_list_view);
        transListView = (ListView) list.findViewById(R.id.swipe_list_view);
        transListAdapter = new ListItemAdapter(getActivity(),transmissionStationList,TransmissionStation.class);
        transListView.setAdapter(transListAdapter);
        transListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                displayActivity(transmissionStationList.get(i));
            }
        });

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

    private void displayActivity(TransmissionStation ts){

    }

    public class GetStations extends AsyncTask<String,Void,String> {
        String result;
        SwipeRefreshLayout swl;

        GetStations(SwipeRefreshLayout sl){this.swl = sl;}
        @Override
        protected String doInBackground(String... strings) {
            jsonHelper.updateStationsList();
            return result;

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            swl.setRefreshing(true);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(getActivity(),""+transmissionStationList.size()+" not empty",Toast.LENGTH_SHORT).show();
            transmissionStationList = holder.getTransmissionStations();
            transListAdapter.notifyDataSetChanged();
            gridAdapter.notifyDataSetChanged();
            swl.setRefreshing(false);

        }
    }

}
