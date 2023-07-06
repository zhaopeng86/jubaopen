package com.minigame.core.splash;

import android.view.ViewGroup;

import com.minigame.itf.BaseAdapterEvent;
import com.minigame.model.SdkSupplier;

public interface EASplashSetting extends BaseAdapterEvent {

    int getCsjAcceptedSizeWidth();

    int getCsjAcceptedSizeHeight();

    float getCsjExpressViewWidth();

    float getCsjExpressViewHeight();

    boolean getCsjShowAsExpress();

    void adapterDidSkip(SdkSupplier sdkSupplier);

    void adapterDidTimeOver(SdkSupplier sdkSupplier);

    ViewGroup getAdContainer();

    boolean isShowInSingleActivity();
}
