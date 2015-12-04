package com.zerog.prototypefortrace;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;

import java.util.Random;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button createSticky = (Button) this.findViewById(R.id.StickyNotifButton);
        createSticky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotificationCompat.Builder mBuilder =
                        (NotificationCompat.Builder) new NotificationCompat.Builder(getApplicationContext())
                                .setSmallIcon(R.drawable.notification_icon)
                                .setContentTitle("My notification")
                                .setContentText("Testing notification");
                Intent resultIntent = new Intent(getApplicationContext(), MainActivity.class);

                // Because clicking the notification opens a new ("special") activity, there's
                // no need to create an artificial back stack.
                PendingIntent resultPendingIntent =
                        PendingIntent.getActivity(
                                getApplicationContext(),
                                0,
                                resultIntent,
                                PendingIntent.FLAG_UPDATE_CURRENT
                        );

                mBuilder.setContentIntent(resultPendingIntent);
                int mNotificationId = 100;
                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                Notification notif = mBuilder.build();
                notif.flags |= Notification.FLAG_NO_CLEAR;
                notificationManager.notify(mNotificationId, notif);

            }
        });

        Button updateNotif = (Button) this.findViewById(R.id.updateNotif);
        updateNotif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotificationCompat.Builder mBuilder =
                        (NotificationCompat.Builder) new NotificationCompat.Builder(getApplicationContext())
                                .setSmallIcon(R.drawable.notification_icon)
                                .setContentTitle("My notification")
                                .setContentText("Update notification");

                Intent resultIntent = new Intent(getApplicationContext(), MainActivity.class);
                PendingIntent resultPendingIntent =
                        PendingIntent.getActivity(
                                getApplicationContext(),
                                0,
                                resultIntent,
                                PendingIntent.FLAG_UPDATE_CURRENT
                        );


                mBuilder.setContentIntent(resultPendingIntent);
//              Reusing the same notification ID
                int mNotificationId = 100;
                NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
                Notification notif = mBuilder.build();
                notif.flags |= Notification.FLAG_NO_CLEAR;
                notificationManager.notify(mNotificationId, notif);

            }
        });

        Button createNotif = (Button) this.findViewById(R.id.NotifButton);
        createNotif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int mNotificationId = (int) (Math.random() * 100)+100;

                NotificationCompat.Builder mBuilder =
                        (NotificationCompat.Builder) new NotificationCompat.Builder(getApplicationContext())
                                .setSmallIcon(R.drawable.notification_icon)
                                .setContentTitle("My notification")
                                .setContentText("Testing notification "+mNotificationId);


                Intent resultIntent = new Intent(getApplicationContext(), MainActivity.class);

                // Because clicking the notification opens a new ("special") activity, there's
                // no need to create an artificial back stack.
                PendingIntent resultPendingIntent =
                        PendingIntent.getActivity(
                                getApplicationContext(),
                                0,
                                resultIntent,
                                PendingIntent.FLAG_UPDATE_CURRENT
                        );
                mBuilder.setContentIntent(resultPendingIntent);
                NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
                Notification notif = mBuilder.build();
                notificationManager.notify(mNotificationId, notif);

            }
        });

        Button cancelNotif = (Button) this.findViewById(R.id.Cancel_Notif);
        cancelNotif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ns = getApplicationContext().NOTIFICATION_SERVICE;
                NotificationManager nMgr = (NotificationManager)
                        getApplicationContext().getSystemService(ns);
                nMgr.cancel(100);
            }
        });

        Button customNotif = (Button) this.findViewById(R.id.custom_notif);
        customNotif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RemoteViews remoteViews = new RemoteViews(getPackageName(),
                        R.layout.widget);
                NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
                        this).setSmallIcon(R.drawable.app_icon).setContent(
                        remoteViews);
                // Creates an explicit intent for an Activity in your app
                Intent resultIntent = new Intent(this, test.class);
                // The stack builder object will contain an artificial back stack for
                // the
                // started Activity.
                // This ensures that navigating backward from the Activity leads out of
                // your application to the Home screen.
                TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
                // Adds the back stack for the Intent (but not the Intent itself)
                stackBuilder.addParentStack(test.class);
                // Adds the Intent that starts the Activity to the top of the stack
                stackBuilder.addNextIntent(resultIntent);
                PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0,
                        PendingIntent.FLAG_UPDATE_CURRENT);
                remoteViews.setOnClickPendingIntent(R.id.button1, resultPendingIntent);
                NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                // mId allows you to update the notification later on.
                mNotificationManager.notify(100, mBuilder.build());
            }
        });

    }

}
