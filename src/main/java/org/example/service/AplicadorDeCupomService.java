package org.example.service;

import org.example.exception.CupomInvalidoException;
import org.example.model.Cupom;
import org.example.model.Pedido;
import org.example.repository.RepositorioDeCupons;

import java.time.LocalDateTime;

public class AplicadorDeCupomService {
    private RepositorioDeCupons repositorioDeCupons;

    public AplicadorDeCupomService(RepositorioDeCupons repositorioDeCupons){
        this.repositorioDeCupons = repositorioDeCupons;
    }

    public void aplicarCupomPedido(Pedido pedido, String codigoCupom, LocalDateTime dataAtual){
        Cupom cupomEncontrado = repositorioDeCupons.buscarPorCodigo(codigoCupom);
        if(cupomEncontrado == null){
            throw new CupomInvalidoException("O cupom " + codigoCupom + " não existe.");
        }
        if(!cupomEncontrado.estaValido(dataAtual)){
            throw new CupomInvalidoException("O cupom " + codigoCupom + " Está fora do perído de validade.");
        }
        if(pedido.getPercentualDeDesconto() >= cupomEncontrado.getPercentualFixoDesconto()){
            throw new CupomInvalidoException("O cupom já aplicado possui desconto igual ou maior que o novo cupom.");
        }
        pedido.aplicarCupom(cupomEncontrado.getCodigo(), cupomEncontrado.getPercentualFixoDesconto());
    }
}
