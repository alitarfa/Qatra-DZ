package com.example.magictouch.my_application.notification;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

import com.example.magictouch.my_application.R;


public class NewMessageNotification {

    private static final String NOTIFICATION_TAG = "NewMessage";


    public static void notify(final Context context, final String exampleString, int number){

        Intent resultIntent = new Intent(context, test.class);

        if (number==1){
            // TODO: 6/18/18 this case half qauntity 
            resultIntent.putExtra("type",exampleString);
            resultIntent.putExtra("body","your message here lamin");
        }
        if (number==2){
            // TODO: 6/18/18 this case full qantity
            resultIntent.putExtra("type",exampleString);
            resultIntent.putExtra("body","Warning!! can you please check your water flow sensor");//i changed the msg format.
        }


        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);

        stackBuilder.addParentStack(test.class);

        stackBuilder.addNextIntent(resultIntent);

        // Pending intent to the notification manager
        PendingIntent resultPending = stackBuilder
                .getPendingIntent(number, PendingIntent.FLAG_CANCEL_CURRENT);

        final Resources res = context.getResources();
        final Bitmap picture = BitmapFactory.decodeResource(res, R.drawable.small_icon_logo);
        final NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setDefaults(Notification.DEFAULT_ALL)
                .setSmallIcon(R.drawable.little_icon_logo)
                .setContentTitle("Qatra Notification")
                .setContentText(exampleString)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setLargeIcon(picture)
                .setAutoCancel(true)
                .setContentIntent(resultPending);

        notify(context, builder.build(),number);



    }

    @TargetApi(Build.VERSION_CODES.ECLAIR)
    private static void notify(final Context context, final Notification notification,int id) {
        final NotificationManager nm = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ECLAIR) {
            nm.notify(NOTIFICATION_TAG, id, notification);
        } else {
            nm.notify(NOTIFICATION_TAG.hashCode(), notification);
        }
    }

    @TargetApi(Build.VERSION_CODES.ECLAIR)
    public static void cancel(final Context context) {
        final NotificationManager nm = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ECLAIR) {
            nm.cancel(NOTIFICATION_TAG, 0);
        } else {
            nm.cancel(NOTIFICATION_TAG.hashCode());
        }
    }


}
