// src/main/java/com/mycompany/frontend/domain/entity/Color.java
package com.mycompany.frontend.domain.entity;

public class Color {
    private int id;
    private String nombre;

    // Constructor vacío si lo necesitas (p. ej. para frameworks)
    public Color() { 
    }

    // Constructor que usa el mapper
    public Color(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    // Getters y setters
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
