package com.siscom.service.model;

import java.io.Serializable;
import java.util.Date;

public class Fornecedor extends Pessoa implements Serializable {

	private String cnpj;
	private String nomeContato;
	
	public Fornecedor(Integer codigo, String nome, String telefones, String email, Date dateCad, String cnpj,
			String nomeContato) {
		super(codigo, nome, telefones, email, dateCad);
		this.cnpj = cnpj;
		this.nomeContato = nomeContato;
	}

	public Fornecedor() {
	}

	public TipoPessoa informarTipoPessoa(){
		return TipoPessoa.FORNECEDOR;
	}

	@Override
	public String toString() {
		return "Fornecedor [cnpj=" + cnpj + ", nomeContato=" + nomeContato + "]";
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getNomeContato() {
		return nomeContato;
	}
	public void setNomeContato(String nomeContato) {
		this.nomeContato = nomeContato;
	}
	
	
	
}
