package org.example.service;

import org.example.exception.CupomInvalidoException;
import org.example.model.Cupom;
import org.example.model.ICupomRepository;
import org.example.model.Pedido;
import org.example.infra.CupomRepositoryEmMemoria;

import java.time.LocalDateTime;
import java.util.Optional;

public class AplicadorDeCupomService {
    private ICupomRepository repositorioDeCupons;

    public AplicadorDeCupomService(ICupomRepository repositorioDeCupons){
        this.repositorioDeCupons = repositorioDeCupons;
    }

    public void aplicarCupomPedido(Pedido pedido, String codigoCupom, LocalDateTime dataAtual){
        Optional<Cupom> cupomOptional = repositorioDeCupons.getPorCodigo(codigoCupom);

        if(cupomOptional.isEmpty()){
            throw new CupomInvalidoException("O cupom " + codigoCupom + " não existe.");
        }

        Cupom cupomEncontrado = cupomOptional.get();

        if(!cupomEncontrado.estaValido(dataAtual)){
            throw new CupomInvalidoException("O cupom " + codigoCupom + " Está fora do perído de validade.");
        }
        if(pedido.getPercentualDeDesconto() >= cupomEncontrado.getPercentualFixoDesconto()){
            throw new CupomInvalidoException("O cupom já aplicado possui desconto igual ou maior que o novo cupom.");
        }
        pedido.aplicarCupom(cupomEncontrado.getCodigo(), cupomEncontrado.getPercentualFixoDesconto());
    }
}
