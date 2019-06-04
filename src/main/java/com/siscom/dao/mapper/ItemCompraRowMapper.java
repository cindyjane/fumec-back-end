package com.siscom.dao.mapper;

import com.siscom.service.model.ItemCompra;
import com.siscom.service.model.Produto;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemCompraRowMapper implements RowMapper<ItemCompra> {

    @Override
    public ItemCompra mapRow(ResultSet rs, int rowNum) throws SQLException {
        ItemCompra itemCompra = new ItemCompra();
        Produto produto = new Produto();
        produto.setCodigo(rs.getInt("CODIGO_PRODUTO"));

        itemCompra.setProduto(produto);
        itemCompra.setQuantCompra(rs.getInt("QUANTIDADE"));
        itemCompra.setValorCompra(rs.getDouble("VALOR_COMPRA"));


        return itemCompra;
    }

}