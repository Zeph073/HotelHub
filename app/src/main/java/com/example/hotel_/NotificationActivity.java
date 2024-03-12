package com.example.hotel_;

import android.annotation.SuppressLint;
import android.app.AlarmManager;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.example.hotel_.databinding.ActivityNotificationBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class NotificationActivity extends AppCompatActivity {
    ActivityNotificationBinding binding;
    String title, message;
    Long time;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNotificationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


     long currentTimeInMillis = System.currentTimeMillis();
        createChannel();
        binding.allowNotification.setOnClickListener(view -> {
            scheduleNotification();
            startActivity(new Intent(NotificationActivity.this , MainDashboardActivity.class));
            finish();
        });
//            String channel_id = "channel_id";
//            NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), channel_id)
//                    .setSmallIcon(R.drawable.ic_launcher_foreground)
//                    .setContentTitle("Food Order Notification")
//                    .setContentText("Seat 4 reserved. Welcome")
//                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);
//            Intent intent = new Intent(getApplicationContext(), NotificationActivity.class);
//            PendingIntent pendingIntent = PendingIntent.getBroadcast(
//                    NotificationActivity.this,
//                    1,
//                    intent,
//                    PendingIntent.FLAG_IMMUTABLE | PendingIntent.FLAG_UPDATE_CURRENT
//            );
//            builder.setContentIntent(pendingIntent);
//            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                NotificationChannel notificationChannel = notificationManager.getNotificationChannel(channel_id);
//                if (notificationChannel == null) {
//                    int importance = NotificationManager.IMPORTANCE_HIGH;
//                    notificationChannel = new NotificationChannel(channel_id, "Description", importance);
//                    notificationManager.createNotificationChannel(notificationChannel);
//
//                }
//            }
//            notificationManager.notify(0 , builder.build());
//        });




        FirebaseDatabase.getInstance().getReference().child(
                FirebaseAuth.getInstance().getCurrentUser().getUid()
        ).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                title = snapshot.child("title").getValue(String.class);
                message = snapshot.child("message").getValue(String.class);
     time = snapshot.child("time").getValue(Long.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @SuppressLint("ScheduleExactAlarm")
    private void scheduleNotification() {
        Intent intent = new Intent(NotificationActivity.this, Notification.class);
        intent.putExtra("titleExtra", title);
        intent.putExtra("messageExtra", message);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                NotificationActivity.this,
                1,
                intent,
                PendingIntent.FLAG_IMMUTABLE | PendingIntent.FLAG_UPDATE_CURRENT
        );

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.setExactAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP,
                time,
                pendingIntent
        );
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createChannel() {
        String name = "Vaccination";
        String desc = "Channel description";
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel channel = new NotificationChannel("notificationChannel", name, importance);
        channel.setDescription(desc);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(channel);
    }
}