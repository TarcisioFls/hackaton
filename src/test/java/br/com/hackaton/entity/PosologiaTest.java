package br.com.hackaton.entity;

import br.com.hackaton.controller.response.MedicamentoResponse;
import br.com.hackaton.exception.ExceptionAdvice;
import br.com.hackaton.utilsTest.MedicamentoUtils;
import br.com.hackaton.utilsTest.PosologiaUtils;
import org.junit.jupiter.api.Test;

import static br.com.hackaton.exception.CodigoError.DESCRICAO_POSOLOGIA_OBRIGATORIA;
import static br.com.hackaton.exception.CodigoError.QUANTIDADE_POSOLOGIA_MAIOR_QUE_ZERO;
import static br.com.hackaton.exception.CodigoError.QUANTIDADE_POSOLOGIA_OBRIGATORIA;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PosologiaTest {

    @Test
    void testPosologiaConstructorWithAllFields() {
        // When & Then
        var posologia = PosologiaUtils.buildPosologia();

        assertNotNull(posologia);
    }

    @Test
    void testPosologiaConstructorWithResponse() {
        // Given
        var medicamentoResponse = MedicamentoUtils.buildMedicamentoResponse();
        var receita = new Receita();

        // When & Then
        var posologia = new Posologia("Tomar 1 comprimido a cada 8 horas", 3, medicamentoResponse, receita);

        assertNotNull(posologia);
    }

    @Test
    void testValidaDescricaoThrowsException() {
        // When & Then
        var exception = assertThrows(ExceptionAdvice.class, () -> new Posologia("", 3, new MedicamentoResponse(), new Receita()));
        assertEquals(DESCRICAO_POSOLOGIA_OBRIGATORIA.getMensagem(), exception.getMessage());
    }

    @Test
    void testValidaQuantidadeThrowsExceptionWhenNull() {
        // When & Then
        var exception = assertThrows(ExceptionAdvice.class, () -> new Posologia("Tomar 1 comprimido a cada 8 horas", null, new MedicamentoResponse(), new Receita()));
        assertEquals(QUANTIDADE_POSOLOGIA_OBRIGATORIA.getMensagem(), exception.getMessage());
    }

    @Test
    void testValidaQuantidadeThrowsExceptionWhenZeroOrNegative() {
        // When & Then
        var exception = assertThrows(ExceptionAdvice.class, () -> new Posologia("Tomar 1 comprimido a cada 8 horas", 0, new MedicamentoResponse(), new Receita()));
        assertEquals(QUANTIDADE_POSOLOGIA_MAIOR_QUE_ZERO.getMensagem(), exception.getMessage());
    }

}