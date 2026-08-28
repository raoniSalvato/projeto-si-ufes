package org.example.negocio;

import org.example.model.CupomDescontoEntrega;
import org.example.model.Pedido;

public class FormaDescontoValorPedido implements IFormaDescontoTaxaEntrega {
    private double limiteValorPedido;
    private double VALOR_DESCONTO = 5.0;

    public FormaDescontoValorPedido(double limiteValorPedido){
        this.limiteValorPedido = limiteValorPedido;
    }

    @Override
    public CupomDescontoEntrega calcularDesconto(Pedido pedido){
        return new CupomDescontoEntrega("Cupom Valor Pedido", VALOR_DESCONTO);
    }

    @Override
    public boolean seAplica(Pedido pedido){
        return pedido.getValorPedido() > limiteValorPedido;
    }
}
