package com.minigame.info.makemoney;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
//import androidx.viewpager.widget.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.viewpager.widget.ViewPager;

import com.minigame.info.R;
import com.minigame.info.TaskDetailsEditeActivity;
import com.minigame.info.activity.BaseActivity;
import com.minigame.info.activity.SingleUpdateView;
import com.minigame.info.activity.UpDateView;
import com.minigame.info.customview.MainAdapter;
import com.minigame.info.customview.VideoPlayRecyclerView;
import com.minigame.info.entity.AdItem;
import com.minigame.info.entity.GameItemBean;
import com.minigame.info.entity.OwerProjectEntity;
import com.minigame.info.listvideo.video_list_demo.fragments.VideoRecyclerViewFragment;
import com.minigame.info.listview.AdvideoAdapter;
import com.minigame.info.listview.GamelistAdapter;
import com.minigame.info.makemoney.fragment.BlankFragment;
import com.minigame.info.utils.BaseCallBack;
import com.minigame.info.utils.Constant;
import com.minigame.info.utils.HttpUtil;
import com.minigame.info.utils.ParseManager;
import com.minigame.info.utils.UrlUtils;
import com.minigame.info.utils.UserPrivacyDialog;

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
    LinearLayout linearLayouttitle;

    TextView textViewtitle;
    /**
     * @param savedInstanceState
     */
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        linearLayoutAll= (ViewGroup) getLayoutInflater().inflate(R.layout.activity_video_list,null);
        setContentView(linearLayoutAll);
