package com.minigame.core.draw;

import android.view.ViewGroup;

import com.minigame.itf.BaseAdapterEvent;

public interface EADrawSetting extends BaseAdapterEvent {
    ViewGroup getContainer();

    int getCsjExpressHeight();

    int getCsjExpressWidth();
}
