package com.example.noaguantomas;

import android.content.Intent;
import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.android.material.tabs.TabLayout;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Obtener datos del Intent
        Intent intent = getIntent();
        String nombre = intent.getStringExtra("nombre");
        String correo = intent.getStringExtra("correo");
        String nombreperfil = intent.getStringExtra("nombreperfil");

        // Cargar el fragmento de inicio de manera predeterminada
        inicio i = new inicio();
        Bundle args = new Bundle();
        args.putString("nombreperfil", nombreperfil); // Pasar el nombre de perfil
        i.setArguments(args); // Establecer los argumentos
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedor, i).commit();

        // Configurar el TabLayout
        TabLayout tl = findViewById(R.id.tabmain);
        tl.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                switch (position) {
                    case 0:
                        inicio i = new inicio(); // Instanciar el fragmento
                        Bundle args2 = new Bundle();
                        args2.putString("nombreperfil", nombreperfil); // Pasar el nombre de perfil
                        i.setArguments(args2);
                        getSupportFragmentManager().beginTransaction().replace(R.id.contenedor, i).commit();
                        break;
                    case 1:
                        mensajes m = new mensajes();
                        getSupportFragmentManager().beginTransaction().replace(R.id.contenedor, m).commit();
                        break;
                    case 2:
                        perfil p = new perfil();
                        // Pasar datos al fragmento de perfil
                        Bundle args = new Bundle();
                        args.putString("correo", correo);
                        args.putString("nombre", nombre);
                        p.setArguments(args);
                        getSupportFragmentManager().beginTransaction().replace(R.id.contenedor, p).commit();
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });

    }
}





