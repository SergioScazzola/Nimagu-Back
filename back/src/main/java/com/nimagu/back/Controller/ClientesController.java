package com.nimagu.back.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nimagu.back.Entidades.Cliente;
import com.nimagu.back.Entidades.Saldocli;
import com.nimagu.back.Repository.JdbcDegrosRepository;

@CrossOrigin(origins = "${FRONTEND_URL}")
@RestController
@RequestMapping("/api/clientes")
public class ClientesController {


     @Autowired
    JdbcDegrosRepository degrosRepository;
    @SuppressWarnings("null")
    @GetMapping("/clientes")
    public ResponseEntity<List<Cliente>> getAllClientes() {
      List<Cliente> clientes = null;
    try {      
            
      clientes = degrosRepository.AllClientes();
    
      if (clientes.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } else {
         return new ResponseEntity<>(clientes, HttpStatus.OK);
      }
    } catch (Exception e) {
       return new ResponseEntity<>(clientes, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  @RequestMapping(value="/cliente/max")
  public int getCantidadClientes(){
     int cantl = degrosRepository.getMaxClientes();
     return cantl;
  }
  @RequestMapping(value ="/cliente" , params={"id"} )
  public ResponseEntity<Cliente> getClienteById(@RequestParam("id") Integer idcliente) {
    Cliente cliente = degrosRepository.findClienteById(idcliente);
    if (cliente != null){
      return new ResponseEntity<>(cliente, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
    @PostMapping(value="/cliente/nuevo")
    // Graba un nuevo cliente
    public ResponseEntity<String> crearCliente(@RequestBody Cliente cliente) {
       try {
        int nrocliente = degrosRepository.saveCliente(cliente);
        return new ResponseEntity<>(Integer.toString(nrocliente), HttpStatus.CREATED);
       } catch (Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }
    @PutMapping(value="/cliente/actualizar", params={"id"})
    public ResponseEntity<String> updateCliente(@RequestParam("id") Integer idcliente,
                                                @RequestBody Cliente cliente){
      try {
        int resultado = degrosRepository.actualizarCliente(idcliente,cliente);    
        return new ResponseEntity<>(Integer.toString(resultado), HttpStatus.OK);
      } catch (Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
     
      } 
    }

    @RequestMapping(value ="/cliente/saldo" , params={"idcliente","nrosaldo"} )
  public ResponseEntity<Saldocli> getSaldodeCliente(@RequestParam("idcliente") Integer idcli,
                                                   @RequestParam("nrosaldo") Integer  nros) {
    Saldocli scli = degrosRepository.getSaldoDelCliente(idcli,nros);
    if (scli != null){
      return new ResponseEntity<>(scli, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
   @RequestMapping(value = "/saldosxcli", params={"nrocli"})
    public ResponseEntity<List<Saldocli>> getSaldosPorCliente(@RequestParam("nrocli") int ncli) {
    try {
      List<Saldocli> saldos = null;
            
      saldos = degrosRepository.getSaldosPorCliente(ncli);
    
      if (saldos.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } else {
         return new ResponseEntity<>(saldos, HttpStatus.OK);
      }
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PostMapping(value="/saldo/nuevo")
    // Graba un nuevo Saldo del cliente
    public ResponseEntity<String> crearSaldoCliente(@RequestBody Saldocli saldoc) {
       try {
        int nros = degrosRepository.saveSaldoCliente(saldoc);
        return new ResponseEntity<>(Integer.toString(nros), HttpStatus.CREATED);
       } catch (Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }


  
     @DeleteMapping(value="/cliente", params={"id"})    
    public ResponseEntity<String> borrarCliente(@RequestParam("id") Integer idcliente){
      try {
        int nrocliente = degrosRepository.deleteCliente(idcliente);
        return new ResponseEntity<>(Integer.toString(nrocliente),HttpStatus.OK);
      } catch (Exception e) {
        return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR );
      }

    }
    @RequestMapping(value ="/clientesxNombre" , params={"nomcli"} )
    public ResponseEntity<List<Cliente>> getClientesxNombre(@RequestParam("nomcli") String nomcliente) {
      List<Cliente> clientes = degrosRepository.findClientesByNombre(nomcliente);      
      if ( clientes!= null){
        return new ResponseEntity<>(clientes, HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    }

   

  
                                          
  
// INFORMES ESTADISTICOS 

 
}
