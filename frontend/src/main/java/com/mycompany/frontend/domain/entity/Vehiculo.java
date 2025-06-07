package com.mycompany.frontend.domain.entity;

public class Vehiculo {
    private int id;
    private String placa;
    private String chasis;
    private int anio;
    private Marca marca;
    private Modelo modelo;
    private Color color;

    // Constructor vacío (si lo necesitas)
    public Vehiculo() { }

    // Agrega este constructor
    public Vehiculo(int id,
                    String placa,
                    String chasis,
                    int anio,
                    Marca marca,
                    Modelo modelo,
                    Color color) {
        this.id     = id;
        this.placa  = placa;
        this.chasis = chasis;
        this.anio   = anio;
        this.marca  = marca;
        this.modelo = modelo;
        this.color  = color;
    }

    // Getters y setters...
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getPlaca() { return placa; }
    public void setPlaca(String placa) { this.placa = placa; }

    public String getChasis() { return chasis; }
    public void setChasis(String chasis) { this.chasis = chasis; }

    public int getAnio() { return anio; }
    public void setAnio(int anio) { this.anio = anio; }

    public Marca getMarca() { return marca; }
    public void setMarca(Marca marca) { this.marca = marca; }

    public Modelo getModelo() { return modelo; }
    public void setModelo(Modelo modelo) { this.modelo = modelo; }

    public Color getColor() { return color; }
    public void setColor(Color color) { this.color = color; }
}
