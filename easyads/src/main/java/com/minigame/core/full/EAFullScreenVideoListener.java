package com.minigame.core.full;

import com.minigame.itf.EABaseADListener;

public interface EAFullScreenVideoListener extends EABaseADListener {
    void onVideoComplete();

    void onVideoSkipped();

    void onVideoCached();
}
