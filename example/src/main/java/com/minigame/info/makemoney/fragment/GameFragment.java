package com.minigame.info.makemoney.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.minigame.info.R;
import com.minigame.info.activity.UpDateView;
import com.minigame.info.entity.GameItemBean;
import com.minigame.info.listview.GamelistAdapter;
import com.minigame.info.makemoney.GameDetailsActivity;
import com.minigame.info.utils.HttpUtil;
import com.minigame.info.utils.ParseManager;
import com.minigame.info.utils.UrlUtils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

public class GameFragment extends Fragment implements UpDateView {
    View rootView;
    ListView listView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HttpUtil.sendRequestAsynchronous(UrlUtils.App_Get_Game_list, new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                ParseManager.parseJsonWithListJsonObject(response, GameFragment.this);
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.mingame, container, false);
            listView=rootView.findViewById(R.id.gamelist);
        }
        return rootView;
    }

    @Override
    public void updateView(Object ob) {
        try {
            GameItemBean[] userInfers = (GameItemBean[]) ob;
            GamelistAdapter listInformAdapter = new GamelistAdapter(getContext(), (GameItemBean[]) userInfers);
            listView.setAdapter(listInformAdapter);
            listView.setOnItemClickListener((parent, view, position, id) -> {
                Intent intent=new Intent(getContext(), GameDetailsActivity.class);
                intent.putExtra("url", userInfers[position].getClickUrl());
                startActivity(intent);
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("GameFragment","onPause");

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("GameFragment","onResume");

    }
}
