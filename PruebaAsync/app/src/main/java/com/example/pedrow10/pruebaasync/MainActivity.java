package com.example.pedrow10.pruebaasync;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText txtmaximo;
    private TextView txtconteo;
    private Button btonContarSin;
    private Button btonContarAsin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtmaximo = (EditText) findViewById(R.id.txtMaximo);
        txtconteo = (TextView) findViewById(R.id.txtConteo);
        btonContarSin = (Button) findViewById(R.id.buttonContarSin);
        btonContarAsin = (Button) findViewById(R.id.buttonContarAsin);
    }

    public void generarConteo(View v) {
        int maximo = Integer.parseInt(txtmaximo.getText().toString());
        for (int i = 0; i < maximo; i++) {
            Log.v("sinc: "," "+ i);
            txtconteo.append(" " +i);
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void generarConteoAsync(View v) {
        int maximo = Integer.parseInt(txtmaximo.getText().toString());
        new ConteoAsyncronico(txtconteo).execute(maximo);
    }
}
