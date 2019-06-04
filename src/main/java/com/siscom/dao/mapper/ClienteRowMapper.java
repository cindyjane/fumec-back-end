package com.siscom.dao.mapper;

import com.siscom.service.model.Cliente;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteRowMapper implements RowMapper<Cliente> {

    @Override
    public Cliente mapRow(ResultSet rs, int rowNum) throws SQLException {
        Cliente cliente = new Cliente();

        cliente.setNome(rs.getString("NOME"));
        cliente.setTelefones(rs.getString("TELEFONES"));
        cliente.setEmail(rs.getString("EMAIL"));
        cliente.setDateCad(rs.getDate("DATECAD"));
        cliente.setCpf(rs.getString("CLI_CPF"));
        cliente.setLimiteCredito(rs.getDouble("CLI_LIMITECREDITO"));

        return cliente;
    }

}
