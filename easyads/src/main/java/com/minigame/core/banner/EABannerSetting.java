package com.minigame.core.banner;

import android.view.ViewGroup;

import com.minigame.itf.BaseAdapterEvent;
import com.minigame.model.SdkSupplier;

/**
 * banner 位置的一些共有事件接口
 */
public interface EABannerSetting extends BaseAdapterEvent {
    void adapterDidDislike(SdkSupplier sdkSupplier);

    int getRefreshInterval();

    int getCsjExpressViewAcceptedWidth();

    int getCsjExpressViewAcceptedHeight();

    ViewGroup getContainer();
}
