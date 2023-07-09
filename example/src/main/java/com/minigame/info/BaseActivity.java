package com.minigame.info;

import android.app.Activity;
import android.content.Context;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

import com.minigame.info.utils.DialogLogcat;
import com.minigame.info.utils.NormalSetting;
import com.squareup.seismic.ShakeDetector;

public class BaseActivity extends Activity {

    boolean isRunning = false;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ShakeDetector shakeDetector = new ShakeDetector(new ShakeDetector.Listener() {
            @Override
            public void hearShake() {
                if (NormalSetting.getInstance().showLogcat && isRunning()) {
                    new DialogLogcat(BaseActivity.this).show();
                }
            }
        });
        SensorManager sensorManager = (SensorManager) this.getSystemService(Context.SENSOR_SERVICE);
        shakeDetector.start(sensorManager);
    }

    public boolean isRunning() {
        Log.d("BaseActivity", "isRunning = " + isRunning);
        return isRunning;
    }

    @Override
    protected void onResume() {
        super.onResume();
        isRunning = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        isRunning = false;
    }
}
