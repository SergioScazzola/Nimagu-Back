package com.nimagu.back.Entidades;

import java.util.Date;

public class MovHacienda {
    private int      idmovh;
    private Date     fecha;    
    private int      idhacienda;
    private String   nhacienda;
    private int      cantidad;
    private String   tipomov;
    private String   ineg; 
    private int      idcampo;
    private String   ncampo;
    private String   potrero;
    private String   abrev;
    private String   observ;
    private int      marca1;
    private int      marca2;
    private int      marca3;
    
    public MovHacienda(){}

    public int getIdmovh() {
        return idmovh;
    }

    public void setIdmovh(int idmovh) {
        this.idmovh = idmovh;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getObserv() {
        return observ;
    }

    public void setObserv(String obs) {
        this.observ = obs;
    }

    public int getIdhacienda() {
        return idhacienda;
    }

    public void setIdhacienda(int idhacienda) {
        this.idhacienda = idhacienda;
    }

    public String getNhacienda() {
        return nhacienda;
    }

    public void setNhacienda(String nhacienda) {
        this.nhacienda = nhacienda;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getIdcampo() {
        return idcampo;
    }

    public void setIdcampo(int idcampo) {
        this.idcampo = idcampo;
    }

    public String getNcampo() {
        return ncampo;
    }

    public void setNcampo(String ncampo) {
        this.ncampo = ncampo;
    }

    public int getMarca1() {
        return marca1;
    }

    public void setMarca1(int marca1) {
        this.marca1 = marca1;
    }

    public int getMarca2() {
        return marca2;
    }

    public void setMarca2(int marca2) {
        this.marca2 = marca2;
    }

    public int getMarca3() {
        return marca3;
    }

    public void setMarca3(int marca3) {
        this.marca3 = marca3;
    }

    public String getAbrev() {
        return abrev;
    }

    public void setAbrev(String abrev) {
        this.abrev = abrev;
    }

    public String getTipomov() {
        return tipomov;
    }

    public void setTipomov(String tipomov) {
        this.tipomov = tipomov;
    }

    public String getIneg() {
        return ineg;
    }

    public void setIneg(String ineg) {
        this.ineg = ineg;
    }

    public String getPotrero() {
        return potrero;
    }

    public void setPotrero(String potrero) {
        this.potrero = potrero;
    }

    
}
