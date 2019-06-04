package com.siscom.service.model;

import java.io.Serializable;

public class EstatisticaVendasVendedor implements Serializable {

    private String nome;
    private int vezesComprou;
    private int valorTotal;

    public EstatisticaVendasVendedor(String nome, int vezesComprou, int valorTotal) {
        this.nome = nome;
        this.vezesComprou = vezesComprou;
        this.valorTotal = valorTotal;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getVezesComprou() {
        return vezesComprou;
    }

    public void setVezesComprou(int vezesComprou) {
        this.vezesComprou = vezesComprou;
    }

    public int getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(int valorTotal) {
        this.valorTotal = valorTotal;
    }

    @Override
    public String toString() {
        return "EstatisticaVendasVendedor{" +
                "nome='" + nome + '\'' +
                ", vezesComprou=" + vezesComprou +
                ", valorTotal=" + valorTotal +
                '}';
    }
}
