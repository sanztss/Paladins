package phr33ze.google.com.paladins.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import phr33ze.google.com.paladins.R;
import phr33ze.google.com.paladins.adapter.ProBuildsAdapter;
import phr33ze.google.com.paladins.model.ProBuild;

import static phr33ze.google.com.paladins.fragment.FragmentDrawer.getData;

/**
 * Created by Des. Android on 25/09/2017.
 */

public class ChampionBuildsFragment  extends Fragment {

    private RecyclerView recyclerView;
    private ProBuildsAdapter adapter;
    private List<ProBuild> proBuilds = new ArrayList<ProBuild>();

    public ChampionBuildsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.champion_builds_fragment, container, false);
        recyclerView = (RecyclerView)rootView.findViewById(R.id.recycler_view);

        ProBuild proBuild1 = new ProBuild("ic_g2_logo","KusCutie","talent_androxus_darkstalker","card_buying_time","card_buying_time","card_buying_time","card_buying_time", "card_buying_time");
        ProBuild proBuild2 = new ProBuild("ic_g2_logo","KusCutie","talent_androxus_darkstalker","card_buying_time","card_buying_time","card_buying_time","card_buying_time", "card_buying_time");
        ProBuild proBuild3 = new ProBuild("ic_g2_logo","KusCutie","talent_androxus_darkstalker","card_buying_time","card_buying_time","card_buying_time","card_buying_time", "card_buying_time");
        ProBuild proBuild4 = new ProBuild("ic_g2_logo","KusCutie","talent_androxus_darkstalker","card_buying_time","card_buying_time","card_buying_time","card_buying_time", "card_buying_time");
        ProBuild proBuild5 = new ProBuild("ic_g2_logo","KusCutie","talent_androxus_darkstalker","card_buying_time","card_buying_time","card_buying_time","card_buying_time", "card_buying_time");
        proBuilds.add(proBuild1);
        proBuilds.add(proBuild2);
        proBuilds.add(proBuild3);
        proBuilds.add(proBuild4);
        proBuilds.add(proBuild5);
        adapter = new ProBuildsAdapter(getContext(), proBuilds);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return rootView;
    }

}
