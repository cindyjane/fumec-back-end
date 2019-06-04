package com.siscom.controller;

import com.siscom.controller.dto.CompraDto;
import com.siscom.controller.dto.EstatisticaDto;
import com.siscom.controller.dto.ProdutoDto;
import com.siscom.controller.dto.VendaDto;
import com.siscom.service.ComercialService;
import com.siscom.service.model.Cliente;
import com.siscom.service.model.Compra;
import com.siscom.service.model.EstatisticaCompraFornecedor;
import com.siscom.service.model.EstatisticaVendasCliente;
import com.siscom.service.model.EstatisticaVendasVendedor;
import com.siscom.service.model.FormaPgto;
import com.siscom.service.model.Fornecedor;
import com.siscom.service.model.ItemCompra;
import com.siscom.service.model.ItemVenda;
import com.siscom.service.model.Pessoa;
import com.siscom.service.model.Produto;
import com.siscom.service.model.TipoPessoa;
import com.siscom.service.model.Venda;
import com.siscom.service.model.Vendedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.siscom.constant.ControllerConstants.*;

@RestController
public class ComercialController {

    private ComercialService comercialService;

    @Autowired
    public ComercialController(ComercialService comercialService) {
        this.comercialService = comercialService;
    }

    // GET

    @GetMapping("/pessoa/{cpfCnpj}")
    public ResponseEntity<Pessoa> buscarPessoa(@PathVariable(value = "cpfCnpj") String cpfCnpj) {
        //ToDO: Pessoa pessoa = comercialService.buscarPessoa(cpfCnpj);
        return ResponseEntity.ok(null);
    }

    /**
     * Filtra pessoas por nome e tipo
     *
     * @param query
     * @param tipoPessoa
     * @return
     */
    @GetMapping("/pessoa")
    public ResponseEntity<ArrayList<Pessoa>> buscarPessoasOrdemAlfabetica(@RequestParam(value = "query", required = false) String query,
                                                                          @RequestParam(value = "tipoPessoa",
                                                                                        required = false)
                                                                                  TipoPessoa tipoPessoa) {
        //ToDO: ArrayList<Pessoa> pessoas = comercialService.buscarPessoa(cpfCnpj);
        return ResponseEntity.ok(null);
    }

    /**
     * Bucscar um produto pelo codigo
     * @param codigo
     * @return
     */
    @GetMapping("/produto/{codigo}")
    public ResponseEntity<Produto> buscarProduto(@PathVariable Integer codigo) {
        //Todo: Produto produto = comercialService.buscarProduto(codigo);
        return ResponseEntity.ok(null);
    }

    @GetMapping("/produto")
    public ResponseEntity<ArrayList<Produto>> buscarProdutosOrdemAlfabetica(@RequestParam(value = "query", required = false) String query,
                                                                            @RequestParam(value = "emFalta", required = false)
                                                                                    Boolean emFalta) {
        // ToDo: comercialService.buscarProdutos(query);
        return ResponseEntity.ok(null);
    }

    @GetMapping("/venda")
    public ResponseEntity<ArrayList<Venda>> obterListaVendas(@RequestParam(value = "tipoPessoa", required = false) TipoPessoa tipoPessoa,
                                                             @RequestParam(value = "de") Date de,
                                                             @RequestParam(value = "para") Date para) {
        // ToDO: comercialService.buscarVendas();
        return ResponseEntity.ok(null);
    }

    @GetMapping("/compra")
    public ResponseEntity<ArrayList<Compra>> obterListaCompras(@RequestParam(value = "de") Date de,
                                                               @RequestParam(value = "para") Date para) {
        // ToDO: comercialService.buscarCompras();
        return ResponseEntity.ok(null);
    }

    @GetMapping("/venda/statistics")
    public ResponseEntity<ArrayList<EstatisticaDto>> buscarEstatisticaVendas(
            @RequestParam(value = "tipoPessoa") TipoPessoa tipoPessoa,
            @RequestParam(value = "de") Date de,
            @RequestParam(value = "para") Date para) {

        // ToDo: comercialService.buscarEstatisticasVendas();
        return ResponseEntity.ok(null);
    }

    @GetMapping("/compra/statistics")
    public ResponseEntity<ArrayList<EstatisticaDto>> buscarEstatisticaCompras(
            @RequestParam(value = "de") Date de,
            @RequestParam(value = "para") Date para) {
        // ToDo: comercialService.buscarEstatisticasCompras();
        return ResponseEntity.ok(null);
    }

    // POST

    /**
     * Inserir uma pessoa
     * @param pessoa
     * @return
     * @throws Exception
     */
    @PostMapping("/pessoa")
    public ResponseEntity addPessoa(@RequestBody Pessoa pessoa) throws Exception {
        // Todo: comercialService.addPessoa(pessoa);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/compra")
    public ResponseEntity fazerCompra(@RequestBody CompraDto compra) throws Exception {
        // ToDo: comercialService.criarCompra(fornecedor1, listItens);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/venda")
    public ResponseEntity fazerVenda(@RequestBody VendaDto venda) throws Exception {
        // ToDo: comercialService.fazerVendaParaCliente(venda);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/produto")
    public ResponseEntity inserirProduto(@RequestBody ProdutoDto produto) {
        // ToDo: comercialService.addProduto(prod1);
        return ResponseEntity.noContent().build();
    }

    // DELETE

    @DeleteMapping("/pessoa/{codigo}")
    public ResponseEntity deletePessoa(@PathVariable(value = "codigo") int codigo) throws Exception {
        Pessoa pessoa = new Cliente();
        pessoa.setCodigo(1);
        comercialService.removePessoa(pessoa);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/compra/{codigo}")
    public ResponseEntity deleteCompra(@PathVariable(value = "codigo") Integer codigo) {
        comercialService.excluirCompra(codigo);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/venda/{codigo}")
    public ResponseEntity deleteVenda(@PathVariable(value = "codigo") Integer codigo) {
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/produto/{codigo}")
    public ResponseEntity deleteProduto(@PathVariable(value = "codigo") Integer codigo) throws Exception {
        comercialService.excluirProduto(codigo);
        return ResponseEntity.noContent().build();
    }
}
