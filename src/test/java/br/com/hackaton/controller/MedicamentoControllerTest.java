package br.com.hackaton.controller;

import br.com.hackaton.controller.request.MedicamentoRequest;
import br.com.hackaton.exception.ExceptionAdviceHandler;
import br.com.hackaton.service.MedicamentoService;
import br.com.hackaton.utils.MedicamentoUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
public class MedicamentoControllerTest {

    private static final String BASE_URL = "/medicamentos";
    private static final String BUSCA_POR_ID_URL = BASE_URL + "/{id}";

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final MedicamentoService service = mock(MedicamentoService.class);

    private final MedicamentoController controller = new MedicamentoController(service);

    private final MockMvc mockMvc = MockMvcBuilders
            .standaloneSetup(controller)
            .setControllerAdvice(new ExceptionAdviceHandler())
            .build();

    @Nested
    class Criar {
        @Test
        void deveCriarMedicamento() throws Exception {
            // Given
            var request = MedicamentoUtils.buildMedicamentoRequest();

            doNothing().when(service).cria(any(MedicamentoRequest.class));

            // When & Then
            mockMvc.perform(post(BASE_URL)
                            .contentType(APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isCreated());
        }
        @Test
        void deveRetornarBadRequestQuandoNomeNull() throws Exception {
            // Given
            var request = MedicamentoUtils.buildMedicamentoRequest().withNome(null);

            // When & Then
            mockMvc.perform(post(BASE_URL)
                            .contentType(APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isBadRequest());
        }
        @Test
        void deveRetornarBadRequestQuandoTarjaNull() throws Exception {
            // Given
            var request = MedicamentoUtils.buildMedicamentoRequest().withTarja(null);

            // When & Then
            mockMvc.perform(post(BASE_URL)
                            .contentType(APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isBadRequest());
        }
    }

    @Nested
    class BuscaPorId {
        @Test
        void deveBuscarMedicamentoPorId() throws Exception {
            // Given
            var response = MedicamentoUtils.buildMedicamentoResponse();

            when(service.buscarPorId(1L)).thenReturn(response);

            // When & Then
            mockMvc.perform(get(BUSCA_POR_ID_URL, 1L)
                            .contentType(APPLICATION_JSON))
                    .andExpect(status().isOk());
        }
    }

    @Nested
    class Atualiza {
        @Test
        void deveAtualizarMedicamento() throws Exception {
            // Given
            var request = MedicamentoUtils.buildMedicamentoRequest();
            var response = MedicamentoUtils.buildMedicamentoResponse();

            when(service.atualiza(1L, request)).thenReturn(response);

            // When & Then
            mockMvc.perform(put(BUSCA_POR_ID_URL, 1L)
                            .contentType(APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isOk());
        }
        @Test
        void deveRetornarBadRequestQuandoNomeNull() throws Exception {
            // Given
            var request = MedicamentoUtils.buildMedicamentoRequest().withNome(null);

            // When & Then
            mockMvc.perform(put(BUSCA_POR_ID_URL, 1L)
                            .contentType(APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isBadRequest());
        }
        @Test
        void deveRetornarBadRequestQuandoTarjaNull() throws Exception {
            // Given
            var request = MedicamentoUtils.buildMedicamentoRequest().withTarja(null);

            // When & Then
            mockMvc.perform(put(BUSCA_POR_ID_URL, 1L)
                            .contentType(APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isBadRequest());
        }
    }
}