package com.minigame.info.makemoney;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.minigame.info.R;


public class SetttingTaskActivity extends Activity {
    private AlertDialog alertDialog2; //单选框

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settask);
        TextView textView=findViewById(R.id.ll_includetasktitle).findViewById(R.id.title);
        textView.setText("编辑任务步骤");
    }

    public void showSingleAlertDialog(View view){
        final String[] items = {"抖音推广", "快手推广", "ETC业务", "微信点赞"};
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setTitle("选择任务类型");
        Button button= findViewById(R.id.tasktype);
        alertBuilder.setSingleChoiceItems(items, chooseButton(items,button.getText().toString()), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Button button= findViewById(R.id.tasktype);
                button.setText(items[i]);
            }
        });

        alertBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                alertDialog2.dismiss();
            }
        });

        alertBuilder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Button button= findViewById(R.id.tasktype);
//                button.setText(items[i]);
                alertDialog2.dismiss();
            }
        });

        alertDialog2 = alertBuilder.create();
        alertDialog2.show();
    }
    public int  chooseButton(String[] items,String item){
        for (int i=0;i<items.length;i++){
            if (items[i].equals(item))return i;
        }
        return -1;
    }
    public void  setttaskfinish(View view){
        Toast.makeText(SetttingTaskActivity.this, "设置完成", Toast.LENGTH_SHORT).show();
        this.finish();
    }


}
