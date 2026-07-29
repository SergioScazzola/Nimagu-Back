package com.nimagu.back.Repository;

import java.util.List;

import com.nimagu.back.Entidades.CompVta;


public interface NimaguRepository {

List<CompVta> AllCompVtas();
List<CompVta> detalleCyFxFecha(String fechaini,String fechafin);
List<CompVta> detalleCVyFecha(String fechaini,String fechafin);        //Compra/Venta y fecha 
List<CompVta> detalleCliProvyFecha(String fechaini,String fechafin);   // Cli/Prov    y fecha
List<CompVta> detalleCatyFecha(String fechaini,String fechafin);       // categoria   y fecha
List<CompVta> detalleProcyFecha(String fechaini,String fechafin);      // procedencia y fecha
int           getMaxCompVtas();
CompVta       findCompVtaById(int idcomp);
int           saveCompVta(CompVta comp);
int           actualizarCompVta(CompVta comp);   
int           deleteCompVta(int idcomp);



}
