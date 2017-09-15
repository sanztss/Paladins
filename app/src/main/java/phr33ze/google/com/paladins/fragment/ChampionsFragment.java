package phr33ze.google.com.paladins.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import phr33ze.google.com.paladins.R;
import phr33ze.google.com.paladins.activity.ChampionActivity;
import phr33ze.google.com.paladins.activity.MainActivity;
import phr33ze.google.com.paladins.adapter.ChampionsAdapter;
import phr33ze.google.com.paladins.model.Champion;
import phr33ze.google.com.paladins.model.ChampionChart;
import phr33ze.google.com.paladins.util.StringData;
import phr33ze.google.com.paladins.util.TransitionHelper;

import static java.lang.Integer.parseInt;

/**
 * Created by Des. Android on 06/09/2017.
 */

public class ChampionsFragment extends Fragment {
    private Activity mActivity;
    private Context mContext;
    private Resources mResources;
    private ChampionsAdapter mAdapter;
    private List<Champion> champions;
    private List<ChampionChart> championCharts;
    private StringData mydata;

    public static ChampionsFragment newInstance() {
        return new ChampionsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mActivity = getActivity();
        mContext = mActivity.getApplicationContext();
        mResources = mActivity.getResources();
        View rootView = inflater.inflate(R.layout.champions_fragment, null, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        setUpQuizGrid((RecyclerView) view.findViewById(R.id.recyclerViewChampions));
        super.onViewCreated(view, savedInstanceState);
    }

    private void setUpQuizGrid(final RecyclerView championsView) {
        final int spacing = getContext().getResources()
                .getDimensionPixelSize(R.dimen.spacing_zero);
        //championsView.addItemDecoration(new OffsetDecoration(spacing));
        champions = new ArrayList<>();

        try {
            File f = new File("/data/data/" + getActivity().getPackageName() + "/champions.json");
            FileInputStream is = new FileInputStream(f);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String contentJson = new String(buffer);
            JSONArray jsonArray = new JSONArray(contentJson);
            for (int i=0; i < jsonArray.length(); i++){
                Champion champion = new Champion();

                /*ChampionChart championChart = new ChampionChart();
                championChart.setChampionId(jsonArray.getJSONObject(i).getInt("id"));
                championChart.setHealth(jsonArray.getJSONObject(i).getInt("Health"));
                championChart.setSpeed(jsonArray.getJSONObject(i).getInt("Speed"));
                if (jsonArray.getJSONObject(i).getString("Roles").equals(" Paladins Support")){
                    championChart.setHeal(jsonArray.getJSONObject(i).getInt("id"));
                }else {
                    championChart.setHeal(0);
                }
                championChart.setDamage(jsonArray.getJSONObject(i).getInt("id"));
                championChart.setControl(jsonArray.getJSONObject(i).getInt("id"));*/



                /*List<Ability> abilities = new ArrayList<Ability>();
                for (int j=1; j < 6; j++){
                    Ability ability = new Ability();
                    JSONObject abilityObject = jsonArray.getJSONObject(i).getJSONObject("Ability_" + j);
                    ability.setAbilityDescription(abilityObject.getString("Description"));
                    ability.setId(parseInt(abilityObject.getString("Id")));
                    ability.setAbilityIcon(abilityObject.getString("URL"));
                    ability.setAbilityName(abilityObject.getString("Summary"));
                    ability.setAbilityNumber("Ability_" + j);
                    abilities.add(ability);
                }
                champion.setAbilities(abilities);*/
                /*champion.setChampionCardURL("");
                champion.setChampionIconURL(jsonArray.getJSONObject(i).getString("ChampionIcon_URL"));
                champion.setCons(jsonArray.getJSONObject(i).getString("Cons"));
                champion.setHealth(jsonArray.getJSONObject(i).getInt("Health"));*/
                champion.setId(jsonArray.getJSONObject(i).getInt("id"));
                /*champion.setLatestChampion(jsonArray.getJSONObject(i).getString("latestChampion"));
                champion.setLore(jsonArray.getJSONObject(i).getString("Lore"));*/
                champion.setName(jsonArray.getJSONObject(i).getString("Name"));
                /*champion.setOnFreeRotation(jsonArray.getJSONObject(i).getString("OnFreeRotation"));
                champion.setPhanteon(jsonArray.getJSONObject(i).getString("Pantheon"));
                champion.setPros(jsonArray.getJSONObject(i).getString("Pros"));*/
                champion.setRoles(jsonArray.getJSONObject(i).getString("Roles"));
                /*champion.setSpeed(jsonArray.getJSONObject(i).getInt("Speed"));
                champion.setTitle(jsonArray.getJSONObject(i).getString("Title"));
                champion.setType(jsonArray.getJSONObject(i).getString("Type"));*/

                champions.add(champion);
            }
            ((MainActivity)getActivity()).setProgressBarVisibility(View.GONE);
            mAdapter = new ChampionsAdapter(getActivity(), champions);
            mAdapter.setOnItemClickListener(
                    new ChampionsAdapter.OnItemClickListener() {
                        @Override
                        public void onClick(View v, int position) {
                            Activity activity = getActivity();
                            startChamptionActivityWithTransition(activity,
                                    v.findViewById(R.id.championName),
                                    mAdapter.getItem(position));
                        }
                    });
            championsView.setAdapter(mAdapter);
            championsView.getViewTreeObserver()
                    .addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                        @Override
                        public boolean onPreDraw() {
                            championsView.getViewTreeObserver().removeOnPreDrawListener(this);
                            getActivity().supportStartPostponedEnterTransition();
                            return true;
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void startChamptionActivityWithTransition(Activity activity, View toolbar,
                                                      Champion champion) {

        final Pair[] pairs = TransitionHelper.createSafeTransitionParticipants(activity, false,
                new Pair<>(toolbar, "ToolbarTransition"));
        @SuppressWarnings("unchecked")
        ActivityOptionsCompat sceneTransitionAnimation = ActivityOptionsCompat
                .makeSceneTransitionAnimation(activity, pairs);

        final Bundle transitionBundle = sceneTransitionAnimation.toBundle();
        Intent startIntent = ChampionActivity.getStartIntent(activity, champion);
        ActivityCompat.startActivity(activity,
                startIntent,
                transitionBundle);
    }

}

