package com.siscom.controller.dto;

import com.siscom.service.model.FormaPgto;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class VendaDto {
    private Integer codigoCliente;
    private Integer codigoVendedor;
    private FormaPgto formaPagamento;
    private ArrayList<ProdutoDto> listaProdutos;
}
