package com.bestride.helper;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Vibrator;
import android.view.View;
import android.widget.Toast;

import java.util.Random;

/**
 * @author Edward
 */
public class UIUtils {

    /**
     * vibrate
     *
     * @param context
     */
    public static void vibrate1(Context context) {
        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        long[] pattern = {
                0, 20
        };
        vibrator.vibrate(pattern, -1);
    }
    
    public static int playSound(final Context context) {
        NotificationManager mgr = (NotificationManager) context 
                .getSystemService(Context.NOTIFICATION_SERVICE); 
        Notification nt = new Notification(); 
        nt.defaults = Notification.DEFAULT_SOUND; 
        int soundId = new Random(System.currentTimeMillis()) 
                .nextInt(Integer.MAX_VALUE); 
        mgr.notify(soundId, nt); 
        return soundId; 
    } 

    public static ProgressDialog showProgressDialog(Context context, String msg) {
        ProgressDialog pd = new ProgressDialog(context);
        pd.setMessage(msg);
        pd.setCancelable(false);
        pd.show();
        return pd;
    }

    public static void showToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public static void showAlertDialog(Context context, int titleRes, View contentView, int posRes, DialogInterface.OnClickListener posListener, int negRes, DialogInterface.OnClickListener negListener) {
        new AlertDialog.Builder(context).setTitle(titleRes).setView(contentView).setCancelable(false)
                .setPositiveButton(posRes, posListener).setNegativeButton(negRes, negListener).create().show();
    }

    public static void showAlertDialog(Context context, int titleRes, String message, int posRes, DialogInterface.OnClickListener posListener, int negRes, DialogInterface.OnClickListener negListener) {
        new AlertDialog.Builder(context).setTitle(titleRes).setMessage(message).setCancelable(false)
                .setPositiveButton(posRes, posListener).setNegativeButton(negRes, negListener).create().show();
    }
}
