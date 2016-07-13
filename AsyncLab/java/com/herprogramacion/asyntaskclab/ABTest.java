package com.herprogramacion.asyntaskclab;


import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;

public class ABTest extends Activity implements HiddenFragment.TaskCallbacks {

    /*
    Etiqueta de referencia del fragmento invisible
     */
    public static final String HIDDEN_FRAGMENT_TAG = "ABTestFragment";

    /*
    Instancia del Fragmento
     */
    HiddenFragment fragment;

    /*
    Cantidad de números a ordenar
     */
    public static final int LENGHT = 20000;

    /*
    Arreglo para los números
     */
    public static int numbers[] = new int[LENGHT];

    /*
    Posición del item de la lista
     */
    int position;

    /*
    Barra de Progreso
     */
    public ProgressBar progressBar;

    /*
    TextView para mostrar progreso
     */
    TextView progressLabel;

    /*
    Botón que inicia el ordenamiento
     */
    Button sortButton;

    /*
    Botón que cancela el ordenamiento
     */
    Button cancelButton;

    /*
    Instancia de SimpleTask
     */
    SimpleTask simpleTask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abtest);

        // Generar números aleatorios
        if(fragment==null)
        generateNumbers();

        /*
        Obtención de la posición del item en la lista
         */
        Intent i = getIntent();
        position = i.getIntExtra(Main.SEND_KEY_VALUE, -1);

        /*
        Obtención de todos los Views a utilizar
         */
        sortButton = (Button)findViewById(R.id.sortButton);
        cancelButton = (Button)findViewById(R.id.cancelButton);
        progressLabel = (TextView)findViewById(R.id.progressLabel);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);

        // Obtener referencia del fragmento
        fragment = (HiddenFragment)getFragmentManager().
                findFragmentByTag(HIDDEN_FRAGMENT_TAG);

        /*
        En caso de una rotación de pantalla, es necesario hacer visible
        la barra de progreso, hacer visible el botón de cancelar de nuevo y
        desactivar el botón "Ordenar"
         */
        if(position==3 && fragment!=null) {
            if(fragment.progressBarTask.getStatus()== AsyncTask.Status.RUNNING){
                progressBar.setVisibility(View.VISIBLE);
                cancelButton.setVisibility(View.VISIBLE);
                sortButton.setEnabled(false);
            }
        }


    }

    /*
    Método onClick() personalizado para el botón "ORDENAR"
     */
    public void onClickSort(View v) {
        switch (position){
            case 0:
                bubbleSort(numbers);
                progressLabel.setText("Terminado");
                break;
            case 1:
                execWithThread();
                break;
            case 2:
                execWithAsyncTask();
                break;
            case 3:
                execWithProgresBar();
                break;

        }

    }



    /*
    Método onClick() personalizado para el botón "CANCELAR"
     */
    public void onClickCancel(View v){
        if(position==2)
        simpleTask.cancel(true);
        if(position==3)
            fragment.progressBarTask.cancel(true);
    }

    /*
    Método que genera los números aleatorios
     */
    private void generateNumbers() {
        Random rnd = new Random();
        for(int i=0; i<LENGHT; i++){
            numbers[i]= (int) (rnd.nextDouble() * LENGHT + 1);
        }
    }

    /*
    Método que aplica el ordenamiento tipo burbuja simple
     */
    private void bubbleSort(int[] numbers) {

        int aux;

        for (int i = 0; i < numbers.length - 1; i++) {
            for (int j = 0; j < numbers.length -1; j++) {
                if (numbers[j] > numbers[j+1])
                {
                    aux          = numbers[j];
                    numbers[j]   = numbers[j+1];
                    numbers[j+1] = aux;
                }

            }
        }

    }

    /*
    Método para ejecutar el ordenamiento a través de un hilo simple
     */
    public void execWithThread(){

        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        bubbleSort(numbers);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progressLabel.setText("Terminado");
                            }
                        });
                    }
                }
        ).start();

    }

    /*
    Método para ejecutar el ordenamiento a través de una tarea
    asíncrona del tipo SimpleTask
     */
    public void execWithAsyncTask(){
        simpleTask= new SimpleTask();
        simpleTask.execute();
    }

    /*
    Creación del nuevo HiddenFragment para el inicio de la
    ProgressBarTask
     */
    private void execWithProgresBar() {
        FragmentManager fg = getFragmentManager();
        fragment = new HiddenFragment();
        FragmentTransaction transaction = fg.beginTransaction();
        transaction.add(fragment, HIDDEN_FRAGMENT_TAG);
        transaction.commit();
    }

    /*
    La clase SimpleTask está creada para ordenar los números en segundo plano.
    El progreso será mostrado en progressLabel
     */
    private class SimpleTask extends AsyncTask<Void, Integer, Void> {

        /*
        Se hace visible el botón "Cancelar" y se desactiva
        el botón "Ordenar"
         */
        @Override
        protected void onPreExecute() {

            cancelButton.setVisibility(View.VISIBLE);
            sortButton.setEnabled(false);
        }

        /*
        Ejecución del ordenamiento y transmision de progreso
         */
        @Override
        protected Void doInBackground(Void... params) {
            int aux;

            for (int i = 0; i < numbers.length - 1; i++) {
                for (int j = 0; j < numbers.length -1; j++) {
                    if (numbers[j] > numbers[j+1])
                    {
                        aux          = numbers[j];
                        numbers[j]   = numbers[j+1];
                        numbers[j+1] = aux;
                    }
                }
                // Notifica a onProgressUpdate() del progreso actual
                if(!isCancelled())
                    publishProgress((int)(((i+1)/(float)(numbers.length-1))*100));
                else break;
            }
            return null;
        }

        /*
         Se informa en progressLabel que se canceló la tarea y
         se hace invisile el botón "Cancelar"
          */
        @Override
        protected void onCancelled() {
            super.onCancelled();
            progressLabel.setText("En la Espera");
            cancelButton.setVisibility(View.INVISIBLE);
            sortButton.setEnabled(true);
        }

        /*
        Impresión del progreso en tiempo real
          */
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressLabel.setText(values[0] + "%");
        }

        /*
        Se notifica que se completó el ordenamiento y se habilita
        de nuevo el botón "Ordenar"
         */
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            progressLabel.setText("Completado");
            cancelButton.setVisibility(View.INVISIBLE);
            sortButton.setEnabled(true);
        }

    }

    /*
    Hace visible la barra de progreso y el botón "Cancelar,
    además desactiva el botón "Ordenar" para evitar la ejecución
    de la tarea asíncrona multiples veces al mismo tiempo.
     */
    @Override
    public void onPreExecute() {
        progressBar.setVisibility(View.VISIBLE);
        cancelButton.setVisibility(View.VISIBLE);
        sortButton.setEnabled(false);
    }

    /*
    Actualiza el progreso en el barra y en el porcentaje mostado
    en la etiqueta de progreso.
     */
    @Override
    public void onProgressUpdate(int progress) {
        progressBar.setProgress(progress);
        progressLabel.setText(progress+"%");
    }

    /*
    Cuando la tarea sea cancelada, inmediatamente se hace invisible
    la barra de progreso y el botón de cancelar. Adiconalmente se
    informa al usuario que ha sido cancelado el ordenamiento.
     */
    @Override
    public void onCancelled() {
        progressBar.setVisibility(View.INVISIBLE);
        cancelButton.setVisibility(View.INVISIBLE);
        progressLabel.setText("En la Espera");
        sortButton.setEnabled(true);
    }

    /*
    Al terminar el ordenamiento la barra de progreso vuelve a ser
    invisible, se muestra un mensaje de terminado y el botón "Ordenar"
    queda nuevamente habilitado para iniciar la tarea de nuevo.
     */
    @Override
    public void onPostExecute() {
        progressBar.setVisibility(View.INVISIBLE);
        cancelButton.setVisibility(View.INVISIBLE);
        progressLabel.setText("Completado");
        sortButton.setEnabled(true);

    }

}
