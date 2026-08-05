package com.nimagu.back.Repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nimagu.back.Entidades.Campo;
import com.nimagu.back.Entidades.CompVta;

import com.nimagu.back.Entidades.Gasto;
import com.nimagu.back.Entidades.Hacienda;
import com.nimagu.back.Entidades.MovHacienda;
import com.nimagu.back.Entidades.Producto;
import com.nimagu.back.Entidades.TipoProd;







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
public List<CompVta> detalleCVyFecha(String fechaini,String fechafin){
    String selec = "SELECT * FROM compvtas WHERE fecha BETWEEN ? AND ? ORDER BY compvta ASC,fecha ASC";
    return jdbcTemplate.query(selec, BeanPropertyRowMapper.newInstance(CompVta.class),fechaini,fechafin);
}

@Override
public List<CompVta> detalleCliProvyFecha(String fechaini,String fechafin){
    String selec = "SELECT * FROM compvtas WHERE fecha BETWEEN ? AND ? ORDER BY nprovcli ASC,fecha ASC";
    return jdbcTemplate.query(selec, BeanPropertyRowMapper.newInstance(CompVta.class),fechaini,fechafin);
}

@Override
public List<CompVta> detalleCatyFecha(String fechaini,String fechafin){
    String selec = "SELECT * FROM compvtas WHERE fecha BETWEEN ? AND ? ORDER BY categoria ASC,fecha ASC";
    return jdbcTemplate.query(selec, BeanPropertyRowMapper.newInstance(CompVta.class),fechaini,fechafin);
}

@Override
public List<CompVta> detalleProcyFecha(String fechaini,String fechafin){
    String selec = "SELECT * FROM compvtas WHERE fecha BETWEEN ? AND ? ORDER BY proced ASC,fecha ASC";
    return jdbcTemplate.query(selec, BeanPropertyRowMapper.newInstance(CompVta.class),fechaini,fechafin);
}

