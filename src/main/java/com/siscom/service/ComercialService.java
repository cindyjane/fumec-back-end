package com.siscom.service;

import com.siscom.dao.CompraRepository;
import com.siscom.dao.PessoaRepository;
import com.siscom.dao.ProdutoRepository;
import com.siscom.dao.VendaRepository;
import com.siscom.exception.SisComException;
import com.siscom.service.model.Cliente;
import com.siscom.service.model.Compra;
import com.siscom.service.model.FormaPgto;
import com.siscom.service.model.Fornecedor;
import com.siscom.service.model.ItemCompra;
import com.siscom.service.model.ItemVenda;
import com.siscom.service.model.Pessoa;
import com.siscom.service.model.Produto;
import com.siscom.service.model.Venda;
import com.siscom.service.model.Vendedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ComercialService {

    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private CompraRepository compraRepository;
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private VendaRepository vendaRepository;

    private List<Pessoa> listaPessoas;
    private List<Produto> listaProdutos;
    private List<Compra> listaCompras;
    private List<Venda> listaVendas;

    public ComercialService() {
        listaPessoas = new ArrayList<>();
        listaProdutos = new ArrayList<>();
        listaCompras = new ArrayList<>();
        listaVendas = new ArrayList<>();
    }

    //Add

    /**
     * Add a Person
     *
     * @param pessoa
     * @return
     * @throws Exception
     */
    public Pessoa addPessoa(Pessoa pessoa) throws Exception {
        if (pessoa instanceof Fornecedor) {
            final Fornecedor fornecedor = (Fornecedor) pessoa;

            if (this.buscarFornecedor(fornecedor.getCnpj()) != null) {
                throw new Exception("Provider already in system.");
            } else {
                //ToDo: fornecedorRepository.addFornecedor(fornecedor);
            }
        } else if (pessoa instanceof Cliente) {
            final Cliente cliente = (Cliente) pessoa;

            if (this.buscarCliente(cliente.getCpf()) != null) {
                throw new Exception("Client already in system.");
            } else {
                pessoaRepository.addCliente(cliente);
            }
        } else {
            final Vendedor vendedor = (Vendedor) pessoa;

            if (this.buscarVendedor(vendedor.getCpf()) != null) {
                throw new Exception("Sales person already in system.");
            } else if (vendedor.getMetaMensal() <= 0) {
                throw new Exception("Sales goal must be greater than 0");
            } else {
                //ToDo: vendedorRepository.addVendedor(vendedor);
            }
        }

        this.listaPessoas.add(pessoa);
        return pessoa;
    }

    /**
     * Add a Product
     *
     * @param produto
     */
    public void addProduto(Produto produto) {
        listaProdutos.add(produto);
        produtoRepository.addProduto(produto);
    }

    /**
     * Add a Sale
     *
     * @param cliente
     * @param vendedor
     * @param formaPgto
     * @param listaItensVenda
     * @throws Exception
     */
    public void fazerVendaParaCliente(Cliente cliente,
                                      Vendedor vendedor,
                                      FormaPgto formaPgto,
                                      List<ItemVenda> listaItensVenda) throws Exception {
        double valorTotal = 0;
        for (ItemVenda itemVenda : listaItensVenda) {
            valorTotal = +itemVenda.getValorVenda();
        }
        if (formaPgto.equals(FormaPgto.PAGAMENTO_A_PRAZO) && cliente.getLimiteCredito() < valorTotal) {
            throw new Exception("Venda não pode ser feita.");
        } else {
            Venda venda = new Venda(0, cliente, vendedor, listaItensVenda, formaPgto.getCodigo(), new Date());
            listaVendas.add(venda);
            vendaRepository.fazerVenda(venda);
            for (ItemVenda itemVenda : listaItensVenda) {
                removerEstoque(itemVenda.getProduto().getCodigo(), itemVenda.getQuantVenda());
            }

        }

    }

    /**
     * Add item to Stock
     *
     * @param codProduto
     * @param quantidade
     */
    public void addItemEstoque(Integer codProduto, Integer quantidade) {
        Produto produto = buscarProduto(codProduto);
        produto.addEstoque(quantidade);
        produtoRepository.atualizarEstoque(produto.getCodigo(), produto.getEstoque());
    }

    /**
     * Add a Purchase
     *
     * @param fornecedor
     * @param listaItensCompra
     * @throws SisComException
     */
    public void criarCompra(Fornecedor fornecedor, List<ItemCompra> listaItensCompra) throws SisComException {
        for (ItemCompra itemCompra : listaItensCompra) {
            Produto produto = buscarProduto(itemCompra.getProduto().getCodigo());
            produto.removeEstoque(itemCompra.getQuantCompra());
            produtoRepository.atualizarEstoque(produto.getCodigo(), produto.getEstoque());

        }
        Compra compra = new Compra(null, fornecedor, listaItensCompra, new Date());

        compraRepository.fazerCompra(compra);

        listaCompras.add(compra);

    }

    //Remove

    /**
     * Delete a Person
     *
     * @param pessoa
     * @throws Exception
     */
    public void removePessoa(Pessoa pessoa) throws Exception {
        if (pessoa instanceof Fornecedor) {
            final Fornecedor fornecedor = (Fornecedor) pessoa;
            if (buscarCompra(fornecedor) != null) {
                throw new Exception("Fornecedor tem compra registrada para ele. Nao pode excluir.");
            } else {
                //ToDo: pessoaRepository.excluirPessoa(pessoa);

            }
        } else if (pessoa instanceof Cliente) {
            final Cliente cliente = (Cliente) pessoa;
            if (buscarVendaPorCliente(cliente) != null) {
                throw new Exception("Cliente tem venda registrada para ele. Nao pode excluir.");
            } else {
                //ToDo: pessoaRepository.excluirPessoa(pessoa);
            }
        } else {
            Vendedor vendedor = (Vendedor) pessoa;
            if (buscarVendaPorVendedor(vendedor) != null) {
                throw new Exception("Vendedor tem venda registrada para ele. Nao pode excluir.");
            } else {
                //ToDo: pessoaRepository.excluirPessoa(pessoa);
            }
        }

    }

    /**
     * Delete a Product
     *
     * @param codigo
     * @throws Exception
     */
    public void excluirProduto(Integer codigo) throws Exception {
        Produto produto = buscarProduto(codigo);
        if (existeCompra(codigo) || existeVenda(codigo)) {
            throw new Exception("Não podemos excluir o produto");
        } else {
            produtoRepository.excluirProduto(codigo);
        }
    }

    /**
     * Delete a Product from Stock
     *
     * @param codProduto
     * @param quantidade
     * @throws SisComException
     */
    public void removerEstoque(Integer codProduto, Integer quantidade) throws SisComException {
        Produto produto = buscarProduto(codProduto);
        produto.removeEstoque(quantidade);
        produtoRepository.atualizarEstoque(produto.getCodigo(), produto.getEstoque());
    }

    /**
     * Delete a Purchase
     *
     * @param numCompra
     */
    public void excluirCompra(Integer numCompra) {
        for (ItemCompra itemCompra : compraRepository.buscarItens(numCompra)) {
            addItemEstoque(itemCompra.getProduto().getCodigo(), itemCompra.getQuantCompra());
        }

        listaCompras.remove(numCompra);
        compraRepository.excluirCompra(numCompra);
    }

    /**
     * Delete a Sale
     *
     * @param numVenda
     */
    public void excluirVenda(Integer numVenda) {
        Venda venda = buscarVendaPorCodigo(numVenda);
        for (ItemVenda itemVenda : venda.getVendaItens()) {
            Produto produto = buscarProduto(itemVenda.getProduto().getCodigo());
            produto.addEstoque(itemVenda.getQuantVenda());
        }
        listaVendas.remove(venda);
    }

    //Search

    /**
     * Search for a Supplier
     *
     * @param cnpj
     * @return
     */
    public Pessoa buscarFornecedor(String cnpj) {
        //ToDO: return fornecedorRepository.buscarFornecedorporcnpj(cnpj);
        return null;
    }

    /**
     * Search for a Client
     *
     * @param cpf
     * @return
     */
    public Pessoa buscarCliente(String cpf) {
        for (Pessoa pessoa : listaPessoas) {
            if (((Cliente) pessoa).getCpf().equals(cpf)) {
                return pessoa;
            }
        }

        return null;
    }

    /**
     * Search for a Sales Person
     *
     * @param cpf
     * @return
     */
    public Pessoa buscarVendedor(String cpf) {
        for (Pessoa pessoa : listaPessoas) {
            if (((Cliente) pessoa).getCpf().equals(cpf)) {
                return pessoa;
            }
        }

        return null;
    }

    /**
     * Search for a Product
     *
     * @param codigo
     * @return
     */
    public Produto buscarProduto(Integer codigo) {
        return produtoRepository.buscarProduto(codigo);
    }

    /**
     * Search for a Purchase
     *
     * @param fornecedor
     * @return
     */
    private Compra buscarCompra(Fornecedor fornecedor) {
        for (Compra compra : listaCompras) {
            if (compra.getFornecedor().getCnpj().equals(fornecedor.getCnpj())) {
                return compra;
            }
        }
        return null;
    }

    /**
     * Search for a Purchase
     *
     * @param numCompra
     * @return
     */
    private Compra buscarCompra(Integer numCompra) {
        for (Compra compra : listaCompras) {
            if (compra.getNumCompra() == numCompra) {
                return compra;
            }
        }
        return null;
    }

    /**
     * Search for a sale by Client
     *
     * @param cliente
     * @return
     */
    private Venda buscarVendaPorCliente(Cliente cliente) {
        for (Venda venda : listaVendas) {
            if (venda.getCliente().getCpf().equals(cliente.getCpf())) {
                return venda;
            }
        }
        return null;
    }

    /**
     * Search for a sale by Sales Person
     *
     * @param vendedor
     * @return
     */
    private Venda buscarVendaPorVendedor(Vendedor vendedor) {
        for (Venda venda : listaVendas) {
            if (venda.getVendedor().getCpf().equals(vendedor.getCpf())) {
                return venda;
            }
        }
        return null;
    }

    /**
     * Search for a sale by Code
     *
     * @param numVenda
     * @return
     */
    private Venda buscarVendaPorCodigo(Integer numVenda) {
        for (Venda venda : listaVendas) {
            if (venda.getNumVenda() == numVenda) {
                return venda;
            }
        }
        return null;
    }

    /**
     * Search if a Purchase exists
     *
     * @param codProduto
     * @return
     */
    private boolean existeCompra(Integer codProduto) {
        for (Compra compra : listaCompras) {
            for (ItemCompra itemCompra : compra.getCompraItens()) {
                if (codProduto == itemCompra.getProduto().getCodigo()) {
                    return true;
                }

            }
        }
        return false;
    }

    /**
     * Search if a Sale exists
     *
     * @param codProduto
     * @return
     */
    private boolean existeVenda(Integer codProduto) {
        for (Venda venda : listaVendas) {
            for (ItemVenda itemVenda : venda.getVendaItens()) {
                if (codProduto == itemVenda.getProduto().getCodigo()) {
                    return true;
                }

            }
        }
        return false;
    }

    /**
     * Search for a list of Clients
     *
     * @param nomeCliente
     * @return
     */
    public List<Cliente> obterListaClientes(String nomeCliente) {
        return pessoaRepository.obterClientesOrdemAlfabeticaPorNome(nomeCliente);
    }

}
