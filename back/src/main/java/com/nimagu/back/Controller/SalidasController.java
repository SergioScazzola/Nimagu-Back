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


import com.nimagu.back.Entidades.Salida;

import com.nimagu.back.Repository.JdbcDegrosRepository;

@CrossOrigin(origins = "${FRONTEND_URL}")
@RestController
@RequestMapping("/api/salida")
public class SalidasController {


     @Autowired
    JdbcDegrosRepository degrosRepository;
    @SuppressWarnings("null")
    @GetMapping("/egresos")
    public ResponseEntity<List<Salida>> getAllSalidas() {
      List<Salida> salidas = null;
    try {      
            
      salidas = degrosRepository.AllSalidas();
    
      if (salidas.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } else {
         return new ResponseEntity<>(salidas, HttpStatus.OK);
      }
    } catch (Exception e) {
       return new ResponseEntity<>(salidas, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  @RequestMapping(value="/max")
  public int getCantidadSalidas(){
     int cantl = degrosRepository.getMaxSalidas();
     return cantl;
  }
  @RequestMapping(value ="/salida" , params={"id"} )
  public ResponseEntity<Salida> getSalidaById(@RequestParam("id") Integer idsalida) {
    Salida salida = degrosRepository.findSalidaById(idsalida);
    if (salida != null){
      return new ResponseEntity<>(salida, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
    @PostMapping(value="/salida/nuevo")
    // Graba un nuevo egreso
    public ResponseEntity<String> crearEgreso(@RequestBody Salida salida) {
       try {
        int nrosal = degrosRepository.saveSalida(salida);
        return new ResponseEntity<>(Integer.toString(nrosal), HttpStatus.CREATED);
       } catch (Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }
    @PutMapping(value="/salida/actualizar", params={"id"})
    public ResponseEntity<String> updateEgreso(@RequestParam("id") Integer idsalida,
                                                @RequestBody Salida salida){
      try {
        int resultado = degrosRepository.actualizarEgreso(idsalida,salida);    
        return new ResponseEntity<>(Integer.toString(resultado), HttpStatus.OK);
      } catch (Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
     
      } 
    }
     
     @DeleteMapping(value="/salida", params={"id"})    
    public ResponseEntity<String> borrarEgreso(@RequestParam("id") Integer idsalida){
      try {
        int nrosal = degrosRepository.deleteSalida(idsalida);
        return new ResponseEntity<>(Integer.toString(nrosal),HttpStatus.OK);
      } catch (Exception e) {
        return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR );
      }
    }
  @RequestMapping(value ="/salidasxprov" , params={"idprov"} )
    public ResponseEntity<List<Salida>> getEgresosxProv(@RequestParam("idprov") int nroprov) {
      List<Salida> salidas = degrosRepository.getSalidasXProv(nroprov);      
      if ( salidas != null){
        return new ResponseEntity<>(salidas, HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    }
  }

   

  
                                          
  
// INFORMES ESTADISTICOS 

 

