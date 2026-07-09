package com.nimagu.back.Entidades;

import java.util.Date;

public class Endoso {
   private int    idendoso;
   private int    idCuenta;
   private int    nromov;
   private Date   fecha;
   private String nrocheque;   
   private int    idprov;
   private String descrip;
   private double importe;

    public Endoso() {
    }

    
    public int getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(int idCuenta) {
        this.idCuenta = idCuenta;
    }

    public int getNromov() {
        return nromov;
    }

    public void setNromov(int nromov) {
        this.nromov = nromov;
    }

    public String getNrocheque() {
        return nrocheque;
    }

    public void setNrocheque(String nrocheque) {
        this.nrocheque = nrocheque;
    }

    public int getIdprov() {
        return idprov;
    }

    public void setIdprov(int idprov) {
        this.idprov = idprov;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }


    public int getIdendoso() {
        return idendoso;
    }


    public void setIdendoso(int idendoso) {
        this.idendoso = idendoso;
    }


    public double getImporte() {
        return importe;
    }


    public void setImporte(double importe) {
        this.importe = importe;
    }

    
}
