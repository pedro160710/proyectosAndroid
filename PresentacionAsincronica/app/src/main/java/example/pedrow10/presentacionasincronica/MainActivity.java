package example.pedrow10.presentacionasincronica;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    protected TextView textViewMensajeBienvenida;
    protected TextView textViewMensajeLlegada;
    protected TextView textViewProgreso;
    protected ProgressBar barraProgreso;
    protected Integer maximo = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewMensajeBienvenida = (TextView) findViewById(R.id.txtMensajeBienvenida);
        textViewMensajeLlegada = (TextView) findViewById(R.id.txtMensajeLlegada1);
        textViewProgreso= (TextView) findViewById(R.id.txtProgreso);
        barraProgreso = (ProgressBar) findViewById(R.id.barraProgreso);

        new TareaAsincrona(textViewMensajeBienvenida,textViewProgreso, barraProgreso,this).execute(maximo);
    }

}
