package com.example.noaguantomas;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class registro extends AppCompatActivity {
    EditText pedircontra, pedircorreo, pedirnombre;
    Button enviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registro);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        pedircontra = findViewById(R.id.contraregistro);
        pedircorreo = findViewById(R.id.correoregistro);
        pedirnombre = findViewById(R.id.nombreregistro);
        enviar = findViewById(R.id.enviar);

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String contra = pedircontra.getText().toString();
                String correo = pedircorreo.getText().toString();
                String nombre = pedirnombre.getText().toString();

                if (!Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
                    pedircorreo.setError("Ingrese un correo válido.");
                    pedircorreo.setFocusable(true);
                } else if (contra.length() < 6) {
                    pedircontra.setError("La contraseña debe tener al menos 6 caracteres.");
                    pedircontra.setFocusable(true);
                } else {
                    registrar(contra, correo, nombre);
                }
            }
        });
    }

    private void registrar(String contra, String correo, String nombre) {
        HashMap<Object, String> DatosUsuario = new HashMap<>();
        DatosUsuario.put("contra", contra);
        DatosUsuario.put("correo", correo);
        DatosUsuario.put("nombre", nombre);
        DatosUsuario.put("imagen", "");

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("USUARIOS");
        reference.child(nombre).setValue(DatosUsuario);

        Toast.makeText(this, "Se registró con éxito", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(registro.this, MainActivity.class));
        finish(); // Esto cierra la actividad de registro para que no se pueda volver atrás.
    }
}
