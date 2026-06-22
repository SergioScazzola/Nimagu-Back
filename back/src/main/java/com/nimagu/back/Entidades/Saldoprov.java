package com.Sisbul.ApiRrest.entidades;

import java.util.Date;

public class Saldoprov {
  private int     idProv;
  private int     nrosaldo;
  private Date    fecha;
  private double  saldo;

public Saldoprov(){}

public int getIdProv() {
    return idProv;
}

public void setIdProv(int idProv) {
    this.idProv = idProv;
}

public int getNrosaldo() {
    return nrosaldo;
}

public void setNrosaldo(int nrosaldo) {
    this.nrosaldo = nrosaldo;
}

public Date getFecha() {
    return fecha;
}

public void setFecha(Date fecha) {
    this.fecha = fecha;
}

public double getSaldo() {
    return saldo;
}

public void setSaldo(double saldo) {
    this.saldo = saldo;
}


}
