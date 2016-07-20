package example.pedrow10.adminlibrosmov;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

import example.pedrow10.adminlibrosmov.vo.Libro;

public class EditarLibrosActivity extends AppCompatActivity {

    private TextView editTextId;
    private EditText editTextTitulo;
    private EditText editTextAutor;
    private EditText editTextStock;
    private Libro libro;
    private TextView textViewResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_libros);
        editTextId = (TextView) findViewById(R.id.editTextId);
        editTextAutor = (EditText) findViewById(R.id.editTextAutor);
        editTextTitulo = (EditText) findViewById(R.id.editTextTitulo);
        editTextStock = (EditText) findViewById(R.id.editTextStcck);
        textViewResultado = (TextView) findViewById(R.id.textViewResultado);

        libro = (Libro) getIntent().getSerializableExtra("varLibro");
        editTextId.setText("" + libro.getId());
        editTextAutor.setText("" + libro.getAutor());
        editTextTitulo.setText("" + libro.getTitulo());
        editTextStock.setText("" + libro.getStock());
    }

    public void guuardarLibro(View view) {
        new GuardarAsinc().execute();

    }

    /**
     * Siempre las invocaciones servicios REST se realizan en una tarea asincronica
     */
    public class GuardarAsinc extends AsyncTask {

        @Override
        protected void onPreExecute() {
            libro.setAutor(editTextAutor.getText().toString());
            libro.setTitulo(editTextTitulo.getText().toString());
            libro.setStock(Integer.parseInt(editTextStock.getText().toString()));
            super.onPreExecute();
        }

        @Override
        protected Object doInBackground(Object[] objects) {
            HashMap libroMap = new HashMap<>();
            libroMap.put("varId", libro.getId());
            libroMap.put("varAutor", libro.getAutor());
            libroMap.put("varTitulo", libro.getTitulo());
            libroMap.put("varStock", libro.getStock());

            final String url = "http://172.30.251.182:8080/CrudLibroRestMoviles/rest/crudLibroRest/actualizarLibro/id/{varId}/autor/{varAutor}/titulo/{varTitulo}/stock/{varStock}";
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
            String mensaje = restTemplate.getForObject(url, String.class, libroMap);
            Log.v("Guardar", "saved");
            return mensaje;
        }


        @Override
        protected void onPostExecute(Object o) {

            String mensaje = (String) o;
            textViewResultado.setText(mensaje);
            super.onPostExecute(o);
        }


    }
}
