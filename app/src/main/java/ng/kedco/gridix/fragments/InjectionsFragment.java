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
    ViewType viewType;
    ArrayList<InjectionStation> injectionList = new ArrayList<InjectionStation>();
    //Grid setup
    RecyclerView recyclerView;
    CardAdapter gridAdapter;
    //List setup
    ListView injectionListView;
    ListItemAdapter injectionListAdapter;


    public InjectionsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragView = inflater.inflate(R.layout.fragment_injections,container,false);
        SwipeRefreshLayout rootLayout = (SwipeRefreshLayout) fragView.findViewById(R.id.injection_root);
        rootLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(getActivity(),"inject refreshed",Toast.LENGTH_SHORT).show();
            }
        });
        SwipeRefreshLayout.LayoutParams lp = new SwipeRefreshLayout.LayoutParams(SwipeRefreshLayout.LayoutParams.MATCH_PARENT,
                SwipeRefreshLayout.LayoutParams.MATCH_PARENT);
        viewType = (ViewType) getArguments().getSerializable("view_type");
        // Inflate the layout for this fragment
        switch(viewType){
            case LIST:
                injectionListView = new ListView(getActivity());
                injectionListAdapter = new ListItemAdapter(getActivity(),injectionList,InjectionStation.class);
                injectionListView.setAdapter(injectionListAdapter);
                rootLayout.removeAllViews();
                rootLayout.addView(injectionListView,lp);
                prepareStations();
                injectionListAdapter.notifyDataSetChanged();
                break;
            case GRID:
                recyclerView = new RecyclerView(getActivity());
                gridAdapter = new CardAdapter(getActivity(),injectionList,InjectionStation.class);
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
        InjectionStation a = new InjectionStation();
        injectionList.add(a);

        InjectionStation b = new InjectionStation();
        injectionList.add(b);

        InjectionStation c = new InjectionStation();
        injectionList.add(c);

        InjectionStation d = new InjectionStation();
        injectionList.add(d);

        InjectionStation e = new InjectionStation();
        injectionList.add(e);





    }

    private int dpToPx(int dp){
        Resources r = getActivity().getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp,r.getDisplayMetrics()));
    }

    public void setViewArrangement(ViewType vt){

    }

}
