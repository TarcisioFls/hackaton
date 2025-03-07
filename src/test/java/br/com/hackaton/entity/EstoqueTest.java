package br.com.hackaton.entity;

import br.com.hackaton.exception.ExceptionAdvice;
import br.com.hackaton.utilsTest.EstoqueUtils;
import br.com.hackaton.utilsTest.MedicamentoUtils;
import br.com.hackaton.utilsTest.UbsUtils;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static br.com.hackaton.exception.CodigoError.QUANTIDADE_ADICIONADA_ESTOQUE_NEGATIVA;
import static br.com.hackaton.exception.CodigoError.QUANTIDADE_ESTOQUE_NEGATIVA;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EstoqueTest {

    @Test
    void testEstoqueConstructorWithAllFields() {
        // When & Then
        var estoque = EstoqueUtils.buildEstoque();

        assertNotNull(estoque);
        assertEquals(1L, estoque.getId());
        assertEquals(new BigInteger("1"), estoque.getQuantidade());
        assertNotNull(estoque.getMedicamento());
    }

    @Test
    void testEstoqueConstructorWithResponses() {
        // Given
        var quantidade = new BigInteger("100");
        var medicamentoResponse = MedicamentoUtils.buildMedicamentoResponse();
        var ubsResponse = UbsUtils.buildUbsResponse();

        // When & Then
        var estoque = new Estoque(quantidade, medicamentoResponse, ubsResponse);

        assertNotNull(estoque);
        assertEquals(quantidade, estoque.getQuantidade());
        assertNotNull(estoque.getMedicamento());
        assertNotNull(estoque.getUbs());
    }

    @Test
    void testAdicionaQuantidade() {
        // Given
        var estoque = EstoqueUtils.buildEstoque();
        var quantidadeInicial = estoque.getQuantidade();
        var quantidadeAdicionada = new BigInteger("50");

        // When
        estoque.adiciona(quantidadeAdicionada);

        // Then
        assertEquals(quantidadeInicial.add(quantidadeAdicionada), estoque.getQuantidade());
    }

    @Test
    void testRetiraQuantidade() {
        // Given
        var estoque = EstoqueUtils.buildEstoque();
        var quantidadeInicial = estoque.getQuantidade();
        var quantidadeRetirada = new BigInteger("1");

        // When
        estoque.retira(quantidadeRetirada);

        // Then
        assertEquals(quantidadeInicial.subtract(quantidadeRetirada), estoque.getQuantidade());
    }

    @Test
    void testAdicionaQuantidadeThrowsException() {
        // When & Then
        var exception = assertThrows(ExceptionAdvice.class, () -> EstoqueUtils.buildEstoque().adiciona(new BigInteger("-10")));
        assertEquals(QUANTIDADE_ADICIONADA_ESTOQUE_NEGATIVA.getMensagem(), exception.getMessage());
    }

    @Test
    void testRetiraQuantidadeThrowsException() {
        // When & Then
        var exception = assertThrows(ExceptionAdvice.class, () -> EstoqueUtils.buildEstoque().retira(new BigInteger("200")));
        assertEquals(QUANTIDADE_ESTOQUE_NEGATIVA.getMensagem(), exception.getMessage());
    }
}