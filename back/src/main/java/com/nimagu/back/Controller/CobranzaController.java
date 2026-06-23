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

import com.nimagu.back.Entidades.Cobranza;
import com.nimagu.back.Entidades.Detcobro;
import com.nimagu.back.Repository.JdbcDegrosRepository;

@CrossOrigin(origins = "${FRONTEND_URL}")
@RestController
@RequestMapping("/api/cobranza")
public class CobranzaController {


     @Autowired
    JdbcDegrosRepository degrosRepository;
    @SuppressWarnings("null")
    @GetMapping("/cobranzas")
     public ResponseEntity<List<Cobranza>> getAllCobranza() {
      List<Cobranza> cobranza = null;
    try {    
            
      cobranza = degrosRepository.AllCobranza();
    
      if (cobranza.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } else {
         return new ResponseEntity<>(cobranza, HttpStatus.OK);
      }
    } catch (Exception e) {
       return new ResponseEntity<>(cobranza, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping(value="/cobxcliente", params={"idcliente"})
  public ResponseEntity<List<Cobranza>> getAllCobranzaPorCliente(@RequestParam("idcliente") Integer idcli) {
    List<Cobranza> cobranzas = null;
    try {         
      cobranzas = degrosRepository.AllCobranzaPorCliente(idcli);
 
   if (cobranzas.isEmpty()) {
     return new ResponseEntity<>(HttpStatus.NO_CONTENT);
   } else {
      return new ResponseEntity<>(cobranzas, HttpStatus.OK);
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
  public int getCantidadCobranza(){
     int cantl = degrosRepository.getMaxCobranza();
     return cantl;
  }
  @RequestMapping(value ="/cobranza" , params={"id"} )
  public ResponseEntity<Cobranza> leerCobranzaById(@RequestParam("id") Integer idcob) {
    Cobranza cobranza = degrosRepository.findCobranzaById(idcob);
    if (cobranza != null){
      return new ResponseEntity<>(cobranza, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
    @PostMapping(value="/nuevo")
    // Graba un nuevo registro de Cobranza
    public ResponseEntity<String> crearCobranza(@RequestBody Cobranza cobranza) {
       try {
        int nrocob = degrosRepository.saveCobranza(cobranza);
        return new ResponseEntity<>(Integer.toString(nrocob), HttpStatus.CREATED);
       } catch (Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    @PutMapping(value="/actualizar")
    public ResponseEntity<String> updateCobranza(@RequestBody Cobranza cobranza){
      try {
        int resultado = degrosRepository.actualizarCobranza(cobranza);    
        return new ResponseEntity<>(Integer.toString(resultado), HttpStatus.OK);
      } catch (Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
     
      } 
    }

    @DeleteMapping(value="/eliminar", params={"id"})    
    public ResponseEntity<String> borrarCobranza(@RequestParam("id") Integer idcob){
      try {
        int nrocob = degrosRepository.deleteCobranza(idcob);
        return new ResponseEntity<>(Integer.toString(nrocob),HttpStatus.OK);
      } catch (Exception e) {
        return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR );
      }

    }
 


  // ITEMS DE DETALLE DE COBRANZA

    @GetMapping(value="/detalle", params={"idcobro"})
  public ResponseEntity<List<Detcobro>> getAllDetCobro(@RequestParam("idcobro") Integer idcob) {
 try {
   List<Detcobro> detcobro = null;
         
   detcobro = degrosRepository.AllDetCobroPorId(idcob);
 
   if (detcobro.isEmpty()) {
     return new ResponseEntity<>(HttpStatus.NO_CONTENT);
   } else {
      return new ResponseEntity<>(detcobro, HttpStatus.OK);
   }
 } catch (Exception e) {
    //return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
 }
}
@RequestMapping(value="/detalle/cuenta" ,params={"idcobro"})
public int getCantDetCobro(@RequestParam("idcobro") Integer idcob){
   int cantl = degrosRepository.getCantDetCobrosPorId(idcob);
   return cantl;
}

@PostMapping(value="/detalle/nuevo")
    // Graba un nuevo registro de Item de Cobranza
    public ResponseEntity<String> crearItemCobranza(@RequestBody Detcobro detcobro) {
       try {
        int nroitcob = degrosRepository.saveItemDetCobro(detcobro);
        return new ResponseEntity<>(Integer.toString(nroitcob), HttpStatus.CREATED);
       } catch (Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
      }
}
    
@PutMapping(value="/detalle/actualizar")
  public ResponseEntity<String> updateItemCobranza(@RequestBody Detcobro detcobro){
      try {
        int resu = degrosRepository.actualizarItemDetCobro(detcobro);    
        return new ResponseEntity<>(Integer.toString(resu), HttpStatus.OK);
      } catch (Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
     
      } 
}   
@RequestMapping(value ="/detalle" , params={"idcobro","nroitem"} )
public ResponseEntity<Detcobro> leerItemCobranza(@RequestParam("idcobro") Integer idcob,
                                                 @RequestParam("nroitem") Integer iditem ) {
  Detcobro itcobro = degrosRepository.findItemDetCobro(idcob, iditem);
  if (itcobro != null){
    return new ResponseEntity<>(itcobro, HttpStatus.OK);
  } else {
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
}

}