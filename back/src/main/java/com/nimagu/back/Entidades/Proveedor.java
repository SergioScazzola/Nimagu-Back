package com.nimagu.back.Entidades;

public class Proveedor {  
        private int     idProv;
        private String  nombre;     
        private String  domicilio;
        private String  localidad;
        private String  telefono;
        private String  email;
        private String  notas;
        private double  saldoini;
        
        public Proveedor(){                   
        }
    
    
        public String getNombre() {
            return nombre;
        }
    
        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getDomicilio() {
            return domicilio;
        }

        public void setDomicilio(String domicilio) {
            this.domicilio = domicilio;
        }

        public String getLocalidad() {
            return localidad;
        }

        public void setLocalidad(String localidad) {
            this.localidad = localidad;
        }

        public String getTelefono() {
            return telefono;
        }

        public void setTelefono(String telefono) {
            this.telefono = telefono;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getNotas() {
            return notas;
        }

        public void setNotas(String notas) {
            this.notas = notas;
        }

        public double getSaldoini() {
            return saldoini;
        }

        public void setSaldoini(double saldoini) {
            this.saldoini = saldoini;
        }


        public int getIdProv() {
            return idProv;
        }


        public void setIdProv(int idProv) {
            this.idProv = idProv;
        }                      
      
      
}
