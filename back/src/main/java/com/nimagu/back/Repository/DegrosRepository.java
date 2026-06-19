package com.nimagu.back.Repository;


import java.util.List;




import com.nimagu.back.Entidades.Cliente;

import com.nimagu.back.Entidades.Empleado;
          
       
 public interface DegrosRepository {
       
        List<Cliente> AllClientes();
        
        int getMaxClientes();  
        Cliente findClienteById(int nrocliente);

        int saveCliente(Cliente cliente);
      
  
        int actualizarCliente(int idcliente,Cliente cliente);    
        int deleteCliente(int idcliente);
        List<Cliente> findClientesByNombre(String nom); 
      
      
        List<Empleado> AllEmpleados();
       
        int getMaxEmpleados();  
        Empleado findEmpleadoById(int nroempleado);
        int saveEmpleado(Empleado empleado);
      
        int actualizarEmpleado(int nroemp, Empleado empleado);    
        int deleteEmpleado(int idempleado);
      
    }
    

