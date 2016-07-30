package example.pedrow10.serviciosandroid;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class ReproductorMusica extends Service {
    MediaPlayer reproductor;
    public ReproductorMusica() {
    }

    @Override
    public void onCreate() {
        reproductor= MediaPlayer.create(this,R.raw.pista1);
        reproductor.start();
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        reproductor.stop();
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
