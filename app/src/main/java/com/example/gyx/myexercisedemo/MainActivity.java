package com.example.gyx.myexercisedemo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.view.Window;

/**
 * splash 操作
 */
public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //隐藏标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_splash);
        //延迟几秒钟，进入引导界面
        //延迟3秒钟，个handler发送消息
        new Handler() {
            @Override
            public void handleMessage(Message msg) {
                //跳转操作
                //如果用户第一次进入的话，跳转到引导界面；不是第一次，跳转到主界面
                //判断用户是否是第一次进入
                SharedPreferences sp = getSharedPreferences("config", MODE_PRIVATE);
                if (sp.getBoolean("firstEnter", true)) {
                    //跳转到引导界面
                    Intent intent = new Intent(MainActivity.this, GuideActivity.class);
                    startActivity(intent);
                    finish();
                    //保存用户的状态，标示用户不是第一次进入vcxvxcvxcvxcv下次vxcvxcv现场消息称

                    SharedPreferences.Editor edit = sp.edit();
                    edit.putBoolean("firstEnter", false);
                    edit.commit();
                } else {
                    //跳转到主界面
                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();

                }
            }
        }.sendEmptyMessageDelayed(0, 3000);//延迟时间
    }
}
