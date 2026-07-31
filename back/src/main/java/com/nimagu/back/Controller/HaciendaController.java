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

import com.nimagu.back.Entidades.Campo;

import com.nimagu.back.Entidades.Hacienda;
import com.nimagu.back.Entidades.MovHacienda;
import com.nimagu.back.Repository.JdbcNimaguRepository;


@CrossOrigin(origins = "${FRONTEND_URL}")
@RestController
@RequestMapping("/api/hacienda")

public class HaciendaController {

    @Autowired
    JdbcNimaguRepository nimaguRepository;

    @SuppressWarnings("null")
    @GetMapping("/hacs")
    public ResponseEntity<List<Hacienda>> getGastos() {
    List<Hacienda> hac = null;
    try {                  
      hac = nimaguRepository.AllHacienda();
    
      if (hac.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } else {
         return new ResponseEntity<>(hac, HttpStatus.OK);
      }
    } catch (Exception e) {
       return new ResponseEntity<>(hac, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

   @PostMapping(value="/hac/nuevo")
    // Graba un nuevo gasto
    public ResponseEntity<String> crearHacienda(@RequestBody Hacienda hac) {
       try {
        int nrohac = nimaguRepository.saveHacienda(hac);
        return new ResponseEntity<>(Integer.toString(nrohac), HttpStatus.CREATED);
       } catch (Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

  @RequestMapping(value="/hac/maxid")
  public int getMaxHac(){
     int cantl = nimaguRepository.getMaxIdHacienda();
     return cantl;
  }
  @DeleteMapping(value="/hac/delete", params={"id"})    
    public ResponseEntity<String> borrarHacienda(@RequestParam("id") Integer idhac){
      try {
        int nrohac = nimaguRepository.deleteHacienda(idhac);
        return new ResponseEntity<>(Integer.toString(nrohac),HttpStatus.OK);
      } catch (Exception e) {
        return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR );
      }

    }

    @GetMapping("/campos")
    public ResponseEntity<List<Campo>> getCampos() {
    List<Campo> campos = null;
    try {                  
      campos = nimaguRepository.AllCampos();
    
      if (campos.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } else {
         return new ResponseEntity<>(campos, HttpStatus.OK);
      }
    } catch (Exception e) {
       return new ResponseEntity<>(campos, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

   @PostMapping(value="/campo/nuevo")
    // Graba un nuevo campo
    public ResponseEntity<String> crearCampo(@RequestBody Campo campo) {
       try {
        int nrocampo = nimaguRepository.saveCampo(campo);
        return new ResponseEntity<>(Integer.toString(nrocampo), HttpStatus.CREATED);
       } catch (Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

  @RequestMapping(value="/campo/maxid")
  public int getMaxCampo(){
     int cantl = nimaguRepository.getMaxIdCampo();
     return cantl;
  }
  @DeleteMapping(value="/campo/delete", params={"id"})    
    public ResponseEntity<String> borrarCampo(@RequestParam("id") Integer idcampo){
      try {
        int nrocampo = nimaguRepository.deleteCampo(idcampo);
        return new ResponseEntity<>(Integer.toString(nrocampo),HttpStatus.OK);
      } catch (Exception e) {
        return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR );
      }

    }

    @GetMapping("/movhs")
    public ResponseEntity<List<MovHacienda>> getMovsHacienda() {
    List<MovHacienda> movs = null;
    try {                  
      movs = nimaguRepository.AllMovsHacienda();
    
      if (movs.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } else {
         return new ResponseEntity<>(movs, HttpStatus.OK);
      }
    } catch (Exception e) {
       return new ResponseEntity<>(movs, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

   @PostMapping(value="/movh/nuevo")
    // Graba un nuevo Movimiento de Hacienda
    public ResponseEntity<String> crearMovHacienda(@RequestBody MovHacienda movh) {
       try {
        int nromovh = nimaguRepository.saveMovH(movh);
        return new ResponseEntity<>(Integer.toString(nromovh), HttpStatus.CREATED);
       } catch (Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

  @RequestMapping(value="/movh/maxid")
  public int getMaxMovH(){
     int cantl = nimaguRepository.getMaxIdMovH();
     return cantl;
  }

   @PutMapping(value="/movh/actualizar")
    public ResponseEntity<String> updateMovH(@RequestBody MovHacienda movh){
      try {
        int resultado = nimaguRepository.actualizarMovH(movh);    
        return new ResponseEntity<>(Integer.toString(resultado), HttpStatus.OK);
      } catch (Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
     
      } 
    }

  @DeleteMapping(value="/movh/delete", params={"id"})    
    public ResponseEntity<String> borrarMovH(@RequestParam("id") Integer idmovh){
      try {
        int nromovh = nimaguRepository.deleteMovH(idmovh);
        return new ResponseEntity<>(Integer.toString(nromovh),HttpStatus.OK);
      } catch (Exception e) {
        return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR );
      }

    }



}
