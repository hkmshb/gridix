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
import ng.kedco.gridix.models.InjectionStation;
import ng.kedco.gridix.models.TransmissionStation;
import ng.kedco.gridix.decorators.GridSpacingItemDecoration;

/**
 * A simple {@link Fragment} subclass.
 */
public class TransmissionsFragment extends Fragment {
    RecyclerView recyclerView;
    ArrayList<TransmissionStation> transmissionStationList;
    CardAdapter adapter;


    public TransmissionsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_transmissions, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.trans_recycler);
        transmissionStationList = new ArrayList<TransmissionStation>();
        adapter = new CardAdapter(getActivity(), transmissionStationList,TransmissionStation.class);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(),2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2,dpToPx(10),true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        prepareTransmissions();

        return v;
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

        adapter.notifyDataSetChanged();

    }

    private int dpToPx(int dp){
        Resources r = getActivity().getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp,r.getDisplayMetrics()));
    }

}
