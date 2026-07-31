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

import com.nimagu.back.Entidades.Producto;

import com.nimagu.back.Entidades.TipoProd;
import com.nimagu.back.Repository.JdbcNimaguRepository;


    @CrossOrigin(origins = "${FRONTEND_URL}")
    @RestController
    @RequestMapping("/api/prods")
public class ProductoController {


     @Autowired
    JdbcNimaguRepository nimaguRepository;

    @SuppressWarnings("null")
    @GetMapping("/prods")
    public ResponseEntity<List<Producto>> getProductos() {
    List<Producto> produs = null;
    try {                  
      produs = nimaguRepository.AllProds();
    
      if (produs.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } else {
         return new ResponseEntity<>(produs, HttpStatus.OK);
      }
    } catch (Exception e) {
       return new ResponseEntity<>(produs, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  @RequestMapping(value ="/prod" , params={"id"} )
  public ResponseEntity<Producto> getProdById(@RequestParam("id") Integer idprod) {
    Producto prod = nimaguRepository.findProdById(idprod);
    if (prod != null){
      return new ResponseEntity<>(prod, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
    @PostMapping(value="/prod/nuevo")
    // Graba un nuevo producto
    public ResponseEntity<String> crearProd(@RequestBody Producto prod) {
       try {
        int nroprod = nimaguRepository.saveProd(prod);
        return new ResponseEntity<>(Integer.toString(nroprod), HttpStatus.CREATED);
       } catch (Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }
    
    @PutMapping(value="/prod/actualizar")
    public ResponseEntity<String> updateProd(@RequestBody Producto prod){
      try {
        int resultado = nimaguRepository.actualizarProd(prod);    
        return new ResponseEntity<>(Integer.toString(resultado), HttpStatus.OK);
      } catch (Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
     
      } 
    }

  @RequestMapping(value="/maxid")
  public int getMaxProds(){
     int cantl = nimaguRepository.getMaxIdProd();
     return cantl;
  }
  @DeleteMapping(value="/prod/delete", params={"id"})    
    public ResponseEntity<String> borrarProducto(@RequestParam("id") Integer idprod){
      try {
        int nroprod = nimaguRepository.deleteProducto(idprod);
        return new ResponseEntity<>(Integer.toString(nroprod),HttpStatus.OK);
      } catch (Exception e) {
        return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR );
      }

    }

  // TIPOS DE PRODUCTO

  @GetMapping("/tiposprod")
    public ResponseEntity<List<TipoProd>> getTiposProducto() {
    List<TipoProd> tprods = null;
    try {                  
      tprods = nimaguRepository.AllTProds();
    
      if (tprods.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } else {
         return new ResponseEntity<>(tprods, HttpStatus.OK);
      }
    } catch (Exception e) {
       return new ResponseEntity<>(tprods, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }  

@RequestMapping(value="/tiposprod/maxid")
  public int getMaxTProd(){
     int cantl = nimaguRepository.getMaxIdTProd();
     return cantl;
  }

@PostMapping(value="/tiposprod/nuevo")
    // Graba un nuevo tipo de producto
    public ResponseEntity<String> crearTProd(@RequestBody TipoProd tprod) {
       try {
        int nrotprod = nimaguRepository.saveTProd(tprod);
        return new ResponseEntity<>(Integer.toString(nrotprod), HttpStatus.CREATED);
       } catch (Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }  

 @DeleteMapping(value="/tiposprod/delete", params={"id"})    
    public ResponseEntity<String> borrarTProducto(@RequestParam("id") Integer idtprod){
      try {
        int nrotprod = nimaguRepository.deleteTProducto(idtprod);
        return new ResponseEntity<>(Integer.toString(nrotprod),HttpStatus.OK);
      } catch (Exception e) {
        return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR );
      }

    }    
}
