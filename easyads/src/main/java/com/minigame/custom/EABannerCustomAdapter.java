package com.minigame.custom;

import android.app.Activity;

import com.minigame.core.EABaseSupplierAdapter;
import com.minigame.itf.BaseAdapterEvent;

import java.lang.ref.SoftReference;

public abstract class EABannerCustomAdapter extends EABaseSupplierAdapter {

    public EABannerCustomAdapter(SoftReference<Activity> softReferenceActivity, BaseAdapterEvent baseListener) {
        super(softReferenceActivity, baseListener);
    }
}
