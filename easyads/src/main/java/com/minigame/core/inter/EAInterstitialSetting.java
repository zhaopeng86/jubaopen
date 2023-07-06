package com.minigame.core.inter;

import com.minigame.itf.BaseAdapterEvent;
import com.minigame.model.SdkSupplier;
import com.qq.e.ads.interstitial2.UnifiedInterstitialMediaListener;

public interface EAInterstitialSetting extends BaseAdapterEvent {
    void adapterDidClosed(SdkSupplier sdkSupplier);

    float getCsjExpressViewWidth();

    float getCsjExpressViewHeight();

    UnifiedInterstitialMediaListener getYlhMediaListener();

    boolean isCsjNew();

}
