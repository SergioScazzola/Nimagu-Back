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
import com.nimagu.back.Entidades.PagoComp;

import com.nimagu.back.Entidades.Detpago;
import com.nimagu.back.Entidades.Dpagxprov;
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
    public ResponseEntity<String> crearPago(@RequestBody PagoComp pago) {
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
 
@RequestMapping(value ="/detPagXProv" , params={"idprov","feci","fecf"} )
  public ResponseEntity<List<Dpagxprov>> getDetPagoXCli(@RequestParam("idprov") Integer idpro,
                                                        @RequestParam("feci") String fechi,
                                                        @RequestParam("fecf") String fechf) {
   
   List<Dpagxprov> detpago = null;                                                      
  try {
           
   detpago = degrosRepository.DetPagoPorProvyF(idpro,fechi,fechf);
 
   if (detpago.isEmpty()) {
     return new ResponseEntity<>(HttpStatus.NO_CONTENT);
   } else {
      return new ResponseEntity<>(detpago, HttpStatus.OK);
   }
 } catch (Exception e) {
    //return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    return new ResponseEntity<>(detpago,HttpStatus.INTERNAL_SERVER_ERROR);
 }
}     

  // ITEMS DE DETALLE DE PAGOS

  @GetMapping(value="/detalle", params={"idpago","ctadestino"})
   // si ctadestino = 1 : devuelve todos los items, si es = 0 devuelve lo NO transferido : 
    // ctadestino = 0 
  public ResponseEntity<List<Detpago>> getAllDetPago(@RequestParam("idpago") Integer idpag,
                                                     @RequestParam("ctadestino") Integer ctad) {
 try {
   List<Detpago> detpago = null;
         
   detpago = degrosRepository.AllDetPagoPorId(idpag,ctad);
 
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

@PutMapping(value="/detalle/actctad", params={"idpago","nroitem","ctad"} )
// Actualiza el item de cobranza con el nro.de cuenta "ctad" al cual fue transferido
  public ResponseEntity<String> updateCtaDestinoPag(@RequestParam("idpago") Integer idpag,
                                                 @RequestParam("nroitem") Integer iditem,
                                                @RequestParam("ctad")   Integer ctadestino){
      try {
        int resu = degrosRepository.actualizarCtaDestinoPag(idpag,iditem,ctadestino);    
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