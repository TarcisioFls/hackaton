package br.com.hackaton.entity;

import br.com.hackaton.exception.ExceptionAdvice;
import br.com.hackaton.utilsTest.MedicoUtils;
import org.junit.jupiter.api.Test;

import static br.com.hackaton.exception.CodigoError.CRM_MEDICO_OBRIGATORIO;
import static br.com.hackaton.exception.CodigoError.EMAIL_PACIENTE_OBRIGATORIO;
import static br.com.hackaton.exception.CodigoError.NOME_MEDICO_OBRIGATORIO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MedicoTest {

    @Test
    void testMedicoConstructorWithAllFields() {
        // When & Then
        var medico = MedicoUtils.buildMedico();

        assertNotNull(medico);
    }

    @Test
    void testMedicoConstructorWithRequest() {
        // Given
        var request = MedicoUtils.buildMedicoRequest();

        // When & Then
        var medico = new Medico(request);

        assertNotNull(medico);
    }

    @Test
    void testMedicoConstructorWithResponse() {
        // Given
        var response = MedicoUtils.buildMedicoResponse();

        // When & Then
        var medico = new Medico(response);

        assertNotNull(medico);
    }

    @Test
    void testValidaNomeThrowsException() {
        // When & Then
        var exception = assertThrows(ExceptionAdvice.class, () -> new Medico("", "john.doe@example.com", "123-456-7890", "123456", null));
        assertEquals(NOME_MEDICO_OBRIGATORIO.getMensagem(), exception.getMessage());
    }

    @Test
    void testValidaEmailThrowsException() {
        // When & Then
        var exception = assertThrows(ExceptionAdvice.class, () -> new Medico("Dr. John Doe", "", "123-456-7890", "123456", null));
        assertEquals(EMAIL_PACIENTE_OBRIGATORIO.getMensagem(), exception.getMessage());
    }

    @Test
    void testValidaCrmThrowsException() {
        // When & Then
        var exception = assertThrows(ExceptionAdvice.class, () -> new Medico("Dr. John Doe", "john.doe@example.com", "123-456-7890", "", null));
        assertEquals(CRM_MEDICO_OBRIGATORIO.getMensagem(), exception.getMessage());
    }

    @Test
    void testAtualizar() {
        // Given
        var medico = MedicoUtils.buildMedico();
        var request = MedicoUtils.buildMedicoRequest();

        // When
        medico.atualizar(request);

        // Then
        assertNotNull(medico);
    }
}