package com.minigame.info.makemoney.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.minigame.info.R;
import com.minigame.info.customview.MainAdapter;
import com.minigame.info.customview.VideoPlayRecyclerView;

public class ShortCutVideoFragment extends Fragment {

    private View rootView;
    private VideoPlayRecyclerView mRvVideo;
    private MainAdapter adapter;

    public MainAdapter getAdapter() {
        return adapter;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(rootView == null) {
            rootView = inflater.inflate(R.layout.payment, container, false);
            mRvVideo=rootView.findViewById(R.id.rvVideo);
            initView();
        }
        return rootView;
    }

    private void initView() {
        adapter =new MainAdapter(getContext());
        mRvVideo.setAdapter(adapter);
    }

    @Override
    public void onPause() {
        super.onPause();
        adapter.pauseVideo();
        Log.e("ShortCutVideoFragment","onPause");
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.contineVideo();
        Log.e("ShortCutVideoFragment","onResume");

    }
}
