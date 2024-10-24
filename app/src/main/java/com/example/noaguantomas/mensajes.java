package com.example.noaguantomas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class mensajes extends Fragment {

    public mensajes() {
        // Constructor vacío requerido
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Infla el layout para este fragmento
        return inflater.inflate(R.layout.fragment_mensajes, container, false);
    }
}





