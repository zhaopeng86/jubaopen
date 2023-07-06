package com.minigame.custom;

import android.app.Activity;

import com.minigame.core.EABaseSupplierAdapter;
import com.minigame.core.full.EAFullScreenVideoSetting;

import java.lang.ref.SoftReference;

public abstract class EAFullScreenCustomAdapter extends EABaseSupplierAdapter {
    EAFullScreenVideoSetting mFullSetting;

    public EAFullScreenCustomAdapter(SoftReference<Activity> activity, EAFullScreenVideoSetting setting) {
        super(activity, setting);
        mFullSetting = setting;
    }

    public void handleCached() {
        try {
            if (null != mFullSetting) {
                mFullSetting.adapterVideoCached(sdkSupplier);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
