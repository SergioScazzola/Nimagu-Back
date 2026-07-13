package com.nimagu.back.Repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import com.nimagu.back.Entidades.CompVta;
import com.nimagu.back.Entidades.MovCta;






@Repository
public class JdbcNimaguRepository implements NimaguRepository{

 @Autowired
 private JdbcTemplate jdbcTemplate;
     
 @Override
 public List<CompVta> AllCompVtas() {   
        String selec = "SELECT * FROM compvtas ORDER BY fecha ASC";
        return jdbcTemplate.query(selec, BeanPropertyRowMapper.newInstance(CompVta.class));
}

 @Override
public List<CompVta> detalleCyFxFecha(String fechaini,String fechafin){
    String selec = "SELECT * FROM compvtas WHERE fecha BETWEEN ? AND ? ORDER BY fecha ASC";
    return jdbcTemplate.query(selec, BeanPropertyRowMapper.newInstance(CompVta.class),fechaini,fechafin);
}

@Override
public int getMaxCompVtas(){
        String consulta = "SELECT MAX(idcomvta) FROM compvtas";
     
        Object obj = jdbcTemplate.queryForObject(consulta,Integer.class);    
        if (obj==null){
          return 0;
        } else {
          return ((int)obj);
        }         
}   

@Override
public  CompVta  findCompVtaById(int idcomp){
  String q = "SELECT * FROM compvtas WHERE idcomvta=?";
  try {
  CompVta comp = jdbcTemplate.queryForObject(q,
       BeanPropertyRowMapper.newInstance(CompVta.class), idcomp);          
      return comp;
   } catch (IncorrectResultSizeDataAccessException e) {
      return null;
   }
}

@Override 
public int saveCompVta(CompVta comp){
        // Graba nueva Compra/Venta
        return jdbcTemplate.update("INSERT INTO compvtas(idcomvta,compvta,fecha,idprocli,nprovcli,"+
                                   "nroliq,categoria,cantidad,totalk,promedio,preunit,importe,"+
                                   "proced,observ) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
        new Object[] { comp.getIdcomvta(),comp.getCompvta(),comp.getFecha(),comp.getIdprocli(),
                       comp.getNprovcli(),comp.getNroliq(),comp.getCategoria(),comp.getCantidad(),
                       comp.getTotalk(),comp.getPromedio(),comp.getPreunit(),comp.getImporte(),
                       comp.getProced(),comp.getObserv()
        });
         
      }


 @Override
 public int actualizarCompVta(CompVta comp){      
      int resu = 0;
      try {                   
          resu = jdbcTemplate.update("UPDATE compvtas SET compvta=?,fecha=?,idprocli=?,nprovcli=?,"+
                                    "nroliq=?,categoria=?,cantidad=?,totalk=?,promedio=?,preunit=?,"+
                                    "importe=?,proced=?,observ=? WHERE idcomvta=?",
                                    
                    new Object[] { comp.getCompvta(),comp.getFecha(),comp.getIdprocli(),comp.getNprovcli(),
                                  comp.getNroliq(),comp.getCategoria(),comp.getCantidad(),comp.getTotalk(),
                                  comp.getPromedio(),comp.getPreunit(),comp.getImporte(),comp.getProced(),
                                  comp.getObserv(),comp.getIdcomvta()
                                });
        } catch (IncorrectResultSizeDataAccessException e) {
          return -3;
      }
      return resu; 
}
@Override
public int deleteCompVta(int idcomp){
        int resu = 0;
        try {
          resu = jdbcTemplate.update("DELETE FROM compvtas WHERE idcomvta="+idcomp);
        } catch (DataAccessException dae){
          resu = -5;   
        }
        return resu;
      }
      

}
