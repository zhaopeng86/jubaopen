package com.minigame.info.listvideo.listvideo.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


public class Utils {

    private static final String TAG = Utils.class.getSimpleName();
    private static final boolean SHOW_LOGS = true;

    public static boolean isNetworkConnected(Context ctx) {
        boolean isNetworkConnected;
        ConnectivityManager cm = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        isNetworkConnected = ni != null;
        return isNetworkConnected;
    }

}
