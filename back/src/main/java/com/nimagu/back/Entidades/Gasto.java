package com.nimagu.back.Entidades;

import java.util.Date;

public class Gasto {
  
  private int    idgasto;
  private Date   fecha;
  private int    idproducto;
  private String nprod;
  private int    idtipo;
  private String ntipo;
  private int    idprov;
  private String nprov;
  private String ncomp;
  private float  cantidad;
  private double precioun;
  private float  tiva;
  private double importe;
  private int    marca1;
  private String fpago;
  private String observ;
  
  public Gasto(){}

  public int getIdgasto() {
    return idgasto;
  }

  public void setIdgasto(int idgasto) {
    this.idgasto = idgasto;
  }

  public Date getFecha() {
    return fecha;
  }

  public void setFecha(Date fecha) {
    this.fecha = fecha;
  }

  public int getIdproducto() {
    return idproducto;
  }

  public void setIdproducto(int idproducto) {
    this.idproducto = idproducto;
  }

  public String getNprod() {
    return nprod;
  }

  public void setNprod(String nprod) {
    this.nprod = nprod;
  }

  public int getIdtipo() {
    return idtipo;
  }

  public void setIdtipo(int idtipo) {
    this.idtipo = idtipo;
  }

  public String getNtipo() {
    return ntipo;
  }

  public void setNtipo(String ntipo) {
    this.ntipo = ntipo;
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

  public String getNcomp() {
    return ncomp;
  }

  public void setNcomp(String ncomp) {
    this.ncomp = ncomp;
  }

  public float getCantidad() {
    return cantidad;
  }

  public void setCantidad(float cantidad) {
    this.cantidad = cantidad;
  }

  public double getPrecioun() {
    return precioun;
  }

  public void setPrecioun(double precioun) {
    this.precioun = precioun;
  }

  public float getTiva() {
    return tiva;
  }

  public void setTiva(float tiva) {
    this.tiva = tiva;
  }

  public double getImporte() {
    return importe;
  }

  public void setImporte(double importe) {
    this.importe = importe;
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

  public String getFpago() {
    return fpago;
  }

  public void setFpago(String fpago) {
    this.fpago = fpago;
  }

  

}
