package phr33ze.google.com.paladins.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import phr33ze.google.com.paladins.R;
import phr33ze.google.com.paladins.activity.MainActivity;
import phr33ze.google.com.paladins.adapter.ChampionsAdapter;
import phr33ze.google.com.paladins.api.HiRezAPI;
import phr33ze.google.com.paladins.hirezstudios.Paladins;
import phr33ze.google.com.paladins.model.Ability;
import phr33ze.google.com.paladins.model.Champion;
import phr33ze.google.com.paladins.util.OffsetDecoration;
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
                .getDimensionPixelSize(R.dimen.spacing_nano);
        championsView.addItemDecoration(new OffsetDecoration(spacing));
        champions = new ArrayList<>();

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                HiRezAPI api = new HiRezAPI("2252", "46B55A6B78144BD281621F003E8E759E");
                mydata = api.paladins(Paladins.Platform.PC, mContext).getChampions();
                return null;
            }

            @Override
            protected void onPostExecute(Void result) {
                super.onPostExecute(result);
                try {
                    System.out.println(mydata.toJsonArray());
                    for (int i=0; i < mydata.toJsonArray().length(); i++){
                        Champion champion = new Champion();
                        /*List<Ability> abilities = new ArrayList<Ability>();
                        for (int j=1; j < 6; j++){
                            Ability ability = new Ability();
                            JSONObject abilityObject = mydata.toJsonArray().getJSONObject(i).getJSONObject("Ability_" + j);
                            ability.setAbilityDescription(abilityObject.getString("Description"));
                            ability.setAbilityId(parseInt(abilityObject.getString("Id")));
                            ability.setAbilityIcon(abilityObject.getString("URL"));
                            ability.setAbilityName(abilityObject.getString("Summary"));
                            ability.setAbilityNumber("Ability_" + j);
                            abilities.add(ability);
                        }
                        champion.setAbilities(abilities);*/
                        //champion.setChampionCardURL("");
                        champion.setChampionIconURL(mydata.toJsonArray().getJSONObject(i).getString("ChampionIcon_URL"));
                        /*champion.setCons(mydata.toJsonArray().getJSONObject(i).getString("Cons"));
                        champion.setHealth(mydata.toJsonArray().getJSONObject(i).getInt("Health"));*/
                        champion.setId(mydata.toJsonArray().getJSONObject(i).getInt("id"));
                        /*champion.setLatestChampion(mydata.toJsonArray().getJSONObject(i).getString("latestChampion"));
                        champion.setLore(mydata.toJsonArray().getJSONObject(i).getString("Lore"));*/
                        champion.setName(mydata.toJsonArray().getJSONObject(i).getString("Name"));
                       /* champion.setOnFreeRotation(mydata.toJsonArray().getJSONObject(i).getString("OnFreeRotation"));
                        champion.setPhanteon(mydata.toJsonArray().getJSONObject(i).getString("Pantheon"));
                        champion.setPros(mydata.toJsonArray().getJSONObject(i).getString("Pros"));*/
                        champion.setRoles(mydata.toJsonArray().getJSONObject(i).getString("Roles"));
                        /*champion.setSpeed(mydata.toJsonArray().getJSONObject(i).getInt("Speed"));
                        champion.setTitle(mydata.toJsonArray().getJSONObject(i).getString("Title"));
                        champion.setType(mydata.toJsonArray().getJSONObject(i).getString("Type"));*/

                        champions.add(champion);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                ((MainActivity)getActivity()).setProgressBarVisibility(View.GONE);
                mAdapter = new ChampionsAdapter(getActivity(), champions);
                mAdapter.setOnItemClickListener(
                        new ChampionsAdapter.OnItemClickListener() {
                            @Override
                            public void onClick(View v, int position) {
                                Activity activity = getActivity();
                                startQuizActivityWithTransition(activity,
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
            }
        }.execute();
    }

    private List<Champion> getData() {
        champions = new ArrayList<>();

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                HiRezAPI api = new HiRezAPI("2252", "46B55A6B78144BD281621F003E8E759E");
                mydata = api.paladins(Paladins.Platform.PC, mContext).getChampions();
                return null;
            }

            @Override
            protected void onPostExecute(Void result) {
                super.onPostExecute(result);
                try {
                    System.out.println(mydata.toJsonArray());
                    for (int i=0; i < mydata.toJsonArray().length(); i++){
                        System.out.println(mydata.toJsonArray().getJSONObject(i).getJSONObject("Ability_1"));
                        Champion champion = new Champion();
                        List<Ability> abilities = new ArrayList<Ability>();
                        for (int j=1; j < 6; j++){
                            Ability ability = new Ability();
                            JSONObject abilityObject = mydata.toJsonArray().getJSONObject(i).getJSONObject("Ability_" + j);
                            ability.setAbilityDescription(abilityObject.getString("Description"));
                            ability.setAbilityId(parseInt(abilityObject.getString("Id")));
                            ability.setAbilityIcon(abilityObject.getString("URL"));
                            ability.setAbilityName(abilityObject.getString("Summary"));
                            ability.setAbilityNumber("Ability_" + j);
                            abilities.add(ability);
                        }
                        champion.setAbilities(abilities);
                        champion.setChampionCardURL("");
                        champion.setChampionIconURL(mydata.toJsonArray().getJSONObject(i).getString("ChampionIcon_URL"));
                        champion.setCons(mydata.toJsonArray().getJSONObject(i).getString("Cons"));
                        champion.setHealth(mydata.toJsonArray().getJSONObject(i).getInt("Health"));
                        champion.setId(mydata.toJsonArray().getJSONObject(i).getInt("id"));
                        champion.setLatestChampion(mydata.toJsonArray().getJSONObject(i).getString("latestChampion"));
                        champion.setLore(mydata.toJsonArray().getJSONObject(i).getString("Lore"));
                        champion.setName(mydata.toJsonArray().getJSONObject(i).getString("Name"));
                        champion.setOnFreeRotation(mydata.toJsonArray().getJSONObject(i).getString("OnFreeRotation"));
                        champion.setPhanteon(mydata.toJsonArray().getJSONObject(i).getString("Pantheon"));
                        champion.setPros(mydata.toJsonArray().getJSONObject(i).getString("Pros"));
                        champion.setRoles(mydata.toJsonArray().getJSONObject(i).getString("Roles"));
                        champion.setSpeed(mydata.toJsonArray().getJSONObject(i).getInt("Speed"));
                        champion.setTitle(mydata.toJsonArray().getJSONObject(i).getString("Title"));
                        champion.setType(mydata.toJsonArray().getJSONObject(i).getString("Type"));

                        champions.add(champion);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                ((MainActivity)getActivity()).setProgressBarVisibility(View.GONE);
            }
        }.execute();

        return champions;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void startQuizActivityWithTransition(Activity activity, View toolbar,
                                                 Champion champion) {

        final Pair[] pairs = TransitionHelper.createSafeTransitionParticipants(activity, false,
                new Pair<>(toolbar, "ToolbarTransition"));
        @SuppressWarnings("unchecked")
        ActivityOptionsCompat sceneTransitionAnimation = ActivityOptionsCompat
                .makeSceneTransitionAnimation(activity, pairs);

        /*final Bundle transitionBundle = sceneTransitionAnimation.toBundle();
        Intent startIntent = QuizActivity.getStartIntent(activity, territorio);
        ActivityCompat.startActivityForResult(activity,
                startIntent,
                REQUEST_CATEGORY,
                transitionBundle);*/
    }
}

