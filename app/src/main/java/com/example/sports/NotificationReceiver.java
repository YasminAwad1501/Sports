package com.example.sports;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class NotificationReceiver extends BroadcastReceiver {

    //this class is initiated when a  broadcast is received from the OS
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent intent1 = new Intent(context, NotificationIntentService.class);
        //context.startService(intent1);
        // TODO: 12/20/2021  להחזיר!!
    }
}
