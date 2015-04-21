package com.bestride.comet;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.bestride.data.helper.WorkDetail;
import com.bestride.data.helper.MessageJsonBean.MessageObj;
import com.bestride.helper.FinalValue;
import com.bestride.waiterwork.HotelApplication;
import com.bestride.waiterwork.MainActivity;
import com.bestride.waiterwork.R;
import com.google.gson.Gson;
import com.kyleduo.icomet.message.Message.Content;

public class MessageReceiver extends BroadcastReceiver {

    private static String TAG = "MessageReceiver";

    public MessageReceiver() {

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (!intent.getAction().equals(FinalValue.ACTION_MESSAGE_ARRIVED)) {
            return;
        }
        Log.d(TAG, "message received in receiver");

        NotificationManager nm = (NotificationManager) context.getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

        //Content content = (Content) intent.getSerializableExtra("content");
        //MessageObj message = new MessageObj(content);
        String content = intent.getStringExtra("content");
        Intent i = new Intent(context.getApplicationContext(), MainActivity.class);
        //i.putExtra("with", message.from);
        i.putExtra("with", "admin");
        PendingIntent pi = PendingIntent.getActivity(context, getResultCode(), i,
                Intent.FILL_IN_ACTION);

        //String detail = message.text.replace("&quot;", "\"");
        WorkDetail works = new Gson().fromJson(content, WorkDetail.class);
        String doing = "";
        if(works.getDatalist().get(0).getServtype() == FinalValue.CHECK_OUT){
            doing = context.getApplicationContext().getString(R.string.check_out);
        }else if (works.getDatalist().get(0).getServtype() == FinalValue.CLEAN){
            doing = context.getApplicationContext().getString(R.string.clean);
        }
        Notification notification = new Notification.Builder(context.getApplicationContext())
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.ic_launcher)
//		.setContentTitle(message.from)
                .setContentTitle(works.getDatalist().get(0).getRoomno())
//		.setContentText(message.text)
                .setContentText(doing)
                .setTicker(context.getString(R.string.dispatch_message))
                .setContentIntent(pi)
                .setDefaults(Notification.DEFAULT_ALL)
                .setAutoCancel(true)
                .build();

        nm.notify(0, notification);
        abortBroadcast();
    }
}