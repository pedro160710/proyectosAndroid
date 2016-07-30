package example.pedrow10.reproductordoctorfit;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import example.pedrow10.reproductordoctorfit.asincrono.busquedaPistasAudio;

public class MainActivity extends AppCompatActivity {
    String items[];
    ListView lvlPistas;
    List<File> pistas =new ArrayList<File>();
    Button pararPista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvlPistas = (ListView) findViewById(R.id.lvlPistas);
        pararPista = (Button) findViewById(R.id.btnPararaMusica);
//        se traen todos los archivos encontrados
//         List<String> pistas=null;// = findSongs(Environment.getExternalStorageDirectory());

        new busquedaPistasAudio(lvlPistas, this, pistas).execute(Environment.getExternalStorageDirectory());

//        items= new String[pistas.size()];
//        for(int i=0; i<pistas.size();i++){
//            Log.v("musica",pistas.get(i).getName().toString() );
//            items[i]= pistas.get(i).getName().toString().replace(".mp3","").replace(".wav","").replace(".m4a","");
//        }

//        ArrayAdapter<String> arrayAdapter= new ArrayAdapter(getApplicationContext(),R.layout.song_layout,R.id.lblTextView,pistas );
//        lvlPistas.setAdapter(arrayAdapter);

        lvlPistas.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicion, long l) {
//                String[] pistas1 = new String[pistas.size()];
//                for(int i=0; i<pistas.size();i++){
//                    pistas1[i]= pistas.get(i).getName().toString().replace(".mp3","").replace(".wav","").replace(".m4a","");
//
//                }
                Log.v("pistasSize",""+pistas.size());
                startService(new Intent(getApplicationContext(), Player.class).putExtra("pos",posicion).putExtra("songList", (ArrayList)pistas));
            }
        });
    }
    public void pararPista(View v){

        Intent servicioReproductor= new Intent(this, Player.class);
        stopService(servicioReproductor);

    }

}


