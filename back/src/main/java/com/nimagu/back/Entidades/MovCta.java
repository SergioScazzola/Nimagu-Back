package com.nimagu.back.Entidades;

import java.util.Date;

public class MovCta {
  private int          idCuenta;
  private int          nromov;
  private Date         fecha;
  private Date         fechamov;
  private int          cliprov;
  private String       ingegre;
  private String       tipomov;
  private String       nrocheque;
  private String       descrip;
  private String       nroliq;
  private double       importe;
  private String       coment;
  private int          movvinc; 
  private int          marcada;


  public MovCta() {}


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


  public Date getFechamov() {
    return fechamov;
  }


  public void setFechamov(Date fechamov) {
    this.fechamov = fechamov;
  }


  public String getIngegre() {
    return ingegre;
  }


  public void setIngegre(String ingegre) {
    this.ingegre = ingegre;
  }


  public String getTipomov() {
    return tipomov;
  }


  public void setTipomov(String tipomov) {
    this.tipomov = tipomov;
  }


  public String getNrocheque() {
    return nrocheque;
  }


  public void setNrocheque(String nrocheque) {
    this.nrocheque = nrocheque;
  }


  public String getDescrip() {
    return descrip;
  }


  public void setDescrip(String descrip) {
    this.descrip = descrip;
  }


  public String getNroliq() {
    return nroliq;
  }


  public void setNroliq(String nroliq) {
    this.nroliq = nroliq;
  }


  public double getImporte() {
    return importe;
  }


  public void setImporte(double importe) {
    this.importe = importe;
  }


  public String getComent() {
    return coment;
  }


  public void setComent(String coment) {
    this.coment = coment;
  }


  public Date getFecha() {
    return fecha;
  }


  public void setFecha(Date fecha) {
    this.fecha = fecha;
  }


  public int getMovvinc() {
    return movvinc;
  }


  public void setMovvinc(int movvinc) {
    this.movvinc = movvinc;
  }


  public int getCliprov() {
    return cliprov;
  }


  public void setCliprov(int cliprov) {
    this.cliprov = cliprov;
  }


  public int getMarcada() {
    return marcada;
  }


  public void setMarcada(int marcada) {
    this.marcada = marcada;
  }

 

  
}
