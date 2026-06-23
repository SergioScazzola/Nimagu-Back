package com.nimagu.back.Repository;


import java.util.List;




import com.nimagu.back.Entidades.Cliente;
import com.nimagu.back.Entidades.Cobranza;
import com.nimagu.back.Entidades.Detcobro;
import com.nimagu.back.Entidades.Detpago;
import com.nimagu.back.Entidades.Pago;
import com.nimagu.back.Entidades.Saldoprov;

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
        int saveCobranza(Cobranza cobranza);
        int actualizarCobranza(Cobranza cobranza);    
        int deleteCobranza(int idcobro);
        List<Detcobro> AllDetCobroPorId(int idcob);
        int getCantDetCobrosPorId(int idcob);
        int saveItemDetCobro(Detcobro detcobro);
        int actualizarItemDetCobro(Detcobro detcobro);    
        Detcobro findItemDetCobro(int idcob, int iditem);
       
        List<Saldoprov> getSaldosPorProv(int nprov);
        int saveSaldoProv(Saldoprov saldop);
        //int actSaldodelProv(Saldoprov saldop);
        //Saldoprov getSaldoDelProv(int idprov, int nros);
        
        List<Pago> AllPagos();
        List<Pago> AllPagosPorProveedor(int idpro);
        int getMaxPagos();        
        Pago findPagoById(int idpag);
        int savePago(Pago pago);
        int actualizarPago(Pago pago);
        int deletePago(int idpag);
        List<Detpago> AllDetPagoPorId(int idpag);
        int getCantDetPagosPorId(int idpag);
        int saveItemDetPago(Detpago detpago);
        int actualizarItemDetPago(Detpago detpago);
        Detpago findItemDetPago(int idpag, int iditem);

    }
    

