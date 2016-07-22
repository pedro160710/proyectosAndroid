package example.pedrow10.reproductorfromsdcard;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
private ListView lvlPlayList;
    private String[] items;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvlPlayList = (ListView) findViewById(R.id.lvlPlayList);
        final ArrayList<File> mySongs = findSongs(Environment.getExternalStorageDirectory());
        items =new  String [mySongs.size()];
        for(int i=0; i<mySongs.size(); i++){
            toast(mySongs.get(i).getName().toString());
            items[i]= mySongs.get(i).getName().toString().replace(".mp3","").replace("wav","").replace("m4a","");
//            items[i]=mySongs.get(i).getName().toString();
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.song_layout,R.id.lblTextView,items);
        lvlPlayList.setAdapter(arrayAdapter);
        lvlPlayList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int posicion, long id){
                startActivity(new Intent(getApplicationContext(), Player.class).putExtra("pos",posicion).putExtra("songlist",mySongs));
            }

        });

    }
    public ArrayList<File> findSongs(File root){
        ArrayList<File> al = new ArrayList<File>();
        File[] files = root.listFiles();
        for(File singleFile: files){
//            si el single File es un directorio y no esta oculto
            if(singleFile.isDirectory() && !singleFile.isHidden()){
//                recorre recursivamente los direcorios hasta que se tope con un archivo
                al.addAll(findSongs(singleFile));

            }else{
                if(singleFile.getName().endsWith(".mp3") || singleFile.getName().endsWith(".wav") || singleFile.getName().endsWith(".m4a")){
                    al.add(singleFile);
                }
            }

        }
        return al;
    }

    public void toast(String text){
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();

    }
}
