package com.nimagu.back.Repository;

import java.util.List;

import com.nimagu.back.Entidades.CuentaB;
import com.nimagu.back.Entidades.Endoso;
import com.nimagu.back.Entidades.MovCta;
import com.nimagu.back.Entidades.SaldoCta;
import com.nimagu.back.Entidades.SaldoMov;

public interface DegrosCuentaRepository {


List<CuentaB> AllCuentasb();
int           getMaxCuentas();
CuentaB       findCuentaById(int idcuenta);
int           saveCuenta(CuentaB cuenta);
int           actualizarCuenta(CuentaB cuenta);    
int           getExisteCBUPer(String periodo, String cbu);

List<MovCta>  detalleCuenta(int idcta, String fechaini, String fechafin);
List<MovCta>  detalleCuentaXtipo(int idcta, String tm1, String tm2);
SaldoMov      saldoEntreFechas(int idcta,String fechaini, String fechafin);
int           getMaxMovCta(int idcta);
MovCta        findMovCuentaById(int idcta, int idmovim);
int           saveMovCuenta(MovCta movcuenta);
int           actualizarMovCuenta(MovCta movcuenta); 
int           deleteMovCuenta(int idcta,int idmovim);
int           deleteCuentaB(int idcuenta);
List<SaldoCta> saldosInicialesCta(int idcta);

List<Endoso>  AllEndosos();
List<Endoso>  AllEndososXCuenta(int idcta);
int           getMaxEndosos();
Endoso        findEndosoById(int idendo);
int           saveEndoso(Endoso endoso);
int           actualizarEndoso(Endoso endoso);
int           deleteEndoso(int idendo,int idcta,int nmov);

}
