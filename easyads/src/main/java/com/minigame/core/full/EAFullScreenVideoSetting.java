package com.minigame.core.full;

import com.minigame.itf.BaseAdapterEvent;
import com.minigame.model.SdkSupplier;
import com.qq.e.ads.cfg.VideoOption;
import com.qq.e.ads.interstitial2.UnifiedInterstitialMediaListener;

public interface EAFullScreenVideoSetting extends BaseAdapterEvent {

    void adapterVideoCached(SdkSupplier sdkSupplier);

    void adapterVideoComplete(SdkSupplier sdkSupplier);

    void adapterClose(SdkSupplier sdkSupplier);

    void adapterVideoSkipped(SdkSupplier sdkSupplier);

    UnifiedInterstitialMediaListener getYlhMediaListener();

    VideoOption getYlhVideoOption();

}
