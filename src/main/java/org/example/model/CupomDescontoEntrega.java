package org.example.model;

public class CupomDescontoEntrega {
    private String nomeMetodo;
    private double valorDesconto;

    public CupomDescontoEntrega(String nomeMetodo, double valorDesconto){
        this.nomeMetodo = nomeMetodo;
        this.valorDesconto = valorDesconto;
    }

    public double getValorDescontoCupom(){
        return valorDesconto;
    }

    public String getNomeMetodo(){
        return nomeMetodo;
    }

    @Override
    public String toString(){
        return "\nCupom de Desconto Entrega" +
                "\nNome metodo: " + this.nomeMetodo +
                "\nValor do Desconto: " + this.valorDesconto;
    }
}
