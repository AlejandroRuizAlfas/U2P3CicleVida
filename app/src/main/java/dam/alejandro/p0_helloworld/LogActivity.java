package dam.alejandro.p0_helloworld;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class LogActivity extends Activity {
    private String DEBUG_TAG = "LOG-";
    @Override
    protected void onStart() {
        super.onStart();
        Log.i(DEBUG_TAG,"onStart");
        notify("onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(DEBUG_TAG,"onStop");
        notify("onStop");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(DEBUG_TAG,"onPause");
        notify("onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(DEBUG_TAG,"onResume");
        notify("onResume");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isFinishing()){
            Log.i(DEBUG_TAG,"onDestroy -> El usuario ha matado a la aplicacion");
        }else{
            Log.i(DEBUG_TAG,"onDestroy -> El sistema ha matado a la aplicacion");
        }
        notify("onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(DEBUG_TAG,"onRestart");
        notify("onRestart");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setDebugTag(this.getClass().getSimpleName());
        Log.i(DEBUG_TAG,"onCreate");
        notify("onCreate");
        //setContentView(R.layout.activity_main);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(DEBUG_TAG,"onSaveInstanceState");
    }

    @Override
    public void onRestoreInstanceState(@Nullable Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i(DEBUG_TAG,"onRestoreInstanceState");
    }

    private void notify(String eventName){
        String activityName = this.getClass().getSimpleName();

        String CHANNEL_ID = "My_LifeCycle";

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID,"My Lifecycle",
                    NotificationManager.IMPORTANCE_DEFAULT);

            notificationChannel.setDescription("lifecycle events");
            notificationChannel.setShowBadge(true);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);

            if (notificationManager != null){
                notificationManager.createNotificationChannel(notificationChannel);
            }
        }

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle(eventName+" "+activityName)
                .setContentText(getPackageName())
                .setAutoCancel(true)
                .setSmallIcon(R.mipmap.ic_launcher);

        notificationManagerCompat.notify((int)System.currentTimeMillis(),notificationBuilder.build());
    }

    public void setDebugTag(String activity) {
        this.DEBUG_TAG += activity;
    }
}
