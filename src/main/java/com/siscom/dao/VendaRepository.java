package com.siscom.dao;

import com.siscom.service.model.ItemVenda;
import com.siscom.service.model.Venda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
public class VendaRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public int excluirVenda(int numVenda) {
        String deleteQuery = "DELETE FROM VENDA WHERE NUMVENDA = ?";
        return jdbcTemplate.update(deleteQuery, new Object[] { numVenda });
    }

    public void fazerVenda(Venda venda) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        String insertQuery = "INSERT INTO VENDA(COD_CLIENTE, COD_VENDEDOR, FORMAPGTO, DATA) values (?,?,?)";
        String insertQueryItem = "INSERT INTO ITEM_VENDA(CODIGO_VENDA, CODIGO_PRODUTO, QUANTIDADE, VALOR_UNITARIO) values (?,?,?,?)";

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, venda.getCliente().getCodigo());
            ps.setInt(2, venda.getFormaPagto());
            ps.setDate(3, new java.sql.Date(venda.getDataVenda().getTime()));
            return ps;
        }, keyHolder);

        for (ItemVenda itemVenda : venda.getVendaItens()) {
            jdbcTemplate.update(insertQueryItem, keyHolder.getKey(), itemVenda.getProduto().getCodigo(),
                    itemVenda.getQuantVenda(), itemVenda.getProduto().getPrecoUnitario());
        }
    }
}
