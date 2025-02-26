package br.com.hackaton.controller;

import br.com.hackaton.controller.request.MedicoRequest;
import br.com.hackaton.exception.ExceptionAdviceHandler;
import br.com.hackaton.service.MedicoService;
import br.com.hackaton.utils.MedicoUtils;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
public class MedicoControllerTest {

    private static final String BASE_URL = "/medicos";
    private static final String BUSCA_POR_ID_URL = BASE_URL + "/{id}";

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final MedicoService service = mock(MedicoService.class);

    private final MedicoController controller = new MedicoController(service);

    private final MockMvc mockMvc = MockMvcBuilders
            .standaloneSetup(controller)
            .setControllerAdvice(new ExceptionAdviceHandler())
            .build();

    @Nested
    class Criar {
        @Test
        void deveCriarMedico() throws Exception {
            // Given
            var request = MedicoUtils.buildMedicoRequest();

            doNothing().when(service).criar(any(MedicoRequest.class));

            // When & Then
            mockMvc.perform(post(BASE_URL)
                            .contentType(APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isCreated());
        }
        @Test
        void deveRetornarBadRequestQuandoNomeNull() throws Exception {
            // Given
            var request = MedicoUtils.buildMedicoRequest().withNome(null);

            // When & Then
            mockMvc.perform(post(BASE_URL)
                            .contentType(APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isBadRequest());
        }
        @Test
        void deveRetornarBadRequestQuandoEmailNull() throws Exception {
            // Given
            var request = MedicoUtils.buildMedicoRequest().withEmail(null);

            // When & Then
            mockMvc.perform(post(BASE_URL)
                            .contentType(APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isBadRequest());
        }
        @Test
        void deveRetornarBadRequestQuandoTelefoneNull() throws Exception {
            // Given
            var request = MedicoUtils.buildMedicoRequest().withTelefone(null);

            // When & Then
            mockMvc.perform(post(BASE_URL)
                            .contentType(APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isBadRequest());
        }
        @Test
        void deveRetornarBadRequestQuandoCrmNull() throws Exception {
            // Given
            var request = MedicoUtils.buildMedicoRequest().withCrm(null);

            // When & Then
            mockMvc.perform(post(BASE_URL)
                            .contentType(APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isBadRequest());
        }
        @Test
        void deveRetornarBadRequestQuandoEspecialidadesNull() throws Exception {
            // Given
            var request = MedicoUtils.buildMedicoRequest().withEspecialidades(null);

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
        void deveBuscarMedicoPorId() throws Exception {
            // Given
            var response = MedicoUtils.buildMedicoResponse();

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
        void deveAtualizarMedico() throws Exception {
            // Given
            var request = MedicoUtils.buildMedicoRequest();
            var response = MedicoUtils.buildMedicoResponse();

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
            var request = MedicoUtils.buildMedicoRequest().withNome(null);

            // When & Then
            mockMvc.perform(put(BUSCA_POR_ID_URL, 1L)
                            .contentType(APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isBadRequest());
        }
        @Test
        void deveRetornarBadRequestQuandoEmailNull() throws Exception {
            // Given
            var request = MedicoUtils.buildMedicoRequest().withEmail(null);

            // When & Then
            mockMvc.perform(put(BUSCA_POR_ID_URL, 1L)
                            .contentType(APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isBadRequest());
        }
        @Test
        void deveRetornarBadRequestQuandoTelefoneNull() throws Exception {
            // Given
            var request = MedicoUtils.buildMedicoRequest().withTelefone(null);

            // When & Then
            mockMvc.perform(put(BUSCA_POR_ID_URL, 1L)
                            .contentType(APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isBadRequest());
        }
        @Test
        void deveRetornarBadRequestQuandoCrmNull() throws Exception {
            // Given
            var request = MedicoUtils.buildMedicoRequest().withCrm(null);

            // When & Then
            mockMvc.perform(put(BUSCA_POR_ID_URL, 1L)
                            .contentType(APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isBadRequest());
        }
        @Test
        void deveRetornarBadRequestQuandoEspecialidadesNull() throws Exception {
            // Given
            var request = MedicoUtils.buildMedicoRequest().withEspecialidades(null);

            // When & Then
            mockMvc.perform(put(BUSCA_POR_ID_URL, 1L)
                            .contentType(APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isBadRequest());
        }
    }

    @Nested
    class Excluir {
        @Test
        void deveExcluirMedico() throws Exception {
            // Given
            doNothing().when(service).excluir(1L);

            // When & Then
            mockMvc.perform(delete(BUSCA_POR_ID_URL, 1L)
                            .contentType(APPLICATION_JSON))
                    .andExpect(status().isNoContent());
        }
    }
}