package com.yaidel.vacations.UI;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.app.NotificationManager;
import androidx.core.app.NotificationCompat;
import com.yaidel.vacations.R;

public class ExcursionAlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String excursionTitle = intent.getStringExtra("excursionTitle");

        //notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "excursionChannel")
                .setSmallIcon(R.drawable.baseline_access_alarm_24)
                .setContentTitle("Excursion Alert")
                .setContentText("Excursion: " + excursionTitle + " is happening today!")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(2, builder.build());

    }

}// end
