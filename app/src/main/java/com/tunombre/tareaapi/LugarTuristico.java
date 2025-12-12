package com.tunombre.tareaapi;

public class LugarTuristico {
    private String nombre;
    private String categoria;
    private String telefono;

    public LugarTuristico(String nombre, String categoria, String telefono) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.telefono = telefono;
    }

    public String getNombre() { return nombre; }
    public String getCategoria() { return categoria; }
    public String getTelefono() { return telefono; }
}