package com.siscom.service.model;

import java.util.Date;
import java.util.List;

public class Venda {

	private int numVenda;
	private Cliente cliente;
	private Vendedor vendedor;
	private List<ItemVenda> vendaItens;
	private int formaPagto;//já criamos o enum FormaPgto
	private Date dataVenda;

	public int getNumVenda() {
		return numVenda;
	}
	public void setNumVenda(int numVenda) {
		this.numVenda = numVenda;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Vendedor getVendedor() {
		return vendedor;
	}
	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}
	public List<ItemVenda> getVendaItens() {
		return vendaItens;
	}
	public void setVendaItens(List<ItemVenda> vendaItens) {
		this.vendaItens = vendaItens;
	}
	public int getFormaPagto() {
		return formaPagto;
	}
	public void setFormaPagto(int formaPagto) {
		this.formaPagto = formaPagto;
	}
	public Date getDataVenda() {
		return dataVenda;
	}
	public void setDataVenda(Date dataVenda) {
		this.dataVenda = dataVenda;
	}
	@Override
	public String toString() {
		return "Venda [numVenda=" + numVenda + ", cliente=" + cliente + ", vendedor=" + vendedor + ", vendaItens="
				+ vendaItens + ", formaPagto=" + formaPagto + ", dataVenda=" + dataVenda + "]";
	}
	public Venda(int numVenda, Cliente cliente, Vendedor vendedor, List<ItemVenda> vendaItens, int formaPagto,
			Date dataVenda) {
		super();
		this.numVenda = numVenda;
		this.cliente = cliente;
		this.vendedor = vendedor;
		this.vendaItens = vendaItens;
		this.formaPagto = formaPagto;
		this.dataVenda = dataVenda;
	}
	
	
	
}
