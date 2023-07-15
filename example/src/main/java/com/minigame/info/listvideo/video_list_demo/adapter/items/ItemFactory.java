package com.minigame.info.listvideo.video_list_demo.adapter.items;

import android.app.Activity;

import com.minigame.info.listvideo.video_player_manager.manager.VideoPlayerManager;
import com.minigame.info.listvideo.video_player_manager.meta.MetaData;
import com.squareup.picasso.Picasso;

import java.io.IOException;

public class ItemFactory {

    public static BaseVideoItem createItemFromAsset(String assetName, int imageResource, Activity activity, VideoPlayerManager<MetaData> videoPlayerManager) throws IOException {
        return new AssetVideoItem(assetName, activity.getAssets().openFd(assetName), videoPlayerManager, Picasso.with(activity), imageResource);
    }
}
