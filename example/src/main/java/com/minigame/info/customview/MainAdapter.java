package com.minigame.info.customview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.minigame.info.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashSet;
import java.util.LinkedHashSet;


public class MainAdapter extends VideoPlayAdapter<MainAdapter.ViewHolder> {
    private Context mContext;

    private int mCurrentPosition;
    private ViewHolder mCurrentHolder;

    private VideoPlayer videoPlayer;
    private TextureView textureView;

    LinkedHashSet<String>linkedHashSet=new LinkedHashSet<String>();

    public MainAdapter(Context mContext) {
        this.mContext = mContext;
        videoPlayer = new VideoPlayer();
        textureView = new TextureView(mContext);
        videoPlayer.setTextureView(textureView);
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
        Glide.with(mContext).load("https://tx2.a.yximgs.com/upic/2020/04/24/20/BMjAyMDA0MjQyMDExMDRfNTkzMzMzMzJfMjczMzU3NDA0ODVfMV8z_B26385cd47079260e711ba54cdf65b594.jpg").apply(options).into(holder.ivCover);
    }


    @Override
    public int getItemCount() {
        return 20;
    }

    @Override
    public void onPageSelected(int itemPosition, View itemView) {
        mCurrentPosition = itemPosition;
        mCurrentHolder = new ViewHolder(itemView);
        playVideo();
    }

    public void pauseVideo(){
        videoPlayer.pause();
    }

    public void contineVideo(){

        videoPlayer.start();
    }
    public void playVideo() {
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

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {

                    String url = "https://haokan.baidu.com/";
                    Document doc = Jsoup.connect(url)
                            .timeout(5000)
                            .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.142 Safari/537.36")
                            .get();
                    Elements movieList = doc.select("div.ssr-videoitem");
                    for (Element content : movieList) {
//                        String links = movie.attr("href");
//                        Log.e("dfa",links);
                        Elements linksElements = content.getElementsByTag("a");
                        for (Element ele : linksElements) {
                            String a = ele.attr("href");
                            if (a.contains("recommend")){
                                linkedHashSet.add(a);
                            }
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }

                for (String o:linkedHashSet){

                    Log.e("afdadf",o);

                }


            }
        }).start();


        videoPlayer.setDataSource("https://vd4.bdstatic.com/mda-pgdybj10g39z4njs/sc/cae_h264/1689386790610360654/mda-pgdybj10g39z4njs.mp4?v_from_s=hkapp-haokan-hbf&auth_key=1689532901-0-0-416964d13e7a1ebf6958e4d39aa63f05&bcevod_channel=searchbox_feed&cr=2&cd=0&pd=1&pt=3&logid=2501623209&vid=9897889990358439252&abtest=&klogid=2501623209");

//        videoPlayer.setDataSource("https://txmov2.a.yximgs.com/bs2/newWatermark/MjczMzU3NDA0ODU_zh_3.mp4");

//                videoPlayer.setDataSource("https://download-video.akamaized.net/2/download/6c333d09-188c-458b-94ec-3b277da938dc/925b490e/pexels-noman-khan-17436125%20%281440p%29.mp4?__token__=st=1689515414~exp=1689530916~acl=%2F2%2Fdownload%2F6c333d09-188c-458b-94ec-3b277da938dc%2F925b490e%2Fpexels-noman-khan-17436125%2520%25281440p%2529.mp4%2A~hmac=4a09b37b37f3c7a1758b65362772fe0b2025d2952d32d85a117de76ac934ee65&r=dXMtY2VudHJhbDE%3D");

        videoPlayer.prepare();
    }

    public void release() {
        videoPlayer.release();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private FrameLayout flVideo;
        private ImageView ivCover;
        private VideoLoadingProgressbar pbLoading;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            flVideo = itemView.findViewById(R.id.flVideo);
            ivCover = itemView.findViewById(R.id.ivCover);
            pbLoading = itemView.findViewById(R.id.pbLoading);
        }
    }
}
