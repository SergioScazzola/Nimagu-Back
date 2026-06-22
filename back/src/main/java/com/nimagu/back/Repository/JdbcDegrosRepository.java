package com.nimagu.back.Repository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nimagu.back.Entidades.Cliente;

import com.nimagu.back.Entidades.Empleado;
import com.nimagu.back.Entidades.Proveedor;
import com.nimagu.back.Entidades.Saldocli;
import com.nimagu.back.Entidades.Saldoemp;
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

   
    
    // EMPLEADOS
    @Override
    public List<Empleado> AllEmpleados() {   
      String selec = "SELECT * FROM empleados ORDER BY nomEmpleado";
      return jdbcTemplate.query(selec, BeanPropertyRowMapper.newInstance(Empleado.class));
    }

   

    @Override
    public int getMaxEmpleados(){
      String consulta = "SELECT MAX(idEmpleado) FROM empleados";
   
      Object obj = jdbcTemplate.queryForObject(consulta,Integer.class);    
      if (obj==null){
        return 0;
      } else {
        return ((int)obj);
      }         
    }
    @Override
    public Empleado findEmpleadoById(int id) {
      String q = "SELECT * FROM empleados WHERE idEmpleado=?";
      try {
        Empleado empleado = jdbcTemplate.queryForObject(q,
            BeanPropertyRowMapper.newInstance(Empleado.class), id);          
        return empleado;
      } catch (IncorrectResultSizeDataAccessException e) {
        return null;
      }
    }

    @Override
    public int saveEmpleado(Empleado empleado){
    // Graba nuevo Empleado autonumerado
        return jdbcTemplate.update("INSERT INTO empleados(idEmpleado,nomEmpleado,dni,domicilio,telefono,notas,saldoini) "+
                                   "VALUES(?,?,?,?,?,?,?)",
            new Object[] { empleado.getIdEmpleado(),empleado.getNomEmpleado(),empleado.getDni(),empleado.getDomicilio(),empleado.getTelefono(),
                           empleado.getNotas(),empleado.getSaldoini()});    
    }

    @Override
    public int actualizarEmpleado(int nroemp, Empleado empleado ){
    // Actualizar el Empleado
    int resu = 0;
    try {                   
        resu = jdbcTemplate.update("UPDATE empleados SET nomEmpleado=?,dni=?,domicilio=?,telefono=?,"+
                                  "notas=?,saldoini=? WHERE idEmpleado=?",                                 
            new Object[] { empleado.getNomEmpleado(),empleado.getDni(),empleado.getDomicilio(),
                           empleado.getTelefono(),empleado.getNotas(),empleado.getSaldoini(),empleado.getIdEmpleado()});
      } catch (IncorrectResultSizeDataAccessException e) {
        return -3;
    }
    return resu; 
    }
    public int deleteEmpleado(int idemp){
      int resu = 0;
      try {
        resu = jdbcTemplate.update("DELETE FROM empleados WHERE idEmpleado="+idemp);
      } catch (DataAccessException dae){
        resu = -5;   
      }
      return resu;
    }  
    @Override
    // Devuelve todos los saldos registrados del empleado ordenados por fecha
    public List<Saldoemp> getSaldosPorEmpleado(int nroemp) {   
      String selec = "SELECT * FROM saldosemp WHERE idEmpleado=? ORDER BY fecha";
      return jdbcTemplate.query(selec,BeanPropertyRowMapper.newInstance(Saldoemp.class),nroemp);
    }
   @Override
    public int saveSaldoEmpleado(Saldoemp saldoe){
    // Graba un nuevo saldo para el Empleado
        return jdbcTemplate.update("INSERT INTO saldosemp(idEmpleado,nrosaldo,fecha,saldo) "+
                                   "VALUES(?,?,?,?)",
            new Object[] { saldoe.getIdEmpleado(),saldoe.getNrosaldo(),saldoe.getFecha(),saldoe.getSaldo()});    
    }
  @Override
    public Saldoemp  getSaldoDelEmpleado(int idemp, int nros){
      String q = "SELECT * FROM saldosemp WHERE idEmpleado=? AND nrosaldo=?";
      try {
        Saldoemp saldoemp = jdbcTemplate.queryForObject(q,
            BeanPropertyRowMapper.newInstance(Saldoemp.class), idemp,nros);          
        return saldoemp;
      } catch (IncorrectResultSizeDataAccessException e) {
        return null;
      }
    }
     @Override
    // actualiza el saldo inicial del Empleado en la tabla "empleados"
    public int actSaldoInicialEmp(Saldoemp saldoe){
    
    int resu = 0;
    try {                   
        resu = jdbcTemplate.update("UPDATE empleados SET saldoini=? WHERE idEmpleado=?",
                                 
            new Object[] { saldoe.getSaldo(),saldoe.getIdEmpleado()});
      } catch (IncorrectResultSizeDataAccessException e) {
        return -3;
    }
    return resu; 
    }
     @Override
    // actualiza el saldo un saldo del Empleado en la tabla saldosemp"
    public int actSaldodelEmpleado(Saldoemp saldoe){
    
    int resu = 0;
    try {                   
        resu = jdbcTemplate.update("UPDATE saldosemp SET fecha=?,saldo=? WHERE idEmpleado=? AND nrosaldo=?",
                                 
            new Object[] { saldoe.getFecha(),saldoe.getSaldo(),saldoe.getIdEmpleado(),saldoe.getNrosaldo()});
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

   
  
}
    
    
    
    

     

    