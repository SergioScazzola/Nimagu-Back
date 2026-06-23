package com.nimagu.back.Entidades;

import java.util.Date;

public class Pago {
   private int     idPago;
   private Date    fecha;
   private int     idProv;
   private String  nomprov;
   private String  nrofactura;
   private double  importe;
   private int     nroegreso;
   private String  observaciones;

   public Pago(){}

   public int getIdPago() {
    return idPago;
   }

   public void setIdPago(int idPago) {
    this.idPago = idPago;
   }

   public Date getFecha() {
    return fecha;
   }

   public void setFecha(Date fecha) {
    this.fecha = fecha;
   }

   public int getIdProv() {
    return idProv;
   }

   public void setIdProv(int idProv) {
    this.idProv = idProv;
   }

   public String getNomprov() {
    return nomprov;
   }

   public void setNomprov(String nomprov) {
    this.nomprov = nomprov;
   }

   public String getNrofactura() {
    return nrofactura;
   }

   public void setNrofactura(String nrofactura) {
    this.nrofactura = nrofactura;
   }

   public double getImporte() {
    return importe;
   }

   public void setImporte(double importe) {
    this.importe = importe;
   }

   public int getNroegreso() {
    return nroegreso;
   }

   public void setNroegreso(int nroegreso) {
    this.nroegreso = nroegreso;
   }

   public String getObservaciones() {
    return observaciones;
   }

   public void setObservaciones(String observaciones) {
    this.observaciones = observaciones;
   }

   
}
