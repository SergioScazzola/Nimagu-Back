package com.nimagu.back.Entidades;

import java.util.Date;

public class Cobranza {
   private int     idCobro;
   private Date    fecha;
   private int     idCliente;
   private String  nomcliente;
   private String  nrofactura;
   private double  importe;
   private int     nroventa;
   private String  observaciones;

   public Cobranza(){}

   public int getIdCliente() {
    return idCliente;
}

   public String getNrofactura() {
      return nrofactura;
   }

   public void setNrofactura(String nrofactura) {
      this.nrofactura = nrofactura;
   }

   public int getNroventa() {
      return nroventa;
   }

   public void setNroventa(int nroventa) {
      this.nroventa = nroventa;
   }


   public void setIdCliente(int idCliente) {
    this.idCliente = idCliente;
   }

   public String getNomcliente() {
    return nomcliente;
   }

   public void setNomcliente(String nomcliente) {
    this.nomcliente = nomcliente;
   }

   public int getIdCobro() {
    return idCobro;
   }
   public void setIdCobro(int idCobro) {
    this.idCobro = idCobro;
   }
   public Date getFecha() {
    return fecha;
   }
   public void setFecha(Date fecha) {
    this.fecha = fecha;
   }
 
   public double getImporte() {
    return importe;
   }
   public void setImporte(double importe) {
    this.importe = importe;
   }
 
   public String getObservaciones() {
    return observaciones;
   }
   public void setObservaciones(String observaciones) {
    this.observaciones = observaciones;
   }
   
}
