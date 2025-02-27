package br.com.hackaton.service;

import br.com.hackaton.exception.ExceptionAdvice;
import br.com.hackaton.service.impl.ReceitaPdfServiceImpl;
import br.com.hackaton.utilsTest.ReceitaUtils;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class ReceitaPdfServiceImplTest {

    private final ReceitaService receitaService = mock(ReceitaService.class);
    private final ReceitaPdfServiceImpl receitaPdfService = new ReceitaPdfServiceImpl(receitaService);

    @Nested
    class DownloadPdf {
        @Test
        void deveBaixarPdf() {
            // Given
            var receitaResponse = ReceitaUtils.buildReceitaResponse();
            when(receitaService.buscarPorId(1L)).thenReturn(receitaResponse);

            // When
            Optional<byte[]> pdfBytes = receitaPdfService.downloadPdf(1L);

            // Then
            assertTrue(pdfBytes.isPresent());
            assertTrue(pdfBytes.get().length > 0);
        }
        @Test
        void deveRetornarExceptionQuandoReceitaNaoEncontrada() {
            // Given
            when(receitaService.buscarPorId(1L)).thenReturn(null);

            // When & Then
            assertThrows(ExceptionAdvice.class, () -> receitaPdfService.downloadPdf(1L));
        }
    }

}