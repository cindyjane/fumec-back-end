package com.siscom.service.model;

public class EstatisticaVendasCliente {

    private String nome;
    private int vezesComprou;
    private int valorTotal;

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

    public EstatisticaVendasCliente(String nome, int vezesComprou, int valorTotal) {
        this.nome = nome;
        this.vezesComprou = vezesComprou;
        this.valorTotal = valorTotal;
    }

    public void setNome(String nome) {

        this.nome = nome;
    }

    @Override
    public String toString() {
        return "EstatisticaVendasCliente{" +
                "nome='" + nome + '\'' +
                ", vezesComprou=" + vezesComprou +
                ", valorTotal=" + valorTotal +
                '}';
    }

    public String getNome() {

        return nome;
    }
}
