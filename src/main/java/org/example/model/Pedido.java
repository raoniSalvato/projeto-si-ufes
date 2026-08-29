package org.example.model;

import org.example.infra.APITaxaEntregaMoc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private Cliente cliente;
    private LocalDateTime data;
    private List<Item> itens;
    private String codigoCupomDesconto;
    private double percentualCupomDeDesconto;
    private double taxaEntrega;

    public Pedido(LocalDateTime data, Cliente cliente){
        itens = new ArrayList<>();
        this.data = data;
        this.cliente = cliente;
        this.taxaEntrega = APITaxaEntregaMoc.getTaxaEntrega();

        this.codigoCupomDesconto = null;
        this.percentualCupomDeDesconto = 0.0;
    }

    public void adicionarItem(Item item){
        itens.add(item);
    }

    public String getCodigo(){
        return codigoCupomDesconto;
    }

    public double getPercentualDeDesconto(){
        return percentualCupomDeDesconto;
    }

    public void aplicarCupom(String codigo, double percentualDeDesconto){
        this.codigoCupomDesconto = codigo;
        this.percentualCupomDeDesconto = percentualDeDesconto;
    }

    public double getValorPedido(){
        double valorBruto = 0;
        for(Item i: itens){
            valorBruto += i.getValorTotal();
        }
        valorBruto += this.taxaEntrega;
        return valorBruto * (1-(percentualCupomDeDesconto/100));
    }

    public Cliente getCliente(){
        return cliente;
    }

    public List<Item> getItens(){
        return itens;
    }

    @Override
    public String toString(){
        return "\nPedido" +
                "\nData: " + this.data +
                "\nCliente: " + this.cliente +
                "\nItens: " + this.itens +
                "\nTaxa de Entrega: " + this.taxaEntrega;
    }
}
