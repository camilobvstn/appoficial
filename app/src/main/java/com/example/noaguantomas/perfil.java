package com.example.noaguantomas;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class perfil extends Fragment {
    private TextView nombreperfil, correoperfil;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);

        nombreperfil = view.findViewById(R.id.nombreperfil);
        correoperfil = view.findViewById(R.id.correoperfil);

        // Aquí recibimos los argumentos
        Bundle args = getArguments();
        if (args != null) {
            String nombre = args.getString("nombre");
            String correo = args.getString("correo");

            // Mostrar los datos en los TextViews
            nombreperfil.setText(nombre != null ? nombre : "Nombre no disponible");
            correoperfil.setText(correo != null ? correo : "Correo no disponible");
        }

        return view;
    }
}




