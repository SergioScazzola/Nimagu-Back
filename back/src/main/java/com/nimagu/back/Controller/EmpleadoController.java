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
import com.nimagu.back.Entidades.Empleado;



import com.nimagu.back.Repository.JdbcDegrosRepository;

@CrossOrigin(origins = "${FRONTEND_URL}")
@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {

     @Autowired
    JdbcDegrosRepository degrosRepository;

    @SuppressWarnings("null")
    @GetMapping("/empleados")
    public ResponseEntity<List<Empleado>> getAllEmpleados() {
      List<Empleado> empleados = null;
    try {                  
      empleados = degrosRepository.AllEmpleados();
    
      if (empleados.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } else {
         return new ResponseEntity<>(empleados, HttpStatus.OK);
      }
    } catch (Exception e) {
       return new ResponseEntity<>(empleados, HttpStatus.INTERNAL_SERVER_ERROR);
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
  
// INFORMES ESTADISTICOS 

 

}
