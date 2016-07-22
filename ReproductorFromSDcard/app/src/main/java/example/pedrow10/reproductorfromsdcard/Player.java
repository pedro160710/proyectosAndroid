package example.pedrow10.reproductorfromsdcard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.io.File;
import java.util.ArrayList;

public class Player extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        Intent i = getIntent();
        Bundle b= i.getExtras();

        ArrayList<File> mySongs = (ArrayList) b.getParcelableArrayList("mySongs");
        int posicion= b.getInt("pos",0);
    }
}
