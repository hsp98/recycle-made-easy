package com.technocrats.recycle.made.easy.ui.settings

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.os.SystemClock
import android.util.Log
import androidx.core.app.JobIntentService
import com.technocrats.recycle.made.easy.MainActivity
import com.technocrats.recycle.made.easy.R
import java.text.SimpleDateFormat
import java.util.*


class NotificationJobService : JobIntentService() {

    lateinit var notificationManager: NotificationManager
    lateinit var notificationChannel: NotificationChannel
    lateinit var builder : Notification.Builder
    private val channelId = "i.apps.notifications"
    private val description = "Test notification"

    private val TAG = "NotificationJobService"

    fun enqueueWork(context: Context, work: Intent) {
        enqueueWork(context, NotificationJobService::class.java, 123, work)
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate")
    }

    override fun onHandleWork(intent: Intent) {
        Log.d(TAG, "onHandleWork")

        val sdf = SimpleDateFormat("dd/mm/yyyy")
        val str_date = "2/4/2020"
        if(Date().compareTo(sdf.parse(str_date)) == 0)
        {
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val intent = Intent(this, NotificationJobService::class.java)

        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel = NotificationChannel(channelId, description, NotificationManager.IMPORTANCE_HIGH)
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.GREEN
            notificationChannel.enableVibration(true)
            notificationManager.createNotificationChannel(notificationChannel)
            builder = Notification.Builder(this, channelId)
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.ic_notifications_black_24dp))
                .setContentTitle("Recycle Day Reminder")
                .setContentText("It's pick up day. Garbage, Recycling Bag 1, and Paper, Recycling Bag 2 will be picked up.")
        }
        else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                builder = Notification.Builder(this, channelId)
                    .setContentIntent(pendingIntent)
                    .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                    .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.ic_notifications_black_24dp))
                    .setContentTitle("Recycle Day Reminder")
                    .setContentText("It's pick up day. Garbage, Recycling Bag 1, and Paper, Recycling Bag 2 will be picked up.")
            }
        }
        notificationManager.notify(1234, builder.build())
    }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }

    override fun onStopCurrentWork(): Boolean {
        Log.d(TAG, "onStopCurrentWork")
        return super.onStopCurrentWork()
    }

}