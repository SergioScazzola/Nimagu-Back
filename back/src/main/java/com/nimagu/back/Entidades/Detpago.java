package com.nimagu.back.Entidades;

import java.util.Date;

public class Detpago {
   private int      idPago;
   private int      nroitem;
   private int      idmpago;
   private String   nmpago;
   private Date     fecha;
   private String   nrompago;
   private String   banco;
   private Date     fecvto;
   private double   importe;
   private int      ctadest;
   private String   comentario; 

   public Detpago(){}

   public int getIdPago() {
    return idPago;
   }

   public void setIdPago(int idPago) {
    this.idPago = idPago;
   }

   public int getNroitem() {
    return nroitem;
   }

   public void setNroitem(int nroitem) {
    this.nroitem = nroitem;
   }

   public int getIdmpago() {
    return idmpago;
   }

   public void setIdmpago(int idmpago) {
    this.idmpago = idmpago;
   }

   public String getNmpago() {
    return nmpago;
   }

   public void setNmpago(String nmpago) {
    this.nmpago = nmpago;
   }

   public Date getFecha() {
    return fecha;
   }

   public void setFecha(Date fecha) {
    this.fecha = fecha;
   }

   public String getNrompago() {
    return nrompago;
   }

   public void setNrompago(String nrompago) {
    this.nrompago = nrompago;
   }

   public String getBanco() {
    return banco;
   }

   public void setBanco(String banco) {
    this.banco = banco;
   }

   public Date getFecvto() {
    return fecvto;
   }

   public void setFecvto(Date fecvto) {
    this.fecvto = fecvto;
   }

   public double getImporte() {
    return importe;
   }

   public void setImporte(double importe) {
    this.importe = importe;
   }

   public int getCtadest() {
    return ctadest;
   }

   public void setCtadest(int ctadest) {
    this.ctadest = ctadest;
   }

   public String getComentario() {
    return comentario;
   }

   public void setComentario(String comentario) {
    this.comentario = comentario;
   }

   
}
