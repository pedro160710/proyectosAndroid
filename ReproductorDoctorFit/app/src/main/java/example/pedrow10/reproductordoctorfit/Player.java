package example.pedrow10.reproductordoctorfit;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;

public class Player extends Service {
    ArrayList<File> pistas;
    MediaPlayer md;
    Uri u;
    Bundle buldle;

    @Override
    public void onCreate() {

        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        buldle = intent.getExtras();
        int posicion = buldle.getInt("pos", 0);
        Log.v("POSICION", "" + posicion);
        pistas = (ArrayList) buldle.getStringArrayList("songList");
        u = Uri.parse(pistas.get(posicion).toString());
        md = MediaPlayer.create(this, u);
        md.start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        md.stop();
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    //    ArrayList<File> pistas;
//    MediaPlayer md;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_player);
//        Intent i= getIntent();
//        Bundle buldle= i.getExtras();
//        pistas = (ArrayList)buldle.getParcelableArrayList("songList");
//        int posicion = buldle.getInt("pos",0);
//        Uri u= Uri.parse(pistas.get(posicion).toString());
//        md = MediaPlayer.create(getApplicationContext(),u);
//        md.start();
//;    }
//
//    public void parar(View v) {
//        md.stop();
//    }
}
