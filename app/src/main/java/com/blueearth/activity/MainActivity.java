package com.blueearth.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.blueearth.receiver.MessageReceiver;
import com.tencent.android.tpush.XGIOperateCallback;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.XGPushManager;

import blueearth.xingepushdemo.R;

/**
 * MainActivity</br>
 * Author: xialifan </br>
 * Date: 2018/6/25</br>
 * Title:
 */

public class MainActivity extends Activity {

    private MsgReceiver updateListViewReceiver;
    private TextView tvReceiveInfo;
    private TextView tvToken;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvReceiveInfo = findViewById(R.id.main_tv_receiveInfo);
        tvToken = findViewById(R.id.main_tv_token);

        //清除通知栏消息
        //XGPushManager.cancelAllNotifaction();

        //代码内动态注册access ID
        XGPushConfig.setAccessKey(this,"AR289TQSF84M");
        XGPushConfig.setAccessId(this,2100297266);

        XGPushConfig.setMiPushAppId(this, "2882303761517828158");
        XGPushConfig.setMiPushAppKey(this, "5951782885158");

        //打开第三方推送
        XGPushConfig.enableOtherPush(this, true);

//        XG_ACCESS_ID:"2100297266 ",
//        XG_ACCESS_KEY : "AR289TQSF84M",

        //开启信鸽的日志输出，线上版本不建议调用
        XGPushConfig.enableDebug(this, true);
//        XGPushConfig.getToken(this);
        //注册数据更新监听器
        updateListViewReceiver = new MsgReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.qq.xgdemo.activity.UPDATE_LISTVIEW");
        registerReceiver(updateListViewReceiver, intentFilter);
//        // 1.获取设备Token
//        Handler handler = new HandlerExtension(MainActivity.this);
//        m = handler.obtainMessage();
        /*
        注册信鸽服务的接口
        如果仅仅需要发推送消息调用这段代码即可
        */
        XGPushManager.registerPush(getApplicationContext(),
                new XGIOperateCallback() {
                    @Override
                    public void onSuccess(Object data, int flag) {
                        String token = (String) data;
                        Log.i("xlftest",token);
                        tvToken.setText(token);
                        Toast.makeText(MainActivity.this,token,Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFail(Object data, int errCode, String msg) {
                        String info = (String) data;
                        Log.i("xlftest",msg);
                        tvToken.setText(msg);
                        Toast.makeText(MainActivity.this,msg,Toast.LENGTH_SHORT).show();
                    }
                });

        // 获取token
        XGPushConfig.getToken(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(updateListViewReceiver);
    }

    public static class MsgReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO Auto-generated method stub
//            allRecorders = notificationService.getCount();
//            getNotificationswithouthint(id);
        }
    }
}
