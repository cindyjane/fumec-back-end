package com.siscom.service.model;

public enum TipoPessoa {
    CLIENTE(1),
    FORNECEDOR(2),
    VENDEDOR(3);

    private int codigo;

    public int getCodigo() {
        return codigo;
    }

    TipoPessoa(int codigo) {
        this.codigo = codigo;
    }
}