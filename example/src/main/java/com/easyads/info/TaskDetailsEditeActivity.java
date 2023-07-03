package com.easyads.info;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.easyads.info.makemoney.SetttingTaskActivity;

public class TaskDetailsEditeActivity extends Activity {
    private AlertDialog alertDialog1; //信息框
    private AlertDialog alertDialog2; //单选框
    private AlertDialog alertDialog3; //多选框
    final String[] items1 = {"抖音推广", "快手推广", "ETC业务", "微信点赞"};
    final String[] items2 = {"10000金币", "20000金币", "30000金币", "40000金币"};
    final String[] items3 = {"只适合女性", "只适合男性", "位置5公里之内", "不限位置"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.taskdetaileditile);
        TextView textView =findViewById(R.id.ll_includetitle).findViewById(R.id.title);
        textView.setText("任务编辑");
        Button button=findViewById(R.id.rewardButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSingleAlertDialog(items2,v);
            }
        });
        findViewById(R.id.rewardfaver).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMutilAlertDialog(items3,v);
            }
        });
    }
    public void showList(View view){
        final String[] items = {"列表1", "列表2", "列表3", "列表4"};
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setTitle("这是列表框");
        alertBuilder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(TaskDetailsEditeActivity.this, items[i], Toast.LENGTH_SHORT).show();
                alertDialog1.dismiss();
            }
        });
        alertDialog1 = alertBuilder.create();
        alertDialog1.show();
    }

    public void showSingleAlertDialog(String[] items,View view){
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setTitle("选择任务类型");
        Button button= findViewById(R.id.tasktype);
        alertBuilder.setSingleChoiceItems(items, chooseButton(items,button.getText().toString()), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Button button= (Button) view;
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

    public void showMutilAlertDialog(String[] items,View view){
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setTitle("选择偏好");

        alertBuilder.setMultiChoiceItems(items, null, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i, boolean isChecked) {

                Button button= (Button) view;

                button.setText(items[i]);
//                if (isChecked){
//                    Toast.makeText(TaskDetailsEditeActivity.this, "选择" + items[i], Toast.LENGTH_SHORT).show();
//                }else {
//                    Toast.makeText(TaskDetailsEditeActivity.this, "取消选择" + items[i], Toast.LENGTH_SHORT).show();
//                }
            }
        });
        alertBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                alertDialog3.dismiss();
            }
        });

        alertBuilder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                alertDialog3.dismiss();
            }
        });


        alertDialog3 = alertBuilder.create();
        alertDialog3.show();
    }

    public void chooseTaskType(View view){
        showSingleAlertDialog(items1,view);
    }

    public void sendReport(View view) {
        Toast.makeText(TaskDetailsEditeActivity.this, "任务发布完成", Toast.LENGTH_SHORT).show();
        this.finish();
    }
    public void chooseData(View view){}

    public void chooseTime(View view){}
    public int  chooseButton(String[] items,String item){
        for (int i=0;i<items.length;i++){
            if (items[i].equals(item))return i;
        }
        return -1;
    }
    public  void  setTask(View view){
        Intent intent =new Intent(TaskDetailsEditeActivity.this, SetttingTaskActivity.class);
        startActivity(intent);
    }

}
