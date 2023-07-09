package com.minigame.info.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.minigame.info.R;
import com.minigame.info.utils.HttpUtil;
import com.minigame.info.utils.ParseManager;
import com.minigame.info.utils.UrlUtils;

import java.io.IOException;
import okhttp3.Call;
import okhttp3.Response;

public class MainActivity extends BaseActivity implements UpDateView{
    private String userName;
    private String passWord;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
        EditText un= findViewById(R.id.editTextTextPersonName);
        EditText ps= findViewById(R.id.passwordEditText);
        findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userName=un.getText().toString();
                passWord=ps.getText().toString();
                String passWordD5=Utils.stringToMD5(passWord);
//                String url = "https://hd215.api.yesapi.cn/?s=App.Table.Create&model_name=Peter&data=%7B%22username%22%3A%22赵云%22%2C%22mobilenumber%22%3A%22176990030092%22%7D&app_key=CD0FD909500176B1D464830847C96DDA&sign=32EA1778D32ADE7E8E396FD4D832FFF1";
//                String url="https://hd215.api.yesapi.cn/?s=App.Table.FreeQuery&return_data=0&model_name=Peter&logic=and&where=%5B%5B%22id%22%2C%22%3E%22%2C0%5D%5D&page=1&perpage=10&is_real_total=1&app_key=CD0FD909500176B1D464830847C96DDA&sign=5B75813E76093052136C7167BD94E5EF";
                HttpUtil.sendRequestAsynchronous(UrlUtils.App_Register_Url, new okhttp3.Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {}
                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        ParseManager.parseJsonWithJsonObject(response,MainActivity.this);
                    }
                });
            }

        });
    }

    @Override
    public void updateView(Object ob) {
        Toast.makeText(MainActivity.this, "恭喜您已经注册成功", Toast.LENGTH_SHORT).show();
        Intent intent =new Intent(MainActivity.this,LoginActivity.class);
        startActivity(intent);
    }
}