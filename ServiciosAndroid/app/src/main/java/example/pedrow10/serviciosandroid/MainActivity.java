package example.pedrow10.serviciosandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnIniciar;
    Button btnDetener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnIniciar = (Button) findViewById(R.id.btnIniciar);
        btnDetener = (Button) findViewById(R.id.btnDetener);
    }

    public void iniciarServicio(View v) {
        Intent servicio = new Intent(this, ReproductorMusica.class);
        startService(servicio);
    }

    public void detenerServicio(View v) {
        Intent servicio = new Intent(this, ReproductorMusica.class);
        stopService(servicio);
    }


}
