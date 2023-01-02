package com.example.puzzle_game_arwa_shamaly.Services;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.puzzle_game_arwa_shamaly.Activities.MainActivity;
import com.example.puzzle_game_arwa_shamaly.R;

public class NotificationService extends JobService {

    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("1",
                    "channel name", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        Intent intent = new Intent(NotificationService.this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(NotificationService.this
                , 0, intent, 0);

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(NotificationService.this,"1");
        builder.setSmallIcon(R.drawable.ic_notifications_active);
        builder.setContentTitle("Puzzle");
        builder.setContentText("Let's go to solve the puzzles !");
        builder.setPriority(NotificationCompat.PRIORITY_HIGH);
        builder.addAction(R.drawable.ic_notifications_active, "Action", pendingIntent);

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(getBaseContext());
        managerCompat.notify(1, builder.build());
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        return false;
    }
}
