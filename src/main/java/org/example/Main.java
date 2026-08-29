package org.example;

import org.example.exception.CupomInvalidoException;
import org.example.model.*;
import org.example.repository.RepositorioDeCupons;
import org.example.service.AplicadorDeCupomService;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {

        RepositorioDeCupons repositorioDeCupons = new RepositorioDeCupons();
        repositorioDeCupons.adicionarCupom(new Cupom("DESC10", 10.0, LocalDateTime.of(2026, 9, 25, 0, 0), LocalDateTime.of(2026, 9, 27, 23, 59)));
        repositorioDeCupons.adicionarCupom(new Cupom("DESC20", 20.0, LocalDateTime.of(2026, 10, 1, 0, 0), LocalDateTime.of(2026, 10, 5, 23, 59)));
        repositorioDeCupons.adicionarCupom(new Cupom("DESC30", 30.0, LocalDateTime.of(2026, 9, 24, 0, 0), LocalDateTime.of(2026, 9, 24, 23, 59)));
        repositorioDeCupons.adicionarCupom(new Cupom("DIAPAI12", 12.0, LocalDateTime.of(2026, 10, 9, 0, 0), LocalDateTime.of(2026, 10, 10, 23, 59)));
        repositorioDeCupons.adicionarCupom(new Cupom("DIAMAE12", 12.0, LocalDateTime.of(2026, 10, 10, 0, 0), LocalDateTime.of(2026, 10, 12, 23, 59)));
        repositorioDeCupons.adicionarCupom(new Cupom("NATAL10", 10.0, LocalDateTime.of(2026, 9, 20, 0, 0), LocalDateTime.of(2026, 9, 26, 23, 59)));
        repositorioDeCupons.adicionarCupom(new Cupom("FESTA15", 15.0, LocalDateTime.of(2026, 9, 30, 18, 0), LocalDateTime.of(2026, 10, 1, 6, 0)));
        repositorioDeCupons.adicionarCupom(new Cupom("BLACK50", 50.0, LocalDateTime.of(2026, 9, 28, 0, 0), LocalDateTime.of(2026, 9, 28, 23, 59)));

        AplicadorDeCupomService aplicadorDeCupomService = new AplicadorDeCupomService(repositorioDeCupons);

        Cliente cliente = new Cliente("João", "Ouro", 100, "Rua A", "Centro", "Alegre");
        Pedido pedido = new Pedido(LocalDateTime.now(), cliente);
        pedido.adicionarItem(new Item("Arroz", 2, 100.0, "Alimentação"));

        System.out.println("Valor total inicial (sem desconto): R$ " + pedido.getValorPedido());
        LocalDateTime dataSimuladaCompra = LocalDateTime.of(2026, 9, 26, 10, 0);

        try{
            aplicadorDeCupomService.aplicarCupomPedido(pedido, "DESC10", dataSimuladaCompra);
            System.out.println("Cupom " + pedido.getCodigo() + " aplicado com sucesso!");
            System.out.println("Valor total com desconto do cupom " + pedido.getCodigo() + ": R$ " + pedido.getValorPedido());
        } catch (CupomInvalidoException e){
            System.out.println("Erro: " + e.getMessage());
        }

        LocalDateTime dataBlackFriday = LocalDateTime.of(2026, 9, 28, 14, 30);

        try {
            aplicadorDeCupomService.aplicarCupomPedido(pedido, "BLACK50", dataBlackFriday);
            System.out.println("Cupom " + pedido.getCodigo() + " aplicado com sucesso!");
            System.out.println("Valor total com desconto do cupom " + pedido.getCodigo() + ": R$ " + pedido.getValorPedido());
        } catch (CupomInvalidoException e) {
            System.out.println("Erro: " + e.getMessage());
        }

    }
}