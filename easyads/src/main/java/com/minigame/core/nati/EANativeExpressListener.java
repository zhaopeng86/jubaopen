package com.minigame.core.nati;

import com.minigame.itf.EABaseADListener;

public interface EANativeExpressListener extends EABaseADListener {
    void onAdRenderFailed();

    void onAdRenderSuccess();
}
