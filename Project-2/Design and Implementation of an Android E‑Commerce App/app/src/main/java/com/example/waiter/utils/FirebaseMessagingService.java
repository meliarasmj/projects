package com.example.waiter.utils;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioAttributes;
import android.net.Uri;
import android.text.Html;
import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import com.example.waiter.MainActivity;
import com.example.waiter.R;
import com.google.firebase.messaging.RemoteMessage;

public class FirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {

    private static final String TAG = "FirebaseMessageService";
    private NotificationCompat.Builder notificationBuilder;
    private NotificationChannel notificationChannel;
    static int notificationCount2 = 1;

    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
    }


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            String id = remoteMessage.getData().get("id");
            String title = remoteMessage.getData().get("title");
            String body = remoteMessage.getData().get("body");
            NotificationTransaksi(title, body, id);
        }

        // Check if message contains a notification payload.
//        if (remoteMessage.getData().size() > 0) {
//            String id = remoteMessage.getData().get("id");
//            String title = remoteMessage.getData().get("title");
//            String body = remoteMessage.getData().get("body");
//            NotificationTransaksi(title, body, id);
//        }
    }

    private void NotificationTransaksi(String title, String body, String id) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);


        NotificationCompat.BigTextStyle bigStyle = new NotificationCompat.BigTextStyle();
        bigStyle.setBigContentTitle(title);
        bigStyle.setSummaryText("Penjualan");
        bigStyle.bigText(body);
        Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher_foreground);

        Uri NOTIFICATION_SOUND_URI = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://com.example.renalmobil/" + R.raw.notif);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            // Creating Channel
            notificationChannel = new NotificationChannel(
                    "CH_ID_NOTIF_KONSUMEN",
                    "KONSUMEN_NOTIF_APPS",
                    NotificationManager.IMPORTANCE_HIGH
            );

            notificationBuilder = new NotificationCompat.Builder(this, notificationChannel.toString())
                    .setSmallIcon(R.drawable.ic_logos)
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setContentTitle(Html.fromHtml(title))
                    .setContentText(Html.fromHtml(body))
                    .setAutoCancel(true)
                    .setSound(NOTIFICATION_SOUND_URI)
                    .setChannelId("CH_ID_NOTIF_KONSUMEN")
                    .setLargeIcon(largeIcon)
                    .setStyle(bigStyle)
                    .setContentIntent(pendingIntent);
        } else {
            notificationBuilder = new NotificationCompat.Builder(this)
                    .setSmallIcon(R.drawable.ic_logos)
                    .setContentTitle(Html.fromHtml(title))
                    .setContentText(Html.fromHtml(body))
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setAutoCancel(true)
                    .setLargeIcon(largeIcon)
                    .setStyle(bigStyle)
                    .setSound(NOTIFICATION_SOUND_URI)
                    .setContentIntent(pendingIntent);
        }

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            if (NOTIFICATION_SOUND_URI != null) {
                notificationBuilder.setDefaults(Notification.DEFAULT_VIBRATE);
                AudioAttributes audioAttributes = new AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                        .setUsage(AudioAttributes.USAGE_ALARM)
                        .build();

                notificationChannel.setSound(NOTIFICATION_SOUND_URI, audioAttributes);
                notificationManager.createNotificationChannel(notificationChannel);
            }
        }

        notificationManager.notify(notificationCount2++, notificationBuilder.build());
    }
}