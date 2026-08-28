package org.example.negocio;

import org.example.model.CupomDescontoEntrega;
import org.example.model.Pedido;

public class FormaDescontoTaxaPorBairro implements IFormaDescontoTaxaEntrega {
    private String bairroCliente;

    @Override
    public CupomDescontoEntrega calcularDesconto(Pedido pedido){
        bairroCliente = pedido.getCliente().getBairro();
        if(bairroCliente.equalsIgnoreCase("Centro")) {
            return new CupomDescontoEntrega("FormaDescontoTaxaPorBairro",2.0);
        }else if(bairroCliente.equalsIgnoreCase("Bela Vista")){
            return new CupomDescontoEntrega("FormaDescontoTaxaPorBairro", 3.0);
        }else{
            return new CupomDescontoEntrega("FormaDescontoTaxaPorBairro", 1.5);
        }
    }

    @Override
    public boolean seAplica(Pedido pedido){
        bairroCliente = pedido.getCliente().getBairro();
        return "Centro".equalsIgnoreCase(bairroCliente) ||
                "Bela Vista".equalsIgnoreCase(bairroCliente) ||
                "Cidade Maravilhosa".equalsIgnoreCase(bairroCliente);
    }
}

