package org.example;

import org.example.exception.CupomInvalidoException;
import org.example.model.*;
import org.example.infra.CupomRepositoryEmMemoria;
import org.example.service.AplicadorDeCupomService;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {

        try{
            ICupomRepository repositoryCupons = new CupomRepositoryEmMemoria();
            AplicadorDeCupomService aplicadorDeCupomService = new AplicadorDeCupomService(repositoryCupons);

            Cliente cliente = new Cliente("João", "Ouro", 100, "Rua A", "Centro", "Alegre");
            Pedido pedido = new Pedido(LocalDateTime.now(), cliente);
            pedido.adicionarItem(new Item("Arroz", 2, 100.0, "Alimentação"));

            System.out.println("Valor total inicial (sem desconto): R$ " + pedido.getValorPedido());

            LocalDateTime dataSimuladaCompra = LocalDateTime.of(2026, 9, 26, 10, 0);
            aplicadorDeCupomService.aplicarCupomPedido(pedido, "DESC10", dataSimuladaCompra);

            System.out.println("Cupom " + pedido.getCodigo() + " aplicado com sucesso!");
            System.out.println("Valor total com desconto do cupom " + pedido.getCodigo() + ": R$ " + pedido.getValorPedido());

            LocalDateTime dataBlackFriday = LocalDateTime.of(2026, 9, 28, 14, 30);
            aplicadorDeCupomService.aplicarCupomPedido(pedido, "BLACK50", dataBlackFriday);

            System.out.println("Cupom " + pedido.getCodigo() + " aplicado com sucesso!");
            System.out.println("Valor total com desconto do cupom " + pedido.getCodigo() + ": R$ " + pedido.getValorPedido());

        }catch (RuntimeException runtimeException){
            System.out.println(runtimeException.getMessage());
        }
    }
}