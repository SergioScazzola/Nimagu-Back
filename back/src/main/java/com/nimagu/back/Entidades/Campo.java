package com.nimagu.back.Entidades;

public class Campo {
    private int    idcampo;
    private String nombre;
    private String abrev;
    private String proced;

    public Campo(){}

    public int getIdcampo() {
        return idcampo;
    }

    public void setIdcampo(int idcampo) {
        this.idcampo = idcampo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getProced() {
        return proced;
    }

    public void setProced(String proced) {
        this.proced = proced;
    }

    public String getAbrev() {
        return abrev;
    }

    public void setAbrev(String abrev) {
        this.abrev = abrev;
    }

    
}
