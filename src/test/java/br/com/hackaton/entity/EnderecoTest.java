package br.com.hackaton.entity;

import br.com.hackaton.exception.ExceptionAdvice;
import br.com.hackaton.utilsTest.EnderecoUtils;
import org.junit.jupiter.api.Test;

import static br.com.hackaton.exception.CodigoError.BAIRRO_ENDERECO_OBRIGATORIO;
import static br.com.hackaton.exception.CodigoError.CIDADE_ENDERECO_OBRIGATORIO;
import static br.com.hackaton.exception.CodigoError.ESTADO_ENDERECO_OBRIGATORIO;
import static br.com.hackaton.exception.CodigoError.LATITUDE_ENDERECO_OBRIGATORIO;
import static br.com.hackaton.exception.CodigoError.LONGITUDE_ENDERECO_OBRIGATORIO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EnderecoTest {

    @Test
    void testEnderecoConstructorWithAllFields() {
        // When & Then
        var endereco = EnderecoUtils.buildEndereco();

        assertNotNull(endereco);
    }

    @Test
    void testEnderecoConstructorWithRequest() {
        // Given
        var request = EnderecoUtils.buildEnderecoRequest();

        // When & Then
        var endereco = new Endereco(request);

        assertNotNull(endereco);
    }

    @Test
    void testEnderecoConstructorWithResponse() {
        // Given
        var response = EnderecoUtils.buildEnderecoResponse();

        // When & Then
        var endereco = new Endereco(response);

        assertNotNull(endereco);
    }

    @Test
    void testValidaBairroThrowsException() {
        // When & Then
        var exception = assertThrows(ExceptionAdvice.class, () -> EnderecoUtils.buildEndereco().withBairro(""));
        assertEquals(BAIRRO_ENDERECO_OBRIGATORIO.getMensagem(), exception.getMessage());
    }

    @Test
    void testValidaCidadeThrowsException() {
        // When & Then
        var exception = assertThrows(ExceptionAdvice.class, () -> EnderecoUtils.buildEndereco().withCidade(""));
        assertEquals(CIDADE_ENDERECO_OBRIGATORIO.getMensagem(), exception.getMessage());
    }

    @Test
    void testValidaEstadoThrowsException() {
        // When & Then
        var exception = assertThrows(ExceptionAdvice.class, () -> EnderecoUtils.buildEndereco().withEstado(""));
        assertEquals(ESTADO_ENDERECO_OBRIGATORIO.getMensagem(), exception.getMessage());
    }

    @Test
    void testValidaLatitudeThrowsException() {
        // When & Then
        var exception = assertThrows(ExceptionAdvice.class, () -> EnderecoUtils.buildEndereco().withLatitude(null));
        assertEquals(LATITUDE_ENDERECO_OBRIGATORIO.getMensagem(), exception.getMessage());
    }

    @Test
    void testValidaLongitudeThrowsException() {
        // When & Then
        var exception = assertThrows(ExceptionAdvice.class, () -> EnderecoUtils.buildEndereco().withLongitude(null));
        assertEquals(LONGITUDE_ENDERECO_OBRIGATORIO.getMensagem(), exception.getMessage());
    }

    @Test
    void testAtualiza() {
        // Given
        var endereco = EnderecoUtils.buildEndereco();
        var request = EnderecoUtils.buildEnderecoRequest();

        // When
        endereco.atualiza(request);

        // Then
        assertNotNull(endereco);
    }
}