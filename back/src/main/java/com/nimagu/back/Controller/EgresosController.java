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


import com.nimagu.back.Entidades.Egreso;
import com.nimagu.back.Entidades.Ingreso;
import com.nimagu.back.Repository.JdbcDegrosRepository;

@CrossOrigin(origins = "${FRONTEND_URL}")
@RestController
@RequestMapping("/api/egreso")
public class EgresosController {


     @Autowired
    JdbcDegrosRepository degrosRepository;
    @SuppressWarnings("null")
    @GetMapping("/egresos")
    public ResponseEntity<List<Egreso>> getAllEgresos() {
      List<Egreso> egresos = null;
    try {      
            
      egresos = degrosRepository.AllEgresos();
    
      if (egresos.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } else {
         return new ResponseEntity<>(egresos, HttpStatus.OK);
      }
    } catch (Exception e) {
       return new ResponseEntity<>(egresos, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  @RequestMapping(value="/max")
  public int getCantidadEgresos(){
     int cantl = degrosRepository.getMaxEgresos();
     return cantl;
  }
  @RequestMapping(value ="/egreso" , params={"id"} )
  public ResponseEntity<Egreso> getIngresoById(@RequestParam("id") Integer idegreso) {
    Egreso egreso = degrosRepository.findEgresoById(idegreso);
    if (egreso != null){
      return new ResponseEntity<>(egreso, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
    @PostMapping(value="/egreso/nuevo")
    // Graba un nuevo egreso
    public ResponseEntity<String> crearEgreso(@RequestBody Egreso egreso) {
       try {
        int nroeg = degrosRepository.saveEgreso(egreso);
        return new ResponseEntity<>(Integer.toString(nroeg), HttpStatus.CREATED);
       } catch (Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }
    @PutMapping(value="/egreso/actualizar", params={"id"})
    public ResponseEntity<String> updateEgreso(@RequestParam("id") Integer idegreso,
                                                @RequestBody Egreso egreso){
      try {
        int resultado = degrosRepository.actualizarEgreso(idegreso,egreso);    
        return new ResponseEntity<>(Integer.toString(resultado), HttpStatus.OK);
      } catch (Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
     
      } 
    }
     
     @DeleteMapping(value="/egreso", params={"id"})    
    public ResponseEntity<String> borrarEgreso(@RequestParam("id") Integer idegreso){
      try {
        int nroeg = degrosRepository.deleteIngreso(idegreso);
        return new ResponseEntity<>(Integer.toString(nroeg),HttpStatus.OK);
      } catch (Exception e) {
        return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR );
      }
    }
  @RequestMapping(value ="/egresosxprov" , params={"idprov"} )
    public ResponseEntity<List<Egreso>> getEgresosxProv(@RequestParam("idprov") int nroprov) {
      List<Egreso> egresos = degrosRepository.getEgresosXProv(nroprov);      
      if ( egresos != null){
        return new ResponseEntity<>(egresos, HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    }
  }

   

  
                                          
  
// INFORMES ESTADISTICOS 

 

