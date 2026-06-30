package com.nimagu.back.Entidades;

import java.util.Date;

public class Salida {
  private int        idSalida;
  private Date       fecha;
  private int        idprov;
  private String     nprov;
  private String     nroliq;
  private int        idcat;
  private String     categoria;
  private float      cantidad;
  private float      tkilos;
  private float      precioun;
  private double     importe;
  private String     proced;
  private int        idpago;
  private String     observ; 

  public Salida(){}

  public int getIdSalida() {
    return idSalida;
  }

  public void setIdSalida(int idSalida) {
    this.idSalida = idSalida;
  }

  public Date getFecha() {
    return fecha;
  }

  public void setFecha(Date fecha) {
    this.fecha = fecha;
  }

  public int getIdprov() {
    return idprov;
  }

  public void setIdprov(int idprov) {
    this.idprov = idprov;
  }

  public String getNprov() {
    return nprov;
  }

  public void setNprov(String nprov) {
    this.nprov = nprov;
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

  public int getIdpago() {
    return idpago;
  }

  public void setIdpago(int idpago) {
    this.idpago = idpago;
  }

  public String getObserv() {
    return observ;
  }

  public void setObserv(String observ) {
    this.observ = observ;
  }

  
}
