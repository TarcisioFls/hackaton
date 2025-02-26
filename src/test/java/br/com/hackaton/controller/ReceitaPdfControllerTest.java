package br.com.hackaton.controller;

import br.com.hackaton.service.ReceitaPdfService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
public class ReceitaPdfControllerTest {

    private static final String BASE_URL = "/receita/pdf";
    private static final String DOWNLOAD_URL = BASE_URL + "/download/{id}";

    private final ReceitaPdfService service = mock(ReceitaPdfService.class);

    private final ReceitaPdfController controller = new ReceitaPdfController(service);

    private final MockMvc mockMvc = MockMvcBuilders
            .standaloneSetup(controller)
            .build();

    @Nested
    class DownloadPdf {
        @Test
        void deveBaixarPdf() throws Exception {
            // Given
            byte[] pdfBytes = new byte[]{1, 2, 3};

            when(service.downloadPdf(1L)).thenReturn(Optional.of(pdfBytes));

            // When & Then
            mockMvc.perform(get(DOWNLOAD_URL, 1L)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(header().string(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=receita.pdf"));
        }
        @Test
        void deveRetornarNotFoundQuandoPdfNaoExistir() throws Exception {
            // Given
            when(service.downloadPdf(1L)).thenReturn(Optional.empty());

            // When & Then
            mockMvc.perform(get(DOWNLOAD_URL, 1L)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNotFound());
        }
    }
}