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


import com.nimagu.back.Entidades.Proveedor;
import com.nimagu.back.Entidades.Saldoprov;
import com.nimagu.back.Repository.JdbcDegrosRepository;

@CrossOrigin(origins = "${FRONTEND_URL}")
@RestController
@RequestMapping("/api/provs")
public class ProveedorController {


     @Autowired
    JdbcDegrosRepository degrosRepository;

    @SuppressWarnings("null")
    @GetMapping("/proveeds")
    public ResponseEntity<List<Proveedor>> getProveedores() {
    List<Proveedor> proves = null;
    try {                  
      proves = degrosRepository.AllProvs();
    
      if (proves.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } else {
         return new ResponseEntity<>(proves, HttpStatus.OK);
      }
    } catch (Exception e) {
       return new ResponseEntity<>(proves, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  @RequestMapping(value ="/prov" , params={"id"} )
  public ResponseEntity<Proveedor> getProvById(@RequestParam("id") Integer idprov) {
    Proveedor prov = degrosRepository.findProvById(idprov);
    if (prov != null){
      return new ResponseEntity<>(prov, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
    @PostMapping(value="/prov/nuevo")
    // Graba un nuevo proveedor
    public ResponseEntity<String> crearProv(@RequestBody Proveedor prov) {
       try {
        int nroprov = degrosRepository.saveProv(prov);
        return new ResponseEntity<>(Integer.toString(nroprov), HttpStatus.CREATED);
       } catch (Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }
    @PutMapping(value="/prov/actualizar")
    public ResponseEntity<String> updateProv(@RequestBody Proveedor prove){
      try {
        int resultado = degrosRepository.actualizarProv(prove);    
        return new ResponseEntity<>(Integer.toString(resultado), HttpStatus.OK);
      } catch (Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
     
      } 
    }

     @PutMapping(value="/prov/actsaldoini")
    public ResponseEntity<String> updateSaldoInicial(@RequestBody Saldoprov saldoprov){
      try {
        int resultado = degrosRepository.actSaldoIniProv(saldoprov);    
        return new ResponseEntity<>(Integer.toString(resultado), HttpStatus.OK);
      } catch (Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
     
      } 
    }

  @RequestMapping(value="/maxid")
  public int getMaxProvs(){
     int cantl = degrosRepository.getMaxIdProv();
     return cantl;
  }
  @DeleteMapping(value="/prov/delete", params={"id"})    
    public ResponseEntity<String> borrarProveedor(@RequestParam("id") Integer idprov){
      try {
        int nroprov = degrosRepository.deleteProveedor(idprov);
        return new ResponseEntity<>(Integer.toString(nroprov),HttpStatus.OK);
      } catch (Exception e) {
        return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR );
      }

    }
}
