package com.nimagu.back.Entidades;

import java.util.Date;

public class CompVta {

  private int    idcomvta;  
  private String compvta;  // compra o venta
  private Date   fecha;
  private int    idprocli; // proveedor o cliente
  private String nprovcli;
  private String nroliq;
  private String categoria;
  private float  cantidad;
  private float  totalk;
  private float  promedio;
  private float  preunit;
  private double importe;
  private String proced;
  private int    marca1;
  private String observ;

  public CompVta(){}

  public int getIdcomvta() {
    return idcomvta;
  }

  public void setIdcomvta(int idcomvta) {
    this.idcomvta = idcomvta;
  }

  public String getCompvta() {
    return compvta;
  }

  public void setCompvta(String compvta) {
    this.compvta = compvta;
  }

  public Date getFecha() {
    return fecha;
  }

  public void setFecha(Date fecha) {
    this.fecha = fecha;
  }

  public int getIdprocli() {
    return idprocli;
  }

  public void setIdprocli(int idprocli) {
    this.idprocli = idprocli;
  }

  public String getNprovcli() {
    return nprovcli;
  }

  public void setNprovcli(String nprovcli) {
    this.nprovcli = nprovcli;
  }

  public String getNroliq() {
    return nroliq;
  }

  public void setNroliq(String nroliq) {
    this.nroliq = nroliq;
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

  public float getTotalk() {
    return totalk;
  }

  public void setTotalk(float totalk) {
    this.totalk = totalk;
  }

  public float getPromedio() {
    return promedio;
  }

  public void setPromedio(float promedio) {
    this.promedio = promedio;
  }

  public float getPreunit() {
    return preunit;
  }

  public void setPreunit(float preunit) {
    this.preunit = preunit;
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

  public String getObserv() {
    return observ;
  }

  public void setObserv(String observ) {
    this.observ = observ;
  }

  public int getMarca1() {
    return marca1;
  }

  public void setMarca1(int marca1) {
    this.marca1 = marca1;
  }

  
}