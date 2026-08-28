package org.example.model;

public class Item {
    private String nome;
    private int quantidade;
    private double valorUnitario;
    private String tipo;

    public Item(String nome, int quantidade, double valorUnitario, String tipo){
        this.nome = nome;
        this.quantidade = quantidade;
        this.valorUnitario = valorUnitario;
        this.tipo = tipo;
    }

    public double getValorTotal(){
        return quantidade * valorUnitario;
    }

    public String getTipo(){
        return tipo;
    }

    @Override
    public String toString(){
        return "\nItem" +
                "\nNome: " + this.nome +
                "\nQuantidade: " + this.quantidade +
                "\nValor Unitario: " + this.valorUnitario +
                "\nTipo: " + this.tipo;
    }
}
