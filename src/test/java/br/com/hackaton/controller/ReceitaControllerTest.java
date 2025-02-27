package br.com.hackaton.controller;

import br.com.hackaton.controller.request.ReceitaRequest;
import br.com.hackaton.exception.ExceptionAdviceHandler;
import br.com.hackaton.service.ReceitaService;
import br.com.hackaton.utilsTest.ReceitaUtils;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
class ReceitaControllerTest {

    private static final String BASE_URL = "/receitas";
    private static final String BUSCA_POR_ID_URL = BASE_URL + "/{id}";
    private static final String BUSCA_POR_MEDICO_URL = BASE_URL + "/medico/{id}";
    private static final String BUSCA_POR_PACIENTE_URL = BASE_URL + "/paciente/{id}";
    private static final String ENVIAR_EMAIL_URL = BASE_URL + "/{id}/enviar-email";

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final ReceitaService service = mock(ReceitaService.class);

    private final ReceitaController controller = new ReceitaController(service);

    private final MockMvc mockMvc = MockMvcBuilders
            .standaloneSetup(controller)
            .setControllerAdvice(new ExceptionAdviceHandler())
            .build();

    @Nested
    class Criar {
        @Test
        void deveCriarReceita() throws Exception {
            // Given
            var request = ReceitaUtils.buildReceitaRequest();

            doNothing().when(service).criar(any(ReceitaRequest.class));

            // When & Then
            mockMvc.perform(post(BASE_URL)
                            .contentType(APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isCreated());
        }
        @Test
        void deveRetornarBadRequestQuandoPosologiasNull() throws Exception {
            // Given
            var request = ReceitaUtils.buildReceitaRequest().withPosologias(null);

            // When & Then
            mockMvc.perform(post(BASE_URL)
                            .contentType(APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isBadRequest());
        }
        @Test
        void deveRetornarBadRequestQuandoMedicoIdNull() throws Exception {
            // Given
            var request = ReceitaUtils.buildReceitaRequest().withMedicoId(null);

            // When & Then
            mockMvc.perform(post(BASE_URL)
                            .contentType(APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isBadRequest());
        }
        @Test
        void deveRetornarBadRequestQuandoPacienteIdNull() throws Exception {
            // Given
            var request = ReceitaUtils.buildReceitaRequest().withPacienteId(null);

            // When & Then
            mockMvc.perform(post(BASE_URL)
                            .contentType(APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isBadRequest());
        }
    }

    @Nested
    class BuscarTodos {
        @Test
        void deveBuscarTodasReceitas() throws Exception {
            // Given
            var response = new PageImpl<>(List.of(ReceitaUtils.buildReceitaResponse()), PageRequest.of(0, 50), 1);

            when(service.buscarTodos(0, 50)).thenReturn(response);

            // When & Then
            mockMvc.perform(get(BASE_URL)
                            .contentType(APPLICATION_JSON))
                    .andExpect(status().isOk());
        }
    }

    @Nested
    class BuscarPorId {
        @Test
        void deveBuscarReceitaPorId() throws Exception {
            // Given
            var response = ReceitaUtils.buildReceitaResponse();

            when(service.buscarPorId(1L)).thenReturn(response);

            // When & Then
            mockMvc.perform(get(BUSCA_POR_ID_URL, 1L)
                            .contentType(APPLICATION_JSON))
                    .andExpect(status().isOk());
        }
    }

    @Nested
    class BuscarPorMedico {
        @Test
        void deveBuscarReceitasPorMedico() throws Exception {
            // Given
            var response = new PageImpl<>(List.of(ReceitaUtils.buildReceitaResponse()), PageRequest.of(0, 50), 1);

            when(service.buscarPorMedicoId(1L, 0, 50)).thenReturn(response);

            // When & Then
            mockMvc.perform(get(BUSCA_POR_MEDICO_URL, 1L)
                            .contentType(APPLICATION_JSON))
                    .andExpect(status().isOk());
        }
    }

    @Nested
    class BuscarPorPaciente {
        @Test
        void deveBuscarReceitasPorPaciente() throws Exception {
            // Given
            var response = new PageImpl<>(List.of(ReceitaUtils.buildReceitaResponse()), PageRequest.of(0, 50), 1);

            when(service.buscarPorPacienteId(1L, 0, 50)).thenReturn(response);

            // When & Then
            mockMvc.perform(get(BUSCA_POR_PACIENTE_URL, 1L)
                            .contentType(APPLICATION_JSON))
                    .andExpect(status().isOk());
        }
    }

    @Nested
    class Deletar {
        @Test
        void deveDeletarReceita() throws Exception {
            // Given
            doNothing().when(service).deletar(1L);

            // When & Then
            mockMvc.perform(delete(BUSCA_POR_ID_URL, 1L)
                            .contentType(APPLICATION_JSON))
                    .andExpect(status().isOk());
        }
    }

    @Nested
    class EnviarEmail {
        @Test
        void deveEnviarEmail() throws Exception {
            // Given
            doNothing().when(service).enviarEmail(1L);

            // When & Then
            mockMvc.perform(post(ENVIAR_EMAIL_URL, 1L)
                            .contentType(APPLICATION_JSON))
                    .andExpect(status().isOk());
        }
    }
}