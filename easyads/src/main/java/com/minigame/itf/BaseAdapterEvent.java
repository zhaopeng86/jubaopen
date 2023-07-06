package com.minigame.itf;

import com.minigame.model.EasyAdError;
import com.minigame.model.SdkSupplier;

/**
 * adapter 处理基类，所有的广告adapter必须实现的方法
 */
public interface BaseAdapterEvent {
    void adapterDidSucceed(SdkSupplier supplier);

    void adapterDidExposure(SdkSupplier supplier);

    void adapterDidClicked(SdkSupplier supplier);

    void adapterDidFailed(EasyAdError easyAdError, SdkSupplier sdkSupplier);//聚合失败回调,回传sdkSupplier信息

    SdkSupplier getSupplierInf(); //获取当前的渠道策略信息
}
