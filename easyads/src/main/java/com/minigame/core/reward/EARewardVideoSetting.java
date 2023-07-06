package com.minigame.core.reward;

import com.minigame.itf.BaseAdapterEvent;
import com.minigame.model.SdkSupplier;
import com.qq.e.ads.rewardvideo.ServerSideVerificationOptions;

public interface EARewardVideoSetting extends BaseAdapterEvent {

    void adapterVideoCached(SdkSupplier sdkSupplier);

    void adapterAdReward(SdkSupplier sdkSupplier);

    void adapterVideoSkipped(SdkSupplier sdkSupplier);

    void postRewardServerInf(EARewardServerCallBackInf inf, SdkSupplier sdkSupplier);

    void adapterVideoComplete(SdkSupplier sdkSupplier);

    void adapterDidClosed(SdkSupplier sdkSupplier);


    String getCsjRewardName();

    int getCsjRewardAmount();

    String getCsjUserId();

    int getCsjOrientation();

    String getCsjMediaExtra();

    boolean isYlhVolumeOn();


    ServerSideVerificationOptions getYlhSSVO();
}
