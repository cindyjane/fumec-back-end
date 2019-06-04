package com.siscom.service.model;

public enum FormaPgto {
    PAGAMENTO_A_VISTA(1),
    PAGAMENTO_A_PRAZO(2);

    private int codigo;

    public int getCodigo() {
        return codigo;
    }

    FormaPgto(int codigo){
        this.codigo = codigo;
    }


}
