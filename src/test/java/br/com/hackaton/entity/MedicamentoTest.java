package br.com.hackaton.entity;

import br.com.hackaton.exception.ExceptionAdvice;
import br.com.hackaton.utilsTest.MedicamentoUtils;
import org.junit.jupiter.api.Test;

import static br.com.hackaton.exception.CodigoError.NOME_MEDICAMENTO_OBRIGATORIO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MedicamentoTest {

    @Test
    void testMedicamentoConstructorWithAllFields() {
        // When & Then
        var medicamento = MedicamentoUtils.buildMedicamento();

        assertNotNull(medicamento);
    }

    @Test
    void testMedicamentoConstructorWithRequest() {
        // Given
        var request = MedicamentoUtils.buildMedicamentoRequest();

        // When & Then
        var medicamento = new Medicamento(request);

        assertNotNull(medicamento);
    }

    @Test
    void testMedicamentoConstructorWithResponse() {
        // Given
        var response = MedicamentoUtils.buildMedicamentoResponse();

        // When & Then
        var medicamento = new Medicamento(response);

        assertNotNull(medicamento);
    }

    @Test
    void testValidaNomeThrowsException() {
        // When & Then
        var exception = assertThrows(ExceptionAdvice.class, () -> new Medicamento("", Tarja.PRETA));
        assertEquals(NOME_MEDICAMENTO_OBRIGATORIO.getMensagem(), exception.getMessage());
    }

    @Test
    void testAtualiza() {
        // Given
        var medicamento = MedicamentoUtils.buildMedicamento();
        var request = MedicamentoUtils.buildMedicamentoRequest();

        // When
        medicamento.atualiza(request);

        // Then
        assertNotNull(medicamento);
    }
}