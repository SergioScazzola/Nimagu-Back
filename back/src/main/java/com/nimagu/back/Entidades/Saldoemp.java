package com.Sisbul.ApiRrest.entidades;

import java.util.Date;

public class Saldoemp {
  private int     idEmpleado;
  private int     nrosaldo;
  private Date    fecha;
  private double  saldo;
  
  
  public Saldoemp(){}


  public int getIdEmpleado() {
    return idEmpleado;
  }


  public void setIdEmpleado(int idEmpleado) {
    this.idEmpleado = idEmpleado;
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
