package com.siscom.dao;

import com.siscom.dao.mapper.ItemCompraRowMapper;
import com.siscom.service.model.Compra;
import com.siscom.service.model.ItemCompra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

@Repository
public class CompraRepository implements Serializable {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public int excluirCompra(Integer numCompra){
        String deleteQuery = "DELETE FROM COMPRA WHERE NUMCOMPRA = ?";
        return jdbcTemplate.update(deleteQuery, new Object[] { numCompra });
    }

    public void fazerCompra(Compra compra) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        String insertQuery = "INSERT INTO COMPRA(COD_FORNECEDOR, DATA) values (?,?)";
        String insertQueryItem = "INSERT INTO ITEM_COMPRA(CODIGO_COMPRA, CODIGO_PRODUTO, QUANTIDADE, VALOR_UNITARIO) values (?,?,?,?)";

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, compra.getFornecedor().getCodigo());
            ps.setDate(2, new java.sql.Date(compra.getDataCompra().getTime()));
            return ps;
        }, keyHolder);

        for (ItemCompra itemCompra : compra.getCompraItens()) {
            jdbcTemplate.update(insertQueryItem, keyHolder.getKey(), itemCompra.getProduto().getCodigo(),
                    itemCompra.getQuantCompra(), itemCompra.getProduto().getPrecoUnitario());

        }
    }

    public List<Compra> obterListaCompras(Date de, Date para) {
        String query = "SELECT P.NOME, C.Data, C.* FROM COMPRA C INNER JOIN PESSOA P ON C.COD_FORNECEDOR = P.CODIGO WHERE DATA BETWEEN ? AND ? ORDER BY P.NOME ASC, C.DATA DESC";

        // ToDo CompraRowMapper e definir colunas no select return jdbcTemplate.query(query, de, para, new CompraRowMapper());
        return null;
    }

    public List<Compra> buscarEstatisticaCompras(Date de, Date para) {
        String query = "SELECT P.NOME, COUNT(C.*) AS QTD_VENDAS, SUM(IC.QUANTIDADE * IC.VALOR_COMPRA) AS VALOR_TOTAL\n" +
                "FROM COMPRA C \n" +
                "INNER JOIN PESSOA P ON C.COD_FORNECEDOR = P.CODIGO\n" +
                "INNER JOIN ITEM_COMPRA IC ON C.NUMCOMPRA = IC.NUMCOMPRA\n" +
                "WHERE DATA BETWEEN ? AND ?\n" +
                "GROUP BY P.NOME\n" +
                "ORDER BY P.NOME ASC;";

        // ToDo EstatisticaMapper e definir colunas no select return jdbcTemplate.query(query, de, para, new EstatisticaMapper());
        return null;
    }

    public List<ItemCompra> buscarItens(Integer numCompra) {
        String query = "SELECT * FROM ITEM_COMPRA WHERE NUMCOMPRA = ?";
        return jdbcTemplate.query(query, new Object[] { numCompra }, new ItemCompraRowMapper());
    }
}
