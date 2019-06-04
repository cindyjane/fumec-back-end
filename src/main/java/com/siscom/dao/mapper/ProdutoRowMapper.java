package com.siscom.dao.mapper;

import com.siscom.service.model.Produto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProdutoRowMapper implements RowMapper<Produto> {

    @Override
    public Produto mapRow(ResultSet rs, int rowNum) throws SQLException {
        Produto produto = new Produto();

        produto.setCodigo(rs.getInt("CODIGO"));
        produto.setPrecoUnitario(rs.getDouble("PRECOUNITARIO"));
        produto.setNome(rs.getString("NOME"));
        produto.setEstoqueMinimo(rs.getInt("ESTOQUEMINIMO"));
        produto.setEstoque(rs.getInt("ESTOQUE"));
        produto.setDateCad(rs.getDate("DATACAD"));

        return produto;

    }
}