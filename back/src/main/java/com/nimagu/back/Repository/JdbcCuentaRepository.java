package com.nimagu.back.Repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nimagu.back.Entidades.CuentaB;

import com.nimagu.back.Entidades.MovCta;





@Repository
public class JdbcCuentaRepository implements DegrosCuentaRepository{

 @Autowired
 private JdbcTemplate jdbcTemplate;
     
 @Override
 public List<CuentaB> AllCuentasb() {   
        String selec = "SELECT * FROM cuentasb ORDER BY banco ASC";
        return jdbcTemplate.query(selec, BeanPropertyRowMapper.newInstance(CuentaB.class));
}
 @Override
      public int getMaxCuentas(){
        String consulta = "SELECT MAX(idCuenta) FROM cuentasb";
     
        Object obj = jdbcTemplate.queryForObject(consulta,Integer.class);    
        if (obj==null){
          return 0;
        } else {
          return ((int)obj);
        }         
      }   

  @Override
  public  int getMaxMovCta(int idcta){
    String consulta = "SELECT MAX(nromov) FROM movcuenta WHERE idCuenta=?";
     
    Object obj = jdbcTemplate.queryForObject(consulta,Integer.class,idcta);    
    if (obj==null){
       return 0;
    } else {
       return ((int)obj);
    }         
  }
@Override
public int getExisteCBUPer(String per, String cbuu){

  String selec = "SELECT idCuenta FROM cuentasb WHERE periodo=? AND cbu=?";
  Integer conta = jdbcTemplate.queryForObject(selec,Integer.class,per,cbuu);    
  if (conta==null || conta==0){
    return 0;
  } else {
    return conta;
  }


}
@Override 
public int saveCuenta(CuentaB cuenta){
        // Graba nueva cuenta Bancaria
        return jdbcTemplate.update("INSERT INTO cuentasb(idCuenta,periodo,titular,banco,cbu,"+
                                   "fecsaldo,saldoini,saldofin,observ) "+
                                   "VALUES(?,?,?,?,?,?,?,?,?)",
        new Object[] { cuenta.getIdCuenta(),cuenta.getPeriodo(),cuenta.getTitular(),cuenta.getBanco(),cuenta.getCbu(),
                       cuenta.getFecsaldo(),cuenta.getSaldoini(),cuenta.getSaldofin(),cuenta.getObserv() });
         
      }

@Override
public CuentaB findCuentaById(int idcuenta) {
        String q = "SELECT * FROM cuentasb WHERE idCuenta=?";
        try {
          CuentaB cuenta = jdbcTemplate.queryForObject(q,
              BeanPropertyRowMapper.newInstance(CuentaB.class), idcuenta);          
          return cuenta;
        } catch (IncorrectResultSizeDataAccessException e) {
          return null;
        }
      }


 @Override
 public int actualizarCuenta(CuentaB cuenta){      
      int resu = 0;
      try {                   
          resu = jdbcTemplate.update("UPDATE cuentasb SET periodo=?,titular=?,banco=?,cbu=?,"+
                                    "fecsaldo=?,saldoini=?,saldofin=?,observ=?"+
                                    " WHERE idCuenta=?",
                    new Object[] { cuenta.getPeriodo(),cuenta.getTitular(),cuenta.getBanco(),
                                   cuenta.getCbu(),cuenta.getFecsaldo(),cuenta.getSaldoini(),
                                   cuenta.getSaldofin(),cuenta.getObserv(),cuenta.getIdCuenta()                               
                                });
        } catch (IncorrectResultSizeDataAccessException e) {
          return -3;
      }
      return resu; 
}
@Override
public int deleteCuentaB(int idcuenta){
        int resu = 0;
        try {
          resu = jdbcTemplate.update("DELETE FROM cuentasb WHERE idCuenta="+idcuenta);
        } catch (DataAccessException dae){
          resu = -5;   
        }
        return resu;
      }
      
// Movimientos de cuentas bancarias
@Override
// Devuelve el detalle de la cuenta "idcta" entre "fechaini" y "fechafin"
public List<MovCta>  detalleCuenta(int idcta, String fechaini, String fechafin){
   String selec = "SELECT * FROM movcuenta WHERE idCuenta=? AND fechamov BETWEEN ? AND ? ORDER BY fechamov ASC";
        return jdbcTemplate.query(selec, BeanPropertyRowMapper.newInstance(MovCta.class),idcta,fechaini,fechafin);
   }   


@Override
public MovCta findMovCuentaById(int idcta, int idmovim){
  String q = "SELECT * FROM movcuenta WHERE idCuenta=? AND nromov=?";
  try {
    return jdbcTemplate.queryForObject(q,BeanPropertyRowMapper.newInstance(MovCta.class), idcta,idmovim);          
 
  } catch (IncorrectResultSizeDataAccessException e) {
     return null;
  }
}

@Override
public int saveMovCuenta(MovCta movcuenta){
  // Graba nuevo movimiento en cuenta bancaria
        return jdbcTemplate.update("INSERT INTO movcuenta(idCuenta,nromov,fechamov,ingegre,tipocomp,"+
                                   "comprob,concepto,importe,coment) VALUES(?,?,?,?,?,?,?,?,?)",
        new Object[] { movcuenta.getIdCuenta(),movcuenta.getNromov(),movcuenta.getFechamov(),
                       movcuenta.getIngegre(),movcuenta.getTipocomp(),movcuenta.getComprob(),
                       movcuenta.getConcepto(),movcuenta.getImporte(),movcuenta.getComent() 
         }); 
}

  @Override
  public int actualizarMovCuenta(MovCta movim){      
      int resu = 0;
      try {                   
          resu = jdbcTemplate.update("UPDATE movcuenta SET fechamov=?,ingegre=?,tipocomp=?,"+
                                    "comprob=?,concepto=?,importe=?,coment=?"+
                                    " WHERE idCuenta=? AND nromov=?",
                    new Object[] { movim.getFechamov(),movim.getIngegre(),movim.getTipocomp(),
                                   movim.getComprob(),movim.getConcepto(),movim.getImporte(),
                                   movim.getComent(),movim.getIdCuenta(),movim.getNromov()
                                });
        } catch (IncorrectResultSizeDataAccessException e) {
          return -3;
      }
      return resu; 
      }

  @Override
  public int deleteMovCuenta(int idcta,int idmovim){
        int resu = 0;
        try {
          resu = jdbcTemplate.update("DELETE FROM movcuenta WHERE idCuenta=? AND nromov=?",idcta,idmovim);
        } catch (DataAccessException dae){
          resu = -5;   
        }
        return resu;
      }
}
