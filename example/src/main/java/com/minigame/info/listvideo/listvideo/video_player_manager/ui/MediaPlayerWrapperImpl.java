package com.minigame.info.listvideo.listvideo.video_player_manager.ui;

import android.media.MediaPlayer;

import com.minigame.info.listvideo.video_player_manager.ui.MediaPlayerWrapper;

public class MediaPlayerWrapperImpl extends MediaPlayerWrapper {

    public MediaPlayerWrapperImpl() {
        super(new MediaPlayer());
    }
}
