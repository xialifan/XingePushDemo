package com.blueearth.push;

import android.content.Context;
import android.util.Log;

import com.tencent.android.tpush.XGIOperateCallback;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.XGPushManager;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 整合推送服务的代理类
 *
 * @author xlf
 */
public class PushHubProxy {
    private Context context;

    private PushHubListener pushHubListener;

    public PushHubProxy(Context context) {
        this.context = context;
    }

    public void startRegisterPush() {

        loadConfigFromAssets();

        XGPushManager.registerPush(context,
                new XGIOperateCallback() {
                    @Override
                    public void onSuccess(Object data, int flag) {
                        if (pushHubListener != null) {
                            String token = (String) data;
                            pushHubListener.onRegisterSuccess(token, flag);
                        }
                    }

                    @Override
                    public void onFail(Object data, int errCode, String msg) {
                        if (pushHubListener != null) {
                            pushHubListener.onRegisterFail(msg, errCode);//

                        }
                    }
                });

        // 开始获取token
        XGPushConfig.getToken(context);

    }


    public void setPushHubListener(PushHubListener pushHubListener) {
        this.pushHubListener = pushHubListener;
    }

    public interface PushHubListener {
        public void onRegisterSuccess(String token, int flag);

        public void onRegisterFail(String msg, int errCode);
    }


    private boolean loadConfigFromAssets(){
        InputStream is = null;
        try {
            is = context.getAssets().open("push.properties");
            Properties properties = new Properties();
            properties.load(is);


            Log.i("xlftest",properties.getProperty("xiaomiAppId")+"/"+properties.getProperty("xiaomiAppKey"));
            //小米推送注册的appid和appkey
            XGPushConfig.setMiPushAppId(context, properties.getProperty("xiaomiAppId"));
            XGPushConfig.setMiPushAppKey(context, properties.getProperty("xiaomiAppKey"));

            //注册魅族推送appid和appkey
            XGPushConfig.setMzPushAppId(context, properties.getProperty("meizuAppId"));
            XGPushConfig.setMzPushAppKey(context, properties.getProperty("meizuAppKey"));

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            //打开第三方推送
            XGPushConfig.enableOtherPush(context, true);

            //开启信鸽的日志输出，线上版本不建议调用
            XGPushConfig.enableDebug(context, true);
        }


        return true;
    }


}
