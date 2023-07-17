package com.minigame.info.makemoney.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
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
import com.minigame.info.entity.ShortItemBean;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;

public class ShortCutVideoFragment extends Fragment {

    private View rootView;
    private VideoPlayRecyclerView mRvVideo;
    private MainAdapter adapter;

    public MainAdapter getAdapter() {
        return adapter;
    }
    LinkedHashSet<String> linkedHashSet=new LinkedHashSet<String>();
    private ArrayList<ShortItemBean> shortItemBeans=new ArrayList<>();

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

        adapter =new MainAdapter(getContext(),shortItemBeans);
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

