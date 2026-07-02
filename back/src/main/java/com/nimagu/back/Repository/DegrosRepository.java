package com.nimagu.back.Repository;


import java.util.List;

import com.nimagu.back.Entidades.Categoria;
import com.nimagu.back.Entidades.Cliente;
import com.nimagu.back.Entidades.Cobranza;
import com.nimagu.back.Entidades.CobroComp;
import com.nimagu.back.Entidades.CuentaB;
import com.nimagu.back.Entidades.Dcobxcli;
import com.nimagu.back.Entidades.Detcobro;
import com.nimagu.back.Entidades.Detpago;
import com.nimagu.back.Entidades.Dpagxprov;
import com.nimagu.back.Entidades.Egreso;
import com.nimagu.back.Entidades.Ingreso;
import com.nimagu.back.Entidades.MedioPago;
import com.nimagu.back.Entidades.Pago;
import com.nimagu.back.Entidades.PagoComp;
import com.nimagu.back.Entidades.Procedencia;
import com.nimagu.back.Entidades.Saldoprov;
import com.nimagu.back.Entidades.Salida;
import com.nimagu.back.Entidades.Proveedor;
import com.nimagu.back.Entidades.Saldocli;

          
       
 public interface DegrosRepository {
       
        List<Cliente> AllClientes();
        
        int getMaxClientes();  
        Cliente findClienteById(int nrocliente);
        int saveCliente(Cliente cliente);        
        int actualizarCliente(int idcliente,Cliente cliente);    
        int deleteCliente(int idcliente);
        List<Cliente> findClientesByNombre(String nom); 
        List<Saldocli> getSaldosPorCliente(int ncli);
        int saveSaldoCliente(Saldocli saldoc);
        int actSaldoInicial(Saldocli saldoc);
        Saldocli getSaldoDelCliente(int idcli, int nros);
        int actSaldodelCliente(Saldocli saldoc);
                 
        List<Proveedor> AllProvs();
        int getMaxIdProv();
        Proveedor findProvById(int nroprov);
        int saveProv(Proveedor prov); 
        int actualizarProv(Proveedor prove);   
        int actSaldoIniProv(Saldoprov saldoprov);    
        int deleteProveedor(int idprov);

        List<Cobranza> AllCobranza();
        List<Cobranza> AllCobranzaPorCliente(int idcli);
        int getMaxCobranza();
        Cobranza findCobranzaById(int idcobro);
        int saveCobranza(CobroComp cobroc);
        int actualizarCobranza(Cobranza cobranza);    
        int deleteCobranza(int idcobro);
        List<Dcobxcli> DetCobroPorCliyF(int idcli,String fechi, String fechf);
        List<Detcobro> AllDetCobroPorId(int idcob,int ctadest);
        int getCantDetCobrosPorId(int idcob);
        int saveItemDetCobro(Detcobro detcobro);
        int actualizarItemDetCobro(Detcobro detcobro);    
        int actualizarCtaDestino(int idcob,int iditem,int ctadestino); 
        Detcobro findItemDetCobro(int idcob, int iditem);
       
        //List<Saldoprov> getSaldosPorProv(int nprov);
        //int saveSaldoProv(Saldoprov saldop);
        //int actSaldodelProv(Saldoprov saldop);
        //Saldoprov getSaldoDelProv(int idprov, int nros);
        
        List<Pago> AllPagos();
        List<Pago> AllPagosPorProveedor(int idpro);
        int getMaxPagos();        
        Pago findPagoById(int idpag);
        int savePago(PagoComp pago);
        int actualizarPago(Pago pago);
        int deletePago(int idpag);
        List<Dpagxprov> DetPagoPorProvyF(int idpro,String fechi,String fechf);
        List<Detpago> AllDetPagoPorId(int idpag,int ctadest);
        int getCantDetPagosPorId(int idpag);
        int saveItemDetPago(Detpago detpago);
        int actualizarCtaDestinoPag(int idpag,int iditem,int ctadestino); 
        int actualizarItemDetPago(Detpago detpago);
        Detpago findItemDetPago(int idpag, int iditem);

        List<Ingreso> AllIngresos();        
        int getMaxIngresos();  
        Ingreso findIngresoById(int nroing);
        int saveIngreso(Ingreso ingreso);        
        int actualizarIngreso(int idingreso,Ingreso ingreso);    
        int deleteIngreso(int idingreso);
        List<Ingreso> getIngresosXCliente(int nrocli,int cobrados);  
        List<MedioPago> getMediosPago();                
        List<Categoria> getCategorias(int ingreeg);  
        List<Procedencia> getProcedencias();        

        List<Salida> AllSalidas();
        int getMaxSalidas();  
        Salida findSalidaById(int nroing);
        int saveSalida(Salida salida);        
        int actualizarSalida(int idsalida,Salida salida);    
        int deleteSalida(int idsalida);
        List<Salida> getSalidasXProv(int nroprov, int pagadas);


    }
    

