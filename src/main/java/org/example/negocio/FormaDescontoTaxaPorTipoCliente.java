package org.example.negocio;

import org.example.model.CupomDescontoEntrega;
import org.example.model.Pedido;

import java.util.HashMap;
import java.util.Map;

public class FormaDescontoTaxaPorTipoCliente implements IFormaDescontoTaxaEntrega {
    private Map<String, Double> descontosPorTipoCliente;
    private String tipoCliente;

    public FormaDescontoTaxaPorTipoCliente(){
        descontosPorTipoCliente = new HashMap<>();
        descontosPorTipoCliente.put("Ouro", 3.0);
        descontosPorTipoCliente.put("Prata", 2.0);
        descontosPorTipoCliente.put("Bronze", 1.0);
    }

    @Override
    public CupomDescontoEntrega calcularDesconto(Pedido pedido){
        tipoCliente = pedido.getCliente().getTipo();
        double desconto = descontosPorTipoCliente.get(tipoCliente);
        return new CupomDescontoEntrega("FormaDescontoTaxaPorTipoCliente",desconto);
    }

    @Override
    public boolean seAplica(Pedido pedido){
        String tipoCliente = pedido.getCliente().getTipo();
        return descontosPorTipoCliente.containsKey(tipoCliente);
    }
}
