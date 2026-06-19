package com.Sisbul.ApiRrest.controller;

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
import com.Sisbul.ApiRrest.entidades.Empleado;

import com.Sisbul.ApiRrest.entidades.ResuSEmp;

import com.Sisbul.ApiRrest.entidades.Saldoemp;
import com.Sisbul.ApiRrest.repository.JdbcDegrosRepository;

@CrossOrigin(origins = "${FRONTEND_URL}")
@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {

     @Autowired
    JdbcDegrosRepository degrosRepository;

    @SuppressWarnings("null")
    @GetMapping("/empleados")
    public ResponseEntity<List<Empleado>> getAllEmpleados() {
    try {
      List<Empleado> empleados = null;
            
      empleados = degrosRepository.AllEmpleados();
    
      if (empleados.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } else {
         return new ResponseEntity<>(empleados, HttpStatus.OK);
      }
    } catch (Exception e) {
       return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @RequestMapping(value="/empleados/max")
  public int getCantidadEmpleados(){
     int cantl = degrosRepository.getMaxEmpleados();
     return cantl;
  }
  
  @RequestMapping(value ="/empleado" , params={"id"} )
  public ResponseEntity<Empleado> getEmpleadoById(@RequestParam("id") Integer idempleado) {
    Empleado empleado = degrosRepository.findEmpleadoById(idempleado);
    if (empleado != null){
      return new ResponseEntity<>(empleado, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
    @PostMapping(value="/empleado/nuevo")
    // Graba un nuevo empleado
    public ResponseEntity<String> crearEmpleado(@RequestBody Empleado empleado) {
       try {
        int nroempleado = degrosRepository.saveEmpleado(empleado);
        return new ResponseEntity<>(Integer.toString(nroempleado), HttpStatus.CREATED);
       } catch (Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }
    @PutMapping(value="/empleado/actualizar", params={"id"})
    public ResponseEntity<String> updateEmpleado(@RequestParam("id") Integer idempleado,
                                                @RequestBody Empleado empleado){
      try {
        int resultado = degrosRepository.actualizarEmpleado(idempleado,empleado);    
        return new ResponseEntity<>(Integer.toString(resultado), HttpStatus.OK);
      } catch (Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
     
      } 
    }
     @DeleteMapping(value="/empleado", params={"id"})    
    public ResponseEntity<String> borrarEmpleado(@RequestParam("id") Integer idempleado){
      try {
        int nroempleado = degrosRepository.deleteEmpleado(idempleado);
        return new ResponseEntity<>(Integer.toString(nroempleado),HttpStatus.OK);
      } catch (Exception e) {
        return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR );
      }

    }
    @RequestMapping(value = "/saldosxemp", params={"nroemp"})
    public ResponseEntity<List<Saldoemp>> getSaldosPorEmpleado(@RequestParam("nroemp") int nemp) {
    try {
      List<Saldoemp> saldos = null;
            
      saldos = degrosRepository.getSaldosPorEmpleado(nemp);
    
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
    // Graba un nuevo Saldo del empleado
    public ResponseEntity<String> crearSaldoEmpleado(@RequestBody Saldoemp saldoe) {
       try {
        int nros = degrosRepository.saveSaldoEmpleado(saldoe);
        return new ResponseEntity<>(Integer.toString(nros), HttpStatus.CREATED);
       } catch (Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    @PutMapping(value="/actsaldoini")
    public ResponseEntity<String> updateSaldoInicial(@RequestBody Saldoemp saldoemp){
      try {
        int resultado = degrosRepository.actSaldoInicialEmp(saldoemp);    
        return new ResponseEntity<>(Integer.toString(resultado), HttpStatus.OK);
      } catch (Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
     
      } 
    }

    @PutMapping(value="/actsaldoemp")
     public ResponseEntity<String> updateSaldoEmpleado(@RequestBody Saldoemp saldoemp){
      try {
        int resultado = degrosRepository.actSaldodelEmpleado(saldoemp);    
        return new ResponseEntity<>(Integer.toString(resultado), HttpStatus.OK);
      } catch (Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
     
      } 
    }
  @RequestMapping(value ="/empleado/saldo" , params={"idempleado","nrosaldo"} )
  public ResponseEntity<Saldoemp> getSaldodeCliente(@RequestParam("idempleado") Integer idemp,
                                                   @RequestParam("nrosaldo") Integer  nros) {
    Saldoemp semp = degrosRepository.getSaldoDelEmpleado(idemp,nros);
    if (semp != null){
      return new ResponseEntity<>(semp, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
// INFORMES ESTADISTICOS 

 @RequestMapping(value = "/infosaldos")
    public ResponseEntity<List<ResuSEmp>> getInformedeSaldosEmp() {
    try {
      List<ResuSEmp> informe = null;
            
      informe = degrosRepository.getInformeSaldosEmp();
    
      if (informe.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } else {
         return new ResponseEntity<>(informe, HttpStatus.OK);
      }
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
