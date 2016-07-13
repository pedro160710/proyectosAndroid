/*
Creado por Hermosa Programación - www.hermosaprogramacion.com

Artículo: Uso de Hilos y Tareas Asíncronas (AsyncTask) en Android

Objetivos:
    - Comprender el uso de Hilos en Android
    - Comprender el uso de la clase AsyncTask en Android
    - Evaluar la mejor opción para realizar trabajos en segundo plano
 */

package com.herprogramacion.asyntaskclab;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class Main extends ListActivity{

    /*
    Clave para el valor extra del Intent explícito
     */
    public static final String SEND_KEY_VALUE = "position";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(android.R.layout.list_content);

        // Se crea un nuevo adaptador desde strings.xml
        ArrayAdapter adapter = ArrayAdapter.
                createFromResource(
                        this,
                        R.array.Items,
                        android.R.layout.simple_list_item_1);

        this.setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView parent, View view, int position, long id) {

        /*
        Se envía el indice del item seleccionado hacia ABTest
         */
        Intent i = new Intent(this, ABTest.class);
        i.putExtra(SEND_KEY_VALUE, position);
        startActivity(i);

    }


}
