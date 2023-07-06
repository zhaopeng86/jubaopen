package com.minigame.info.utils;

import android.os.Handler;
import android.os.Looper;

import com.minigame.info.activity.SingleUpdateView;
import com.minigame.info.activity.UpDateView;
import com.minigame.info.entity.BaseDataEntity;
import com.minigame.info.entity.GameItemBean;
import com.minigame.info.entity.OwerProjectEntity;
import com.minigame.info.entity.PromoteEntity;
import com.google.gson.Gson;

import org.json.JSONObject;
import java.io.IOException;
import okhttp3.Response;

public class ParseManager {
    public static final String PACKAGE_NAME="com.minigame.info";
    public static void parseJsonWithJsonObject(Response response, UpDateView upDateView) throws IOException {
        try{
            if (response.code()==200){
                if (upDateView!=null)
                    upDateView.updateView(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void parseJsonWithListJsonObject(Response response, SingleUpdateView upDateView)  {
        try {
            if (upDateView==null) return;
            String responseData = response.body().string();
            if (response == null)  throw new IOException();
            if (response.code() == 200) {
                JSONObject jsonArray = new JSONObject(responseData);
                if (jsonArray != null) {
                    BaseDataEntity[] userInfers =getBaseSingleDataEntities(upDateView, jsonArray.getJSONObject("data").getJSONArray("list").toString());
                    if (userInfers == null || userInfers.length == 0) return;
                    new Handler(Looper.getMainLooper()).post(() -> {
                        upDateView.singleUpdateView(userInfers);
                    });
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * @param response is serverResponse
     * @param upDateView  is implement
     * @throws IOException
     */
    public static void parseJsonWithListJsonObject(Response response,UpDateView upDateView)  {
        try {
            if (upDateView==null) return;
            String responseData = response.body().string();
            if (response == null)  throw new IOException();
            if (response.code() == 200) {
                JSONObject jsonArray = new JSONObject(responseData);
                if (jsonArray != null) {
                    BaseDataEntity[] userInfers = getBaseDataEntities(upDateView, jsonArray.getJSONObject("data").getJSONArray("list").toString());
                    if (userInfers == null || userInfers.length == 0) return;
                    new Handler(Looper.getMainLooper()).post(() -> {
                            upDateView.updateView(userInfers);
                    });
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param upDateView is updateView through data, implement updateView
     * @param jsonStr    is datasource, from server
     * @return BaseDataEntity
     *
     * case 的地方PACKAGE_NAME+".ListInfoActivity" 采用的是字符串拼接，存在性能开销，后续优化
     */
    private static BaseDataEntity[] getBaseDataEntities(UpDateView upDateView, String jsonStr) {
        BaseDataEntity[] userInfers =null;
        String className=upDateView.getClass().getName();
        switch (className){
            case PACKAGE_NAME+".ListInfoActivity":
                userInfers =new Gson().fromJson(jsonStr, PromoteEntity[].class);
                break;
            case PACKAGE_NAME+".OwnerProjectActivity":
                userInfers =new Gson().fromJson(jsonStr, OwerProjectEntity[].class);
                break;
            case PACKAGE_NAME+".makemoney.MakeMoneyActivity":
                userInfers =new Gson().fromJson(jsonStr, GameItemBean[].class);
                break;
            default:
                break;
        }
        return userInfers;
    }

    private static BaseDataEntity[] getBaseSingleDataEntities(SingleUpdateView upDateView, String jsonStr) {
        BaseDataEntity[] userInfers =null;
        String className=upDateView.getClass().getName();
        userInfers =new Gson().fromJson(jsonStr, OwerProjectEntity[].class);
        return userInfers;
    }


}
