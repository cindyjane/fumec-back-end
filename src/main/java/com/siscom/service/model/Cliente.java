package com.siscom.service.model;

import java.io.Serializable;
import java.util.Date;

public class Cliente extends Pessoa implements Serializable {

	private String cpf;
	private double limiteCredito;

	public Cliente(int codigo, String nome, String telefones, String email, Date dateCad, String cpf, double limiteCredito) {
		super(codigo, nome, telefones, email, dateCad);
		this.cpf = cpf;
		this.limiteCredito = limiteCredito;
	}

	public Cliente(){}

	public TipoPessoa informarTipoPessoa(){
		return TipoPessoa.CLIENTE;
	}

	@Override
	public String toString() {
		return "Cliente [cpf=" + cpf + ", limiteCredito=" + limiteCredito + "]";
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public double getLimiteCredito() {
		return limiteCredito;
	}

	public void setLimiteCredito(double limiteCredito) {
		this.limiteCredito = limiteCredito;
	}
}
