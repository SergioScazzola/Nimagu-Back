package com.nimagu.back.Entidades;

import java.util.Date;

public class Dcobxcli {
     private   int    idCobro;
     private   Date   fechaCobro;
     private   int    idCliente;
     private   String nomcliente;
     private   String nrofactura;
     private   double importeCobro;
     private   int    nroitem;
     private   String nmpago;
     private   Date   fechaDetalle;
     private   String nrompago;
     private   String banco;
     private   Date   fecvto;
     private   double importeDetalle;
     private   int    ctadest;
     private   String comentario;

     public Dcobxcli(){}

     public int getIdCobro() {
         return idCobro;
     }

     public Date getFechaCobro() {
         return fechaCobro;
     }

     public int getIdCliente() {
         return idCliente;
     }

     public String getNomcliente() {
         return nomcliente;
     }

     public String getNrofactura() {
         return nrofactura;
     }

     public double getImporteCobro() {
         return importeCobro;
     }

     public int getNroitem() {
         return nroitem;
     }

     public String getNmpago() {
         return nmpago;
     }

     public Date getFechaDetalle() {
         return fechaDetalle;
     }

     public String getNrompago() {
         return nrompago;
     }

     public String getBanco() {
         return banco;
     }

     public Date getFecvto() {
         return fecvto;
     }

     public double getImporteDetalle() {
         return importeDetalle;
     }

     public int getCtadest() {
         return ctadest;
     }

     public String getComentario() {
         return comentario;
     }

     
}
