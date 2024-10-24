package com.example.noaguantomas;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class publicar extends AppCompatActivity {

    private Spinner spinnerCategoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_publicar);

        // Manejo de insets para pantallas con bordes
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inicializar el Spinner
        spinnerCategoria = findViewById(R.id.spinner_categoria);

        // Crear un ArrayAdapter usando un array de cadenas y un diseño de spinner predeterminado
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.categorias_array, android.R.layout.simple_spinner_item);
        // Especificar el diseño a usar cuando se muestra la lista de opciones
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Aplicar el adaptador al spinner
        spinnerCategoria.setAdapter(adapter);
    }
}
