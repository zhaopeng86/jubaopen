package com.minigame.info.entity;

import java.util.LinkedHashSet;

public class ShortItemBean {
   private LinkedHashSet<String> linkedHashSet=new LinkedHashSet<String>();
   private String title;
   private String imageUrl;

    public String getVideoUrl() {
        return videoUrl;
    }



    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    private String videoUrl;

    public LinkedHashSet<String> getLinkedHashSet() {
        return linkedHashSet;
    }

    public void setLinkedHashSet(LinkedHashSet<String> linkedHashSet) {
        this.linkedHashSet = linkedHashSet;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
