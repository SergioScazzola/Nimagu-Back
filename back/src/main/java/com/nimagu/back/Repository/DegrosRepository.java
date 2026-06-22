package com.nimagu.back.Repository;


import java.util.List;




import com.nimagu.back.Entidades.Cliente;
import com.nimagu.back.Entidades.Saldoprov;
import com.nimagu.back.Entidades.Empleado;
import com.nimagu.back.Entidades.Proveedor;
import com.nimagu.back.Entidades.Saldocli;
import com.nimagu.back.Entidades.Saldoemp;
          
       
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

      
      
        List<Empleado> AllEmpleados();
       
        int getMaxEmpleados();  
        Empleado findEmpleadoById(int nroempleado);
        int saveEmpleado(Empleado empleado);      
        int actualizarEmpleado(int nroemp, Empleado empleado);    
        int deleteEmpleado(int idempleado);
        List<Saldoemp> getSaldosPorEmpleado(int nrocli);
        int saveSaldoEmpleado(Saldoemp saldoe);
        int actSaldodelEmpleado(Saldoemp saldoe);
        Saldoemp getSaldoDelEmpleado(int idemp, int nros);
        int actSaldoInicialEmp(Saldoemp saldoe);

        List<Proveedor> AllProvs();
        int getMaxIdProv();
        Proveedor findProvById(int nroprov);
        int saveProv(Proveedor prov); 
        int actualizarProv(Proveedor prove);   
        int actSaldoIniProv(Saldoprov saldoprov);    
        int deleteProveedor(int idprov);

      
    }
    

