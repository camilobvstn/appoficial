package com.example.noaguantomas;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link inicio#newInstance} factory method to
 * create an instance of this fragment.
 */
public class inicio extends Fragment {

    private ArrayList<Publicacion>listado;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public inicio() {

    }



    public static inicio newInstance(String param1, String param2) {
        inicio fragment = new inicio();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inicio, container, false);


        TextView nombreInicio = view.findViewById(R.id.nombreinicio);


        Bundle args = getArguments();
        if (args != null) {
            String nombreperfil = args.getString("nombreperfil");
            nombreInicio.setText(nombreperfil != null ? nombreperfil : "Nombre no disponible");
        }
        Bundle args2 = getArguments();
        if (args != null) {
            String nombreinicio = args.getString("nombreinicio");
            nombreInicio.setText(nombreinicio != null ? nombreinicio : "Nombre no disponible");
        }


        TextView acategorias = view.findViewById(R.id.acategorias);
        acategorias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), objperdidos.class);
                startActivity(intent);
            }
        });

        View avisos = view.findViewById(R.id.avisos);
        avisos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), avisos.class);
                startActivity(intent);
            }
        });

        View objetos = view.findViewById(R.id.objetos);
        objetos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), objetos.class);
                startActivity(intent);
            }
        });

        View categorias1 = view.findViewById(R.id.comunidad);
        categorias1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), comunidad.class);
                startActivity(intent);
            }
        });
        return view;

    }

}

