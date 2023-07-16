package com.minigame.info.listvideo.listvideo.video_player_manager.meta;

import android.view.View;

import com.minigame.info.listvideo.video_player_manager.meta.MetaData;

public class CurrentItemMetaData implements MetaData {

    public final int positionOfCurrentItem;
    public final View currentItemView;

    public CurrentItemMetaData(int positionOfCurrentItem, View currentItemView) {
        this.positionOfCurrentItem = positionOfCurrentItem;
        this.currentItemView = currentItemView;
    }

    @Override
    public String toString() {
        return "CurrentItemMetaData{" +
                "positionOfCurrentItem=" + positionOfCurrentItem +
                ", currentItemView=" + currentItemView +
                '}';
    }
}