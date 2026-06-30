package com.nimagu.back.Repository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nimagu.back.Entidades.Categoria;
import com.nimagu.back.Entidades.Cliente;
import com.nimagu.back.Entidades.Cobranza;
import com.nimagu.back.Entidades.CobroComp;

import com.nimagu.back.Entidades.Dcobxcli;
import com.nimagu.back.Entidades.Detcobro;
import com.nimagu.back.Entidades.Detpago;
import com.nimagu.back.Entidades.Dpagxprov;

import com.nimagu.back.Entidades.Ingreso;
import com.nimagu.back.Entidades.MedioPago;
import com.nimagu.back.Entidades.Pago;
import com.nimagu.back.Entidades.Procedencia;
import com.nimagu.back.Entidades.Proveedor;
import com.nimagu.back.Entidades.Saldocli;

import com.nimagu.back.Entidades.Saldoprov;
import com.nimagu.back.Entidades.Salida;

    
    @Repository
    public class JdbcDegrosRepository implements DegrosRepository {

      @Autowired
      private JdbcTemplate jdbcTemplate;
     
      
    // CLIENTES
    @Override
    public List<Cliente> AllClientes() {   
      String selec = "SELECT * FROM clientes ORDER BY nombre";
      return jdbcTemplate.query(selec, BeanPropertyRowMapper.newInstance(Cliente.class));
    }

    
    @Override
    public int getMaxClientes(){
      String consulta = "SELECT MAX(idCliente) FROM clientes";
   
      Object obj = jdbcTemplate.queryForObject(consulta,Integer.class);    
      if (obj==null){
        return 0;
      } else {
        return ((int)obj);
      }         
    }
    @Override
    public Cliente findClienteById(int id) {
      String q = "SELECT * FROM clientes WHERE idCliente=?";
      try {
        Cliente cliente = jdbcTemplate.queryForObject(q,
            BeanPropertyRowMapper.newInstance(Cliente.class), id);          
        return cliente;
      } catch (IncorrectResultSizeDataAccessException e) {
        return null;
      }
    }

    @Override
    public int saveCliente(Cliente cliente){
    // Graba nuevo Cliente 
        return jdbcTemplate.update("INSERT INTO clientes(idCliente,nombre,telefono,contacto,cuit,notas,saldoini) "+
                                   "VALUES(?,?,?,?,?,?,?)",
            new Object[] { cliente.getIdCliente(),cliente.getNombre(),cliente.getTelefono(),cliente.getContacto(),cliente.getCuit(),
                          cliente.getNotas(),cliente.getSaldoini()});    
    }
        
    @Override
    public int actualizarCliente(int idcliente,Cliente cliente){    
    int resu = 0;
    try {                   
        resu = jdbcTemplate.update("UPDATE clientes SET nombre=?,telefono=?,contacto=?,cuit=?,notas=?,"+
                                   "saldoini=? WHERE idCliente=?",
                                 
            new Object[] { cliente.getNombre(),cliente.getTelefono(),cliente.getContacto(),cliente.getCuit(),
                           cliente.getNotas(),cliente.getSaldoini(),cliente.getIdCliente()});
      } catch (IncorrectResultSizeDataAccessException e) {
        return -3;
    }
    return resu; 
    }
    @Override
    public int deleteCliente(int idcliente){
      int resu = 0;
      try {
        resu = jdbcTemplate.update("DELETE FROM clientes WHERE idCliente="+idcliente);
      } catch (DataAccessException dae){
        resu = -5;   
      }
      return resu;
    }    
    @Override
    public List<Cliente> findClientesByNombre(String nom ){
      String selec = "SELECT * FROM clientes WHERE nombre LIKE '%"+nom+"%' ORDER BY nombre";

      return jdbcTemplate.query(selec, new ClienteRowMapper());      
    } 
     @Override
    // Devuelve todos los saldos registrados del cliente ordenados por fecha
    public List<Saldocli> getSaldosPorCliente(int nrocliente) {   
      String selec = "SELECT * FROM saldoscli WHERE idCliente=? ORDER BY fecha";
      return jdbcTemplate.query(selec,BeanPropertyRowMapper.newInstance(Saldocli.class),nrocliente);
    }
   @Override
    public int saveSaldoCliente(Saldocli saldoc){
    // Graba un nuevo saldo para el Cliente
        return jdbcTemplate.update("INSERT INTO saldoscli(idCliente,nrosaldo,fecha,saldo) "+
                                   "VALUES(?,?,?,?)",
            new Object[] { saldoc.getIdCliente(),saldoc.getNrosaldo(),
                           saldoc.getFecha(),saldoc.getSaldo()});    
    }

 @Override
    public Saldocli  getSaldoDelCliente(int idcli, int nros){
      String q = "SELECT * FROM saldoscli WHERE idCliente=? AND nrosaldo=?";
      try {
        Saldocli saldocli = jdbcTemplate.queryForObject(q,
            BeanPropertyRowMapper.newInstance(Saldocli.class), idcli,nros);          
        return saldocli;
      } catch (IncorrectResultSizeDataAccessException e) {
        return null;
      }
    }
    @Override
    // actualiza el saldo inicial del Cliente en la tabla "clientes"
    public int actSaldoInicial(Saldocli saldoc){
    
    int resu = 0;
    try {                   
        resu = jdbcTemplate.update("UPDATE clientes SET saldoini=? WHERE idCliente=?",
                                 
            new Object[] { saldoc.getSaldo(),saldoc.getIdCliente()});
      } catch (IncorrectResultSizeDataAccessException e) {
        return -3;
    }
    return resu; 
    }

 @Override
    // actualiza el saldo un saldo del Cliente en la tabla saldoscli"
    public int actSaldodelCliente(Saldocli saldoc){
    
    int resu = 0;
    try {                   
        resu = jdbcTemplate.update("UPDATE saldoscli SET fecha=?,saldo=? WHERE idCliente=? AND nrosaldo=?",
                                 
            new Object[] { saldoc.getFecha(),saldoc.getSaldo(),saldoc.getIdCliente(),saldoc.getNrosaldo()});
      } catch (IncorrectResultSizeDataAccessException e) {
        return -3;
    }
    return resu; 
    }


    // PROVEEDORES
     // PROVEEDORES
   @Override
   public List<Proveedor> AllProvs(){
      String selec = "SELECT * FROM proveedores ORDER BY nombre ASC";
      return jdbcTemplate.query(selec, BeanPropertyRowMapper.newInstance(Proveedor.class));
   }
   @Override
   public Proveedor findProvById(int nroprov){
    String q = "SELECT * FROM  proveedores WHERE idProv=?";
    try {
      Proveedor prove  = jdbcTemplate.queryForObject(q,
          BeanPropertyRowMapper.newInstance(Proveedor.class), nroprov);          
      return prove;
    } catch (IncorrectResultSizeDataAccessException e) {
      return null;
    }
   }
  
   public int saveProv(Proveedor prov){
   // Graba nuevo registro de Proveedor 
     return jdbcTemplate.update("INSERT INTO proveedores(idProv,nombre,domicilio,localidad,telefono,"+
                                "email,notas,saldoini) "+
                                "VALUES(?,?,?,?,?,?,?,?)",
                                    
         new Object[] { prov.getIdProv(),prov.getNombre(),prov.getDomicilio(),prov.getLocalidad(),prov.getTelefono(),
                        prov.getEmail(),prov.getNotas(),prov.getSaldoini(),
                         });    
   }
   public int actualizarProv(Proveedor prove){
   int resu = 0;
   try {
    resu = jdbcTemplate.update("UPDATE proveedores SET nombre=?,domicilio=?,localidad=?,telefono=?,"+
                                "email=?,notas=?,saldoini=? WHERE idProv=?",                                
                                    
         new Object[] { prove.getNombre(),prove.getDomicilio(),prove.getLocalidad(),prove.getTelefono(),
                        prove.getEmail(),prove.getNotas(),prove.getSaldoini(),prove.getIdProv()
                         });    

   } catch (IncorrectResultSizeDataAccessException e) {
     return -3;
   }
   return resu;   
   }
   public int actSaldoIniProv(Saldoprov saldoprov){
   // actualiza el saldo inicial del Proveedor en la tabla "proveedores"       
    int resu = 0;
    try {                   
        resu = jdbcTemplate.update("UPDATE proveedores SET saldoini=? WHERE idProv=?",
                                 
            new Object[] { saldoprov.getSaldo(),saldoprov.getIdProv()});
      } catch (IncorrectResultSizeDataAccessException e) {
        return -3;
    }
    return resu; 
    }
  @Override
  public int getMaxIdProv(){
    String consulta = "SELECT MAX(idProv) FROM proveedores";
 
    Object obj = jdbcTemplate.queryForObject(consulta,Integer.class);    
    if (obj==null){
      return 0;
    } else {
      return ((int)obj);
    }         
  } 
  @Override
  public int deleteProveedor(int idprov){
   int resu = 0;
   try {
     resu = jdbcTemplate.update("DELETE FROM proveedores WHERE idProv=?",idprov);
   } catch (DataAccessException dae){
     resu = -5;   
   }
   return resu;
 }

 // COBRANZA de Clientes

   @Override
   public List<Cobranza> AllCobranza() {   
     String selec = "SELECT * FROM cobranza ORDER BY fecha DESC";
     return jdbcTemplate.query(selec, BeanPropertyRowMapper.newInstance(Cobranza.class));
   }

  
   @Override
   public List<Cobranza> AllCobranzaPorCliente(int nrocli) {   
     String selec = "SELECT * FROM cobranza WHERE idCliente=? ORDER BY fecha DESC";
     return jdbcTemplate.query(selec, BeanPropertyRowMapper.newInstance(Cobranza.class),nrocli);
   }
   @Override
    public  List<Dcobxcli> DetCobroPorCliyF(int idcli,String fechi, String fechf){
      String selec = "SELECT " + 
                "c.idCobro," + 
                "c.fecha AS fechac,"+
                "c.idCliente,"+ 
                "c.nomcliente,"+
                "c.nrofactura,"+
                "c.importe AS importec,"+              
                "d.nroitem," +                 
                "d.nmpago,"+
                "d.fecha AS fechad,"+
                "d.nrompago,"+
                "d.banco,"+
                "d.fecvto,"+
                "d.importe AS imported,"+
                "d.ctadest,"+
                "d.comentario "+
                "FROM cobranza c "+
                "INNER JOIN detcobro d "+
                "ON c.idCobro = d.idCobro "+ 
                "WHERE c.idCliente = ? " +
                "AND c.fecha BETWEEN ? AND ? " +                
                "ORDER BY c.fecha, c.idCobro, d.nroitem";
      return jdbcTemplate.query(selec,BeanPropertyRowMapper.newInstance(Dcobxcli.class),idcli,fechi,fechf);          
    }
   @Override
   public int getMaxCobranza(){
     String consulta = "SELECT MAX(idCobro) FROM cobranza";
  
     Object obj = jdbcTemplate.queryForObject(consulta,Integer.class);    
     if (obj==null){
       return 0;
     } else {
       return ((int)obj);
     }         
   }
   @Override
   public Cobranza findCobranzaById(int nrocob){
     String q = "SELECT * FROM  cobranza WHERE idCobro=?";
     try {
       Cobranza cobranza  = jdbcTemplate.queryForObject(q,
           BeanPropertyRowMapper.newInstance(Cobranza.class), nrocob);          
       return cobranza;
     } catch (IncorrectResultSizeDataAccessException e) {
       return null;
     }
   } 

  @Override
  @Transactional
  public int saveCobranza(CobroComp cobroc){
     // Graba nueva cobranza (Cabecera y detalle)
    // Cabecera de Cobranza -INSERT
    int resu = 0;
    resu =  jdbcTemplate.update("INSERT INTO cobranza(idCobro,fecha,idCliente,nomcliente,nrofactura,importe,"+
                                 "nroventa,observaciones) "+
                                 "VALUES(?,?,?,?,?,?,?,?)",
          new Object[] { cobroc.getCabcob().getIdCobro(),cobroc.getCabcob().getFecha(),
                         cobroc.getCabcob().getIdCliente(),cobroc.getCabcob().getNomcliente(),
                         cobroc.getCabcob().getNrofactura(),cobroc.getCabcob().getImporte(),
                         cobroc.getCabcob().getNroventa(),cobroc.getCabcob().getObservaciones() });  
    
    // Detalle de Cobranza - INSERT
     for (var i=0;i<cobroc.getDetcob().length;i++){
       resu =  jdbcTemplate.update("INSERT INTO detcobro(idCobro,nroitem,idmpago,nmpago,fecha,nrompago,banco,"+
                                  " fecvto,importe,ctadest,comentario) VALUES(?,?,?,?,?,?,?,?,?,?,?)",
           new Object[] { cobroc.getDetcob()[i].getIdCobro(),cobroc.getDetcob()[i].getNroitem(),
                          cobroc.getDetcob()[i].getIdmpago(),cobroc.getDetcob()[i].getNmpago(),
                          cobroc.getDetcob()[i].getFecha(),cobroc.getDetcob()[i].getNrompago(),
                          cobroc.getDetcob()[i].getBanco(),cobroc.getDetcob()[i].getFecvto(),
                          cobroc.getDetcob()[i].getImporte(),cobroc.getDetcob()[i].getCtadest(),
                          cobroc.getDetcob()[i].getComentario() });    
       
     };
     // Actualiza el "idcobro" en la tabla de ingresos
     resu = jdbcTemplate.update("UPDATE ingresos SET idcobro=? "+
                                   "WHERE idingre=?",
                                 
            new Object[] {cobroc.getCabcob().getIdCobro(),cobroc.getCabcob().getNroventa()});

    
   return resu;
    
  }

  @Override
  public int actualizarCobranza(Cobranza cobranza){
  
  int resu = 0;
  try {                   
      resu = jdbcTemplate.update("UPDATE cobranza SET fecha=?,idCliente=?,nomcliente=?,nrofactura=?,importe=?,"+
                                 "nroventa=?,observaciones=? WHERE idCobro=?",
          new Object[] { cobranza.getFecha(),cobranza.getIdCliente(),cobranza.getNomcliente(),
                         cobranza.getNrofactura(),cobranza.getImporte(),cobranza.getNroventa(),
                         cobranza.getObservaciones(),cobranza.getIdCobro() });    
    } catch (IncorrectResultSizeDataAccessException e) {
      return -3;
  }
  return resu; 
  }
  @Override
  public int deleteCobranza(int idcobro){
    int resu = 0;
    try {
      resu = jdbcTemplate.update("DELETE FROM cobranza WHERE idCobro="+idcobro);
    } catch (DataAccessException dae){
      resu = -5;   
    }
    return resu;
  }

  // DETALLE DE COBRANZAS

 /* @Override
  public List<InfoDetCobro> infoDetCobranza(String fecini, String fecfin){
    String selec = "SELECT  cobranza.idCobro,"+
                   "cobranza.fecha,cobranza.idCliente,cobranza.nomcliente,"+
                   "cobranza.nrofactura,cobranza.importe AS impcobro,"+
                   "detcobro.nmpago,detcobro.nrompago,detcobro.banco,detcobro.fecha AS fecemi,detcobro.fecvto,"+
                   "detcobro.importe AS impitem,detcobro.comentario AS coment "+
                   "FROM cobranza JOIN detcobro ON cobranza.idCobro = detcobro.idCobro "+
	                 "WHERE cobranza.fecha BETWEEN ? AND ? "+		               
	                 "ORDER BY nomcliente ASC, idCobro ASC";
                   
    return jdbcTemplate.query(selec, BeanPropertyRowMapper.newInstance(InfoDetCobro.class),fecini,fecfin);

  }*/
  @Override
  public List<Detcobro> AllDetCobroPorId(int nrocobro, int ctadestino) {   
    String selec = "";
    if (ctadestino==0){// devolver lo NO transferido
       selec = "SELECT * FROM detcobro WHERE idCobro=? AND ctadest=0 ORDER BY nroitem";
    } else { // devolver todo
      selec = "SELECT * FROM detcobro WHERE idCobro=? ORDER BY nroitem";
    }    
    return jdbcTemplate.query(selec, BeanPropertyRowMapper.newInstance(Detcobro.class),nrocobro);
  }

  @Override
   public int getCantDetCobrosPorId(int nrocobro){
     String consulta = "SELECT MAX(idCobro) FROM detcobro WHERE idCobro="+nrocobro;
  
     Object obj = jdbcTemplate.queryForObject(consulta,Integer.class);    
     if (obj==null){
       return 0;
     } else {
       return ((int)obj);
     }         
   }
   
   @Override
   public int saveItemDetCobro(Detcobro detcobro){
   // Graba nuevo item de cobro
       return jdbcTemplate.update("INSERT INTO detcobro(idCobro,nroitem,idmpago,nmpago,fecha,nrompago,banco,"+
                                  " fecvto,importe,ctadest,comentario) VALUES(?,?,?,?,?,?,?,?,?,?,?)",
           new Object[] { detcobro.getIdCobro(),detcobro.getNroitem(),detcobro.getIdmpago(),detcobro.getNmpago(),
                          detcobro.getFecha(),detcobro.getNrompago(),detcobro.getBanco(),detcobro.getFecvto(),
                          detcobro.getImporte(),detcobro.getCtadest(),detcobro.getComentario() });    
   }

   @Override
   public int actualizarItemDetCobro(Detcobro detcobro){
   
   int resu = 0;
   try {                   
       resu = jdbcTemplate.update("UPDATE detcobro SET idmpago=?,nmpago=?,fecha=?,nrompago=?,banco=?,"+
                                  "fecvto=?,importe=?,ctadest,comentario=? WHERE idCobro=? AND nroitem=?",
           new Object[] { detcobro.getIdmpago(),detcobro.getNmpago(),detcobro.getFecha(),detcobro.getNrompago(),
                          detcobro.getBanco(),detcobro.getFecvto(),detcobro.getImporte(),detcobro.getCtadest(),
                          detcobro.getComentario(),detcobro.getIdCobro(),detcobro.getNroitem() });    
     } catch (IncorrectResultSizeDataAccessException e) {
       return -3;
   }
   return resu; 
   }
   @Override
    public int actualizarCtaDestino(int idcob,int iditem,int ctadestino){
      int resu = 0;
      try {                   
         resu = jdbcTemplate.update("UPDATE detcobro SET ctadest=? WHERE idCobro=? AND nroitem=?",
            new Object[] { ctadestino,idcob,iditem});    
         return resu;
      } catch (IncorrectResultSizeDataAccessException e) {
        return -3;
   }

    }
   @Override
   public Detcobro findItemDetCobro(int nrocobro,int nroit){
     String q = "SELECT * FROM  detcobro WHERE idCobro=? AND nroitem=?";
     try {
       Detcobro detcobro  = jdbcTemplate.queryForObject(q,
           BeanPropertyRowMapper.newInstance(Detcobro.class), nrocobro,nroit);          
       return detcobro;
     } catch (IncorrectResultSizeDataAccessException e) {
       return null;
     }
   } 

   // PAGOS a Proveedores

   @Override
   public List<Pago> AllPagos() {   
     String selec = "SELECT * FROM pagos ORDER BY fecha DESC";
     return jdbcTemplate.query(selec, BeanPropertyRowMapper.newInstance(Pago.class));
   }

  
   @Override
   public List<Pago> AllPagosPorProveedor(int nrocli) {   
     String selec = "SELECT * FROM pagos WHERE idProv=? ORDER BY fecha DESC";
     return jdbcTemplate.query(selec, BeanPropertyRowMapper.newInstance(Pago.class),nrocli);
   }

   @Override
   public int getMaxPagos(){
     String consulta = "SELECT MAX(idPago) FROM pagos";
  
     Object obj = jdbcTemplate.queryForObject(consulta,Integer.class);    
     if (obj==null){
       return 0;
     } else {
       return ((int)obj);
     }         
   }
   @Override
   public Pago findPagoById(int nropag){
     String q = "SELECT * FROM  pagos WHERE idPago=?";
     try {
       Pago pago  = jdbcTemplate.queryForObject(q,
           BeanPropertyRowMapper.newInstance(Pago.class), nropag);          
       return pago;
     } catch (IncorrectResultSizeDataAccessException e) {
       return null;
     }
   } 

  @Override
  public int savePago(Pago pago){
  // Graba nueva cobranza 
      return jdbcTemplate.update("INSERT INTO pagos(idPago,fecha,idProv,nomprov,nrofactura,importe,"+
                                 "nroegreso,observaciones) "+
                                 "VALUES(?,?,?,?,?,?,?,?)",
          new Object[] { pago.getIdPago(),pago.getFecha(),pago.getIdProv(),pago.getNomprov(),
                         pago.getNrofactura(),pago.getImporte(),pago.getNroegreso(),
                         pago.getObservaciones()
          });    
  }

  @Override
  public int actualizarPago(Pago pago){
  
  int resu = 0;
  try {                   
      resu = jdbcTemplate.update("UPDATE pagos SET fecha=?,idProv=?,nomprov=?,nrofactura=?,importe=?,"+
                                 "nroegreso=?,observaciones=? WHERE idPago=?",
          new Object[] { pago.getFecha(),pago.getIdProv(),pago.getNomprov(),
                         pago.getNrofactura(),pago.getImporte(),pago.getNroegreso(),
                         pago.getObservaciones(),pago.getIdPago() });    
    } catch (IncorrectResultSizeDataAccessException e) {
      return -3;
  }
  return resu; 
  }
  @Override
  public int deletePago(int idpago){
    int resu = 0;
    try {
      resu = jdbcTemplate.update("DELETE FROM pagos WHERE idPago="+idpago);
    } catch (DataAccessException dae){
      resu = -5;   
    }
    return resu;
  }
@Override
public List<Dpagxprov> DetPagoPorProvyF(int idpro,String fechi,String fechf){
      String selec = "SELECT " + 
                "c.idPago," + 
                "c.fecha AS fechac,"+
                "c.idProv,"+ 
                "c.nomprov,"+
                "c.nrofactura,"+
                "c.importe AS importec,"+              
                "d.nroitem," +                 
                "d.nmpago,"+
                "d.fecha AS fechad,"+
                "d.nrompago,"+
                "d.banco,"+
                "d.fecvto,"+
                "d.importe AS imported,"+
                "d.ctadest,"+
                "d.comentario "+
                "FROM pagos c "+
                "INNER JOIN detpago d "+
                "ON c.idPago = d.idPago "+ 
                "WHERE c.idProv = ? " +
                "AND c.fecha BETWEEN ? AND ? " +                
                "ORDER BY c.fecha, c.idPago, d.nroitem";
      return jdbcTemplate.query(selec,BeanPropertyRowMapper.newInstance(Dpagxprov.class),idpro,fechi,fechf);          
 }

  // DETALLE DE PAGOS

 /* @Override
  public List<InfoDetCobro> infoDetCobranza(String fecini, String fecfin){
    String selec = "SELECT  cobranza.idCobro,"+
                   "cobranza.fecha,cobranza.idCliente,cobranza.nomcliente,"+
                   "cobranza.nrofactura,cobranza.importe AS impcobro,"+
                   "detcobro.nmpago,detcobro.nrompago,detcobro.banco,detcobro.fecha AS fecemi,detcobro.fecvto,"+
                   "detcobro.importe AS impitem,detcobro.comentario AS coment "+
                   "FROM cobranza JOIN detcobro ON cobranza.idCobro = detcobro.idCobro "+
	                 "WHERE cobranza.fecha BETWEEN ? AND ? "+		               
	                 "ORDER BY nomcliente ASC, idCobro ASC";
                   
    return jdbcTemplate.query(selec, BeanPropertyRowMapper.newInstance(InfoDetCobro.class),fecini,fecfin);

  }*/
  @Override
  public List<Detpago> AllDetPagoPorId(int nropago) {   
    String selec = "SELECT * FROM detpago WHERE idPago=? ORDER BY nroitem";
    return jdbcTemplate.query(selec, BeanPropertyRowMapper.newInstance(Detpago.class),nropago);
  }

  @Override
   public int getCantDetPagosPorId(int nropago){
     String consulta = "SELECT MAX(idPago) FROM detpago WHERE idPago="+nropago;
  
     Object obj = jdbcTemplate.queryForObject(consulta,Integer.class);    
     if (obj==null){
       return 0;
     } else {
       return ((int)obj);
     }         
   }
   
   @Override
   public int saveItemDetPago(Detpago detpago){
   // Graba nuevo item de cobro
       return jdbcTemplate.update("INSERT INTO detpago(idPago,nroitem,idmpago,nmpago,fecha,nrompago,banco,"+
                                  " fecvto,importe,ctadest,comentario) VALUES(?,?,?,?,?,?,?,?,?,?,?)",
           new Object[] {detpago.getIdPago(),detpago.getNroitem(),detpago.getIdmpago(),
                         detpago.getNmpago(),detpago.getFecha(),detpago.getNrompago(),
                         detpago.getBanco(),detpago.getFecvto(),detpago.getImporte(),
                         detpago.getCtadest(),detpago.getComentario()
            });    
   }
   @Override
    public int actualizarCtaDestinoPag(int idpag,int iditem,int ctadestino){
      int resu = 0;
      try {                   
         resu = jdbcTemplate.update("UPDATE detpago SET ctadest=? WHERE idPago=? AND nroitem=?",
            new Object[] { ctadestino,idpag,iditem});    
         return resu;
      } catch (IncorrectResultSizeDataAccessException e) {
        return -3;
      }
   }
   @Override
   public int actualizarItemDetPago(Detpago detpago){
   
   int resu = 0;
   try {                   
       resu = jdbcTemplate.update("UPDATE detpago SET idmpago=?,nmpago=?,fecha=?,nrompago=?,banco=?,"+
                                  "fecvto=?,importe=?,ctadest,comentario=? WHERE idPago=? AND nroitem=?",
           new Object[] { detpago.getIdPago(),detpago.getNroitem(),detpago.getIdmpago(),
                          detpago.getNmpago(),detpago.getFecha(),detpago.getNrompago(),
                          detpago.getBanco(),detpago.getFecvto(),detpago.getImporte(),
                          detpago.getCtadest(),detpago.getComentario() });    
     } catch (IncorrectResultSizeDataAccessException e) {
       return -3;
   }
   return resu; 
   }

   @Override
   public Detpago findItemDetPago(int nropago,int nroit){
     String q = "SELECT * FROM  detpago WHERE idPago=? AND nroitem=?";
     try {
       Detpago detpago  = jdbcTemplate.queryForObject(q,
           BeanPropertyRowMapper.newInstance(Detpago.class), nropago,nroit);          
       return detpago;
     } catch (IncorrectResultSizeDataAccessException e) {
       return null;
     }
   } 
    
// INGRESOS de clientes

  @Override
    public List<Ingreso> AllIngresos() {   
      String selec = "SELECT * FROM ingresos ORDER BY fecha ASC";
      return jdbcTemplate.query(selec, BeanPropertyRowMapper.newInstance(Ingreso.class));
    }

    
    @Override
    public int getMaxIngresos(){
      String consulta = "SELECT MAX(idingre) FROM ingresos";
   
      Object obj = jdbcTemplate.queryForObject(consulta,Integer.class);    
      if (obj==null){
        return 0;
      } else {
        return ((int)obj);
      }         
    }
    @Override
    public Ingreso findIngresoById(int id) {
      String q = "SELECT * FROM ingresos WHERE idingre=?";
      try {
        Ingreso ingreso = jdbcTemplate.queryForObject(q,
            BeanPropertyRowMapper.newInstance(Ingreso.class), id);          
        return ingreso;
      } catch (IncorrectResultSizeDataAccessException e) {
        return null;
      }
    }

    @Override
    public int saveIngreso(Ingreso ing){
    // Graba nuevo Cliente 
        return jdbcTemplate.update("INSERT INTO ingresos(idingre,fecha,idcliente,ncliente,"+
                                   "nroliq,idcat,categoria,cantidad,tkilos,precioun,importe,"+
                                   "proced,idcobro,observ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
      
            new Object[] {ing.getIdingre(),ing.getFecha(),ing.getIdcliente(),ing.getNcliente(),
                          ing.getNroliq(),ing.getIdcat(),ing.getCategoria(),ing.getCantidad(),
                          ing.getTkilos(),ing.getPrecioun(),ing.getImporte(),ing.getProced(),
                          ing.getIdcobro(),ing.getObserv()
            });    
    }

    @Override
    public int actualizarIngreso(int idingreso, Ingreso ing){    
    int resu = 0;
    try {                   
        resu = jdbcTemplate.update("UPDATE ingresos SET fecha=?,idcliente=?,ncliente=?,"+
                                   "nroliq=?,idcat=?,categoria=?,cantidad=?,tkilos=?,precioun=?,"+
                                   "importe=?,proced=?,idcobro=?,observ=? "+
                                   "WHERE idingre=?",
                                 
            new Object[] {ing.getFecha(),ing.getIdcliente(),ing.getNcliente(),
                          ing.getNroliq(),ing.getIdcat(),ing.getCategoria(),ing.getCantidad(),
                          ing.getTkilos(),ing.getPrecioun(),ing.getImporte(),ing.getProced(),
                          ing.getIdcobro(),ing.getObserv(),ing.getIdingre() });
      } catch (IncorrectResultSizeDataAccessException e) {
        return -3;
    }
    return resu; 
    }
    @Override
    public int deleteIngreso(int idingreso){
      int resu = 0;
      try {
        resu = jdbcTemplate.update("DELETE FROM ingresos WHERE idingre="+idingreso);
      } catch (DataAccessException dae){
        resu = -5;   
      }
      return resu;
    }    
    @Override
    public List<Ingreso> getIngresosXCliente(int nrocli){
      String selec = "SELECT * FROM ingresos WHERE idcliente=? ORDER BY fecha DESC LIMIT 30";
      return jdbcTemplate.query(selec,BeanPropertyRowMapper.newInstance(Ingreso.class),nrocli);

     }
    
   
    @Override
    public List<MedioPago> getMediosPago(){
      String selec = "SELECT * FROM mediospago ORDER BY mediopago ASC";
      return jdbcTemplate.query(selec,BeanPropertyRowMapper.newInstance(MedioPago.class));
    }

    @Override
    public List<Categoria> getCategorias(int ingegre){
      String selec = "";
      if (ingegre==0){
        selec = "SELECT * FROM categorias ORDER BY nombre ASC";
         return jdbcTemplate.query(selec,BeanPropertyRowMapper.newInstance(Categoria.class));
      } else {
        selec = "SELECT * FROM categorias WHERE ingeg=? ORDER BY nombre ASC";
        return jdbcTemplate.query(selec,BeanPropertyRowMapper.newInstance(Categoria.class),ingegre);
      }      
      
    }

     @Override
    public List<Procedencia> getProcedencias(){
      String selec = "SELECT * FROM procedencias ORDER BY idProcedencia ASC";
      return jdbcTemplate.query(selec,BeanPropertyRowMapper.newInstance(Procedencia.class));
    }
    // SALIDAS a proveedores

  @Override
    public List<Salida> AllSalidas() {   
      String selec = "SELECT * FROM salidas ORDER BY fecha ASC";
      return jdbcTemplate.query(selec, BeanPropertyRowMapper.newInstance(Salida.class));
    }

    
    @Override
    public int getMaxSalidas(){
      String consulta = "SELECT MAX(idSalida) FROM salidas";
   
      Object obj = jdbcTemplate.queryForObject(consulta,Integer.class);    
      if (obj==null){
        return 0;
      } else {
        return ((int)obj);
      }         
    }
    @Override
    public Salida findSalidaById(int id) {
      String q = "SELECT * FROM salidas WHERE idSalida=?";
      try {
        Salida salida = jdbcTemplate.queryForObject(q,
            BeanPropertyRowMapper.newInstance(Salida.class), id);          
        return salida;
      } catch (IncorrectResultSizeDataAccessException e) {
        return null;
      }
    }

    @Override
    public int saveSalida(Salida sal){
    // Graba nueva Salida
        return jdbcTemplate.update("INSERT INTO salidas(idSalida,fecha,idprov,nprov,"+
                                   "nroliq,idcat,categoria,cantidad,tkilos,precioun,importe,"+
                                   "proced,idpago,observ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
      
            new Object[] {sal.getIdSalida(),sal.getFecha(),sal.getIdprov(),sal.getNprov(),
                          sal.getNroliq(),sal.getIdcat(),sal.getCategoria(),sal.getCantidad(),
                          sal.getTkilos(),sal.getPrecioun(),sal.getImporte(),sal.getProced(),
                          sal.getIdpago(),sal.getObserv()
            });    
    }
        
    @Override
    public int actualizarSalida(int idsalida, Salida sal){    
    int resu = 0;
    try {                   
        resu = jdbcTemplate.update("UPDATE salidas SET fecha=?,idprov=?,nprov=?,"+
                                   "nroliq=?,idcat=?,categoria=?,cantidad=?,tkilos=?,precioun=?,"+
                                   "importe=?,proced=?,idpago=?,observ=? "+
                                   "WHERE idSalida=?",
                                 
            new Object[] {sal.getFecha(),sal.getIdprov(),sal.getNprov(),
                          sal.getNroliq(),sal.getIdcat(),sal.getCategoria(),sal.getCantidad(),
                          sal.getTkilos(),sal.getPrecioun(),sal.getImporte(),sal.getProced(),
                          sal.getIdpago(),sal.getObserv(),sal.getIdSalida()});
      } catch (IncorrectResultSizeDataAccessException e) {
        return -3;
    }
    return resu; 
    }
    @Override
    public int deleteSalida(int idsalida){
      int resu = 0;
      try {
        resu = jdbcTemplate.update("DELETE FROM salidas WHERE idSalida="+idsalida);
      } catch (DataAccessException dae){
        resu = -5;   
      }
      return resu;
    }    
     @Override
    public List<Salida> getSalidasXProv(int nroprov){
      String selec = "SELECT * FROM salidas WHERE idprov=? ORDER BY fecha DESC";
      return jdbcTemplate.query(selec,BeanPropertyRowMapper.newInstance(Salida.class),nroprov);

     }
  
  
}
    
    
    
    

     

    