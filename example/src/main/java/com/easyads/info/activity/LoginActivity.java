package com.easyads.info.activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.easyads.info.R;

public class LoginActivity extends BaseActivity {
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginlayout);
        findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(LoginActivity.this,ListInfoActivity.class);
                startActivity(intent);
            }
        });
    }
}
