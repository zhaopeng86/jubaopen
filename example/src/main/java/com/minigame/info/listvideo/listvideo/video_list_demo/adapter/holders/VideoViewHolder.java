package com.minigame.info.listvideo.listvideo.video_list_demo.adapter.holders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.minigame.info.R;
import com.minigame.info.listvideo.video_player_manager.ui.VideoPlayerView;


public class VideoViewHolder extends RecyclerView.ViewHolder{

    public final VideoPlayerView mPlayer;
    public final TextView mTitle;
    public final ImageView mCover;
    public final TextView mVisibilityPercents;

    public VideoViewHolder(View view) {
        super(view);
        mPlayer = (VideoPlayerView) view.findViewById(R.id.player);
        mTitle = (TextView) view.findViewById(R.id.title);
        mCover = (ImageView) view.findViewById(R.id.cover);
        mVisibilityPercents = (TextView) view.findViewById(R.id.visibility_percents);
    }
}
