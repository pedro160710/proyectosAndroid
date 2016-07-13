package example.pedrow10.presentacionasincronica;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SaludoApp extends AppCompatActivity {
TextView textViewMensajeLlegada;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saludo_app);
        textViewMensajeLlegada =(TextView) findViewById(R.id.txtMensajeLlegada1);
    }
}
