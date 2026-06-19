package com.nimagu.back.Repository;

import java.util.List;

import com.nimagu.back.Entidades.CuentaB;
import com.nimagu.back.Entidades.MovCta;

public interface DegrosCuentaRepository {


List<CuentaB> AllCuentasb();
int           getMaxCuentas();
CuentaB       findCuentaById(int idcuenta);
int           saveCuenta(CuentaB cuenta);
int           actualizarCuenta(CuentaB cuenta);    
int           getExisteCBUPer(String periodo, String cbu);

List<MovCta>  detalleCuenta(int idcta, String fechaini, String fechafin);
int           getMaxMovCta(int idcta);
MovCta        findMovCuentaById(int idcta, int idmovim);
int           saveMovCuenta(MovCta movcuenta);
int           actualizarMovCuenta(MovCta movcuenta); 
int           deleteMovCuenta(int idcta,int idmovim);
int           deleteCuentaB(int idcuenta);

}
