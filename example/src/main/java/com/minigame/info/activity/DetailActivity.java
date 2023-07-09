package com.minigame.info.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.minigame.info.R;
import com.minigame.info.utils.HttpUtil;
import com.minigame.info.utils.ParseManager;
import com.minigame.info.utils.UrlUtils;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Response;


public class DetailActivity extends BaseActivity implements UpDateView{
    TextView textViewInfo;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);
        textViewInfo=findViewById(R.id.textView3);
        Intent intent = getIntent();
        String str = intent.getStringExtra("data");
        textViewInfo.setText(str+"：报备信息");
        findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText etUserName=findViewById(R.id.editTextTextPersonName);
                String userName=etUserName.getText().toString();
                EditText etNumber=findViewById(R.id.passwordEditText);
                String strNumber=etNumber.getText().toString();
                if (!TextUtils.isEmpty(userName)&&!TextUtils.isEmpty(strNumber)){
                    HttpUtil.sendRequestAsynchronous(UrlUtils.createUrlInfor(userName,strNumber,str), new okhttp3.Callback() {
                        @Override
                        public void onResponse(@NotNull okhttp3.Call call, @NotNull Response response) throws IOException {
                            ParseManager.parseJsonWithJsonObject(response,DetailActivity.this);
                        }

                        @Override
                        public void onFailure(@NotNull okhttp3.Call call, @NotNull IOException e) {

                        }
                    });
                }else {
                    Toast.makeText(DetailActivity.this, "请填写必要信息", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    public void updateView(Object ob) {
        Intent intent =new Intent(DetailActivity.this, OwnerProjectActivity.class);
        startActivity(intent);
    }

}
