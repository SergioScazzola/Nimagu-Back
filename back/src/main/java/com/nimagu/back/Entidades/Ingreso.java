package com.nimagu.back.Entidades;

import java.util.Date;

public class Ingreso {
  private int        idIngreso;
  private Date       fecha;
  private int        idcliente;
  private String     ncliente;
  private String     nroliq;
  private int        idcat;
  private String     categoria;
  private float      cantidad;
  private float      tkilos;
  private float      precioun;
  private double     importe;
  private String     proced;
  private int        idcobro;
  private String     observ;
 
  public Ingreso(){}

  public int getIdIngreso() {
    return idIngreso;
  }

  public void setIdIngreso(int idIngreso) {
    this.idIngreso = idIngreso;
  }

  public Date getFecha() {
    return fecha;
  }

  public void setFecha(Date fecha) {
    this.fecha = fecha;
  }

  public int getIdcliente() {
    return idcliente;
  }

  public void setIdcliente(int idcliente) {
    this.idcliente = idcliente;
  }

  public String getNcliente() {
    return ncliente;
  }

  public void setNcliente(String ncliente) {
    this.ncliente = ncliente;
  }

  public String getNroliq() {
    return nroliq;
  }

  public void setNroliq(String nroliq) {
    this.nroliq = nroliq;
  }

  public int getIdcat() {
    return idcat;
  }

  public void setIdcat(int idcat) {
    this.idcat = idcat;
  }

  public String getCategoria() {
    return categoria;
  }

  public void setCategoria(String categoria) {
    this.categoria = categoria;
  }

  public float getCantidad() {
    return cantidad;
  }

  public void setCantidad(float cantidad) {
    this.cantidad = cantidad;
  }

  public float getTkilos() {
    return tkilos;
  }

  public void setTkilos(float tkilos) {
    this.tkilos = tkilos;
  }

  public float getPrecioun() {
    return precioun;
  }

  public void setPrecioun(float precioun) {
    this.precioun = precioun;
  }

  public double getImporte() {
    return importe;
  }

  public void setImporte(double importe) {
    this.importe = importe;
  }

  public String getProced() {
    return proced;
  }

  public void setProced(String proced) {
    this.proced = proced;
  }

  public int getIdcobro() {
    return idcobro;
  }

  public void setIdcobro(int idcobro) {
    this.idcobro = idcobro;
  }

  public String getObserv() {
    return observ;
  }

  public void setObserv(String observ) {
    this.observ = observ;
  }

   
}
