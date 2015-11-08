package com.zerog.prototypefortrace;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

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

//                int mNotificationId = (int) (Math.random() * 100)+100;
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

    }




}
