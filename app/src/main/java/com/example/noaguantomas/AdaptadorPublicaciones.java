package com.example.noaguantomas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorPublicaciones extends ArrayAdapter<Publicacion> {
    private Context context;
    private List<Publicacion> listaPublicaciones;

    public AdaptadorPublicaciones(@NonNull Context context, @NonNull List<Publicacion> publicaciones) {
        super(context, 0, publicaciones);
        this.context = context;
        this.listaPublicaciones = publicaciones;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.publiaciones, parent, false);
        }

        Publicacion publicacion = getItem(position);

        // Obtener las vistas del diseño XML
        TextView nombreView = convertView.findViewById(R.id.nombrepubli);
        TextView correoView = convertView.findViewById(R.id.correopubli);
        TextView textoView = convertView.findViewById(R.id.textopubli);
        TextView horaView = convertView.findViewById(R.id.horapubli);

        // Asignar valores a las vistas
        nombreView.setText(publicacion.getNombre());
        correoView.setText(publicacion.getCorreo());
        textoView.setText(publicacion.getTexto());
        horaView.setText(publicacion.getHora());

        return convertView;
    }
}