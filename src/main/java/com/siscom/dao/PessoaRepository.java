package com.siscom.dao;

import com.siscom.dao.mapper.ClienteRowMapper;
import com.siscom.dao.mapper.FornecedorRowMapper;
import com.siscom.service.model.Cliente;
import com.siscom.service.model.Fornecedor;
import com.siscom.service.model.Pessoa;
import com.siscom.service.model.TipoPessoa;
import com.siscom.service.model.Vendedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.Null;

import java.util.List;

@Repository
public class PessoaRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public int excluirPessoa(Integer codigo) {
        String deleteQuery = "DELETE FROM PESSOA WHERE CODIGO = ?";
        return jdbcTemplate.update(deleteQuery, new Object[] { codigo });
    }

    public void addCliente(Cliente cliente) {
        String insertQuery = "INSERT INTO PESSOA(NOME, TELEFONES, EMAIL, DATECAD, CLI_CPF, CLI_LIMITECREDITO, TIPO_PESSOA) values (?,?,?,?,?,?,?)";
        jdbcTemplate.update(insertQuery, cliente.getNome(), cliente.getTelefones(), cliente.getEmail(),
                cliente.getDateCad(), cliente.getCpf(), cliente.getLimiteCredito(), TipoPessoa.CLIENTE);
    }

    public void addFornecedor(Fornecedor fornecedor) {
        String insertQuery = "INSERT INTO PESSOA(NOME, TELEFONES, EMAIL, DATECAD, FOR_CNPJ, FOR_NOMECONTATO, TIPO_PESSOA) values (?,?,?,?,?,?,?)";
        jdbcTemplate.update(insertQuery, fornecedor.getNome(), fornecedor.getTelefones(), fornecedor.getEmail(),
                fornecedor.getDateCad(), fornecedor.getCnpj(), fornecedor.getNomeContato(), TipoPessoa.FORNECEDOR);
    }

    public void addVendedor(Vendedor vendedor) {
        String insertQuery = "INSERT INTO PESSOA(NOME, TELEFONES, EMAIL, DATECAD, FOR_CNPJ, VEN_CPF, VEN_METAMENSAL) values (?,?,?,?,?,?,?)";
        jdbcTemplate.update(insertQuery, vendedor.getNome(), vendedor.getTelefones(), vendedor.getEmail(),
                vendedor.getDateCad(), vendedor.getCpf(), vendedor.getMetaMensal(), TipoPessoa.VENDEDOR);

    }

    public List<Cliente> buscarPessoasOrdemAlfabetica(String nomePessoa, @Null TipoPessoa tipo) {
        String query = "SELECT * FROM PESSOA WHERE LOWER(NOME) LIKE ? AND TIPO_PESSOA = ISNULL(?, TIPO_PESSOA) ORDER BY NOME";
        return jdbcTemplate.query(query, new Object[] { "%" + nomePessoa.toLowerCase() + "%", tipo },
                new ClienteRowMapper());
    }

    public Fornecedor buscarFornecedorporcnpj(String cnpj) {
        String buscarQuery = "SELECT * FROM PESSOA WHERE FOR_CNPJ = ? AND TIPO_PESSOA = ?";
        return jdbcTemplate.queryForObject(buscarQuery, new Object[] { cnpj, 2 }, new FornecedorRowMapper());
    }

}