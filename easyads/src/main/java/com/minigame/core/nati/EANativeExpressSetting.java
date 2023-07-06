package com.minigame.core.nati;

import android.view.View;
import android.view.ViewGroup;

import com.minigame.itf.BaseAdapterEvent;
import com.minigame.model.SdkSupplier;

public interface EANativeExpressSetting extends BaseAdapterEvent {

    int getExpressViewWidth();

    int getExpressViewHeight();

    boolean isVideoMute();

    void adapterRenderFailed(SdkSupplier sdkSupplier);

    void adapterRenderSuccess(SdkSupplier sdkSupplier);

    void adapterDidClosed(SdkSupplier sdkSupplier);

    void setNativeExpressADView(View expressADView);

    int getYlhMaxVideoDuration();

    ViewGroup getAdContainer();
}
