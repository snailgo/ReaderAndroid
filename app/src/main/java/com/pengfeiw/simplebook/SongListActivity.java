package com.pengfeiw.simplebook;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.pengfeiw.simplebook.library.SongContainer;

import java.util.Locale;


public class SongListActivity extends ActionBarActivity
        implements SongListFragment.Callbacks {
    private static String TAG = "SongListActivity";
    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;
    private Locale langLocal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_list);

        if (findViewById(R.id.song_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-large and
            // res/values-sw600dp). If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;

            // In two-pane mode, list items should be given the
            // 'activated' state when touched.
            ((SongListFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.song_list))
                    .setActivateOnItemClick(true);
        }

    }

    @Override
    public void onItemSelected(String id) {
        if (mTwoPane) {
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString(SongDetailFragment.ARG_ITEM_ID, id);
            SongDetailFragment fragment = new SongDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.song_detail_container, fragment)
                    .commit();

        } else {
            // In single-pane mode, simply start the detail activity
            // for the selected item ID.
            Intent detailIntent = new Intent(this, SongDetailActivity.class);
            detailIntent.putExtra(SongDetailFragment.ARG_ITEM_ID, id);
            startActivity(detailIntent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Configuration langConfig;
        Toast langToast;
        Intent refresh;
        switch (item.getItemId()) {
            case R.id.lang_en:
                if (getApplicationContext().getResources().getConfiguration().locale.getLanguage() == "en") {
                    break;
                }
                langLocal = new Locale("en");
                langConfig = new Configuration();
                langConfig.locale = langLocal;
                Locale.setDefault(langLocal);
                getApplicationContext().getResources().updateConfiguration(langConfig, getResources().getDisplayMetrics());
                SongContainer.reload(getBaseContext());
                langToast = Toast.makeText(getBaseContext(), "Change to en", Toast.LENGTH_LONG);
                langToast.show();
                refresh = new Intent(this, SongListActivity.class);
                startActivity(refresh);
                finish();
                break;
            case R.id.lang_th:
                if (getApplicationContext().getResources().getConfiguration().locale.getLanguage() == "th") {
                    break;
                }
                langLocal = new Locale("th");
                langConfig = new Configuration();
                langConfig.locale = langLocal;
                Locale.setDefault(langLocal);
                getApplicationContext().getResources().updateConfiguration(langConfig, getResources().getDisplayMetrics());
                langToast = Toast.makeText(getBaseContext(), "Change to th", Toast.LENGTH_LONG);
                SongContainer.reload(getBaseContext());
                refresh = new Intent(this, SongListActivity.class);
                startActivity(refresh);
                finish();
                langToast.show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
