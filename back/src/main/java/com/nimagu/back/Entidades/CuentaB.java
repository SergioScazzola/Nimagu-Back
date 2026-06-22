package com.nimagu.back.Entidades;

import java.util.Date;

public class CuentaB {
   private int      idCuenta;
   private String   periodo;
   private String   titular;
   private String   banco;
   private String   cbu;
   private Date     fecsaldo;
   private double   saldoini;
   private double   saldofin;
   private int      cantmovs;
   private String   observ;    

   public CuentaB(){}

   public int getIdCuenta() {
    return idCuenta;
   }

   public void setIdCuenta(int idCuenta) {
    this.idCuenta = idCuenta;
   }

   public String getTitular() {
    return titular;
   }

   public void setTitular(String titular) {
    this.titular = titular;
   }

   public String getBanco() {
    return banco;
   }

   public void setBanco(String banco) {
    this.banco = banco;
   }

   public String getCbu() {
    return cbu;
   }

   public void setCbu(String cbu) {
    this.cbu = cbu;
   }

   public Date getFecsaldo() {
    return fecsaldo;
   }

   public void setFecsaldo(Date fecsaldo) {
    this.fecsaldo = fecsaldo;
   }

   public double getSaldoini() {
    return saldoini;
   }

   public void setSaldoini(double saldoini) {
    this.saldoini = saldoini;
   }

   public String getObserv() {
    return observ;
   }

   public void setObserv(String observ) {
    this.observ = observ;
   }

   public String getPeriodo() {
      return periodo;
   }

   public void setPeriodo(String periodo) {
      this.periodo = periodo;
   }

   public double getSaldofin() {
      return saldofin;
   }

   public void setSaldofin(double saldofin) {
      this.saldofin = saldofin;
   };
  public int getCantmovs() {
      return cantmovs;
   }

   public void setCantmovs(int cantmovs) {
      this.cantmovs = cantmovs;
   }
   
}
