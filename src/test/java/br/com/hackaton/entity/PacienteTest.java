package br.com.hackaton.entity;

import br.com.hackaton.exception.ExceptionAdvice;
import br.com.hackaton.utilsTest.PacienteUtils;
import org.junit.jupiter.api.Test;

import static br.com.hackaton.exception.CodigoError.CPF_PACIENTE_OBRIGATORIO;
import static br.com.hackaton.exception.CodigoError.EMAIL_PACIENTE_OBRIGATORIO;
import static br.com.hackaton.exception.CodigoError.NOME_PACIENTE_OBRIGATORIO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PacienteTest {

    @Test
    void testPacienteConstructorWithAllFields() {
        // When & Then
        var paciente = PacienteUtils.buildPaciente();

        assertNotNull(paciente);
    }

    @Test
    void testPacienteConstructorWithRequest() {
        // Given
        var request = PacienteUtils.buildPacienteRequest();

        // When & Then
        var paciente = new Paciente(request);

        assertNotNull(paciente);
    }

    @Test
    void testPacienteConstructorWithResponse() {
        // Given
        var response = PacienteUtils.buildPacienteResponse();

        // When & Then
        var paciente = new Paciente(response);

        assertNotNull(paciente);
    }

    @Test
    void testValidaNomeThrowsException() {
        // When & Then
        var exception = assertThrows(ExceptionAdvice.class, () -> PacienteUtils.buildPaciente().withNome(""));
        assertEquals(NOME_PACIENTE_OBRIGATORIO.getMensagem(), exception.getMessage());
    }

    @Test
    void testValidaEmailThrowsException() {
        // When & Then
        var exception = assertThrows(ExceptionAdvice.class, () -> PacienteUtils.buildPaciente().withEmail(""));
        assertEquals(EMAIL_PACIENTE_OBRIGATORIO.getMensagem(), exception.getMessage());
    }

    @Test
    void testValidaCpfThrowsException() {
        // When & Then
        var exception = assertThrows(ExceptionAdvice.class, () -> PacienteUtils.buildPaciente().withCpf(""));
        assertEquals(CPF_PACIENTE_OBRIGATORIO.getMensagem(), exception.getMessage());
    }

    @Test
    void testAtualiza() {
        // Given
        var paciente = PacienteUtils.buildPaciente();
        var request = PacienteUtils.buildPacienteRequest();

        // When
        paciente.atualiza(request);

        // Then
        assertNotNull(paciente);
    }
}