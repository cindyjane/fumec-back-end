package com.siscom.controller.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@Builder
public class CompraDto {
    private Integer codigoFornecedor;
    private ArrayList<ProdutoDto> listaProdutos;
}
