package com.minigame.info.listvideo.video_player_manager.player_messages;

import android.media.MediaPlayer;

import com.minigame.info.listvideo.video_player_manager.ui.VideoPlayerView;
import com.minigame.info.listvideo.video_player_manager.PlayerMessageState;
import com.minigame.info.listvideo.video_player_manager.manager.VideoPlayerManagerCallback;

/**
 * This PlayerMessage calls {@link MediaPlayer#reset()} on the instance that is used inside {@link VideoPlayerView}
 */
public class Reset extends PlayerMessage {
    public Reset(VideoPlayerView videoPlayerView, VideoPlayerManagerCallback callback) {
        super(videoPlayerView, callback);
    }

    @Override
    protected void performAction(VideoPlayerView currentPlayer) {
        currentPlayer.reset();
    }

    @Override
    protected PlayerMessageState stateBefore() {
        return PlayerMessageState.RESETTING;
    }

    @Override
    protected PlayerMessageState stateAfter() {
        return PlayerMessageState.RESET;
    }
}
