package com.easyads.info.makemoney;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.view.ViewPager;
//import androidx.viewpager.widget.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;


import com.easyads.info.R;
import com.easyads.info.activity.BaseActivity;
import com.easyads.info.activity.DetailActivity;
import com.easyads.info.activity.SingleUpdateView;
import com.easyads.info.activity.UpDateView;
import com.easyads.info.entity.AdItem;
import com.easyads.info.entity.GameItemBean;
import com.easyads.info.entity.OwerProjectEntity;
import com.easyads.info.listview.AdvideoAdapter;
import com.easyads.info.listview.GamelistAdapter;
import com.easyads.info.utils.BaseCallBack;
import com.easyads.info.utils.Constant;
import com.easyads.info.utils.HttpUtil;
import com.easyads.info.utils.ParseManager;
import com.easyads.info.utils.UrlUtils;
import com.easyads.info.utils.UserPrivacyDialog;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;
public class MakeMoneyActivity extends BaseActivity implements View.OnClickListener, UpDateView {
    ViewGroup linearLayoutAll;
    ViewPager viewPager;
    ListView listView;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        linearLayoutAll= (ViewGroup) getLayoutInflater().inflate(R.layout.makemoney,null);
        setContentView(linearLayoutAll);
        boolean hasPri = getSharedPreferences(Constant.SP_NAME, Context.MODE_PRIVATE).getBoolean(Constant.SP_AGREE_PRIVACY, false);
        /**
         * 注意！：由于工信部对设备权限等隐私权限要求愈加严格，强烈推荐APP提前申请好权限，且用户同意隐私政策后再加载广告
         */
        if (!hasPri) {
            UserPrivacyDialog dialog = new UserPrivacyDialog(this);
            dialog.callBack = new BaseCallBack() {
                @Override
                public void call() {
                    //一定要用户授权同意隐私协议后，再申领必要权限
                    if (Build.VERSION.SDK_INT >= 23 && Build.VERSION.SDK_INT < 29) {
                        checkAndRequestPermission();
                    }
                }
            };
            dialog.show();
        }
        HttpUtil.sendRequestAsynchronous(UrlUtils.App_Get_Game_list, new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                ParseManager.parseJsonWithListJsonObject(response, MakeMoneyActivity.this);
            }
        });

        HttpUtil.sendRequestAsynchronous(UrlUtils.App_Get_MyProject_list, new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                ParseManager.parseJsonWithListJsonObject(response, new SingleUpdateView() {
                    @Override
                    public void singleUpdateView(Object ob) {
                        OwerProjectEntity[] owerProjectEntity=(OwerProjectEntity[])ob;
                        TextView textViewconr=findViewById(R.id.cornid);
                        textViewconr.setText(owerProjectEntity[0].getCommitinfo());
                        TextView textViewmonry=findViewById(R.id.moneyid);
                        int a=Integer.parseInt(String.valueOf(textViewconr.getText()));
                        NumberFormat numberFormat = NumberFormat.getNumberInstance();
                        numberFormat.setMaximumFractionDigits(2);
                        double f=a;
                        double c = f/10000;
                        String d=numberFormat.format(c);
                        textViewmonry.setText("约="+ d+ "元");
                    }
                });
            }
        });

        inittabBar();
        initViewPage();
    }
    public void inittabBar(){
        TextView textView = findViewById(R.id.ll_include).findViewById(R.id.textView5);
        textView.setText("游戏");
        TextView textView2 = findViewById(R.id.ll_include2).findViewById(R.id.textView5);
        textView2.setText("看视频");
        TextView textView3 = findViewById(R.id.ll_include3).findViewById(R.id.textView5);
        textView3.setText("购物");
        TextView textView4 = findViewById(R.id.ll_include4).findViewById(R.id.textView5);
        textView4.setText("我的");
        findViewById(R.id.ll_include).setOnClickListener(this);
        findViewById(R.id.ll_include2).setOnClickListener(this);
        findViewById(R.id.ll_include3).setOnClickListener(this);
        findViewById(R.id.ll_include4).setOnClickListener(this);
    }
    public void initViewPage(){
        viewPager=findViewById(R.id.viewpage);
        LinkedList list= new LinkedList();
        list.add(getLayoutInflater().inflate(R.layout.mingame,null));
        list.add(getLayoutInflater().inflate(R.layout.advideo,null));
        list.add(getLayoutInflater().inflate(R.layout.payment,null));
        list.add(getLayoutInflater().inflate(R.layout.setting,null));
        MoneyPageAdapter moneyPageAdapter=new MoneyPageAdapter(list);
        viewPager.setAdapter(moneyPageAdapter);
        listView=((ViewGroup)list.get(0)).findViewById(R.id.gamelist);
        GameItemBean[]gameItemBean={ new GameItemBean("","1212"), new GameItemBean("","232"),new GameItemBean("","3342"),new GameItemBean("","3342"),new GameItemBean("","3342"),new GameItemBean("","3342"),new GameItemBean("","3342"),new GameItemBean("","3342"),new GameItemBean("","3342"),new GameItemBean("","3342")};
        GamelistAdapter gamelistAdapter=new GamelistAdapter(this,gameItemBean);
        listView.setAdapter(gamelistAdapter);
        ListView listView2=((ViewGroup)list.get(1)).findViewById(R.id.advideo);
        AdItem[]gameItemBean2={ new AdItem("看广告赚金币","看广告直接零钱，今天还剩8次","5000最高"),new AdItem("今天看10个广告领奖","以观看4个","200"),new AdItem("看广告赚金币","看广告直接零钱，今天还剩8次","100"),new AdItem("看广告赚金币","看广告直接零钱，今天还剩8次","5000最高"),new AdItem("看广告赚金币","看广告直接零钱，今天还剩8次","5000最高"),new AdItem("看广告赚金币","看广告直接零钱，今天还剩8次","5000最高"),new AdItem("看广告赚金币","看广告直接零钱，今天还剩8次","5000最高"),new AdItem("看广告赚金币","看广告直接零钱，今天还剩8次","5000最高"),new AdItem("看广告赚金币","看广告直接零钱，今天还剩8次","5000最高"),new AdItem("看广告赚金币","看广告直接零钱，今天还剩8次","5000最高"),new AdItem("看广告赚金币","看广告直接零钱，今天还剩8次","5000最高"),new AdItem("看广告赚金币","看广告直接零钱，今天还剩8次","5000最高") };
        AdvideoAdapter gamelistAdapter2=new AdvideoAdapter(this,gameItemBean2);
        listView2.setAdapter(gamelistAdapter2);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_include:
                viewPager.setCurrentItem(0);
                break;
            case R.id.ll_include2:
                viewPager.setCurrentItem(1);
                break;
            case R.id.ll_include3:
                viewPager.setCurrentItem(2);
                break;
            case R.id.ll_include4:
                viewPager.setCurrentItem(3);
                break;
        }
    }
    @Override
    public void updateView(Object ob) {
        GameItemBean[] userInfers = (GameItemBean[]) ob;
        GamelistAdapter listInformAdapter = new GamelistAdapter(this, (GameItemBean[]) userInfers);
        listView.setAdapter(listInformAdapter);
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent=new Intent(MakeMoneyActivity.this, GameDetailsActivity.class);
            intent.putExtra("url", userInfers[position].getClickUrl());
            startActivity(intent);
        });
    }
    @TargetApi(Build.VERSION_CODES.M)
    private void checkAndRequestPermission() {
        List<String> lackedPermission = new ArrayList<String>();
        if (!(checkSelfPermission(Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED)) {
            lackedPermission.add(Manifest.permission.READ_PHONE_STATE);
        }

        if (!(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)) {
            lackedPermission.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }

        // 缺少权限，进行申请
        if (lackedPermission.size() > 0) {
            // 请求所缺少的权限，在onRequestPermissionsResult中再看是否获得权限，如果获得权限就可以调用SDK，否则不要调用SDK。
            String[] requestPermissions = new String[lackedPermission.size()];
            lackedPermission.toArray(requestPermissions);
            requestPermissions(requestPermissions, 1024);
        }
    }

    public Handler handler= new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 101:
                    updateMoneyText(msg.arg1);
                    updateServerMoney();
                    break;
                default:
                break;
            }
        }
    };


    public void updateServerMoney(){
        TextView textViewconr=findViewById(R.id.cornid);
        HttpUtil.sendRequestAsynchronous(UrlUtils.updateInfor(String.valueOf(textViewconr.getText().toString())), new okhttp3.Callback() {
            @Override
            public void onResponse(@NotNull okhttp3.Call call, @NotNull Response response) throws IOException {
            }
            @Override
            public void onFailure(@NotNull okhttp3.Call call, @NotNull IOException e) {
            }
        });
    }
    private void updateMoneyText(int value) {
        TextView textViewconr=findViewById(R.id.cornid);
        int b=Integer.parseInt(String.valueOf(textViewconr.getText()))+value;
        textViewconr.setText(String.valueOf(b));
        TextView textViewmonry=findViewById(R.id.moneyid);
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        numberFormat.setMaximumFractionDigits(2);
        double f=b;
        String d=numberFormat.format(f/10000);
        textViewmonry.setText("约="+ d+ "元");
    }

}
