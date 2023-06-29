package com.easyads.info.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.easyads.info.R;
import com.easyads.info.entity.OwerProjectEntity;
import com.easyads.info.listview.OwerProjectListAdapter;
import com.easyads.info.utils.HttpUtil;
import com.easyads.info.utils.ParseManager;
import com.easyads.info.utils.UrlUtils;

import java.io.IOException;
import okhttp3.Call;
import okhttp3.Response;
public class OwnerProjectActivity extends BaseActivity implements UpDateView{
    public ListView listView;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.owerprojectmain);
        listView=findViewById(R.id.listower);
        HttpUtil.sendRequestAsynchronous(UrlUtils.App_Get_MyProject_list, new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {}
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                ParseManager.parseJsonWithListJsonObject(response,OwnerProjectActivity.this);
            }
        });
    }

    @Override
    public void updateView(Object ob) {
        OwerProjectEntity[] userInfers = (OwerProjectEntity[]) ob;
        OwerProjectListAdapter listInferAdapter=new OwerProjectListAdapter(OwnerProjectActivity.this, userInfers);
        listView.setAdapter(listInferAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(OwnerProjectActivity.this,DetailActivity.class);
                intent.putExtra("data",userInfers[position].getCommitinfo());
                startActivity(intent);
            }
        });
    }
}
