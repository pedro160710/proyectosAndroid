package example.pedrow10.reproductorfromsdcard;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.io.File;
import java.util.ArrayList;

public class Player extends AppCompatActivity {
    ArrayList<File> mySongs;
    MediaPlayer md;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        Intent i = getIntent();
        Bundle b= i.getExtras();

        mySongs = (ArrayList) b.getParcelableArrayList("songlist");
        int posicion= b.getInt("pos",0);//0 es el valor por default
        Uri u= Uri.parse(mySongs.get(posicion).toString());
        md = MediaPlayer.create(getApplicationContext(),u);
        md.start();
    }
    public void parar(View v){
md.stop();
    }
}
