package com.example.pedrow10.demo1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button calcular;
    private Button clear;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.txtview2);
        clear = (Button) findViewById(R.id.digitclear);

        calcular = (Button) findViewById(R.id.digitigual);
        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcular();
            }
        });

    }

    ArrayList<String> arrayList = new ArrayList<String>();
    String string = "";
    String string1 = "";

    public void obtenerDigito(View v) {


        Button button = (Button) v;
        string = (String) button.getText().toString();

        if (!string.equals("+") && !string.equals("-") && !string.equals("*") && !string.equals("/") && !string.equals("^")) {
            string1 = string1 + string;

            if (arrayList.size() > 0) {
//                rmovemos el indice del ultimo valor del array
                arrayList.remove(arrayList.size() - 1);

            }
//            el numero de un digito o mas siempre se va a guardar en esta posicion del arreglo
            arrayList.add(string1);
        } else {
            arrayList.add(string);
            arrayList.add("");
            string1 = "";
        }
//        tomamos lo que tiene la pantalla y mostramos en la misma pantalla este contenido mas el simbolo añadido
//        textView.setText(textView.getText().toString() + string);
        textView.setText(arrayList.toString());
    }

    public void calcular() {
//        calcular = (Button) findViewById(R.id.digitigual);
//        TextView textView1 = (TextView) findViewById(R.id.txtview1);
        Double calc = 0.0;
        int c = arrayList.size();
        System.out.println("INGRESA A CACULAR "+ c);
//ejemplo [2 + 3 * 4 - 3 ] size = 7, so c=
        while (c != 1) {
            System.out.println("INGRESA A WHILE");
            if (c > 3) {
                if (arrayList.get(3).equals("*") || arrayList.get(3).equals("/")) {
                    if (arrayList.get(3).equals("*")) {
                        calc = Double.parseDouble(arrayList.get(2)) * Double.parseDouble(arrayList.get(4));
                    }  if (arrayList.get(3).equals("/")) {
                        calc = Double.parseDouble(arrayList.get(2)) / Double.parseDouble(arrayList.get(4));
                    }
//                   calc =12; array= [2 + 3 * 4 - 3 ]
                    arrayList.remove(2); //array= [2 + * 4 - 3]
                    arrayList.remove(2); //array= [2 + 4 - 3]
                    arrayList.remove(2); //array= [2 + - 3]
//                    mapeamos en la segunda posicion el resultado
                    arrayList.add(2, Double.toString(calc)); //array= [2 + 12 - 3]
//                    actualizamos el tamanio del arreglo en la variable c.
                    c = arrayList.size(); //c = 5

                } else {
                    //array= [2 + 12 - 3]
                    if (arrayList.get(1).equals("+")) {
                        calc = Double.parseDouble(arrayList.get(0)) + Double.parseDouble(arrayList.get(2));
                    } if (arrayList.get(1).equals("-")) {
                        calc = Double.parseDouble(arrayList.get(0)) - Double.parseDouble(arrayList.get(2));
                    } if (arrayList.get(1).equals("*")) {
                        calc = Double.parseDouble(arrayList.get(0)) * Double.parseDouble(arrayList.get(2));
                    } if (arrayList.get(1).equals("/")) {
                        calc = Double.parseDouble(arrayList.get(0)) / Double.parseDouble(arrayList.get(2));
                    }

//                    calc=14; array=[2+12-3]
                    arrayList.remove(0);//array=[ + 12 - 3]
                    arrayList.remove(0);//array=[12 - 3]
                    arrayList.remove(0);//array=[ -3]
//                    mapeamos en la segunda posicion el resultado
                    arrayList.add(0, Double.toString(calc)); //array= [14 -3 ]
//                    actualizamos el tamanio del arreglo en la variable c.
                    c = arrayList.size(); //c =3
                }
            } else {
//                si el arreglo es menor a 3
                //array= [14 -3 ]
                if (arrayList.get(1).equals("+")) {
                    calc = Double.parseDouble(arrayList.get(0)) + Double.parseDouble(arrayList.get(2));
                }  if (arrayList.get(1).equals("-")) {
                    calc = Double.parseDouble(arrayList.get(0)) - Double.parseDouble(arrayList.get(2));
                }  if (arrayList.get(1).equals("*")) {
                    calc = Double.parseDouble(arrayList.get(0)) * Double.parseDouble(arrayList.get(2));
                }  if (arrayList.get(1).equals("/")) {
                    calc = Double.parseDouble(arrayList.get(0)) / Double.parseDouble(arrayList.get(2));
                }
                //calc = 11; array =[14 - 3]
                arrayList.remove(0);//array=[- 3]
                arrayList.remove(0);//array=[ 3]
                arrayList.remove(0);//array=[ ]
//                    mapeamos en la segunda posicion el resultado
                arrayList.add(0, Double.toString(calc)); //array= [11]
                System.out.println("valor array 0 "+arrayList.get(0).toString());
//                    actualizamos el tamanio del arreglo en la variable c.
                c = arrayList.size(); //c= 1, ya que el tamanio es igual a 1, finaliza el loop
            }


        }
        textView.setText(Double.toString(calc));
    }

    public void clear(View v) {
//        TextView textView1 = (TextView) findViewById(R.id.txtview1);
//        TextView textView2 = (TextView) findViewById(R.id.txtview2);
        textView.setText("0");
//        textView2.setText("");
        string = "";
        string1 = "";
        arrayList.clear();
    }

    public void funcionEspecial(View v){
        Double resultadoOperacion;
        int opcion= v.getId();
        switch(opcion){
            case R.id.digitseno:
                resultadoOperacion = Math.sin(Double.parseDouble(arrayList.get(0)));
                textView.setText(String.valueOf(resultadoOperacion));
                break;
            case R.id.digitcoseno:
                resultadoOperacion = Math.cos(Double.parseDouble(arrayList.get(0)));
                textView.setText(String.valueOf(resultadoOperacion));
                break;
            case R.id.digittangente:
                resultadoOperacion = Math.tan(Double.parseDouble(arrayList.get(0)));
                textView.setText(String.valueOf(resultadoOperacion));
                break;
            case R.id.digitpotencia:
                resultadoOperacion = Math.pow(Double.parseDouble(arrayList.get(0)),Double.parseDouble(arrayList.get(2)));
                textView.setText(String.valueOf(resultadoOperacion));
                break;
            case R.id.digitraiz:
                resultadoOperacion = Math.sqrt(Double.parseDouble(arrayList.get(0)));
                textView.setText(String.valueOf(resultadoOperacion));
                break;
        }
    }
}
