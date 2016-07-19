package adpatadorLibro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import example.pedrow10.adminlibrosmov.R;
import example.pedrow10.adminlibrosmov.vo.Libro;

/**
 * Created by PedroW10 on 18/7/2016.
 */
public class AdaptadorLibro extends ArrayAdapter {
    private List<Libro>  libros;
    public AdaptadorLibro(Context context, List<Libro> libros) {
        super(context, 0, libros);
        this.libros= libros;
    }
@Override
    public View getView(int posicion, View view, ViewGroup padre){
        if(view== null){
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            view= layoutInflater.inflate(R.layout.item_libro,null);
        }
    TextView titulo = (TextView) view.findViewById(R.id.txtViewTitulo);
    TextView autor = (TextView) view.findViewById(R.id.txtViewAutor);
    TextView stock = (TextView) view.findViewById(R.id.txtViewStock);
    Libro libro = libros.get(posicion);
    titulo.setText(libro.getTitulo());
    autor.setText(libro.getAutor());
    stock.setText(String.valueOf(libro.getStock()));

    return view;

}


}
