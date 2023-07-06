package com.minigame.custom;

import android.app.Activity;

import com.minigame.core.EABaseSupplierAdapter;
import com.minigame.core.inter.EAInterstitialSetting;

import java.lang.ref.SoftReference;

public abstract class EAInterstitialCustomAdapter extends EABaseSupplierAdapter {
    EAInterstitialSetting mInterSetting;

    public EAInterstitialCustomAdapter(SoftReference<Activity> activity, EAInterstitialSetting setting) {
        super(activity, setting);
        mInterSetting = setting;
    }

}
