package com.siscom.exception;


public class SisComException extends Exception {

	private String nomeProduto;
	private int estoque;
	private String mensagemErro;
	public String getNomeProduto() {
		return nomeProduto;
	}
	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}
	public int getEstoque() {
		return estoque;
	}
	public void setEstoque(int estoque) {
		this.estoque = estoque;
	}
	public String getMensagemErro() {
		return mensagemErro;
	}
	public void setMensagemErro(String mensagemErro) {
		this.mensagemErro = mensagemErro;
	}

	@Override
	public String toString() {
		return "SisComException [nomeProduto=" + nomeProduto + ", estoque=" + estoque + ", mensagemErro=" + mensagemErro
				+ "]";
	}
	public SisComException(String nomeProduto, int estoque, String mensagemErro) {
		super();
		this.nomeProduto = nomeProduto;
		this.estoque = estoque;
		this.mensagemErro = mensagemErro;
	}


	
}
