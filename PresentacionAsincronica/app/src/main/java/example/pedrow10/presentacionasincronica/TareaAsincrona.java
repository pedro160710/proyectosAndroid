package example.pedrow10.presentacionasincronica;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by PedroW10 on 12/7/2016.
 */
public class TareaAsincrona extends AsyncTask<Integer, Integer, Boolean> {
    protected TextView textViewMensajeBienvenida;
    protected TextView textViewProgreso;
    protected ProgressBar barraProgreso;
    protected Context context;



    public TareaAsincrona(TextView textViewMensajeBienvenida,TextView txtProgreso, ProgressBar barraProgreso, Context context) {
        super();
        this.textViewMensajeBienvenida = textViewMensajeBienvenida;
        this.barraProgreso = barraProgreso;
        this.context = context;
        this.textViewProgreso= txtProgreso;
    }

    @Override
    protected void onPreExecute() {
        textViewMensajeBienvenida.append("Welcome to the jungle!!");
    }

    @Override
    protected Boolean doInBackground(Integer... integers) {
        Integer progreso = 0;
        for (int i = 0; i < integers[0]; i++) {
            Log.v("valores", " " + i);
            progreso = i;
            publishProgress(progreso);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return true;
    }

    @Override
    protected void onProgressUpdate(Integer... porcentajeProgreso) {
        textViewProgreso.setText(porcentajeProgreso[0]+" %");
        Log.v("on progressUpdate", " " + porcentajeProgreso[0]);
        barraProgreso.setProgress(Math.round(porcentajeProgreso[0]));

    }

    @Override
    protected void onPostExecute(Boolean v) {
        Log.v("onPostExecute: ", "fin" + v);
        Intent intent = new Intent(context, SaludoApp.class);
        context.startActivity(intent);
    }
}
