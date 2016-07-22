package org.example.serviciomusica;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.Toast;

/**
 * Created by PedroW10 on 21/7/2016.
 */
public class ServicioMusica1 extends Service {
    MediaPlayer reproductor;
    @Override
    public void onCreate() {
        Toast.makeText(this,"servicio1 creado",Toast.LENGTH_SHORT).show();
        reproductor = MediaPlayer.create(this, R.raw.audio);
    }

    @Override
    public int onStartCommand(Intent intenc, int flags, int idArranque) {
        Toast.makeText(this,"servicio1 arrancado",Toast.LENGTH_LONG).show();
        reproductor.start();
        return START_STICKY;

    }

    @Override
    public void onDestroy() {
        Toast.makeText(this,"servicio1 detenido",Toast.LENGTH_SHORT).show();
reproductor.stop();
    }

    @Override
    public IBinder onBind(Intent intenc) {
        return null;
    }

}
