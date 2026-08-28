package org.example.service;

import org.example.model.Cliente;
import org.example.model.Item;
import org.example.model.Pedido;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;

class CalculadoraTaxaDeDescontoServiceTest {

    @Test
    void deveAplicarDescontosEmCascataRespeitandoLimiteDeDezReais() {
        // 1. Cenário (Arrange): Preparamos os dados exatamente como na sua classe Main
        Cliente cliente = new Cliente("João", "Ouro", 100, "Rua A", "Centro", "Alegre");
        Pedido pedido = new Pedido(LocalDateTime.now(), cliente);
        pedido.adicionarItem(new Item("Arroz", 2, 100.0, "Alimentação"));
        pedido.adicionarItem(new Item("Livro", 1, 50.0, "Educação"));

        CalculadoraTaxaDeDescontoService calculadora = new CalculadoraTaxaDeDescontoService();

        // 2. Ação (Act): Rodamos o motor de regras
        calculadora.calcularDesconto(pedido);

        // 3. Verificações (Assert): O computador confere a matemática para você
        assertEquals(10.0, pedido.getValorTotalDescontoTaxaDeEntrega(), "O desconto não respeitou o limite máximo de R$ 10,00.");
        assertEquals(3, pedido.getCuponsDescontoEntrega().size(), "A quantidade de cupons aplicados (Item, Bairro e Parcial) está incorreta.");
    }
}