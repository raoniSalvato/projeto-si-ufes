package org.example.negocio;

import org.example.model.CupomDescontoEntrega;
import org.example.model.Pedido;

public interface IFormaDescontoTaxaEntrega {
    CupomDescontoEntrega calcularDesconto(Pedido pedido);
    boolean seAplica(Pedido pedido);
}
