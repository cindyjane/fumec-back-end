package com.siscom.service.model;

import java.io.Serializable;

public class EstatisticaCompraFornecedor implements Serializable {

    private String nome;
    private int vezesVendeu;
    private int valorTotal;

    public EstatisticaCompraFornecedor(String nome, int vezesVendeu, int valorTotal) {
        this.nome = nome;
        this.vezesVendeu = vezesVendeu;
        this.valorTotal = valorTotal;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getVezesVendeu() {
        return vezesVendeu;
    }

    public void setVezesVendeu(int vezesVendeu) {
        this.vezesVendeu = vezesVendeu;
    }

    public int getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(int valorTotal) {
        this.valorTotal = valorTotal;
    }

    @Override
    public String toString() {
        return "EstatisticaCompraFornecedor{" +
                "nome='" + nome + '\'' +
                ", vezesVendeu=" + vezesVendeu +
                ", valorTotal=" + valorTotal +
                '}';
    }
}
