package com.nimagu.back.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


import com.nimagu.back.Entidades.Cliente;

public class ClienteRowMapper implements RowMapper<Cliente>{
   @SuppressWarnings("null")
public Cliente mapRow(ResultSet rs, int rowNum) throws SQLException {
       Cliente cliente = new Cliente();
       cliente.setIdCliente(rs.getInt("idCliente"));
       cliente.setNombre(rs.getString("nombre"));
              
       return cliente;
    }
}
