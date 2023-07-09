package com.minigame.info.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.minigame.info.MyApplication;
import com.minigame.info.R;

public class UserPrivacyDialog extends Dialog {

    public BaseCallBack callBack;

    public UserPrivacyDialog(@NonNull final Context context) {
        super(context);
        setContentView(R.layout.dialog_user_privacy);

        TextView cont = findViewById(R.id.tv_dup_content);
        cont.setText("获取手机基础权限，有助于更好的体验app");
        TextView y = findViewById(R.id.tv_dup_yes);
        TextView n = findViewById(R.id.tv_dup_no);

        y.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.getSharedPreferences(Constant.SP_NAME, Context.MODE_PRIVATE).edit().putBoolean(Constant.SP_AGREE_PRIVACY, true).commit();
                /** 注意！！！：一定要在用户同意APP隐私政策要求后再调用此初始化方法。*/

                if (callBack != null) {
                    callBack.call();
                }
                MyApplication.getInstance().initSDK();
                dismiss();
            }
        });

        n.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                System.exit(0);
            }
        });

        setCanceledOnTouchOutside(false);
        setCancelable(false);

    }
}
