package com.minigame.info.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.minigame.info.R;
import com.minigame.info.entity.PromoteEntity;
import com.minigame.info.listview.ListInferAdapter;
import com.minigame.info.utils.HttpUtil;
import com.minigame.info.utils.ParseManager;
import com.minigame.info.utils.UrlUtils;

import java.io.IOException;
import okhttp3.Call;
import okhttp3.Response;
public class ListInfoActivity extends BaseActivity implements UpDateView{
    public ListView listView;
    SwipeRefreshLayout swipeRefreshLayout;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listinfolayout);
        listView =findViewById(R.id.list);
        swipeRefreshLayout=findViewById(R.id.swipeRefreshLayout);
        HttpUtil.sendRequestAsynchronous(UrlUtils.App_Get_Info_list, new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {}
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                ParseManager.parseJsonWithListJsonObject(response,ListInfoActivity.this);
            }
        });


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                HttpUtil.sendRequestAsynchronous(UrlUtils.App_Get_Info_list, new okhttp3.Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {}
                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        ParseManager.parseJsonWithListJsonObject(response,ListInfoActivity.this);
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });

            }
        });
    }

    @Override
    public void updateView(Object ob) {
        PromoteEntity[] userInfers = (PromoteEntity[]) ob;
        ListInferAdapter listInformAdapter = new ListInferAdapter(this, (PromoteEntity[]) userInfers);
        listView.setAdapter(listInformAdapter);
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent=new Intent(ListInfoActivity.this,DetailActivity.class);
            intent.putExtra("data", userInfers[position].getTitle());
            startActivity(intent);
        });
    }
}
