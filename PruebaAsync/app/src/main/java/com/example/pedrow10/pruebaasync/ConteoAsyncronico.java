package com.example.pedrow10.pruebaasync;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by PedroW10 on 11/7/2016.
 */
public class ConteoAsyncronico extends AsyncTask<Integer, Integer, Void> {
    TextView resultados;

    public ConteoAsyncronico(TextView resultados) {
        super();
        this.resultados = resultados;


    }

    @Override
    protected Void doInBackground(Integer[] integers) {

        for (int i = 0; i < integers[0]; i++) {
            Log.v("sinc: ", " " + i);
//            txtconteo.append(" " +i);
            publishProgress(i);

            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... progreso) {
        super.onProgressUpdate(progreso);
        int i = progreso[0];
        resultados.append(" " + i);
    }
}
