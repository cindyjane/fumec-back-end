package com.siscom.dao.mapper;

import com.siscom.service.model.Cliente;
import com.siscom.service.model.Fornecedor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FornecedorRowMapper  implements RowMapper<Fornecedor>{

    @Override
    public Fornecedor mapRow(ResultSet rs, int rowNum) throws SQLException {
        Fornecedor fornecedor = new Fornecedor();

        fornecedor.setNome(rs.getString("NOME"));
        fornecedor.setTelefones(rs.getString("TELEFONES"));
        fornecedor.setEmail(rs.getString("EMAIL"));
        fornecedor.setDateCad(rs.getDate("DATECAD"));
        fornecedor.setCnpj(rs.getString("FOR_CNPJ"));
        fornecedor.setNomeContato(rs.getString("FOR_NOMECONTATO"));

        return fornecedor;
    }
}
