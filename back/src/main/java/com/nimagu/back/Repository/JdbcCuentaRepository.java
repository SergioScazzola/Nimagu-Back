package com.nimagu.back.Repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nimagu.back.Entidades.CuentaB;
import com.nimagu.back.Entidades.Endoso;
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

  String selec = "SELECT COUNT(idCuenta) FROM cuentasb WHERE periodo=? AND cbu=?";
  int conta = 0;
  conta = jdbcTemplate.queryForObject(selec,Integer.class,per,cbuu);    
  if (conta==0){
    return 0;
  } else {
    return conta;
  }


}
@Override 
public int saveCuenta(CuentaB cuenta){
        // Graba nueva cuenta Bancaria
        return jdbcTemplate.update("INSERT INTO cuentasb(idCuenta,periodo,titular,banco,cbu,"+
                                   "fecsaldo,saldoini,saldofin,cantmovs,observ) "+
                                   "VALUES(?,?,?,?,?,?,?,?,?,?)",
        new Object[] { cuenta.getIdCuenta(),cuenta.getPeriodo(),cuenta.getTitular(),cuenta.getBanco(),cuenta.getCbu(),
                       cuenta.getFecsaldo(),cuenta.getSaldoini(),cuenta.getSaldofin(),
                       cuenta.getCantmovs(),cuenta.getObserv() });
         
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
                                    "fecsaldo=?,saldoini=?,saldofin=?,cantmovs=?,observ=?"+
                                    " WHERE idCuenta=?",
                    new Object[] { cuenta.getPeriodo(),cuenta.getTitular(),cuenta.getBanco(),
                                   cuenta.getCbu(),cuenta.getFecsaldo(),cuenta.getSaldoini(),
                                   cuenta.getSaldofin(),cuenta.getCantmovs(),
                                   cuenta.getObserv(),cuenta.getIdCuenta()                               
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
        return jdbcTemplate.update("INSERT INTO movcuenta(idCuenta,nromov,fecha,fechamov,cliprov,ingegre,"+
                                   "tipomov,nrocheque,descrip,nroliq,importe,coment,movvinc) "+
                                   "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)",
        new Object[] { movcuenta.getIdCuenta(),movcuenta.getNromov(),movcuenta.getFecha(),
                       movcuenta.getFechamov(),movcuenta.getCliprov(),movcuenta.getIngegre(),
                       movcuenta.getTipomov(),movcuenta.getNrocheque(),movcuenta.getDescrip(),
                       movcuenta.getNroliq(),movcuenta.getImporte(),movcuenta.getComent(),
                       movcuenta.getMovvinc() 
                               }); 
}

  @Override
  public int actualizarMovCuenta(MovCta movim){      
      int resu = 0;
      try {                   
    resu = jdbcTemplate.update("UPDATE movcuenta SET fecha=?,fechamov=?,cliprov=?,ingegre=?,"+
                                   "tipomov=?,nrocheque=?,descrip=?,nroliq=?,importe=?,"+
                                   "coment=?,movvinc=? WHERE idCuenta=? AND nromov=?",
                                 
            new Object[] {movim.getFecha(),movim.getFechamov(),movim.getCliprov(),
                          movim.getIngegre(),movim.getTipomov(),movim.getNrocheque(),
                          movim.getDescrip(),movim.getNroliq(),movim.getImporte(),
                          movim.getComent(),movim.getMovvinc(),
                          movim.getIdCuenta(),movim.getNromov()                               
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

// ENDOSOS
@Override
public List<Endoso> AllEndosos() {
    String q = "SELECT * FROM endoso";
    return jdbcTemplate.query(q, BeanPropertyRowMapper.newInstance(Endoso.class));
}

@Override
public List<Endoso> AllEndososXCuenta(int idcta) {
    String q = "SELECT * FROM endoso WHERE idCuenta = ?";
    return jdbcTemplate.query(q, BeanPropertyRowMapper.newInstance(Endoso.class), idcta);
}

@Override
public int getMaxEndososCta(int idcta) {
    String q = "SELECT MAX(nromov) FROM endoso WHERE idCuenta = ?";
    return jdbcTemplate.queryForObject(q, Integer.class, idcta);
}

@Override
public Endoso findEndosoById(int idcuenta, int idmovim) {
    String q = "SELECT * FROM endoso WHERE idCuenta = ? AND nromov = ?";
    try {
        return jdbcTemplate.queryForObject(q, BeanPropertyRowMapper.newInstance(Endoso.class), idcuenta, idmovim);
    } catch (IncorrectResultSizeDataAccessException e) {
        return null;
    }
}

@Override
public int saveEndoso(Endoso endoso) {
    return jdbcTemplate.update("INSERT INTO endoso(idCuenta,nromov,fecha,nrocheque,idprov,"+
                               "descrip) VALUES(?,?,?,?,?,?)",
            new Object[] { endoso.getIdCuenta(), endoso.getNromov(), endoso.getFecha(),
                    endoso.getNrocheque(), endoso.getIdprov(), endoso.getDescrip()
            });
}

@Override
public int actualizarEndoso(Endoso endoso) {
    return jdbcTemplate.update("UPDATE endoso SET fecha=?,nrocheque=?,idprov=?,descrip=? "+
                               "WHERE idCuenta=? AND nromov=?",
            new Object[] { endoso.getFecha(), endoso.getNrocheque(),endoso.getIdprov(),
                           endoso.getDescrip(),endoso.getIdCuenta(),endoso.getNromov() });
}

@Override
public int deleteEndoso(int idcuenta, int idmovim) {
    return jdbcTemplate.update("DELETE FROM endoso WHERE idCuenta=? AND nromov=?", idcuenta, idmovim);
}
}
