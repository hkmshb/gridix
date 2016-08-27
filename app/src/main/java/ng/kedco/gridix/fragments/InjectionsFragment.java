package ng.kedco.gridix.fragments;


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
import ng.kedco.gridix.categories.Distribution;
import ng.kedco.gridix.categories.InjectionSubstation;
import ng.kedco.gridix.categories.Transmission;
import ng.kedco.gridix.decorators.GridSpacingItemDecoration;

/**
 * A simple {@link Fragment} subclass.
 */
public class InjectionsFragment extends Fragment {
    RecyclerView recyclerView;
    ArrayList<InjectionSubstation> injectionList;
    CardAdapter adapter;


    public InjectionsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_injections, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.injection_recycler);
        injectionList = new ArrayList<InjectionSubstation>();
        adapter = new CardAdapter(getActivity(),injectionList,InjectionSubstation.class);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(),2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2,dpToPx(10),true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        prepareTransmissions();

        return v;
    }

    private void prepareTransmissions() {
        InjectionSubstation a = new InjectionSubstation("Injection one",new ArrayList<Distribution>(),
                new Transmission("anonymous",new ArrayList<InjectionSubstation>()));
        injectionList.add(a);

        InjectionSubstation b = new InjectionSubstation("Injection one",new ArrayList<Distribution>(),
                new Transmission("anonymous",new ArrayList<InjectionSubstation>()));
        injectionList.add(b);

        InjectionSubstation c = new InjectionSubstation("Injection one",new ArrayList<Distribution>(),
                new Transmission("anonymous",new ArrayList<InjectionSubstation>()));
        injectionList.add(c);

        InjectionSubstation d = new InjectionSubstation("Injection one",new ArrayList<Distribution>(),
                new Transmission("anonymous",new ArrayList<InjectionSubstation>()));
        injectionList.add(d);

        InjectionSubstation e = new InjectionSubstation("Injection one",new ArrayList<Distribution>(),
                new Transmission("anonymous",new ArrayList<InjectionSubstation>()));
        injectionList.add(e);



        adapter.notifyDataSetChanged();

    }

    private int dpToPx(int dp){
        Resources r = getActivity().getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp,r.getDisplayMetrics()));
    }

}
