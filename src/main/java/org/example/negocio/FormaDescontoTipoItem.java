package org.example.negocio;

import org.example.model.CupomDescontoEntrega;
import org.example.model.Item;
import org.example.model.Pedido;

import java.util.HashMap;
import java.util.Map;

public class FormaDescontoTipoItem implements IFormaDescontoTaxaEntrega {
    private Map<String, Double> descontosPorTipoItem;

    public FormaDescontoTipoItem(){
        descontosPorTipoItem = new HashMap<>();
        descontosPorTipoItem.put("Alimentação", 5.0);
        descontosPorTipoItem.put("Educação", 2.0);
        descontosPorTipoItem.put("Lazer", 1.5);
    }

    @Override
    public CupomDescontoEntrega calcularDesconto(Pedido pedido){
        String tipoItem = "";
        double totalDesconto = 0;
        for(Item i: pedido.getItens()){
            if(descontosPorTipoItem.containsKey(i.getTipo())) {
                totalDesconto += descontosPorTipoItem.get(i.getTipo());
                tipoItem = i.getTipo();
            }
        }
        return new CupomDescontoEntrega("Desconto tipo item - Tipo: " + tipoItem, totalDesconto);
    }

    @Override
    public boolean seAplica(Pedido pedido){
        for(Item i: pedido.getItens()){
            if(descontosPorTipoItem.containsKey(i.getTipo())){
                return true;
            }
        }
        return false;
    }
}
