package com.Sisbul.ApiRrest.entidades;

public class ResuCult {

    private String     ncultivo;
    private int        clab;
    private int        hasTrab;
    private double     valorLaboreo;
    private double     valorNeto;

    public ResuCult(){}

    
    public String getNcultivo() {
        return ncultivo;
    }

    public void setNcultivo(String ncultivo) {
        this.ncultivo = ncultivo;
    }

    public int getClab() {
        return clab;
    }

    public void setClab(int clab) {
        this.clab = clab;
    }

    public int getHasTrab() {
        return hasTrab;
    }

    public void setHasTrab(int hasTrab) {
        this.hasTrab = hasTrab;
    }

    public double getValorLaboreo() {
        return valorLaboreo;
    }

    public void setValorLaboreo(double valorLaboreo) {
        this.valorLaboreo = valorLaboreo;
    }


    public double getValorNeto() {
        return valorNeto;
    }


    public void setValorNeto(double valorNeto) {
        this.valorNeto = valorNeto;
    }

           
}
