package com.siscom.service.model;

import java.util.Date;

public abstract class Pessoa {

	private Integer codigo;
	private String nome;
	private String telefones;
	private String email;
	private Date dateCad;
	
	public abstract TipoPessoa informarTipoPessoa();
	
	public Pessoa getPessoa() { return this; }
	
	@Override
	public String toString() {
		return "Pessoa [codigo=" + codigo + ", nome=" + nome + ", telefones=" + telefones + ", email=" + email
				+ ", dateCad=" + dateCad + "]";
	}

	public Pessoa(Integer codigo, String nome, String telefones, String email, Date dateCad) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.telefones = telefones;
		this.email = email;
		this.dateCad = dateCad;
	}

	public Pessoa() {
		
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefones() {
		return telefones;
	}

	public void setTelefones(String telefones) {
		this.telefones = telefones;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDateCad() {
		return dateCad;
	}

	public void setDateCad(Date dateCad) {
		this.dateCad = dateCad;
	}

	public int compareTo(Pessoa pessoa) {
		if(this.nome==pessoa.getNome()){
			return 0;
		}else if(this.nome.length()<pessoa.getNome().length()){
			return -1;
		}else{
			return 1;
		}
	}
}


