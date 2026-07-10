package com.nimagu.back.Repository;

import java.util.List;

import com.nimagu.back.Entidades.CompVta;


public interface NimaguRepository {

List<CompVta> AllCompVtas();
int           getMaxCompVtas();
CompVta       findCompVtaById(int idcomp);
int           saveCompVta(CompVta comp);
int           actualizarCompVta(CompVta comp);   
int           deleteCompVta(int idcomp);



}
