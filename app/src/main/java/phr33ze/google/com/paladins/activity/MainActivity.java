package phr33ze.google.com.paladins.activity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import org.json.JSONException;

import java.io.FileWriter;
import java.io.IOException;

import okhttp3.OkHttpClient;
import phr33ze.google.com.paladins.R;
import phr33ze.google.com.paladins.api.HiRezAPI;
import phr33ze.google.com.paladins.fragment.ChampionsFragment;
import phr33ze.google.com.paladins.fragment.FragmentDrawer;
import phr33ze.google.com.paladins.hirezstudios.Paladins;
import phr33ze.google.com.paladins.hirezstudios.instance.Language;
import phr33ze.google.com.paladins.util.CacheData;
import phr33ze.google.com.paladins.util.StringData;

public class MainActivity extends AppCompatActivity implements FragmentDrawer.FragmentDrawerListener {
    private Context context;
    private final OkHttpClient client = new OkHttpClient();
    private FragmentDrawer drawerFragment;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();

        final CacheData cacheData = new CacheData(context);
        cacheData.putString("language", Language.Portuguese.toString());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //noinspection ConstantConditions
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                HiRezAPI api = new HiRezAPI("2252", "46B55A6B78144BD281621F003E8E759E");
                StringData getPatchInfo = api.paladins(Paladins.Platform.PC, context).getPatchInfo();
                System.out.println(api.paladins(Paladins.Platform.PC, context).getItems());
                StringData getChampions;
                try {
                    if (!cacheData.getString("patchInfo").equals(getPatchInfo.toJsonObject().getString("version_string"))){
                        cacheData.putString("patchInfo", getPatchInfo.toJsonObject().getString("version_string"));
                        switch (cacheData.getString("language")){
                            case "english":
                                getChampions = api.paladins(Paladins.Platform.PC, context).getChampions(Language.English);
                                break;
                            case "german":
                                getChampions = api.paladins(Paladins.Platform.PC, context).getChampions(Language.German);
                                break;
                            case "french":
                                getChampions = api.paladins(Paladins.Platform.PC, context).getChampions(Language.French);
                                break;
                            case "spanish":
                                getChampions = api.paladins(Paladins.Platform.PC, context).getChampions(Language.Spanish);
                                break;
                            case "latinSpanish":
                                getChampions = api.paladins(Paladins.Platform.PC, context).getChampions(Language.Latin_Spanish);
                                break;
                            case "portuguese":
                                getChampions = api.paladins(Paladins.Platform.PC, context).getChampions(Language.Portuguese);
                                break;
                            case "russian":
                                getChampions = api.paladins(Paladins.Platform.PC, context).getChampions(Language.Russian);
                                break;
                            case "polish":
                                getChampions = api.paladins(Paladins.Platform.PC, context).getChampions(Language.Polish);
                                break;
                            case "turkish":
                                getChampions = api.paladins(Paladins.Platform.PC, context).getChampions(Language.Turkish);
                                break;
                            default:
                                getChampions = api.paladins(Paladins.Platform.PC, context).getChampions();
                                createJsonFile("champions.json", getChampions.toString());
                                break;
                        }
                    }
                    else {

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void result) {
                super.onPostExecute(result);
                if (savedInstanceState == null) {
                    attachChampionGridFragment();
                } else {
                    setProgressBarVisibility(View.GONE);
                }
                supportPostponeEnterTransition();
            }
        }.execute();

        drawerFragment = (FragmentDrawer)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), toolbar);
        drawerFragment.setDrawerListener(this);

        // display the first navigation drawer view on app launch
        //displayView(0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.frameLayoutMain);
        if (fragment != null) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onDrawerItemSelected(View view, int position) {
        displayView(position);
    }

    private void displayView(int position) {
        Fragment fragment = null;
        String title = getString(R.string.app_name);
        switch (position) {
            case 0:
                fragment = new ChampionsFragment();
                title = getString(R.string.title_champions);
                break;
            case 1:
                fragment = new ChampionsFragment();
                title = getString(R.string.title_items);
                break;
            case 2:
                fragment = new ChampionsFragment();
                title = getString(R.string.title_profiles);
                break;
            case 3:
                fragment = new ChampionsFragment();
                title = getString(R.string.title_patchs);
                break;
            case 4:
                fragment = new ChampionsFragment();
                title = getString(R.string.title_settings);
                break;
            case 5:
                fragment = new ChampionsFragment();
                title = getString(R.string.title_about);
                break;
            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frameLayoutMain, fragment);
            fragmentTransaction.commit();
            // set the toolbar title
            getSupportActionBar().setTitle(title);
        }
    }

    private void attachChampionGridFragment() {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        Fragment fragment = supportFragmentManager.findFragmentById(R.id.frameLayoutMain);
        if (!(fragment instanceof ChampionsFragment)) {
            fragment = ChampionsFragment.newInstance();
        }
        supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayoutMain, fragment)
                .commit();
    }

    public void setProgressBarVisibility(int visibility) {
        findViewById(R.id.progress).setVisibility(visibility);
    }

    public void createJsonFile(String params, String mJsonResponse) {
        try {
            FileWriter file = new FileWriter("/data/data/" + getApplicationContext().getPackageName() + "/" + params);
            file.write(mJsonResponse);
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
