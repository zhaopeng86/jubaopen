package com.minigame.info.makemoney.fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

import com.minigame.info.R;
import com.minigame.info.listvideo.video_list_demo.adapter.VideoRecyclerViewAdapter;
import com.minigame.info.listvideo.video_list_demo.adapter.items.BaseVideoItem;
import com.minigame.info.listvideo.video_list_demo.adapter.items.DirectLinkVideoItem;
import com.minigame.info.listvideo.video_list_demo.adapter.items.ItemFactory;
import com.minigame.info.listvideo.video_player_manager.Config;
import com.minigame.info.listvideo.video_player_manager.manager.PlayerItemChangeListener;
import com.minigame.info.listvideo.video_player_manager.manager.SingleVideoPlayerManager;
import com.minigame.info.listvideo.video_player_manager.manager.VideoPlayerManager;
import com.minigame.info.listvideo.video_player_manager.meta.MetaData;
import com.minigame.info.listvideo.list_visibility_utils.calculator.DefaultSingleItemCalculatorCallback;
import com.minigame.info.listvideo.list_visibility_utils.calculator.ListItemsVisibilityCalculator;
import com.minigame.info.listvideo.list_visibility_utils.calculator.SingleListViewItemActiveCalculator;
import com.minigame.info.listvideo.list_visibility_utils.scroll_utils.ItemsPositionGetter;
import com.minigame.info.listvideo.list_visibility_utils.scroll_utils.RecyclerViewItemPositionGetter;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;

/**
 * This fragment shows of how to use {@link VideoPlayerManager} with a RecyclerView.
 */
public class VideoRecyclerViewFragment extends Fragment {

    private static final boolean SHOW_LOGS = Config.SHOW_LOGS;
    private static final String TAG = VideoRecyclerViewFragment.class.getSimpleName();
    private final ArrayList<BaseVideoItem> mList = new ArrayList<>();

    /**
     * Only the one (most visible) view should be active (and playing).
     * To calculate visibility of views we use {@link SingleListViewItemActiveCalculator}
     */
    private final ListItemsVisibilityCalculator mVideoVisibilityCalculator =
            new SingleListViewItemActiveCalculator(new DefaultSingleItemCalculatorCallback(), mList);

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;

    /**
     * ItemsPositionGetter is used by {@link ListItemsVisibilityCalculator} for getting information about
     * items position in the RecyclerView and LayoutManager
     */
    private ItemsPositionGetter mItemsPositionGetter;

    /**
     * Here we use {@link SingleVideoPlayerManager}, which means that only one video playback is possible.
     */
    private final VideoPlayerManager<MetaData> mVideoPlayerManager = new SingleVideoPlayerManager(new PlayerItemChangeListener() {
        @Override
        public void onPlayerItemChanged(MetaData metaData) {

        }
    });

    private int mScrollState = AbsListView.OnScrollListener.SCROLL_STATE_IDLE;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        DirectLinkVideoItem directLinkVideoItem=new DirectLinkVideoItem("asfsadf","https://img.tukuppt.com/video_show/2405811/00/01/88/5b4c5a2107fb2.mp4",mVideoPlayerManager,Picasso.with(getContext()),0);
        DirectLinkVideoItem directLinkVideoItem2=new DirectLinkVideoItem("asfsadf","https://img.tukuppt.com/video_show/2405811/00/01/88/5b4c5a2107fb2.mp4",mVideoPlayerManager,Picasso.with(getContext()),0);
        DirectLinkVideoItem directLinkVideoItem3=new DirectLinkVideoItem("asfsadf","https://img.tukuppt.com/video_show/2405811/00/01/88/5b4c5a2107fb2.mp4",mVideoPlayerManager,Picasso.with(getContext()),0);
        mList.add(directLinkVideoItem);
        mList.add(directLinkVideoItem2);
        mList.add(directLinkVideoItem3);

//        try {
//            mList.add(ItemFactory.createItemFromAsset("video_sample_1.mp4", R.drawable.video_sample_1_pic, getActivity(), mVideoPlayerManager));
//            mList.add(ItemFactory.createItemFromAsset("video_sample_2.mp4", R.drawable.video_sample_2_pic, getActivity(), mVideoPlayerManager));
//            mList.add(ItemFactory.createItemFromAsset("video_sample_3.mp4", R.drawable.video_sample_3_pic, getActivity(), mVideoPlayerManager));
//            mList.add(ItemFactory.createItemFromAsset("video_sample_4.mp4", R.drawable.video_sample_3_pic, getActivity(), mVideoPlayerManager));
//
//            mList.add(ItemFactory.createItemFromAsset("video_sample_1.mp4", R.drawable.video_sample_1_pic, getActivity(), mVideoPlayerManager));
//            mList.add(ItemFactory.createItemFromAsset("video_sample_2.mp4", R.drawable.video_sample_2_pic, getActivity(), mVideoPlayerManager));
//            mList.add(ItemFactory.createItemFromAsset("video_sample_3.mp4", R.drawable.video_sample_3_pic, getActivity(), mVideoPlayerManager));
//            mList.add(ItemFactory.createItemFromAsset("video_sample_4.mp4", R.drawable.video_sample_3_pic, getActivity(), mVideoPlayerManager));
//
//            mList.add(ItemFactory.createItemFromAsset("video_sample_1.mp4", R.drawable.video_sample_1_pic, getActivity(), mVideoPlayerManager));
//            mList.add(ItemFactory.createItemFromAsset("video_sample_2.mp4", R.drawable.video_sample_2_pic, getActivity(), mVideoPlayerManager));
//            mList.add(ItemFactory.createItemFromAsset("video_sample_3.mp4", R.drawable.video_sample_3_pic, getActivity(), mVideoPlayerManager));
//            mList.add(ItemFactory.createItemFromAsset("video_sample_4.mp4", R.drawable.video_sample_3_pic, getActivity(), mVideoPlayerManager));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        View rootView = inflater.inflate(R.layout.fragment_video_recycler_view, container, false);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        VideoRecyclerViewAdapter videoRecyclerViewAdapter = new VideoRecyclerViewAdapter(mVideoPlayerManager, getActivity(), mList);

        mRecyclerView.setAdapter(videoRecyclerViewAdapter);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int scrollState) {
                mScrollState = scrollState;
                if(scrollState == RecyclerView.SCROLL_STATE_IDLE && !mList.isEmpty()){

                    mVideoVisibilityCalculator.onScrollStateIdle(
                            mItemsPositionGetter,
                            mLayoutManager.findFirstVisibleItemPosition(),
                            mLayoutManager.findLastVisibleItemPosition());
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if(!mList.isEmpty()){
                    mVideoVisibilityCalculator.onScroll(
                            mItemsPositionGetter,
                            mLayoutManager.findFirstVisibleItemPosition(),
                            mLayoutManager.findLastVisibleItemPosition() - mLayoutManager.findFirstVisibleItemPosition() + 1,
                            mScrollState);
                }
            }
        });
        mItemsPositionGetter = new RecyclerViewItemPositionGetter(mLayoutManager, mRecyclerView);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
//        playVideo();
    }

    public void playVideo() {
        if(!mList.isEmpty()){
            // need to call this method from list view handler in order to have filled list

            mRecyclerView.post(new Runnable() {
                @Override
                public void run() {

                    mVideoVisibilityCalculator.onScrollStateIdle(
                            mItemsPositionGetter,
                            mLayoutManager.findFirstVisibleItemPosition(),
                            mLayoutManager.findLastVisibleItemPosition());

                }
            });
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        // we have to stop any playback in onStop
        mVideoPlayerManager.resetMediaPlayer();
    }
}