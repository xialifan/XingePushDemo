package com.blueearth.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.blueearth.push.PushHubProxy;
//import com.tencent.android.tpush.XGIOperateCallback;
//import com.tencent.android.tpush.XGPushConfig;
//import com.tencent.android.tpush.XGPushManager;

import blueearth.xingepushdemo.R;

/**
 * MainActivity</br>
 * Author: xialifan </br>
 * Date: 2018/6/25</br>
 * Title:
 */

public class MainActivity extends Activity {

//    private MsgReceiver updateListViewReceiver;
    private TextView tvReceiveInfo;
    private TextView tvToken;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logData(getIntent(),"oncreate");


        tvReceiveInfo = findViewById(R.id.main_tv_receiveInfo);
        tvToken = findViewById(R.id.main_tv_token);

        tvToken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Intent = new Intent("blueearthdetailactivity");
                startActivity(Intent);

            }
        });

        //推送注册代理
        PushHubProxy pushHubProxy = new PushHubProxy(this);

        pushHubProxy.setPushHubListener(new PushHubProxy.PushHubListener() {
            @Override
            public void onRegisterSuccess(String token, int flag) {
                Toast.makeText(MainActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                tvToken.setText("token:"+token);
            }

            @Override
            public void onRegisterFail(String msg, int errCode) {
                Toast.makeText(MainActivity.this,"注册失败",Toast.LENGTH_SHORT).show();
                tvToken.setText("fail:"+msg+" code:"+errCode);

            }
        });
        pushHubProxy.startRegisterPush();


//        startRegisterPush();


    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        logData(intent,"onNewIntent");

    }

    public static void logData(Intent intent,String tag) {
        if (intent.getData()!=null){
            Log.i("xlftest",tag+"/"+intent.getData().toString());
        }else{
            Log.i("xlftest",tag+"/"+"data is null");
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
