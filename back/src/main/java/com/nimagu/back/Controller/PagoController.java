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

import com.nimagu.back.Entidades.Pago;
import com.nimagu.back.Entidades.Detpago;
import com.nimagu.back.Repository.JdbcDegrosRepository;

@CrossOrigin(origins = "${FRONTEND_URL}")
@RestController
@RequestMapping("/api/pago")
public class PagoController {


     @Autowired
    JdbcDegrosRepository degrosRepository;
    @SuppressWarnings("null")
    @GetMapping("/pagos")
     public ResponseEntity<List<Pago>> getAllPago() {
      List<Pago> pagos = null;
    try {    
            
      pagos = degrosRepository.AllPagos();
    
      if (pagos.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } else {
         return new ResponseEntity<>(pagos, HttpStatus.OK);
      }
    } catch (Exception e) {
       return new ResponseEntity<>(pagos, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping(value="/pagxprov", params={"idprov"})
  public ResponseEntity<List<Pago>> getAllPagosPorProveedor(@RequestParam("idprov") Integer idpro) {
    List<Pago> pagos = null;
    try {         
      pagos = degrosRepository.AllPagosPorProveedor(idpro);
 
   if (pagos.isEmpty()) {
     return new ResponseEntity<>(HttpStatus.NO_CONTENT);
   } else {
      return new ResponseEntity<>(pagos, HttpStatus.OK);
   }
 } catch (Exception e) {
   return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
 }
}
/*// Devuelve informe de Cobranza detallado de todos los clientes entre "feci" y "fecf"
@GetMapping(value="/infoDetCob",params={"feci","fecf"})
  public ResponseEntity<List<InfoDetCobro>> getInfoDetCobranza(@RequestParam("feci") String fecini,
                                                               @RequestParam("fecf") String fecfin) {
 try {
   List<InfoDetCobro> infocob = null;
         
   infocob = degrosRepository.infoDetCobranza(fecini,fecfin);
 
   if (infocob.isEmpty()) {
     return new ResponseEntity<>(HttpStatus.NO_CONTENT);
   } else {
      return new ResponseEntity<>(infocob, HttpStatus.OK);
   }
 } catch (Exception e) {
   return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
 }
}
// Devuelve informe de Cobranza resumido de todos los clientes entre "feci" y "fecf"
@GetMapping(value="/infoCob",params={"feci","fecf"})
  public ResponseEntity<List<Cobranza>> getInfoResCobranza(@RequestParam("feci") String fecini,
                                                           @RequestParam("fecf") String fecfin) {
 try {
   List<Cobranza> infocob = null;
         
   infocob = degrosRepository.infoResCobranza(fecini,fecfin);
 
   if (infocob.isEmpty()) {
     return new ResponseEntity<>(HttpStatus.NO_CONTENT);
   } else {
      return new ResponseEntity<>(infocob, HttpStatus.OK);
   }
 } catch (Exception e) {
   return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
 }
}*/

  @RequestMapping(value="/max")
  public int getCantidadPagos(){
     int cantl = degrosRepository.getMaxPagos();
     return cantl;
  }
  @RequestMapping(value ="/pago" , params={"id"} )
  public ResponseEntity<Pago> leerPagoById(@RequestParam("id") Integer idpag) {
    Pago pago = degrosRepository.findPagoById(idpag);
    if (pago != null){
      return new ResponseEntity<>(pago, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
    @PostMapping(value="/nuevo")
    // Graba un nuevo registro de Pago
    public ResponseEntity<String> crearPago(@RequestBody Pago pago) {
       try {
        int nropag = degrosRepository.savePago(pago);
        return new ResponseEntity<>(Integer.toString(nropag), HttpStatus.CREATED);
       } catch (Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    @PutMapping(value="/actualizar")
    public ResponseEntity<String> updatePago(@RequestBody Pago pago){
      try {
        int resultado = degrosRepository.actualizarPago(pago);    
        return new ResponseEntity<>(Integer.toString(resultado), HttpStatus.OK);
      } catch (Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
     
      } 
    }

    @DeleteMapping(value="/eliminar", params={"id"})    
    public ResponseEntity<String> borrarPago(@RequestParam("id") Integer idpag){
      try {
        int nropag = degrosRepository.deletePago(idpag);
        return new ResponseEntity<>(Integer.toString(nropag),HttpStatus.OK);
      } catch (Exception e) {
        return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR );
      }

    }
 


  // ITEMS DE DETALLE DE COBRANZA

  @GetMapping(value="/detalle", params={"idpago"})
  public ResponseEntity<List<Detpago>> getAllDetPago(@RequestParam("idpago") Integer idpag) {
 try {
   List<Detpago> detpago = null;
         
   detpago = degrosRepository.AllDetPagoPorId(idpag);
 
   if (detpago.isEmpty()) {
     return new ResponseEntity<>(HttpStatus.NO_CONTENT);
   } else {
      return new ResponseEntity<>(detpago, HttpStatus.OK);
   }
 } catch (Exception e) {
    //return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
 }
}
@RequestMapping(value="/detalle/cuenta" ,params={"idpago"})
public int getCantDetPago(@RequestParam("idpago") Integer idpag){
   int cantl = degrosRepository.getCantDetPagosPorId(idpag);
   return cantl;
}

@PostMapping(value="/detalle/nuevo")
    // Graba un nuevo registro de Item de Pago
    public ResponseEntity<String> crearItemPago(@RequestBody Detpago detpago) {
       try {
        int nroitcob = degrosRepository.saveItemDetPago(detpago);
        return new ResponseEntity<>(Integer.toString(nroitcob), HttpStatus.CREATED);
       } catch (Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
      }
}
    
@PutMapping(value="/detalle/actualizar")
  public ResponseEntity<String> updateItemPago(@RequestBody Detpago detpago){
      try {
        int resu = degrosRepository.actualizarItemDetPago(detpago);    
        return new ResponseEntity<>(Integer.toString(resu), HttpStatus.OK);
      } catch (Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
     
      } 
}   
@RequestMapping(value ="/detalle" , params={"idpago","nroitem"} )
public ResponseEntity<Detpago> leerItemPago(@RequestParam("idpago") Integer idpag,
                                            @RequestParam("nroitem") Integer iditem ) {
  Detpago itpago = degrosRepository.findItemDetPago(idpag, iditem);
  if (itpago != null){
    return new ResponseEntity<>(itpago, HttpStatus.OK);
  } else {
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
}

}