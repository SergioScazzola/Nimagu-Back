package com.nimagu.back.Entidades;

import java.util.Date;

public class SaldoCta {
    // Saldo inicial de Cuenta Bancaria
    private int    nrosaldo;
    private int    idcuenta;
    private String periodo;   
    private Date   fechasaldo;
    private double saldo;

    public SaldoCta(){}

    public int getNrosaldo() {
        return nrosaldo;
    }

    public void setNrosaldo(int nrosaldo) {
        this.nrosaldo = nrosaldo;
    }

    public int getIdcuenta() {
        return idcuenta;
    }

    public void setIdcuenta(int idcuenta) {
        this.idcuenta = idcuenta;
    }

    public Date getFechasaldo() {
        return fechasaldo;
    }

    public void setFechasaldo(Date fechasaldo) {
        this.fechasaldo = fechasaldo;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

 
}
