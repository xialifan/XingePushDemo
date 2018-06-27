package com.blueearth.receiver;

import android.content.Context;

import com.xiaomi.mipush.sdk.MiPushMessage;
import com.xiaomi.mipush.sdk.PushMessageReceiver;

/**
 * MiMessageReceiver</br>
 * Author: xialifan </br>
 * Date: 2018/6/26</br>
 * Title:
 */

public class MiMessageReceiver extends PushMessageReceiver {
    @Override
    public void onReceivePassThroughMessage(Context context, MiPushMessage miPushMessage) {
        super.onReceivePassThroughMessage(context, miPushMessage);
    }
}
