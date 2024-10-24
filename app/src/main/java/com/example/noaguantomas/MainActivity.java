package com.example.noaguantomas;

import android.app.Dialog;
import android.app.ProgressDialog;
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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    Button btnregistrar;
    EditText correologin, contralogin;
    Button iniciarsesion;
    private ProgressDialog progressDialog;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        btnregistrar = findViewById(R.id.btnregistrar);
        correologin = findViewById(R.id.correologin);
        contralogin = findViewById(R.id.contralogin);
        iniciarsesion = findViewById(R.id.iniciarsesion);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Iniciando sesión...");
        dialog = new Dialog(MainActivity.this);

        iniciarsesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String correo = correologin.getText().toString();
                String contra = contralogin.getText().toString();

                if (!Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
                    correologin.setError("Ingrese un correo válido.");
                    correologin.setFocusable(true);
                } else if (contra.length() < 6) {
                    contralogin.setError("La contraseña debe tener al menos 6 caracteres.");
                    contralogin.setFocusable(true);
                } else {
                    loginusuario(correo, contra);
                }
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void loginusuario(String correo, String contra) {
        progressDialog.setCancelable(false);
        progressDialog.show();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("USUARIOS");
        reference.orderByChild("correo").equalTo(correo).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        String dbContra = snapshot.child("contra").getValue(String.class);
                        if (dbContra != null && dbContra.equals(contra)) {
                            String nombre = snapshot.child("nombre").getValue(String.class);
                            progressDialog.dismiss();
                            Toast.makeText(MainActivity.this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();

                            // Pasar datos a Home
                            Intent intent = new Intent(MainActivity.this, Home.class);
                            intent.putExtra("nombre", nombre);
                            intent.putExtra("correo", correo);
                            startActivity(intent);
                            finish();
                        } else {
                            progressDialog.dismiss();
                            Dialog_ok_no_inicio();
                        }
                    }
                } else {
                    progressDialog.dismiss();
                    Dialog_ok_no_inicio();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, "Error de conexión: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void Dialog_ok_no_inicio() {
        Button ok_no_inicio;
        dialog.setContentView(R.layout.no_sesion);
        ok_no_inicio = dialog.findViewById(R.id.ok_no_inicio);
        ok_no_inicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.setCancelable(false);
        dialog.show();
    }

    public void registrar(View v) {
        Intent i = new Intent(this, registro.class);
        startActivity(i);
    }
}

