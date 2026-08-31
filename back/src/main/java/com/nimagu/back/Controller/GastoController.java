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

import com.nimagu.back.Entidades.CompVta;
import com.nimagu.back.Entidades.Fpago;
import com.nimagu.back.Entidades.Gasto;
import com.nimagu.back.Entidades.GastoFP;
import com.nimagu.back.Repository.JdbcNimaguRepository;



@CrossOrigin(origins = "${FRONTEND_URL}")
@RestController
@RequestMapping("/api/gasto")
public class GastoController {

    @Autowired
    JdbcNimaguRepository nimaguRepository;

    @SuppressWarnings("null")
    @GetMapping("/gastos")
    public ResponseEntity<List<Gasto>> getGastos() {
    List<Gasto> gastos = null;
    try {                  
      gastos = nimaguRepository.AllGastos();
    
      if (gastos.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } else {
         return new ResponseEntity<>(gastos, HttpStatus.OK);
      }
    } catch (Exception e) {
       return new ResponseEntity<>(gastos, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

 @GetMapping("/gastosfp")
    public ResponseEntity<List<GastoFP>> getGastosConFP() {
    List<GastoFP> gastos = null;
    try {                  
      gastos = nimaguRepository.AllGastosFP();
    
      if (gastos.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } else {
         return new ResponseEntity<>(gastos, HttpStatus.OK);
      }
    } catch (Exception e) {
       return new ResponseEntity<>(gastos, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

   @RequestMapping(value ="/gasto" , params={"id"} )
  public ResponseEntity<Gasto> getGastoById(@RequestParam("id") Integer idgasto) {
    Gasto gasto = nimaguRepository.findGastoById(idgasto);
    if (gasto != null){
      return new ResponseEntity<>(gasto, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
    @PostMapping(value="/gasto/nuevo")
    // Graba un nuevo gasto
    public ResponseEntity<String> crearGasto(@RequestBody Gasto gasto) {
       try {
        int nrogasto = nimaguRepository.saveGasto(gasto);
        return new ResponseEntity<>(Integer.toString(nrogasto), HttpStatus.CREATED);
       } catch (Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }
    
    @PutMapping(value="/gasto/actualizar")
    public ResponseEntity<String> updateGasto(@RequestBody Gasto gasto){
      try {
        int resultado = nimaguRepository.actualizarGasto(gasto);    
        return new ResponseEntity<>(Integer.toString(resultado), HttpStatus.OK);
      } catch (Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
     
      } 
    }

  @RequestMapping(value="/maxid")
  public int getMaxGasto(){
     int cantl = nimaguRepository.getMaxIdGasto();
     return cantl;
  }
  @DeleteMapping(value="/gasto/delete", params={"id"})    
    public ResponseEntity<String> borrarGasto(@RequestParam("id") Integer idgasto){
      try {
        int nrogasto = nimaguRepository.deleteGasto(idgasto);
        return new ResponseEntity<>(Integer.toString(nrogasto),HttpStatus.OK);
      } catch (Exception e) {
        return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR );
      }

    }

 @GetMapping(value="/detxfecha",params={"feci","fecf"})
    public ResponseEntity<List<GastoFP>>  detallexFecha(  @RequestParam("feci") String fechaini,
                                                        @RequestParam("fecf") String fechafin) {
    try {
      List<GastoFP> movims = null;
            
      movims = nimaguRepository.detalleporFecha(fechaini,fechafin);
    
      if (movims.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } else {
         return new ResponseEntity<>(movims, HttpStatus.OK);
      }
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }


    @GetMapping(value="/detxprod",params={"feci","fecf"})
    public ResponseEntity<List<GastoFP>>  detalleXProd(  @RequestParam("feci") String fechaini,
                                                 @RequestParam("fecf") String fechafin) {
    try {
      List<GastoFP> movims = null;
            
      movims = nimaguRepository.detalleporProducto(fechaini,fechafin);
    
      if (movims.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } else {
         return new ResponseEntity<>(movims, HttpStatus.OK);
      }
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

   @GetMapping(value="/detxtprod",params={"feci","fecf"})
    public ResponseEntity<List<GastoFP>>  detallexTipoProd(  @RequestParam("feci") String fechaini,
                                                           @RequestParam("fecf") String fechafin) {
    try {
      List<GastoFP> movims = null;
            
      movims = nimaguRepository.detalleporTipoProd(fechaini,fechafin);
    
      if (movims.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } else {
         return new ResponseEntity<>(movims, HttpStatus.OK);
      }
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping(value="/detxprov",params={"feci","fecf"})
    public ResponseEntity<List<GastoFP>>  detallexProveedor(  @RequestParam("feci") String fechaini,
                                                           @RequestParam("fecf") String fechafin) {
    try {
      List<GastoFP> movims = null;
            
      movims = nimaguRepository.detalleporProveedor(fechaini,fechafin);
    
      if (movims.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } else {
         return new ResponseEntity<>(movims, HttpStatus.OK);
      }
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  // FORMAS DE PAGO

    @GetMapping("/fpago/fpagos")
    public ResponseEntity<List<Fpago>> getFormasdePago() {
    List<Fpago> fpagos = null;
    try {                  
      fpagos = nimaguRepository.AllFormasdePago();
    
      if (fpagos.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } else {
         return new ResponseEntity<>(fpagos, HttpStatus.OK);
      }
    } catch (Exception e) {
       return new ResponseEntity<>(fpagos, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  @RequestMapping(value="/fpago/maxid")
  public int getMaxFPago(){
     int cantl = nimaguRepository.getMaxFPago();
     return cantl;
  }

  @RequestMapping(value ="/fpago" , params={"idgasto"} )
  public ResponseEntity<Fpago> getFPagoXGasto(@RequestParam("idgasto") Integer idg) {
    Fpago fpago = nimaguRepository.findFPagoXGasto(idg);
    if (fpago != null){
      return new ResponseEntity<>(fpago, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping(value="/fpago/nuevo")
    // Graba una nueva f.de pago
    public ResponseEntity<String> crearFormadePago(@RequestBody Fpago fpago) {
       try {
        int nrofp = nimaguRepository.saveFormadePago(fpago);
        return new ResponseEntity<>(Integer.toString(nrofp), HttpStatus.CREATED);
       } catch (Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }
    
  @PutMapping(value="/fpago/actualizar")
    public ResponseEntity<String> updateFPago(@RequestBody Fpago fpago){
      try {
        int resultado = nimaguRepository.actualizarFPago(fpago);    
        return new ResponseEntity<>(Integer.toString(resultado), HttpStatus.OK);
      } catch (Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
     
      } 
    }

}
