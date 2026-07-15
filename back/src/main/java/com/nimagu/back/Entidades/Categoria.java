package com.nimagu.back.Entidades;

public class Categoria {
    private int     idCategoria;
    private String  nombre;
    private String  ingeg; // INEG-todos ING-ingreso EGR-egreso
   

    public Categoria(){}

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

  public String getIngeg() {
        return ingeg;
    }

    public void setIngeg(String ingeg) {
        this.ingeg = ingeg;
    }

    
}
