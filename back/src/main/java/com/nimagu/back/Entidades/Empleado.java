package com.Sisbul.ApiRrest.entidades;

public class Empleado {
    private int idEmpleado;
    private String nomEmpleado;
    private String dni;
    private String domicilio;
    private String telefono;
    private String notas;
    private double saldoini;
    
    public Empleado(){
    }
    public Empleado(String nombre){
        this.nomEmpleado = nombre;
    }
    
    public String getNomEmpleado() {
        return nomEmpleado;
    }
    public void setNomEmpleado(String nomEmpleado) {
        this.nomEmpleado = nomEmpleado;
    }
    public String getDni() {
        return dni;
    }
    public void setDni(String dni) {
        this.dni = dni;
    }
    public String getDomicilio() {
        return domicilio;
    }
    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public String getNotas() {
        return notas;
    }
    public void setNotas(String notas) {
        this.notas = notas;
    }
    public int getIdEmpleado() {
        return idEmpleado;
    }
    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }
    public double getSaldoini() {
        return saldoini;
    }
    public void setSaldoini(double saldoini) {
        this.saldoini = saldoini;
    }
    
}
