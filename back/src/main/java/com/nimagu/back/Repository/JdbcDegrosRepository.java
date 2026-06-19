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

   
  
}
    
    
    
    

     

    