@Override
public List<CompVta> detalleporCYV(String fechaini,String fechafin){
    String selec = "SELECT * FROM compvtas WHERE fecha BETWEEN ? AND ? ORDER BY compvta ASC,fecha ASC";
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
      
// PRODUCTOS

 @Override
 public List<Producto> AllProds() {   
        String selec = "SELECT * FROM productos ORDER BY nombre ASC";
        return jdbcTemplate.query(selec, BeanPropertyRowMapper.newInstance(Producto.class));
}

@Override
public  Producto  findProdById(int idprod){
  String q = "SELECT * FROM productos WHERE idproducto=?";
  try {
  Producto prod = jdbcTemplate.queryForObject(q,
       BeanPropertyRowMapper.newInstance(Producto.class), idprod);          
      return prod;
   } catch (IncorrectResultSizeDataAccessException e) {
      return null;
   }
}
@Override 
public int saveProd(Producto prod){
        // Graba nuevo Producto
        return jdbcTemplate.update("INSERT INTO productos(idproducto,nombre,idtipo,tipoprod) VALUES(?,?,?,?)",
        new Object[] { prod.getIdproducto(),prod.getNombre(),prod.getIdtipo(),prod.getTipoprod()
        });
         
}
 @Override
 public int actualizarProd(Producto prod){      
      int resu = 0;
      try {                   
          resu = jdbcTemplate.update("UPDATE productos SET nombre=?,idtipo=?,tipoprod=? "+
                                    "WHERE idproducto=?",
                                    
                    new Object[] { prod.getNombre(),prod.getIdtipo(),prod.getTipoprod(),prod.getIdproducto()
                                });
        } catch (IncorrectResultSizeDataAccessException e) {
          return -3;
      }
      return resu; 
}
@Override
public int getMaxIdProd(){
        String consulta = "SELECT MAX(idproducto) FROM productos";
     
        Object obj = jdbcTemplate.queryForObject(consulta,Integer.class);    
        if (obj==null){
          return 0;
        } else {
          return ((int)obj);
        }         
}   

@Override
public int deleteProducto(int idprod){
        int resu = 0;
        try {
          resu = jdbcTemplate.update("DELETE FROM productos WHERE idproducto="+idprod);
        } catch (DataAccessException dae){
          resu = -5;   
        }
        return resu;
      }

// TIPOS DE PRODUCTO

 @Override
 public List<TipoProd> AllTProds() {   
        String selec = "SELECT * FROM tiposprod ORDER BY nombre ASC";
        return jdbcTemplate.query(selec, BeanPropertyRowMapper.newInstance(TipoProd.class));
}

@Override
public int getMaxIdTProd(){
        String consulta = "SELECT MAX(idtipoprod) FROM tiposprod";
     
        Object obj = jdbcTemplate.queryForObject(consulta,Integer.class);    
        if (obj==null){
          return 0;
        } else {
          return ((int)obj);
        }         
}   

@Override 
public int saveTProd(TipoProd tprod){
        // Graba nuevo Tipo de producto
        return jdbcTemplate.update("INSERT INTO tiposprod(idtipoprod,nombre) VALUES(?,?)",
        new Object[] { tprod.getIdtipoprod(),tprod.getNombre()
        });         
}

@Override
public int deleteTProducto(int idtprod){
        int resu = 0;
        try {
          resu = jdbcTemplate.update("DELETE FROM tiposprod WHERE idtipoprod="+idtprod);
        } catch (DataAccessException dae){
          resu = -5;   
        }
        return resu;
      }

// GASTOS

 @Override
 public List<Gasto> AllGastos() {   
        String selec = "SELECT * FROM gastos ORDER BY fecha ASC";
        return jdbcTemplate.query(selec, BeanPropertyRowMapper.newInstance(Gasto.class));
}

@Override
public int getMaxIdGasto(){
        String consulta = "SELECT MAX(idgasto) FROM gastos";
     
        Object obj = jdbcTemplate.queryForObject(consulta,Integer.class);    
        if (obj==null){
          return 0;
        } else {
          return ((int)obj);
        }         
}   
@Override
public  Gasto findGastoById(int idgasto){
  String q = "SELECT * FROM gastos WHERE idgasto=?";
  try {
 Gasto gasto = jdbcTemplate.queryForObject(q,
       BeanPropertyRowMapper.newInstance(Gasto.class), idgasto);          
      return gasto;
   } catch (IncorrectResultSizeDataAccessException e) {
      return null;
   }
}

@Override 
public int saveGasto(Gasto gasto){
        // Graba nuevo Gasto
        return jdbcTemplate.update("INSERT INTO gastos(idgasto,fecha,idproducto,nprod,idtipo,ntipo,"+           
                                   "idprov,nprov,ncomp,cantidad,precioun,tiva,importe,"+
                                   "observ) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
        new Object[] { gasto.getIdgasto(),gasto.getFecha(),gasto.getIdproducto(),gasto.getNprod(),
                       gasto.getIdtipo(),gasto.getNtipo(),gasto.getIdprov(),gasto.getNprov(),
                       gasto.getNcomp(),gasto.getCantidad(),gasto.getPrecioun(),gasto.getTiva(),
                       gasto.getImporte(),gasto.getObserv() });
}                       

 @Override
 public int actualizarGasto(Gasto gasto){      
      int resu = 0;
      try {                   
          resu = jdbcTemplate.update("UPDATE gastos SET fecha=?,idproducto=?,nprod=?,idtipo=?,ntipo=?,"+           
                                   "idprov=?,nprov=?,ncomp=?,cantidad=?,precioun=?,tiva=?,importe=?,"+
                                   "observ=? WHERE idgasto=?",
                    new Object[] {gasto.getFecha(),gasto.getIdproducto(),gasto.getNprod(),
                       gasto.getIdtipo(),gasto.getNtipo(),gasto.getIdprov(),gasto.getNprov(),
                       gasto.getNcomp(),gasto.getCantidad(),gasto.getPrecioun(),gasto.getTiva(),
                       gasto.getImporte(),gasto.getObserv(),gasto.getIdgasto()
                                });
        } catch (IncorrectResultSizeDataAccessException e) {
          return -3;
      }
      return resu; 
}
@Override
public int deleteGasto(int idgasto){
        int resu = 0;
        try {
          resu = jdbcTemplate.update("DELETE FROM gastos WHERE idgasto="+idgasto);
        } catch (DataAccessException dae){
          resu = -5;   
        }
        return resu;
      }


// HACIENDA 

@Override
 public List<Hacienda> AllHacienda() {   
        String selec = "SELECT * FROM hacienda ORDER BY nombre ASC";
        return jdbcTemplate.query(selec, BeanPropertyRowMapper.newInstance(Hacienda.class));
}

@Override 
public int saveHacienda(Hacienda hac){
        // Graba nueva Hacienda
        return jdbcTemplate.update("INSERT INTO hacienda(idhacienda,nombre) VALUES(?,?)",
        new Object[] { hac.getIdhacienda(),hac.getNombre()
        });         
}
@Override
public Hacienda findHaciendaById(int idhacienda) {
        String q = "SELECT * FROM hacienda WHERE idhacienda=?";
        try {
          Hacienda hacienda = jdbcTemplate.queryForObject(q,
              BeanPropertyRowMapper.newInstance(Hacienda.class), idhacienda);          
          return hacienda;
        } catch (IncorrectResultSizeDataAccessException e) {
          return null;
        }
}
@Override
public int getMaxIdHacienda(){
    String consulta = "SELECT MAX(idhacienda) FROM hacienda";   
    Object obj = jdbcTemplate.queryForObject(consulta,Integer.class);    
    if (obj==null){
          return 0;
        } else {
          return ((int)obj);
        }         
}   

@Override
public int deleteHacienda(int idhac){
        int resu = 0;
        try {
          resu = jdbcTemplate.update("DELETE FROM hacienda WHERE idhacienda="+idhac);
        } catch (DataAccessException dae){
          resu = -5;   
        }
        return resu;
}

@Override
 public List<Campo> AllCampos() {   
        String selec = "SELECT * FROM campos ORDER BY nombre ASC";
        return jdbcTemplate.query(selec, BeanPropertyRowMapper.newInstance(Campo.class));
}

@Override 
public int saveCampo(Campo campo){
        // Graba nuevo Campo
        return jdbcTemplate.update("INSERT INTO campos(idcampo,nombre,abrev,proced) VALUES(?,?,?,?)",
        new Object[] {campo.getIdcampo(),campo.getNombre(),campo.getAbrev(),campo.getProced()
        });         
}
@Override
public Campo findCampoById(int idcampo) {
        String q = "SELECT * FROM campos WHERE idcampo=?";
        try {
          Campo campo = jdbcTemplate.queryForObject(q,
              BeanPropertyRowMapper.newInstance(Campo.class), idcampo);          
          return campo;
        } catch (IncorrectResultSizeDataAccessException e) {
          return null;
        }
}
@Override
 public int actualizarCampo(Campo campo){
      int resu = 0;
      try {                   
          resu = jdbcTemplate.update("UPDATE campos SET nombre=?,abrev=?,proced=? WHERE idcampo=?",
                    new Object[] {campo.getNombre(),campo.getAbrev(),campo.getProced(),campo.getIdcampo()
                                });
        } catch (IncorrectResultSizeDataAccessException e) {
          return -3;
      }
      return resu; 
 }
 
@Override
public int getMaxIdCampo(){
        String consulta = "SELECT MAX(idcampo) FROM campos";
     
        Object obj = jdbcTemplate.queryForObject(consulta,Integer.class);    
        if (obj==null){
          return 0;
        } else {
          return ((int)obj);
        }         
}  

@Override
public int deleteCampo(int idcampo){
        int resu = 0;
        try {
          resu = jdbcTemplate.update("DELETE FROM campos WHERE idcampo="+idcampo);
        } catch (DataAccessException dae){
          resu = -5;   
        }
        return resu;
}

@Override
 public List<MovHacienda> AllMovsHacienda() {   
        String selec = "SELECT * FROM movhacienda ORDER BY fecha ASC";
        return jdbcTemplate.query(selec, BeanPropertyRowMapper.newInstance(MovHacienda.class));
}

@Override 
// Lista de Movimientos de Hacienda entre dos fechas, para armar matriz de informe
// ordenado por abreviatura de CAMPO
public List<MovHacienda> detalleMovHxFecha(String fechaini, String fechafin){
    String selec = "SELECT * FROM movhacienda WHERE fecha BETWEEN ? AND ? ORDER BY abrev ASC";
    return jdbcTemplate.query(selec, BeanPropertyRowMapper.newInstance(MovHacienda.class),fechaini,fechafin);
}

@Override 
public int saveMovH(MovHacienda movh){
        // Graba nuevo Movimiento de Hacienda
        return jdbcTemplate.update("INSERT INTO movhacienda(idmovh,fecha,idhacienda,nhacienda,"+                   
                                   "cantidad,idcampo,ncampo,abrev,observ,marca1,marca2,marca3) "+
                                   "VALUES(?,?,?,?,?,?,?,?,?,?,?,?)",
        new Object[] {movh.getIdmovh(),movh.getFecha(),movh.getIdhacienda(),movh.getNhacienda(),
                      movh.getCantidad(),movh.getIdcampo(),movh.getNcampo(),movh.getAbrev(),movh.getObserv(),
                      movh.getMarca1(),movh.getMarca2(),movh.getMarca3()
        });         
}

@Override
public  MovHacienda  findMovHById(int idmovh){
  String q = "SELECT * FROM movhacienda WHERE idmovh=?";
  try {
 MovHacienda movh = jdbcTemplate.queryForObject(q,
       BeanPropertyRowMapper.newInstance(MovHacienda.class), idmovh);          
      return movh;
   } catch (IncorrectResultSizeDataAccessException e) {
      return null;
   }
}

@Override
public int getMaxIdMovH(){
        String consulta = "SELECT MAX(idmovh) FROM movhacienda";
     
        Object obj = jdbcTemplate.queryForObject(consulta,Integer.class);    
        if (obj==null){
          return 0;
        } else {
          return ((int)obj);
        }         
}  

 @Override
 public int actualizarMovH(MovHacienda movh){      
      int resu = 0;
      try {                   
          resu = jdbcTemplate.update("UPDATE movhacienda SET fecha=?,idhacienda=?,nhacienda=?,"+                   
                                   "cantidad=?,idcampo=?,ncampo=?,abrev=?,observ=?,marca1=?,marca2=?,marca3=? "+
                                   "WHERE idmovh=?",
                                    
                    new Object[] { movh.getFecha(),movh.getIdhacienda(),movh.getNhacienda(),
                                   movh.getCantidad(),movh.getIdcampo(),movh.getNcampo(),movh.getAbrev(),
                                   movh.getObserv(),
                                   movh.getMarca1(),movh.getMarca2(),movh.getMarca3(),movh.getIdmovh()
                                });
        } catch (IncorrectResultSizeDataAccessException e) {
          return -3;
      }
      return resu; 
}

@Override
public int deleteMovH(int idmov){
        int resu = 0;
        try {
          resu = jdbcTemplate.update("DELETE FROM movhacienda WHERE idmovh="+idmov);
        } catch (DataAccessException dae){
          resu = -5;   
        }
        return resu;
}


}

