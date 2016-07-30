package example.pedrow10.reproductordoctorfit.asincrono;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import example.pedrow10.reproductordoctorfit.R;

/**
 * Created by PedroW10 on 23/7/2016.
 */
public class busquedaPistasAudio extends AsyncTask<File, File, List<File>> {

    private ListView lvlPistas;
    private ArrayList<File> al;
    private Context contexto;
    private List<File> pistas = new ArrayList<File>();
    private List<String> pistasString = new ArrayList<String>();
    private ArrayAdapter<String> arrayAdapter;

    public busquedaPistasAudio(ListView lvlPistas, Context contexto,List<File> pistas) {
        this.lvlPistas = lvlPistas;
        this.contexto = contexto;
        this.pistas= pistas;
    }

    @Override
    protected void onPreExecute() {

        arrayAdapter =
                new ArrayAdapter<String>(contexto, R.layout.song_layout,R.id.lblTextView, pistasString);


        super.onPreExecute();
    }

    @Override
    protected List<File> doInBackground(File... files) {
        //llamo al metodo recursivo de busqueda de pistas
//        ArrayList<File> pistas = buscarPistas(files[0]);

        al = new ArrayList<File>();
        File[] path = files[0].listFiles();
        for (File singleFile : path) {
            if (singleFile.isDirectory() && !singleFile.isHidden()) {
                al.addAll(doInBackground(singleFile));
            } else {
                if (singleFile.getName().endsWith(".mp3") || singleFile.getName().endsWith(".wav") || singleFile.getName().endsWith("m4a")) {
                    al.add(singleFile);
                    publishProgress(singleFile);
                    Log.v("pista encontrada", "" + singleFile.getName());
                }

            }
        }
        return al;
    }

    @Override
    protected void onPostExecute(List<File> files) {
        for(int i=0; i<files.size();i++){
            pistas.add(files.get(i));

        }
        Log.v("directorio encontrado", "onPostExecute" );
//        corre en el UI thread despues de doInBackground
//        lvlPistas.setAdapter(arrayAdapter);
//        lvlPistas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int posicion, long l) {
//                contexto.startActivity(new Intent(contexto, Player.class).putExtra("pos",posicion).putExtra("songList",mySongs));
//            }
//        });
        super.onPostExecute(files);
    }

    @Override
    protected void onProgressUpdate(File... values) {

        pistas.add(values[0]);
        pistasString.add(values[0].getName().toString());
        Log.v("directorio encontrado", ""+values[0].getName().toString() );
        arrayAdapter.notifyDataSetChanged();
        lvlPistas.setAdapter(arrayAdapter);
//        lvlPistas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int posicion, long l) {
//                contexto.startService(new Intent(contexto, Player.class).putExtra("pos",posicion).putExtra("songList", pistas.toArray()));
//            }
//        });
        super.onProgressUpdate(values);
    }

}
