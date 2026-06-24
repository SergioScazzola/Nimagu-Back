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


import com.nimagu.back.Entidades.Ingreso;
import com.nimagu.back.Entidades.MedioPago;
import com.nimagu.back.Repository.JdbcDegrosRepository;

@CrossOrigin(origins = "${FRONTEND_URL}")
@RestController
@RequestMapping("/api/ingreso")
public class IngresosController {


     @Autowired
    JdbcDegrosRepository degrosRepository;
    @SuppressWarnings("null")
    @GetMapping("/ingresos")
    public ResponseEntity<List<Ingreso>> getAllIngresos() {
      List<Ingreso> ingresos = null;
    try {      
            
      ingresos = degrosRepository.AllIngresos();
    
      if (ingresos.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } else {
         return new ResponseEntity<>(ingresos, HttpStatus.OK);
      }
    } catch (Exception e) {
       return new ResponseEntity<>(ingresos, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  @RequestMapping(value="/max")
  public int getCantidadIngresos(){
     int cantl = degrosRepository.getMaxIngresos();
     return cantl;
  }
  @RequestMapping(value ="/ingreso" , params={"id"} )
  public ResponseEntity<Ingreso> getIngresoById(@RequestParam("id") Integer idingreso) {
    Ingreso ingreso = degrosRepository.findIngresoById(idingreso);
    if (ingreso != null){
      return new ResponseEntity<>(ingreso, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
    @PostMapping(value="/ingreso/nuevo")
    // Graba un nuevo ingreso
    public ResponseEntity<String> crearIngreso(@RequestBody Ingreso ingreso) {
       try {
        int nroing = degrosRepository.saveIngreso(ingreso);
        return new ResponseEntity<>(Integer.toString(nroing), HttpStatus.CREATED);
       } catch (Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }
    @PutMapping(value="/ingreso/actualizar", params={"id"})
    public ResponseEntity<String> updateIngreso(@RequestParam("id") Integer idingreso,
                                                @RequestBody Ingreso ingreso){
      try {
        int resultado = degrosRepository.actualizarIngreso(idingreso,ingreso);    
        return new ResponseEntity<>(Integer.toString(resultado), HttpStatus.OK);
      } catch (Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
     
      } 
    }
     
     @DeleteMapping(value="/ingreso", params={"id"})    
    public ResponseEntity<String> borrarIngreso(@RequestParam("id") Integer idingreso){
      try {
        int nroing = degrosRepository.deleteIngreso(idingreso);
        return new ResponseEntity<>(Integer.toString(nroing),HttpStatus.OK);
      } catch (Exception e) {
        return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR );
      }
    }
   @RequestMapping(value ="/ingresosxcli" , params={"idcliente"} )
    public ResponseEntity<List<Ingreso>> getIngresosxCliente(@RequestParam("idcliente") int nrocli) {
      List<Ingreso> ingresos = degrosRepository.getIngresosXCliente(nrocli);      
      if ( ingresos != null){
        return new ResponseEntity<>(ingresos, HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    }

     @RequestMapping(value ="/mediospago"  )
    public ResponseEntity<List<MedioPago>> getMediosPago() {
      List<MedioPago> mpagos = degrosRepository.getMediosPago();      
      if ( mpagos != null){
        return new ResponseEntity<>(mpagos, HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    }
  }

   

  
                                          
  
// INFORMES ESTADISTICOS 

 

