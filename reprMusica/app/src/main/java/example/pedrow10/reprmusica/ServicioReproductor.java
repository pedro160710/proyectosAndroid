package example.pedrow10.reprmusica;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by PedroW10 on 21/7/2016.
 */
public class ServicioReproductor extends Service {
    MediaPlayer reproductor;
    @Override
    public void onCreate(){
        Toast.makeText(this, "servicio creado",Toast.LENGTH_SHORT).show();
        reproductor = MediaPlayer.create(this,R.raw.pista1);
        reproductor.start();
    }
//    @Override
//    public int onStartCommand(Intent intent, int flags, int idArranque){
//        Toast.makeText(this, "servicio arrancado",Toast.LENGTH_SHORT).show();
//        reproductor.start();
//        return START_STICKY;
////        probar con otros valores
//    }
    @Override
    public void onDestroy(){
        Toast.makeText(this, "servicio detenido",Toast.LENGTH_SHORT).show();
        reproductor.stop();
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent){
        return null;
    }
}