//        linearLayouttitle=findViewById(R.id.money);
//        textViewtitle=findViewById(R.id.gametitle);
//        linearLayouttitle.setVisibility(View.GONE);
//        textViewtitle.setVisibility(View.GONE);
//        boolean hasPri = getSharedPreferences(Constant.SP_NAME, Context.MODE_PRIVATE).getBoolean(Constant.SP_AGREE_PRIVACY, false);
//        if (!hasPri) {
//            UserPrivacyDialog dialog = new UserPrivacyDialog(this);
//            dialog.callBack = new BaseCallBack() {
//                @Override
//                public void call() {
//                    //一定要用户授权同意隐私协议后，再申领必要权限
//                    if (Build.VERSION.SDK_INT >= 23 && Build.VERSION.SDK_INT < 29) {
//                        checkAndRequestPermission();
//                    }
//                }
//            };
//            dialog.show();
//        }
//        HttpUtil.sendRequestAsynchronous(UrlUtils.App_Get_Game_list, new okhttp3.Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//
//            }
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                ParseManager.parseJsonWithListJsonObject(response, MakeMoneyActivity.this);
//            }
//        });
//        HttpUtil.sendRequestAsynchronous(UrlUtils.App_Get_MyProject_list, new okhttp3.Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//            }
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                ParseManager.parseJsonWithListJsonObject(response, new SingleUpdateView() {
//                    @Override
//                    public void singleUpdateView(Object ob) {
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
//                    }
//                });
//            }
//        });
//        inittabBar();
//        initViewPage();
//        initViewPageByFragment();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, new VideoRecyclerViewFragment())
                .commit();
//        TextView textView = findViewById(R.id.ll_include).findViewById(R.id.textView5);
//        textView.setTextColor(Color.RED);
//        Button button=findViewById(R.id.getmoney).findViewById(R.id.getmoneyto);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(), "满20元才能提现，请继续努力", Toast.LENGTH_SHORT).show();
//            }
//        });

    }

    private void initViewPageByFragment() {

        viewPager=findViewById(R.id.viewpage);
        LinkedList fragments= new LinkedList();
        fragments.add(new VideoRecyclerViewFragment());
//        fragments.add(BlankFragment.newInstance("我最丑","2"));
//        fragments.add(BlankFragment.newInstance("我很帅","3"));
//        fragments.add(BlankFragment.newInstance("我很丑","4"));
        MyAdapter fragmentAdapter=new MyAdapter(getSupportFragmentManager(),fragments);
        viewPager.setAdapter(fragmentAdapter);
    }

    public void inittabBar(){
        TextView textView = findViewById(R.id.ll_include).findViewById(R.id.textView5);
        textView.setText("视频");
        ImageView imageView= findViewById(R.id.ll_include).findViewById(R.id.imageView2);
        imageView.setBackgroundResource(R.drawable.video);

        TextView textView2 = findViewById(R.id.ll_include2).findViewById(R.id.textView5);
        textView2.setText("游戏");
        ImageView imageView2= findViewById(R.id.ll_include2).findViewById(R.id.imageView2);
        imageView2.setImageResource(R.drawable.game);

        TextView textView3 = findViewById(R.id.ll_include3).findViewById(R.id.textView5);
        textView3.setText("赚钱");
        ImageView imageView3= findViewById(R.id.ll_include3).findViewById(R.id.imageView2);
        imageView3.setImageResource(R.drawable.gw);

        TextView textView4 = findViewById(R.id.ll_include4).findViewById(R.id.textView5);
        textView4.setText("小视频");
        ImageView imageView4= findViewById(R.id.ll_include4).findViewById(R.id.imageView2);
        imageView4.setImageResource(R.drawable.sendtask);


        TextView textView5 = findViewById(R.id.ll_include5).findViewById(R.id.textView5);
        textView5.setText("我的");
        ImageView imageView5= findViewById(R.id.ll_include5).findViewById(R.id.imageView2);
        imageView5.setImageResource(R.drawable.sendtask);

        findViewById(R.id.ll_include).setOnClickListener(this);
        findViewById(R.id.ll_include2).setOnClickListener(this);
        findViewById(R.id.ll_include3).setOnClickListener(this);
        findViewById(R.id.ll_include4).setOnClickListener(this);
        findViewById(R.id.ll_include5).setOnClickListener(this);

    }
    private VideoPlayRecyclerView mRvVideo;
    private MainAdapter adapter;
    public void initViewPage(){
        viewPager=findViewById(R.id.viewpage);
        LinkedList list= new LinkedList();
        list.add(getLayoutInflater().inflate(R.layout.payment,null));
        list.add(getLayoutInflater().inflate(R.layout.mingame,null));
        list.add(getLayoutInflater().inflate(R.layout.advideo,null));
        list.add(getLayoutInflater().inflate(R.layout.tasklayout,null));
        list.add(getLayoutInflater().inflate(R.layout.setting,null));
        MoneyPageAdapter moneyPageAdapter=new MoneyPageAdapter(list);
        viewPager.setAdapter(moneyPageAdapter);
        listView=((ViewGroup)list.get(1)).findViewById(R.id.gamelist);
//        GameItemBean[]gameItemBean={ new GameItemBean("","1212"), new GameItemBean("","232"),new GameItemBean("","3342"),new GameItemBean("","3342"),new GameItemBean("","3342"),new GameItemBean("","3342"),new GameItemBean("","3342"),new GameItemBean("","3342"),new GameItemBean("","3342"),new GameItemBean("","3342")};
//        GamelistAdapter gamelistAdapter=new GamelistAdapter(this,gameItemBean);
//        listView.setAdapter(gamelistAdapter);
        ListView listView2=((ViewGroup)list.get(2)).findViewById(R.id.advideo);
        AdItem[]gameItemBean2={ new AdItem("看广告赚金币","看广告直接零钱，今天还剩8次","5000最高"),new AdItem("今天看10个广告领奖","以观看4个","200"),new AdItem("看广告赚金币","看广告直接零钱，今天还剩8次","100"),new AdItem("看广告赚金币","看广告直接零钱，今天还剩8次","5000最高"),new AdItem("看广告赚金币","看广告直接零钱，今天还剩8次","5000最高"),new AdItem("看广告赚金币","看广告直接零钱，今天还剩8次","5000最高"),new AdItem("看广告赚金币","看广告直接零钱，今天还剩8次","5000最高"),new AdItem("看广告赚金币","看广告直接零钱，今天还剩10次","5000最高"),new AdItem("看广告赚金币","看广告直接零钱，今天还剩8次","5000最高") };
        AdvideoAdapter gamelistAdapter2=new AdvideoAdapter(this,gameItemBean2);
        listView2.setAdapter(gamelistAdapter2);
        ViewGroup viewGroup= (ViewGroup) list.get(0);
        mRvVideo =viewGroup.findViewById(R.id.rvVideo);
        adapter = new MainAdapter(MakeMoneyActivity.this);
        mRvVideo.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener()
        {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                switch (i){
                    case 0:
                        resertColor(R.id.ll_include);
                        TextView textView = findViewById(R.id.ll_include).findViewById(R.id.textView5);
                        textView.setTextColor(Color.RED);
                        linearLayouttitle.setVisibility(View.GONE);
                        textViewtitle.setVisibility(View.GONE);
                        adapter.contineVideo();
                        break;
                    case 1:
                        resertColor(R.id.ll_include2);
                        TextView textView1 = findViewById(R.id.ll_include2).findViewById(R.id.textView5);
                        textView1.setTextColor(Color.RED);
                        linearLayouttitle.setVisibility(View.VISIBLE);
                        textViewtitle.setVisibility(View.VISIBLE);
                        adapter.pauseVideo();
                        break;
                    case 2:
                        resertColor(R.id.ll_include3);
                        TextView textView3 = findViewById(R.id.ll_include3).findViewById(R.id.textView5);
                        textView3.setTextColor(Color.RED);
                        linearLayouttitle.setVisibility(View.VISIBLE);
                        textViewtitle.setVisibility(View.VISIBLE);
                        adapter.pauseVideo();

                        break;

                    case 3:
                        resertColor(R.id.ll_include4);
                        TextView textView4 = findViewById(R.id.ll_include4).findViewById(R.id.textView5);
                        textView4.setTextColor(Color.RED);
                        linearLayouttitle.setVisibility(View.VISIBLE);
                        textViewtitle.setVisibility(View.VISIBLE);
                        adapter.pauseVideo();

                        break;
                    case 4:
                        resertColor(R.id.ll_include5);
                        TextView textView5 = findViewById(R.id.ll_include5).findViewById(R.id.textView5);
                        textView5.setTextColor(Color.RED);
                        linearLayouttitle.setVisibility(View.VISIBLE);
                        textViewtitle.setVisibility(View.VISIBLE);
                        adapter.pauseVideo();

                        break;


                }

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_include:
                if (viewPager!=null)
                viewPager.setCurrentItem(0);
                break;
            case R.id.ll_include2:
                if (viewPager!=null)
                    viewPager.setCurrentItem(1);
                break;
            case R.id.ll_include3:
                if (viewPager!=null)
                    viewPager.setCurrentItem(2);
                break;
            case R.id.ll_include4:
                if (viewPager!=null)
                    viewPager.setCurrentItem(3);
                break;
            case R.id.ll_include5:
                if (viewPager!=null)
                    viewPager.setCurrentItem(4);
                break;
        }
    }

    private void resertColor(int id){
        if (id!=R.id.ll_include){
            TextView textView = findViewById(R.id.ll_include).findViewById(R.id.textView5);
            textView.setTextColor(Color.BLACK);
        }
        if (id!=R.id.ll_include2) {
            TextView textView1 = findViewById(R.id.ll_include2).findViewById(R.id.textView5);
            textView1.setTextColor(Color.BLACK);
        }

        if (id!=R.id.ll_include3) {
            TextView textView3 = findViewById(R.id.ll_include3).findViewById(R.id.textView5);
            textView3.setTextColor(Color.BLACK);
        }

        if (id!=R.id.ll_include4) {
            TextView textView4 = findViewById(R.id.ll_include4).findViewById(R.id.textView5);
            textView4.setTextColor(Color.BLACK);
        }

        if (id!=R.id.ll_include5) {
            TextView textView5 = findViewById(R.id.ll_include5).findViewById(R.id.textView5);
            textView5.setTextColor(Color.BLACK);
        }
    }
    @Override
    public void updateView(Object ob) {
        try {
            GameItemBean[] userInfers = (GameItemBean[]) ob;
            GamelistAdapter listInformAdapter = new GamelistAdapter(this, (GameItemBean[]) userInfers);
            listView.setAdapter(listInformAdapter);
            listView.setOnItemClickListener((parent, view, position, id) -> {
                Intent intent=new Intent(MakeMoneyActivity.this, GameDetailsActivity.class);
                intent.putExtra("url", userInfers[position].getClickUrl());
                startActivity(intent);
            });
        }catch (Exception e){
            e.printStackTrace();
        }

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
        try {
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
        catch (Exception e){
            e.printStackTrace();
        }
    }


    public  void reportTask(View view){
        Intent intent=new Intent(MakeMoneyActivity.this, TaskDetailsEditeActivity.class);
        startActivity(intent);
    }
}
