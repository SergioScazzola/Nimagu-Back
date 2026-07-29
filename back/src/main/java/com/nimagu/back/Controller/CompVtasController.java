package com.nimagu.back.Controller;

import com.nimagu.back.Entidades.CompVta;

import com.nimagu.back.Repository.JdbcNimaguRepository;

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


@CrossOrigin(origins = "${FRONTEND_URL}")
@RestController
@RequestMapping("/api/compvta")
public class CompVtasController {
    @Autowired
    JdbcNimaguRepository nimaguRepository;
    @SuppressWarnings("null")
    @GetMapping("/compvtas")
    public ResponseEntity<List<CompVta>> getAllCobranza() {
    List<CompVta> comps = null;
    try {      
            
      comps = nimaguRepository.AllCompVtas();
    
      if (comps.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } else {
         return new ResponseEntity<>(comps, HttpStatus.OK);
      }
    } catch (Exception e) {
       return new ResponseEntity<>(comps, HttpStatus.INTERNAL_SERVER_ERROR);
    }
 }

 @RequestMapping(value="/max")
  public int getCantidadComp(){
     int cantc = nimaguRepository.getMaxCompVtas();
     return cantc;
  }
   
  @RequestMapping(value ="/compvta" , params={"id"} )
  public ResponseEntity<CompVta> getCuentaById(@RequestParam("id") Integer idcompvta) {
    CompVta comp = nimaguRepository.findCompVtaById(idcompvta);
    if (comp != null){
      return new ResponseEntity<>(comp, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
  @PostMapping(value="/compvta/nuevo")
    // Graba una nueva compra/venta
    public ResponseEntity<String> crearCompVta(@RequestBody CompVta comp) {
       try {
        int nrocomp = nimaguRepository.saveCompVta(comp);
        return new ResponseEntity<>(Integer.toString(nrocomp), HttpStatus.CREATED);
       } catch (Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }
  @PutMapping(value="/compvta/actualizar")
  public ResponseEntity<String> updateCompVta(@RequestBody CompVta comp){
      try {
        int resultado = nimaguRepository.actualizarCompVta(comp);    
        return new ResponseEntity<>(Integer.toString(resultado), HttpStatus.OK);
      } catch (Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
     
      } 
  }
  @DeleteMapping(value="/compvta/borrar", params={"id"})    
  public ResponseEntity<String> borrarCompVta(@RequestParam("id") Integer idcomp){
      try {
        int nrocomp = nimaguRepository.deleteCompVta(idcomp);
        return new ResponseEntity<>(Integer.toString(nrocomp),HttpStatus.OK);
      } catch (Exception e) {
        return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR );
      }
  }
   @GetMapping(value="/DetCyVxFecha",params={"feci","fecf"})
    public ResponseEntity<List<CompVta>> getDetalleCyV(  @RequestParam("feci") String fechaini,
                                                         @RequestParam("fecf") String fechafin) {
    try {
      List<CompVta> movims = null;
            
      movims = nimaguRepository.detalleCyFxFecha(fechaini,fechafin);
    
      if (movims.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } else {
         return new ResponseEntity<>(movims, HttpStatus.OK);
      }
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

@GetMapping(value="/DetCliProvF",params={"feci","fecf"})
public ResponseEntity<List<CompVta>>  detalleCliProvF(  @RequestParam("feci") String fechaini,
                                                        @RequestParam("fecf") String fechafin) {
    try {
      List<CompVta> movims = null;
            
      movims = nimaguRepository.detalleCliProvyFecha(fechaini,fechafin);
    
      if (movims.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } else {
         return new ResponseEntity<>(movims, HttpStatus.OK);
      }
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
@GetMapping(value="/DetCatyF",params={"feci","fecf"})
public ResponseEntity<List<CompVta>>  detalleCatyF(  @RequestParam("feci") String fechaini,
                                                     @RequestParam("fecf") String fechafin) {
    try {
      List<CompVta> movims = null;
            
      movims = nimaguRepository.detalleCatyFecha(fechaini,fechafin);
    
      if (movims.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } else {
         return new ResponseEntity<>(movims, HttpStatus.OK);
      }
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

@GetMapping(value="/DetProcyF",params={"feci","fecf"})
public ResponseEntity<List<CompVta>>  detalleProcyF(  @RequestParam("feci") String fechaini,
                                                      @RequestParam("fecf") String fechafin) {
    try {
      List<CompVta> movims = null;
            
      movims = nimaguRepository.detalleProcyFecha(fechaini,fechafin);
    
      if (movims.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } else {
         return new ResponseEntity<>(movims, HttpStatus.OK);
      }
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }


}