package norman.uri.myapplication;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service
{
    private MediaPlayer mediaPlayer;

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        String action = intent.getAction();
        if("PLAY".equals(action))
        {
            Log.d("service","start playing");
            if(!mediaPlayer.isPlaying())
                mediaPlayer.start();
        }
        else if("PAUSE".equals(action))
        {
            if(mediaPlayer.isPlaying())
                mediaPlayer.pause();
        }
        else if("STOP".equals(action))
        {
            stopSelf();
        }
        return START_STICKY;
    }

    @Override
    public void onCreate()
    {
        super.onCreate();
        mediaPlayer = MediaPlayer.create(this,R.raw.champions);
        mediaPlayer.setLooping(true);
        Log.d("service","start service");
    }

    @Override
    public void onDestroy()
    {
        if(mediaPlayer != null)
        {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
        super.onDestroy();
    }


}