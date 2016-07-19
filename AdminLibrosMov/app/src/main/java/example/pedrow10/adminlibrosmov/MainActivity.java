package example.pedrow10.adminlibrosmov;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import example.pedrow10.adminlibrosmov.vo.Libro;

public class MainActivity extends AppCompatActivity {
    private EditText editTextNombreBusqueda;
    private ListView listViewLibros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextNombreBusqueda = (EditText) findViewById(R.id.editTextNombreBusqueda);
        listViewLibros = (ListView) findViewById(R.id.listViewLibros);
    }

    public void buscarLibro(View v) {
        BuscarLibroAsinc buscarLibroAsinc =new BuscarLibroAsinc();
        Log.v("buscar: ","1");
        buscarLibroAsinc.execute("FreeSoftware");
    }

    public class BuscarLibroAsinc extends AsyncTask<String, Void, List<Libro>> {
        //    va a consultar en la BDD
//    param nombre del libro para buscar
        @Override
        protected List<Libro> doInBackground(String... strings) {
            Log.v("buscar: ","2");
//            String nombreBusqueda = strings[0];
            String nombreBusqueda = "FreeSoftware";
            Log.v("buscar: ","2.1");
            List listLibros = new ArrayList<Libro>();

/*            listLibros.add(new Libro("Libro1", "Autor1", 45, 1));
            listLibros.add(new Libro("Libro2", "Autor2", 10, 2));
            listLibros.add(new Libro("Libro3", "Autor3", 18, 3));*/
//            la consulta se realiza por id
//            final String url = "http://172.30.238.213:8080/CrudLibroRestMoviles/rest/crudLibroRest/consultarLibro?varTitulo="+nombreBusqueda;

//            final String url = "http://172.30.238.213:8080/CrudLibroRestMoviles/rest/crudLibroRest/consultarLibro?varId=1";
            final String url = "http://172.30.238.213:8080/CrudLibroRestMoviles/rest/crudLibroRest/consultarLibros";
            Log.v("buscar: ","3"+ url);
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(
                    new MappingJackson2HttpMessageConverter());
            Log.v("buscar: ","3 ");
            Libro[] librosArray = restTemplate.getForObject(url, Libro[].class);
            //se obtiene un objeto java
            listLibros = Arrays.asList(librosArray);
            Log.v("buscar: ","4 son"+ listLibros.size());
            return listLibros;
        }

        @Override
        protected void onPostExecute(List<Libro> libros) {

//            String[] datos = new String[]{"Libro1", "Libro2", "Libro3"};

//            String[] datos = (String[]) libros.toArray();
            ArrayAdapter<Libro> arrayAdapter = new ArrayAdapter<Libro>(getApplicationContext(), android.R.layout.simple_list_item_1, libros);
            listViewLibros.setAdapter(arrayAdapter);
            //Log.v("Autor Libro::::",libros.get(1).getAutor().toString());
            super.onPostExecute(libros);
        }


    }

}


