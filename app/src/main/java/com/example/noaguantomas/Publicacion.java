package com.example.noaguantomas;

public class Publicacion {
    private String nombre;
    private String correo;
    private String texto;
    private String hora;

    public Publicacion() {
        // Constructor vacío necesario para Firebase
    }

    public Publicacion(String nombre, String correo, String texto, String hora) {
        this.nombre = nombre;
        this.correo = correo;
        this.texto = texto;
        this.hora = hora;
    }

    // Getters y setters
    public String getNombre() { return nombre; }
    public String getCorreo() { return correo; }
    public String getTexto() { return texto; }
    public String getHora() { return hora; }
}

