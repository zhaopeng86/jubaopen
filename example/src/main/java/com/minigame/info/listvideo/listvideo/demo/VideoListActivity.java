package com.minigame.info.listvideo.listvideo.demo;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import com.minigame.info.R;
import com.minigame.info.listvideo.fragments.VideoPlayerManagerFragment;
import com.minigame.info.listvideo.video_list_demo.fragments.VideoListFragment;
import com.minigame.info.listvideo.video_list_demo.fragments.VideoRecyclerViewFragment;
import com.minigame.info.listvideo.visibility_demo.fragments.VisibilityUtilsFragment;

/**
 * This activity contains a fragment and gives the switch option between two fragments.
 * 1. {@link VideoRecyclerViewFragment}
 * 2. {@link VideoListFragment}
 */
public class VideoListActivity extends AppCompatActivity implements VisibilityUtilsFragment.VisibilityUtilsCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_video_list);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, new VideoRecyclerViewFragment())
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_video_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.enable_list_view) {
            if (!item.isChecked()) {
                addListView();
            }
        } else if (itemId == R.id.enable_recycler_view) {
            if (!item.isChecked()) {
                addRecyclerView();
            }
        } else if (itemId == R.id.enable_visibility_utils_demo) {
            if (!item.isChecked()) {
                addVisibilityUtilsFragment();
            }
        } else if (itemId == R.id.enable_video_player_manager_demo) {
            if (!item.isChecked()) {
                addVideoPlayerManagerFragment();
            }
        }
        item.setChecked(!item.isChecked());

        return true;
    }

    private void addVideoPlayerManagerFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new VideoPlayerManagerFragment())
                .commit();
    }

    private void addVisibilityUtilsFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new VisibilityUtilsFragment())
                .commit();
    }

    private void addRecyclerView() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new VideoRecyclerViewFragment())
                .commit();
    }

    private void addListView() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new VideoListFragment())
                .commit();
    }

    @Override
    public void setTitle(String title) {

    }
}
