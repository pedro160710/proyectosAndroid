package org.example.serviciomusica;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button arrancar = (Button) findViewById(R.id.boton_arrancar);
//        arrancar.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View view) {
//
//                startService(new Intent(MainActivity.this,
//                        ServicioMusica.class));
//                System.out.println("arrancar");
//            }
//        });
        Button detener = (Button) findViewById(R.id.boton_detener);
//        detener.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View view) {
//                stopService(new Intent(MainActivity.this,
//                        ServicioMusica.class));
//            }
//        });
    }

    public void arrancarServicio(View v){
        Log.v("arranca","1");
//        Intent servicio = new Intent(MainActivity.this, ServicioMusica1.class);
        Log.v("arranca","2");
        startService(new Intent(MainActivity.this, ServicioMusica1.class));
        Log.v("arranca","3");
    }
    public void detenerServicio(View v){
//        Intent servicio = new Intent(MainActivity.this, ServicioMusica1.class);
        stopService(new Intent(MainActivity.this, ServicioMusica1.class));


    }
}
