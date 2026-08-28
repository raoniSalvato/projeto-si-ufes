package org.example;

import org.example.model.Cliente;
import org.example.model.CupomDescontoEntrega;
import org.example.model.Item;
import org.example.model.Pedido;
import org.example.service.CalculadoraTaxaDeDescontoService;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        Cliente cliente = new Cliente("João", "Ouro", 100, "Rua A", "Centro", "Alegre");

        Pedido pedido = new Pedido(LocalDateTime.now(), cliente);

        pedido.adicionarItem(new Item("Arroz", 2, 100.0, "Alimentação"));
        pedido.adicionarItem(new Item("Livro", 1, 50.0, "Educação"));

        CalculadoraTaxaDeDescontoService calculadora = new CalculadoraTaxaDeDescontoService();
        calculadora.calcularDesconto(pedido);

        System.out.println("Valor do pedido: R$ " + pedido.getValorPedido());
        System.out.println("\nCupons:");

        for(CupomDescontoEntrega cupom : pedido.getCuponsDescontoEntrega()){
            System.out.println(cupom);
        }
        System.out.println("\nDesconto concedido na taxa de entrega: R$ " + pedido.getValorTotalDescontoTaxaDeEntrega());
    }
}