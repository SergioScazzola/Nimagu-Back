package com.nimagu.back.Entidades;

import java.util.Date;

public class MovCta {
  int          idCuenta;
  int          nromov;
  Date         fechamov;
  String       ingegre;
  String       tipocomp;
  String       comprob;
  String       concepto;
  double       importe;
  String       coment;

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

  public String getTipocomp() {
    return tipocomp;
  }

  public void setTipocomp(String tipocomp) {
    this.tipocomp = tipocomp;
  }

  public String getComprob() {
    return comprob;
  }

  public void setComprob(String comprob) {
    this.comprob = comprob;
  }

  public String getConcepto() {
    return concepto;
  }

  public void setConcepto(String concepto) {
    this.concepto = concepto;
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

  
}
