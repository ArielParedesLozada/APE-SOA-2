package com.mycompany.frontend.domain.entity;

public class Modelo {
    private int id;
    private String nombre;

    // Constructor vacío
    public Modelo() { }

    // Constructor con parámetros
    public Modelo(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    // Getters / Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
