package com.minigame.info.listvideo.video_player_manager.player_messages;

import android.media.MediaPlayer;

import com.minigame.info.listvideo.video_player_manager.ui.VideoPlayerView;
import com.minigame.info.listvideo.video_player_manager.PlayerMessageState;
import com.minigame.info.listvideo.video_player_manager.manager.VideoPlayerManagerCallback;

/**
 * This PlayerMessage calls {@link MediaPlayer#release()} on the instance that is used inside {@link VideoPlayerView}
 */
public class Release extends PlayerMessage {

    public Release(VideoPlayerView videoPlayerView, VideoPlayerManagerCallback callback) {
        super(videoPlayerView, callback);
    }

    @Override
    protected void performAction(VideoPlayerView currentPlayer) {
        currentPlayer.release();
    }

    @Override
    protected PlayerMessageState stateBefore() {
        return PlayerMessageState.RELEASING;
    }

    @Override
    protected PlayerMessageState stateAfter() {
        return PlayerMessageState.RELEASED;
    }
}
