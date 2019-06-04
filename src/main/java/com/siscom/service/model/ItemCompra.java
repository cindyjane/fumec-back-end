package com.siscom.service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemCompra {

	private Produto produto;
	private Integer quantCompra;
	private Double valorCompra;

	@Override
	public String toString() {
		return "ItemCompra [produto=" + produto + ", quantCompra=" + quantCompra + ", valorCompra=" + valorCompra + "]";
	}

}