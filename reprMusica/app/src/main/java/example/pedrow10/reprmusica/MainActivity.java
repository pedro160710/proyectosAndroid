package example.pedrow10.reprmusica;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
//probar con AppCompatActivity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void arrancarServicio(View v){


       AlertDialog.Builder reproductorDialogo= new AlertDialog.Builder(this);
        reproductorDialogo.setTitle("Reproductor");
        reproductorDialogo.setMessage("Deseas iniciar el reproductor de música?");

        reproductorDialogo.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        Log.v("serv","arrancado1");
//                        Intent servicio = new Intent(getApplicationContext(), ServicioReproductor.class);
//                        startService(servicio);
                        startService(new Intent(getApplicationContext(), ServicioReproductor.class));
                    }
                });
        reproductorDialogo.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Log.v("serv","arrancado1");
                    }
                });
        reproductorDialogo.setIcon(android.R.drawable.ic_dialog_alert);
        reproductorDialogo.show();
//        Intent servicioReproductor= new Intent(this, ServicioReproductor.class);
//        Log.v("serv","arrancado1");

    }

    public void detenerServicio(View v){
        Intent servicioReproductor= new Intent(this, ServicioReproductor.class);
        Log.v("serv","detenido");
        stopService(servicioReproductor);
    }
}
