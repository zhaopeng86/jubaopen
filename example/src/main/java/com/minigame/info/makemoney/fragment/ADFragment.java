package com.minigame.info.makemoney.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.minigame.info.R;
import com.minigame.info.activity.SingleUpdateView;
import com.minigame.info.activity.UpDateView;
import com.minigame.info.entity.AdItem;
import com.minigame.info.entity.GameItemBean;
import com.minigame.info.entity.OwerProjectEntity;
import com.minigame.info.listview.AdvideoAdapter;
import com.minigame.info.listview.GamelistAdapter;
import com.minigame.info.makemoney.GameDetailsActivity;
import com.minigame.info.utils.HttpUtil;
import com.minigame.info.utils.ParseManager;
import com.minigame.info.utils.UrlUtils;

import java.io.IOException;
import java.text.NumberFormat;

import okhttp3.Call;
import okhttp3.Response;

public class ADFragment extends Fragment {
    View rootView;
    ListView listView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HttpUtil.sendRequestAsynchronous(UrlUtils.App_Get_MyProject_list, new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                ParseManager.parseJsonWithListJsonObject(response, new SingleUpdateView() {
                    @Override
                    public void singleUpdateView(Object ob) {
//                        try {
//                            OwerProjectEntity[] owerProjectEntity=(OwerProjectEntity[])ob;
//                            TextView textViewconr=findViewById(R.id.cornid);
//                            textViewconr.setText(owerProjectEntity[0].getCommitinfo());
//                            TextView textViewmonry=findViewById(R.id.moneyid);
//                            int a=Integer.parseInt(String.valueOf(textViewconr.getText()));
//                            NumberFormat numberFormat = NumberFormat.getNumberInstance();
//                            numberFormat.setMaximumFractionDigits(2);
//                            double f=a;
//                            double c = f/10000;
//                            String d=numberFormat.format(c);
//                            textViewmonry.setText("约="+ d+ "元");
//                        }catch (Exception e){
//                            e.printStackTrace();
//                        }
                    }
                });
            }
        });

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.advideo, container, false);
            listView=rootView.findViewById(R.id.advideo);
            AdItem[]gameItemBean2={ new AdItem("看广告赚金币","看广告直接零钱，今天还剩8次","5000最高"),new AdItem("今天看10个广告领奖","以观看4个","200"),new AdItem("看广告赚金币","看广告直接零钱，今天还剩8次","100"),new AdItem("看广告赚金币","看广告直接零钱，今天还剩8次","5000最高"),new AdItem("看广告赚金币","看广告直接零钱，今天还剩8次","5000最高"),new AdItem("看广告赚金币","看广告直接零钱，今天还剩8次","5000最高"),new AdItem("看广告赚金币","看广告直接零钱，今天还剩8次","5000最高"),new AdItem("看广告赚金币","看广告直接零钱，今天还剩10次","5000最高"),new AdItem("看广告赚金币","看广告直接零钱，今天还剩8次","5000最高") };
            AdvideoAdapter gamelistAdapter2=new AdvideoAdapter(getContext(),gameItemBean2);
            listView.setAdapter(gamelistAdapter2);
        }
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("ADFragment","onResume");

    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("ADFragment","onPause");

    }
}
