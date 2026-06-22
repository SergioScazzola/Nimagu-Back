package com.Sisbul.ApiRrest.entidades;

import java.util.Date;

public class Saldocli {
  private int     idCliente;
  private int     nrosaldo;
  private Date    fecha;
  private double  saldo;
  
  
  public Saldocli(){}


  public int getIdCliente() {
    return idCliente;
  }


  public void setIdCliente(int idCliente) {
    this.idCliente = idCliente;
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
