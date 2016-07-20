package example.pedrow10.adminlibrosmov;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import adpatadorLibro.AdaptadorLibro;
import example.pedrow10.adminlibrosmov.vo.Libro;

public class MainActivity extends AppCompatActivity {
    private EditText editTextNombreBusqueda;
    private ListView listViewLibros;
    private ProgressBar progreso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextNombreBusqueda = (EditText) findViewById(R.id.editTextNombreBusqueda);
        listViewLibros = (ListView) findViewById(R.id.listViewLibros);
        progreso = (ProgressBar) findViewById(R.id.progresoCarga);
    }

    /**
     * Metodo para ejecutar la tarea asincronica de busqueda en la base de datos
     *
     * @param v el objeto de la vista que invoca esta funcion
     */
    public void buscarLibro(View v) {
        BuscarLibroAsinc buscarLibroAsinc = new BuscarLibroAsinc();
        Log.v("buscar: ", "1");
        buscarLibroAsinc.execute(editTextNombreBusqueda.getText().toString());
    }

    public class BuscarLibroAsinc extends AsyncTask<String, Integer, List<Libro>> {
        //        Context context;
//        ListView listViewLibros;

        //    va a consultar en la BDD
//    param nombre del libro para buscar
        public BuscarLibroAsinc() {
//            this.context= contexto;
//            this.listViewLibros= listaLibros;
        }

        @Override
        protected List<Libro> doInBackground(String... strings) {
            Log.v("buscar: ", "2");
            String nombreBusqueda = strings[0];
//            String url;
            Log.v("buscar: ", "2.1");
            List listLibros = new ArrayList<Libro>();

/*            listLibros.add(new Libro("Libro1", "Autor1", 45, 1));
            listLibros.add(new Libro("Libro2", "Autor2", 10, 2));
            listLibros.add(new Libro("Libro3", "Autor3", 18, 3));*/
//            la consulta se realiza por id

            final String url = "http://172.30.251.182:8080/CrudLibroRestMoviles/rest/crudLibroRest/consultarLibros";

//            final String url = "http://172.30.251.182:8080/CrudLibroRestMoviles/rest/crudLibroRest/consultarLibroTitulo?varTitulo=" + nombreBusqueda;

//            final String url = "http://172.30.238.213:8080/CrudLibroRestMoviles/rest/crudLibroRest/consultarLibro?varId=1";
//            final String url = "http://192.168.0.106:8080/CrudLibroRestMoviles/rest/crudLibroRest/consultarLibros";
            Log.v("buscar: ", "3" + url);
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(
                    new MappingJackson2HttpMessageConverter());
            Log.v("buscar: ", "3 ");

            Libro[] librosArray = restTemplate.getForObject(url, Libro[].class);
//            aqui no se puede colocar publish progress, ya que no se sabe cuanto puede durar la consulta a la BDD
            for (int i = 0; i < 100; i++) {
                publishProgress(i);
            }
            //se obtiene un objeto java
            listLibros = Arrays.asList(librosArray);
            Log.v("buscar: ", "4 son" + listLibros.size());
            return listLibros;
        }

        @Override
        protected void onProgressUpdate(Integer... valores) {
            progreso.setProgress(valores[0]);
        }

        @Override
        protected void onPostExecute(List<Libro> libros) {
            AdaptadorLibro adaptadorLibro = new AdaptadorLibro(getApplicationContext(), libros);
            listViewLibros.setAdapter(adaptadorLibro);
            listViewLibros.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Libro libro = (Libro) adapterView.getItemAtPosition(i);
                    Intent intent= new Intent(getApplicationContext(),EditarLibrosActivity.class);
//                    la clase Libro debe ser serializable: para que lo haga internamente java
                    intent.putExtra("varLibro",libro);
                    startActivity(intent);

//                   aqui se puede mandar a imprimir libro gracias al toString sobreescrito en el objeto vo Libro.java
//                   Toast.makeText(getApplicationContext(),"Libro :"+libro,Toast.LENGTH_SHORT).show();
                }
            });

//            String[] datos = new String[]{"Libro1", "Libro2", "Libro3"};
//            String[] datos = (String[]) libros.toArray();


//            ArrayAdapter<Libro> arrayAdapter = new ArrayAdapter<Libro>(getApplicationContext(), android.R.layout.simple_list_item_1, libros);
//            listViewLibros.setAdapter(arrayAdapter);

            //Log.v("Autor Libro::::",libros.get(1).getAutor().toString());
            super.onPostExecute(libros);
        }


    }

}


