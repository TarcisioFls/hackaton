package br.com.hackaton.controller;

import br.com.hackaton.controller.request.UbsRequest;
import br.com.hackaton.exception.ExceptionAdviceHandler;
import br.com.hackaton.service.UbsService;
import br.com.hackaton.utils.UbsUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

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
public class UbsControllerTest {

    private static final String BASE_URL = "/ubs";
    private static final String BUSCA_POR_ID_URL = BASE_URL + "/{id}";
    private static final String BUSCA_TODOS_URL = BASE_URL;
    private static final String BUSCA_POR_RECEITA_URL = BASE_URL + "/receita/{receitaId}";
    private static final String ENVIAR_EMAIL_URL = BASE_URL + "/receita/{receitaId}/enviar-email";

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final UbsService service = mock(UbsService.class);

    private final UbsController controller = new UbsController(service);

    private final MockMvc mockMvc = MockMvcBuilders
            .standaloneSetup(controller)
            .setControllerAdvice(new ExceptionAdviceHandler())
            .build();

    @Nested
    class Criar {
        @Test
        void deveCriarUbs() throws Exception {
            // Given
            var request = UbsUtils.buildUbsRequest();

            doNothing().when(service).cria(any(UbsRequest.class));

            // When & Then
            mockMvc.perform(post(BASE_URL)
                            .contentType(APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isCreated());
        }
        @Test
        void deveRetornarBadRequestQuandoNomeNull() throws Exception {
            // Given
            var request = UbsUtils.buildUbsRequest().withNome(null);

            // When & Then
            mockMvc.perform(post(BASE_URL)
                            .contentType(APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isBadRequest());
        }
        @Test
        void deveRetornarBadRequestQuandoTelefoneNull() throws Exception {
            // Given
            var request = UbsUtils.buildUbsRequest().withTelefone(null);

            // When & Then
            mockMvc.perform(post(BASE_URL)
                            .contentType(APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isBadRequest());
        }
        @Test
        void deveRetornarBadRequestQuandoEnderecoNull() throws Exception {
            // Given
            var request = UbsUtils.buildUbsRequest().withEndereco(null);

            // When & Then
            mockMvc.perform(post(BASE_URL)
                            .contentType(APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isBadRequest());
        }
        @Test
        void deveRetornarBadRequestQuandoInicioAtendimentoNull() throws Exception {
            // Given
            var request = UbsUtils.buildUbsRequest().withInicioAtendimento(null);

            // When & Then
            mockMvc.perform(post(BASE_URL)
                            .contentType(APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isBadRequest());
        }
        @Test
        void deveRetornarBadRequestQuandoFimAtendimentoNull() throws Exception {
            // Given
            var request = UbsUtils.buildUbsRequest().withFimAtendimento(null);

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
        void deveBuscarUbsPorId() throws Exception {
            // Given
            var response = UbsUtils.buildUbsResponse();

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
        void deveAtualizarUbs() throws Exception {
            // Given
            var request = UbsUtils.buildUbsRequest();
            var response = UbsUtils.buildUbsResponse();

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
            var request = UbsUtils.buildUbsRequest().withNome(null);

            // When & Then
            mockMvc.perform(put(BUSCA_POR_ID_URL, 1L)
                            .contentType(APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isBadRequest());
        }
        @Test
        void deveRetornarBadRequestQuandoTelefoneNull() throws Exception {
            // Given
            var request = UbsUtils.buildUbsRequest().withTelefone(null);

            // When & Then
            mockMvc.perform(put(BUSCA_POR_ID_URL, 1L)
                            .contentType(APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isBadRequest());
        }
        @Test
        void deveRetornarBadRequestQuandoEnderecoNull() throws Exception {
            // Given
            var request = UbsUtils.buildUbsRequest().withEndereco(null);

            // When & Then
            mockMvc.perform(put(BUSCA_POR_ID_URL, 1L)
                            .contentType(APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isBadRequest());
        }
        @Test
        void deveRetornarBadRequestQuandoInicioAtendimentoNull() throws Exception {
            // Given
            var request = UbsUtils.buildUbsRequest().withInicioAtendimento(null);

            // When & Then
            mockMvc.perform(put(BUSCA_POR_ID_URL, 1L)
                            .contentType(APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isBadRequest());
        }
        @Test
        void deveRetornarBadRequestQuandoFimAtendimentoNull() throws Exception {
            // Given
            var request = UbsUtils.buildUbsRequest().withFimAtendimento(null);

            // When & Then
            mockMvc.perform(put(BUSCA_POR_ID_URL, 1L)
                            .contentType(APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isBadRequest());
        }
    }

    @Nested
    class BuscarTodos {
        @Test
        void deveBuscarTodasUbs() throws Exception {
            // Given
            var response = new PageImpl<>(List.of(UbsUtils.buildUbsResponse()), PageRequest.of(0, 50), 1);

            when(service.buscarTodos(0, 50)).thenReturn(response);

            // When & Then
            mockMvc.perform(get(BUSCA_TODOS_URL)
                            .contentType(APPLICATION_JSON))
                    .andExpect(status().isOk());
        }
    }

    @Nested
    class BuscarPorReceita {
        @Test
        void deveBuscarUbsPorReceita() throws Exception {
            // Given
            var response = List.of(UbsUtils.buildUbsComMedicamentoResponse());

            when(service.encontrarUbsProximasDePacienteComMedicamentos(1L)).thenReturn(response);

            // When & Then
            mockMvc.perform(get(BUSCA_POR_RECEITA_URL, 1L)
                            .contentType(APPLICATION_JSON))
                    .andExpect(status().isOk());
        }
    }

    @Nested
    class EnviarEmail {
        @Test
        void deveEnviarEmail() throws Exception {
            // Given
            doNothing().when(service).enviarEmailUbsProximasDePacienteComMedicamentos(1L);

            // When & Then
            mockMvc.perform(post(ENVIAR_EMAIL_URL, 1L)
                            .contentType(APPLICATION_JSON))
                    .andExpect(status().isOk());
        }
    }
}