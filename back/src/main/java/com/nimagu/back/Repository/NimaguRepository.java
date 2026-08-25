package com.nimagu.back.Repository;

import java.util.List;

import com.nimagu.back.Entidades.Campo;
import com.nimagu.back.Entidades.CompVta;
import com.nimagu.back.Entidades.Gasto;
import com.nimagu.back.Entidades.Hacienda;
import com.nimagu.back.Entidades.MovHacienda;
import com.nimagu.back.Entidades.Producto;
import com.nimagu.back.Entidades.TipoMovH;
import com.nimagu.back.Entidades.TipoProd;


public interface NimaguRepository {

List<CompVta> AllCompVtas();
List<CompVta> detalleCyFxFecha(String fechaini,String fechafin);
List<CompVta> detalleCVyFecha(String fechaini,String fechafin);        //Compra/Venta y fecha 
List<CompVta> detalleCliProvyFecha(String fechaini,String fechafin);   // Cli/Prov    y fecha
List<CompVta> detalleCatyFecha(String fechaini,String fechafin);       // categoria   y fecha
List<CompVta> detalleProcyFecha(String fechaini,String fechafin);      // procedencia y fecha
List<CompVta> detalleporCYV(String fechaini,String fechafin);          // compra y venta
int           getMaxCompVtas();
CompVta       findCompVtaById(int idcomp);
int           saveCompVta(CompVta comp);
int           actualizarCompVta(CompVta comp);   
int           deleteCompVta(int idcomp);

List<Producto> AllProds();
Producto       findProdById(int idprod);
int            saveProd(Producto prod);
int            actualizarProd(Producto prod);    
int            getMaxIdProd();
int            deleteProducto(int idprod);
List<TipoProd> AllTProds();
int            getMaxIdTProd();
int            saveTProd(TipoProd tprod);
int            deleteTProducto(int idtprod);

List<Gasto>    AllGastos();
Gasto          findGastoById(int idgasto);
int            saveGasto(Gasto gasto);
int            actualizarGasto(Gasto gasto);   
int            getMaxIdGasto();
int            deleteGasto(int idgasto);
List<Gasto>    detalleporFecha(String fechaini, String fechafin);
List<Gasto>    detalleporProducto(String fechaini, String fechafin);
List<Gasto>    detalleporTipoProd(String fechaini, String fechafin);
List<Gasto>    detalleporProveedor(String fechaini,String fechafin);

List<Hacienda> AllHacienda();
int            saveHacienda(Hacienda hac);
Hacienda       findHaciendaById(int idhac);
int            getMaxIdHacienda();
int            deleteHacienda(int idhac);

List<TipoMovH> AllTiposMovHacienda();
List<Campo>    AllCampos();
int            saveCampo(Campo campo);
int            actualizarCampo(Campo campo);
Campo          findCampoById(int idcampo);
int            getMaxIdCampo();
int            deleteCampo(int idcampo);
List<MovHacienda> AllMovsHacienda();
List<MovHacienda> detalleMovHxAbCampo(String fechaini, String fechafin);
List<MovHacienda> detalleMovHxFecha(String fechaini, String fechafin);
List<MovHacienda> detalleMovHxThac(String fechaini, String fechafin);
List<MovHacienda> detalleMovHxCampo(String fechaini, String fechafin);
    
int            saveMovH(MovHacienda movh);
MovHacienda    findMovHById(int idmovh);
int            getMaxIdMovH();
int            actualizarMovH(MovHacienda movh);  
int            deleteMovH(int idmovh);  

}
