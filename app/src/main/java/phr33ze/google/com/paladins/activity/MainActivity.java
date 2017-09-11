package phr33ze.google.com.paladins.activity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mikepenz.iconics.context.IconicsLayoutInflater;

import okhttp3.OkHttpClient;
import phr33ze.google.com.paladins.R;
import phr33ze.google.com.paladins.api.HiRezAPI;
import phr33ze.google.com.paladins.fragment.ChampionsFragment;
import phr33ze.google.com.paladins.hirezstudios.Paladins;
import phr33ze.google.com.paladins.hirezstudios.instance.Language;
import phr33ze.google.com.paladins.util.CacheData;

public class MainActivity extends AppCompatActivity {
    private Context context;
    private final OkHttpClient client = new OkHttpClient();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LayoutInflaterCompat.setFactory(getLayoutInflater(), new IconicsLayoutInflater(getDelegate()));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();

        CacheData cacheData = new CacheData(context);
        cacheData.putString("language", Language.Portuguese.toString());

        setUpToolbar();

        if (savedInstanceState == null) {
            attachCategoryGridFragment();
        } else {
            setProgressBarVisibility(View.GONE);
        }
        supportPostponeEnterTransition();

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                HiRezAPI api = new HiRezAPI("2252", "46B55A6B78144BD281621F003E8E759E");
                //StringData mydata = api.paladins(Paladins.Platform.PC, context).getPlayer("Phreeze");
                System.out.println(api.paladins(Paladins.Platform.PC, context).getChampions());
                /*try {
                    System.out.println(mydata.toJsonArray());
                } catch (JSONException e) {
                    e.printStackTrace();
                }*/
                return null;
            }

            @Override
            protected void onPostExecute(Void result) {
                super.onPostExecute(result);

            }
        }.execute();


    }

    private void setUpToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //noinspection ConstantConditions
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.frameLayoutMain);
        if (fragment != null) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void attachCategoryGridFragment() {
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
}
