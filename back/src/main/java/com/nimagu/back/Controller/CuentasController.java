package com.nimagu.back.Controller;


import com.nimagu.back.Entidades.CuentaB;

import com.nimagu.back.Entidades.MovCta;
import com.nimagu.back.Repository.DegrosCuentaRepository;

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
@RequestMapping("/api")

public class CuentasController {




    @Autowired
    DegrosCuentaRepository degrosctarepo;

    @SuppressWarnings("null")
    @GetMapping("/cuentasb")
    public ResponseEntity<List<CuentaB>> getAllCuentasb() {
    List<CuentaB> cuentas = null;
    try {
                  
      cuentas = degrosctarepo.AllCuentasb();
    
      if (cuentas.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } else {
         return new ResponseEntity<>(cuentas, HttpStatus.OK);
      }
    } catch (Exception e) {
       return new ResponseEntity<>(cuentas, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @RequestMapping(value="/cuentasb/max")
  public int getCantidadCuentas(){
     int cantc = degrosctarepo.getMaxCuentas();
     return cantc;
  }

   @RequestMapping(value="/cuentasb/existecbuper",params={"periodo","cbu"})
  public int getExisteCbuPeriodo(@RequestParam("periodo") String per,
                                     @RequestParam("cbu") String cbuu){
     int ncta = degrosctarepo.getExisteCBUPer(per,cbuu);
     return ncta;
  }
  @RequestMapping(value ="/cuentab" , params={"id"} )
  public ResponseEntity<CuentaB> getCuentaById(@RequestParam("id") Integer idcuenta) {
    CuentaB cuenta = degrosctarepo.findCuentaById(idcuenta);
    if (cuenta != null){
      return new ResponseEntity<>(cuenta, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
    @PostMapping(value="/cuentab/nuevo")
    // Graba una nueva cuenta
    public ResponseEntity<String> crearCuenta(@RequestBody CuentaB cuenta) {
       try {
        int nrocuenta = degrosctarepo.saveCuenta(cuenta);
        return new ResponseEntity<>(Integer.toString(nrocuenta), HttpStatus.CREATED);
       } catch (Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }
    @PutMapping(value="/cuentab/actualizar")
    public ResponseEntity<String> updateCuenta(@RequestBody CuentaB cuenta){
      try {
        int resultado = degrosctarepo.actualizarCuenta(cuenta);    
        return new ResponseEntity<>(Integer.toString(resultado), HttpStatus.OK);
      } catch (Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
     
      } 
    }
    @DeleteMapping(value="/cuentab/borrar", params={"id"})    
    public ResponseEntity<String> borrarCuentaB(@RequestParam("id") Integer idcuenta){
      try {
        int nrocta = degrosctarepo.deleteCuentaB(idcuenta);
        return new ResponseEntity<>(Integer.toString(nrocta),HttpStatus.OK);
      } catch (Exception e) {
        return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR );
      }

    }

   // Movimientos en Cuentas Bancarias
   // Devuelve el detalle de la cuenta "idcuenta" entre las fechas : "feci" y "fecf"
   @GetMapping(value="/cuentasb/detalle",params={"idcuenta","feci","fecf"})
    public ResponseEntity<List<MovCta>> getDetalleCuenta(   @RequestParam("idcuenta") int  idcta,
                                                            @RequestParam("feci") String fechaini,
                                                            @RequestParam("fecf") String fechafin) {
    try {
      List<MovCta> movims = null;
            
      movims = degrosctarepo.detalleCuenta(idcta,fechaini,fechafin);
    
      if (movims.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } else {
         return new ResponseEntity<>(movims, HttpStatus.OK);
      }
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  @RequestMapping(value ="/cuentab" , params={"idcuenta","idmov"} )
  public ResponseEntity<MovCta> getMovCuentaById( @RequestParam("idcuenta") Integer idcta,
                                                  @RequestParam("idmov") Integer idmovim) {
    MovCta movcuenta = degrosctarepo.findMovCuentaById(idcta,idmovim);
    if (movcuenta != null){
      return new ResponseEntity<>(movcuenta, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
 @RequestMapping(value ="/cuentab/maxmov" , params={"idcuenta"} )
  // Devuelve el nro del último movimiento registrado en la cuenta "idcuenta"
  public int getMaxMovCuenta( @RequestParam("idcuenta") Integer idcta   ) {
    int maxmov =  degrosctarepo.getMaxMovCta(idcta);
    return maxmov;
    }
@PostMapping(value="/cuentab/nuevomov")
    // Graba una nueva movimiento en la cuenta bancaria
    public ResponseEntity<String> crearMovCuenta(@RequestBody MovCta movcuenta) {
       try {
        int nrocuenta = degrosctarepo.saveMovCuenta(movcuenta);
        return new ResponseEntity<>(Integer.toString(nrocuenta), HttpStatus.CREATED);
       } catch (Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
      }
}
@PutMapping(value="/cuentab/actmov")
public ResponseEntity<String> updateMovCuenta(@RequestBody MovCta movcuenta){
try {
   int resultado = degrosctarepo.actualizarMovCuenta(movcuenta);    
   return new ResponseEntity<>(Integer.toString(resultado), HttpStatus.OK);
} catch (Exception e) {
   return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
     
} 
}

@DeleteMapping(value="/cuentab/delmov", params={"idcuenta","idmov"})    
public ResponseEntity<String> borrarMovCuenta(@RequestParam("idcuenta") Integer idcta,
                                              @RequestParam("idmov") Integer idmovim){
try {
   int nromov = degrosctarepo.deleteMovCuenta(idcta,idmovim);
   return new ResponseEntity<>(Integer.toString(nromov),HttpStatus.OK);
} catch (Exception e) {
   return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR );
}

}




}
