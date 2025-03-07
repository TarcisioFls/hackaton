package br.com.hackaton.entity;

import br.com.hackaton.exception.ExceptionAdvice;
import br.com.hackaton.utilsTest.MedicoUtils;
import br.com.hackaton.utilsTest.PacienteUtils;
import br.com.hackaton.utilsTest.ReceitaUtils;
import org.junit.jupiter.api.Test;

import static br.com.hackaton.exception.CodigoError.MEDICO_RECEITA_OBRIGATORIO;
import static br.com.hackaton.exception.CodigoError.PACIENTE_RECEITA_OBRIGATORIO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ReceitaTest {

    @Test
    void testReceitaConstructorWithAllFields() {
        // When & Then
        var receita = ReceitaUtils.buildReceita();

        assertNotNull(receita);
        assertEquals(1L, receita.getId());
        assertNotNull(receita.getMedico());
        assertNotNull(receita.getPaciente());
    }

    @Test
    void testReceitaConstructorWithMedicoAndPaciente() {
        // Given
        var medico = new Medico();
        var paciente = new Paciente();

        // When & Then
        var receita = new Receita(medico, paciente);

        assertNotNull(receita);
        assertNotNull(receita.getMedico());
        assertNotNull(receita.getPaciente());
    }

    @Test
    void testReceitaConstructorWithResponses() {
        // Given
        var medicoResponse = MedicoUtils.buildMedicoResponse();
        var pacienteResponse = PacienteUtils.buildPacienteResponse();

        // When & Then
        var receita = new Receita(medicoResponse, pacienteResponse);

        assertNotNull(receita);
        assertNotNull(receita.getMedico());
        assertNotNull(receita.getPaciente());
    }

    @Test
    void testValidaMedicoThrowsException() {
        // When & Then
        var exception = assertThrows(ExceptionAdvice.class, () -> new Receita(null, new Paciente()));
        assertEquals(MEDICO_RECEITA_OBRIGATORIO.getMensagem(), exception.getMessage());
    }

    @Test
    void testValidaPacienteThrowsException() {
        // When & Then
        var exception = assertThrows(ExceptionAdvice.class, () -> new Receita(new Medico(), null));
        assertEquals(PACIENTE_RECEITA_OBRIGATORIO.getMensagem(), exception.getMessage());
    }
}