package com.nimagu.back.Repository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nimagu.back.Entidades.Cliente;
import com.nimagu.back.Entidades.Cobranza;
import com.nimagu.back.Entidades.Detcobro;
import com.nimagu.back.Entidades.Detpago;
import com.nimagu.back.Entidades.Pago;
import com.nimagu.back.Entidades.Proveedor;
import com.nimagu.back.Entidades.Saldocli;

import com.nimagu.back.Entidades.Saldoprov;

    
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
            new Object[] { saldoc.getIdCliente(),saldoc.getNrosaldo(),saldoc.getFecha(),saldoc.getSaldo()});    
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
  public int saveCobranza(Cobranza cobranza){
  // Graba nueva cobranza 
      return jdbcTemplate.update("INSERT INTO cobranza(idCobro,fecha,idCliente,nomcliente,nrofactura,importe,"+
                                 "nroventa,observaciones) "+
                                 "VALUES(?,?,?,?,?,?,?,?)",
          new Object[] { cobranza.getIdCobro(),cobranza.getFecha(),cobranza.getIdCliente(),
                         cobranza.getNomcliente(),cobranza.getNrofactura(),
                         cobranza.getImporte(),cobranza.getNroventa(),cobranza.getObservaciones() });    
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
  public List<Detcobro> AllDetCobroPorId(int nrocobro) {   
    String selec = "SELECT * FROM detcobro WHERE idCobro=? ORDER BY nroitem";
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
    

  
  
}
    
    
    
    

     

    