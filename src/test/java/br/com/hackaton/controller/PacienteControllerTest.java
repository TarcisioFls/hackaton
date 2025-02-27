package br.com.hackaton.controller;

import br.com.hackaton.controller.request.PacienteRequest;
import br.com.hackaton.exception.ExceptionAdviceHandler;
import br.com.hackaton.service.PacienteService;
import br.com.hackaton.utilsTest.PacienteUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.data.domain.PageImpl;
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
class PacienteControllerTest {

    private static final String BASE_URL = "/pacientes";
    private static final String BUSCA_POR_ID_URL = BASE_URL + "/{id}";

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final PacienteService service = mock(PacienteService.class);

    private final PacienteController controller = new PacienteController(service);

    private final MockMvc mockMvc = MockMvcBuilders
            .standaloneSetup(controller)
            .setControllerAdvice(new ExceptionAdviceHandler())
            .build();

    @Nested
    class Criar {
        @Test
        void deveCriarPaciente() throws Exception {
            // Given
            var request = PacienteUtils.buildPacienteRequest();

            doNothing().when(service).criar(any(PacienteRequest.class));

            // When & Then
            mockMvc.perform(post(BASE_URL)
                            .contentType(APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isCreated());
        }
        @Test
        void deveRetornarBadRequestQuandoNomeNull() throws Exception {
            // Given
            var request = PacienteUtils.buildPacienteRequest().withNome(null);

            // When & Then
            mockMvc.perform(post(BASE_URL)
                            .contentType(APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isBadRequest());
        }
        @Test
        void deveRetornarBadRequestQuandoEmailNull() throws Exception {
            // Given
            var request = PacienteUtils.buildPacienteRequest().withEmail(null);

            // When & Then
            mockMvc.perform(post(BASE_URL)
                            .contentType(APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isBadRequest());
        }
        @Test
        void deveRetornarBadRequestQuandoCpfNull() throws Exception {
            // Given
            var request = PacienteUtils.buildPacienteRequest().withCpf(null);

            // When & Then
            mockMvc.perform(post(BASE_URL)
                            .contentType(APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isBadRequest());
        }
        @Test
        void deveRetornarBadRequestQuandoCnsNull() throws Exception {
            // Given
            var request = PacienteUtils.buildPacienteRequest().withCns(null);

            // When & Then
            mockMvc.perform(post(BASE_URL)
                            .contentType(APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isBadRequest());
        }
        @Test
        void deveRetornarBadRequestQuandoEnderecoNull() throws Exception {
            // Given
            var request = PacienteUtils.buildPacienteRequest().withEnderecoRequest(null);

            // When & Then
            mockMvc.perform(post(BASE_URL)
                            .contentType(APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isBadRequest());
        }
    }

    @Nested
    class BuscarTodosPaginado {
        @Test
        void deveBuscarTodosPacientesPaginado() throws Exception {
            // Given
            var response = new PageImpl<>(List.of(PacienteUtils.buildPacienteResponse()));

            when(service.buscarTodos(0, 10)).thenReturn(response);

            // When & Then
            mockMvc.perform(get(BASE_URL)
                            .contentType(APPLICATION_JSON))
                    .andExpect(status().isOk());
        }
    }

    @Nested
    class BuscaPorId {
        @Test
        void deveBuscarPacientePorId() throws Exception {
            // Given
            var response = PacienteUtils.buildPacienteResponse();

            when(service.buscarPorId(1L)).thenReturn(response);

            // When & Then
            mockMvc.perform(get(BUSCA_POR_ID_URL, 1L)
                            .contentType(APPLICATION_JSON))
                    .andExpect(status().isOk());
        }
    }

    @Nested
    class Atualizar {
        @Test
        void deveAtualizarPaciente() throws Exception {
            // Given
            var request = PacienteUtils.buildPacienteRequest();
            var response = PacienteUtils.buildPacienteResponse();

            when(service.atualizar(1L, request)).thenReturn(response);

            // When & Then
            mockMvc.perform(put(BUSCA_POR_ID_URL, 1L)
                            .contentType(APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isOk());
        }
        @Test
        void deveRetornarBadRequestQuandoNomeNull() throws Exception {
            // Given
            var request = PacienteUtils.buildPacienteRequest().withNome(null);

            // When & Then
            mockMvc.perform(put(BUSCA_POR_ID_URL, 1L)
                            .contentType(APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isBadRequest());
        }
        @Test
        void deveRetornarBadRequestQuandoEmailNull() throws Exception {
            // Given
            var request = PacienteUtils.buildPacienteRequest().withEmail(null);

            // When & Then
            mockMvc.perform(put(BUSCA_POR_ID_URL, 1L)
                            .contentType(APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isBadRequest());
        }
        @Test
        void deveRetornarBadRequestQuandoCpfNull() throws Exception {
            // Given
            var request = PacienteUtils.buildPacienteRequest().withCpf(null);

            // When & Then
            mockMvc.perform(put(BUSCA_POR_ID_URL, 1L)
                            .contentType(APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isBadRequest());
        }
        @Test
        void deveRetornarBadRequestQuandoCnsNull() throws Exception {
            // Given
            var request = PacienteUtils.buildPacienteRequest().withCns(null);

            // When & Then
            mockMvc.perform(put(BUSCA_POR_ID_URL, 1L)
                            .contentType(APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isBadRequest());
        }
        @Test
        void deveRetornarBadRequestQuandoEnderecoNull() throws Exception {
            // Given
            var request = PacienteUtils.buildPacienteRequest().withEnderecoRequest(null);

            // When & Then
            mockMvc.perform(put(BUSCA_POR_ID_URL, 1L)
                            .contentType(APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isBadRequest());
        }
    }

}