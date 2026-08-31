package com.nimagu.back.Entidades;

public class Fpago {
    private int      idfpago;
    private int      idgasto;
    private String   descrip;

    public Fpago(){}

    public int getIdfpago() {
        return idfpago;
    }

    public void setIdfpago(int idfpago) {
        this.idfpago = idfpago;
    }

    public int getIdgasto() {
        return idgasto;
    }

    public void setIdgasto(int idgasto) {
        this.idgasto = idgasto;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    
}
