package com.mycompany.frontend.data.dto;

public class VehiculoDTO {
    private int id;
    private String placa;
    private String chasis;
    private int anio;
    private MarcaDTO marca;
    private ModeloDTO modelo;
    private ColorDTO color;

    public VehiculoDTO() { }

    // … constructor si lo necesitas …

    // getters y setters para **todos** los campos
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getPlaca() { return placa; }
    public void setPlaca(String placa) { this.placa = placa; }

    public String getChasis() { return chasis; }
    public void setChasis(String chasis) { this.chasis = chasis; }

    public int getAnio() { return anio; }
    public void setAnio(int anio) { this.anio = anio; }

    public MarcaDTO getMarca() { return marca; }
    public void setMarca(MarcaDTO marca) { this.marca = marca; }

    public ModeloDTO getModelo() { return modelo; }
    public void setModelo(ModeloDTO modelo) { this.modelo = modelo; }

    public ColorDTO getColor() { return color; }
    public void setColor(ColorDTO color) { this.color = color; }
}
