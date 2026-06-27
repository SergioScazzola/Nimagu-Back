package com.nimagu.back.Entidades;

public class Categoria {
    private int     idCategoria;
    private String  nombre;
    private int     ingeg; // 0-todos 1-ingreso 2-egreso
   

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

  public int getIngeg() {
        return ingeg;
    }

    public void setIngeg(int ingeg) {
        this.ingeg = ingeg;
    }

    
}
