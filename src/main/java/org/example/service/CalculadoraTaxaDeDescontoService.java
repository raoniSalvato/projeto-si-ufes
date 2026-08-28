package org.example.service;

import org.example.model.CupomDescontoEntrega;
import org.example.model.Pedido;
import org.example.negocio.*;

import java.util.ArrayList;
import java.util.List;

public class CalculadoraTaxaDeDescontoService {

    private List<IFormaDescontoTaxaEntrega> formasDeDesconto;

    public CalculadoraTaxaDeDescontoService() {
        formasDeDesconto = new ArrayList<>();
        formasDeDesconto.add(new FormaDescontoTipoItem());
        formasDeDesconto.add(new FormaDescontoTaxaPorBairro());
        formasDeDesconto.add(new FormaDescontoValorPedido(200.0));
        formasDeDesconto.add(new FormaDescontoTaxaPorTipoCliente());
    }

    public void calcularDesconto(Pedido pedido){
        if(pedido == null){
            throw new IllegalArgumentException("O pedido não pode ser nulo.");
        }
        for(IFormaDescontoTaxaEntrega forma: formasDeDesconto){
            if(forma.seAplica(pedido)){
                CupomDescontoEntrega cupom = forma.calcularDesconto(pedido);
                pedido.aplicarDesconto(cupom);
            }
        }
    }
}
