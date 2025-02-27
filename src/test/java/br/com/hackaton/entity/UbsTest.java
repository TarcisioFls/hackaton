package br.com.hackaton.entity;

import br.com.hackaton.exception.ExceptionAdvice;
import br.com.hackaton.utilsTest.UbsUtils;
import org.junit.jupiter.api.Test;

import static br.com.hackaton.exception.CodigoError.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UbsTest {

    @Test
    void testUbsConstructorWithAllFields() {
        // When & Then
        var ubs = UbsUtils.buildUbs();

        assertNotNull(ubs);
    }

    @Test
    void testUbsConstructorWithNomeTelefoneAndEndereco() {
        // Given
        var endereco = new Endereco();

        // When & Then
        var ubs = new Ubs("UBS Central", "123456789", "08:00", "17:00", endereco);

        assertNotNull(ubs);
    }

    @Test
    void testValidaNomeThrowsException() {
        // When & Then
        var exception = assertThrows(ExceptionAdvice.class, () -> new Ubs("", "123456789", "08:00", "17:00", new Endereco()));
        assertEquals(NOME_UBS_OBRIGATORIO.getMensagem(), exception.getMessage());
    }

    @Test
    void testValidaTelefoneThrowsException() {
        // When & Then
        var exception = assertThrows(ExceptionAdvice.class, () -> new Ubs("UBS Central", "", "08:00", "17:00", new Endereco()));
        assertEquals(TELEFONE_UBS_OBRIGATORIO.getMensagem(), exception.getMessage());
    }

    @Test
    void testValidaInicioAtendimentoThrowsException() {
        // When & Then
        var exception = assertThrows(ExceptionAdvice.class, () -> new Ubs("UBS Central", "123456789", "", "17:00", new Endereco()));
        assertEquals(INICIO_ATENDIMENTO_UBS_OBRIGATORIO.getMensagem(), exception.getMessage());
    }

    @Test
    void testValidaFimAtendimentoThrowsException() {
        // When & Then
        var exception = assertThrows(ExceptionAdvice.class, () -> new Ubs("UBS Central", "123456789", "08:00", "", new Endereco()));
        assertEquals(FIM_ATENDIMENTO_UBS_OBRIGATORIO.getMensagem(), exception.getMessage());
    }

    @Test
    void testAtualiza() {
        // Given
        var ubs = UbsUtils.buildUbs();
        var request = UbsUtils.buildUbsRequest();

        // When
        ubs.atualiza(request);

        // Then
        assertNotNull(ubs);
    }
}