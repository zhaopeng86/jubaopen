package com.minigame.info.customview;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.minigame.info.R;
import com.minigame.info.entity.ShortItemBean;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;


public class MainAdapter extends VideoPlayAdapter<MainAdapter.ViewHolder> {
    private Context mContext;

    private int mCurrentPosition;
    private ViewHolder mCurrentHolder;
    private VideoPlayer videoPlayer;
    private TextureView textureView;

    private ArrayList<ShortItemBean> shortItemBeans;

    public MainAdapter(Context mContext,ArrayList<ShortItemBean> shortItemBean) {
        this.mContext = mContext;
        videoPlayer = new VideoPlayer();
        textureView = new TextureView(mContext);
        videoPlayer.setTextureView(textureView);
        this.shortItemBeans=shortItemBean;
        getDataFromServer(1);
    }

    private void getDataFromServer(int page) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String buffer="zonghe_0_0_0_0_0_0_"+2+".html";
                    String url = "https://www.tukuppt.com/video/v103/";
                    if (page>=2){
                        url=url+buffer;
                    }
                    Document doc = Jsoup.connect(url)
                            .timeout(5000)
                            .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.71 Safari/537.36")
                            .get();
                    Elements videolist = doc.select("div.b-box dl");
                    for (Element element : videolist) {
                        ShortItemBean shortItemBean=new ShortItemBean();
                        String title=element.select("dt.title").text();
                        shortItemBean.setTitle(title);
                        Elements linksElements = element.getElementsByTag("a");
                        for (Element ele : linksElements) {
                            Elements elements=ele.select("video");
                            for (Element element1:elements){
                                String text=element1.attr("src");
                                String imageUrl=element1.attr("poster");
                                shortItemBean.setVideoUrl("https:"+text);
                                shortItemBean.setImageUrl("https:"+imageUrl);
                                shortItemBeans.add(shortItemBean);
                            }
                        }
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                handler.sendEmptyMessage(1);

            }
        }).start();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_main, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RequestOptions options = new RequestOptions().diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        Glide.with(mContext).load(shortItemBeans.get(position).getImageUrl()).apply(options).into(holder.ivCover);
        holder.nickName.setText(shortItemBeans.get(position).getTitle());
        holder.content.setText(String.valueOf(position));
    }


    @Override
    public int getItemCount() {
        return shortItemBeans.size();
    }

    @Override
    public void onPageSelected(int itemPosition, View itemView) {
        mCurrentPosition = itemPosition;
        mCurrentHolder = new ViewHolder(itemView);
        playVideo(shortItemBeans.get(itemPosition).getVideoUrl());

        if (itemPosition==shortItemBeans.size()-5){

            getDataFromServer(2);
        }
    }

    public void pauseVideo(){
        videoPlayer.pause();
    }

    public void contineVideo(){

        videoPlayer.start();
    }
    public void playVideo(String url) {
        videoPlayer.reset();
        mCurrentHolder.pbLoading.setVisibility(View.VISIBLE);
        videoPlayer.setOnStateChangeListener(new VideoPlayer.OnStateChangeListener() {
            @Override
            public void onReset() {
                mCurrentHolder.ivCover.setVisibility(View.VISIBLE);
                mCurrentHolder.pbLoading.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onRenderingStart() {
                mCurrentHolder.ivCover.setVisibility(View.GONE);
                mCurrentHolder.pbLoading.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onProgressUpdate(float per) {
            }

            @Override
            public void onPause() {
                mCurrentHolder.pbLoading.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onStop() {
                mCurrentHolder.ivCover.setVisibility(View.VISIBLE);
                mCurrentHolder.pbLoading.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onComplete() {
                videoPlayer.start();
            }
        });
        if (textureView.getParent() != mCurrentHolder.flVideo) {
            if (textureView.getParent() != null) {
                ((FrameLayout) textureView.getParent()).removeView(textureView);
            }
            mCurrentHolder.flVideo.addView(textureView);
        }
        if (!TextUtils.isEmpty(url)){
            videoPlayer.setDataSource(url);
            videoPlayer.prepare();
        }
    }

    public void release() {
        videoPlayer.release();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private FrameLayout flVideo;
        private ImageView ivCover;
        private VideoLoadingProgressbar pbLoading;

        private TextView nickName;

        private TextView content;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            flVideo = itemView.findViewById(R.id.flVideo);
            ivCover = itemView.findViewById(R.id.ivCover);
            pbLoading = itemView.findViewById(R.id.pbLoading);
            nickName=itemView.findViewById(R.id.tvNickname);
            content =itemView.findViewById(R.id.tvContent);

        }
    }


    Handler handler =new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            MainAdapter.this.notifyDataSetChanged();

        }
    };
}
