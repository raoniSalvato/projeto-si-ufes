package org.example.model;

import org.example.infra.APITaxaEntregaMoc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private Cliente cliente;
    private LocalDateTime data;
    private List<Item> itens;
    private List<CupomDescontoEntrega> cuponsDeDescontoEntrega;
    private double taxaEntrega;

    public Pedido(LocalDateTime data, Cliente cliente){
        itens = new ArrayList<>();
        cuponsDeDescontoEntrega = new ArrayList<>();
        this.data = data;
        this.cliente = cliente;
        this.taxaEntrega = APITaxaEntregaMoc.getTaxaEntrega();
    }

    public void adicionarItem(Item item){
        itens.add(item);
    }

    public double getValorPedido(){
        double valorPedido = 0;
        for(Item i: itens){
            valorPedido += i.getValorTotal();
        }
        return (valorPedido) - (getValorTotalDescontoTaxaDeEntrega() + (APITaxaEntregaMoc.getTaxaEntrega()));
    }

    public Cliente getCliente(){
        return cliente;
    }

    public List<Item> getItens(){
        return itens;
    }

    public void aplicarDesconto(CupomDescontoEntrega cupom){
        double limiteDescontoNaTaxaDeEntrega = 10.0;
        double descontoConcedidoNaTaxaDeEntrega = getValorTotalDescontoTaxaDeEntrega();
        double restanteDescontoNaTaxaDeEntrega = limiteDescontoNaTaxaDeEntrega - descontoConcedidoNaTaxaDeEntrega;
        if(restanteDescontoNaTaxaDeEntrega <= 0){
            return;
        }
        double desconto = cupom.getValorDescontoCupom();
        if(desconto <= restanteDescontoNaTaxaDeEntrega){
            cuponsDeDescontoEntrega.add(cupom);
        }else{
            CupomDescontoEntrega cupomParcial = new CupomDescontoEntrega(cupom.getNomeMetodo() + " (parcial)", restanteDescontoNaTaxaDeEntrega);
            cuponsDeDescontoEntrega.add(cupomParcial);
        }
    }

    public double getValorTotalDescontoTaxaDeEntrega(){
        double valorTotalDescontoTaxaDeEntrega = 0;
        for(CupomDescontoEntrega c : cuponsDeDescontoEntrega){
            valorTotalDescontoTaxaDeEntrega += c.getValorDescontoCupom();
        }
        return valorTotalDescontoTaxaDeEntrega;
    }

    public List<CupomDescontoEntrega> getCuponsDescontoEntrega(){
        return cuponsDeDescontoEntrega;
    }

    @Override
    public String toString(){
        return "\nPedido" +
                "\nData: " + this.data +
                "\nCliente: " + this.cliente +
                "\nItens: " + this.itens +
                "\nTaxa de Entrega: " + this.taxaEntrega +
                "\nCupons de Desconto: " + this.cuponsDeDescontoEntrega;
    }
}
