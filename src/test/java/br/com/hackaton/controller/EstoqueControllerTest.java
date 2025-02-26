package br.com.hackaton.controller;

import br.com.hackaton.controller.request.EstoqueRequest;
import br.com.hackaton.exception.ExceptionAdviceHandler;
import br.com.hackaton.service.EstoqueService;
import br.com.hackaton.utils.EstoqueUtils;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
public class EstoqueControllerTest {

    private static final String BASE_URL = "/estoques";
    private static final String ADICIONA_URL = BASE_URL + "/adiciona/{id}";
    private static final String RETIRA_URL = BASE_URL + "/retira/{id}";
    private static final String BUSCA_POR_UBS_URL = BASE_URL + "/ubs/{ubsId}";
    private static final String BUSCA_POR_MEDICAMENTO_URL = BASE_URL + "/medicamento/{medicamentoId}";

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final EstoqueService service = mock(EstoqueService.class);

    private final EstoqueController controller = new EstoqueController(service);

    private final MockMvc mockMvc = MockMvcBuilders
            .standaloneSetup(controller)
            .setControllerAdvice(new ExceptionAdviceHandler())
            .build();

    @Nested
    class Criar {
        @Test
        void deveCriarEstoque() throws Exception {
            // Given
            var request = EstoqueUtils.buildEstoqueRequest();

            doNothing().when(service).criar(any(EstoqueRequest.class));

            // When & Then
            mockMvc.perform(post(BASE_URL)
                    .contentType(APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isOk());
        }
        @Test
        void deveRetornarBadRequestQuandoMedicamentoIdNull() throws Exception {
            // Given
            var request = EstoqueUtils.buildEstoqueRequest().withMedicamentoId(null);

            doNothing().when(service).criar(any(EstoqueRequest.class));

            // When & Then
            mockMvc.perform(post(BASE_URL)
                    .contentType(APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isBadRequest());
        }
        @Test
        void deveRetornarBadRequestQuandoUbsIdNull() throws Exception {
            // Given
            var request = EstoqueUtils.buildEstoqueRequest().withUbsId(null);

            doNothing().when(service).criar(any(EstoqueRequest.class));

            // When & Then
            mockMvc.perform(post(BASE_URL)
                    .contentType(APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isBadRequest());
        }
        @Test
        void deveRetornarBadRequestQuandoQuantidadeNull() throws Exception {
            // Given
            var request = EstoqueUtils.buildEstoqueRequest().withQuantidade(null);

            doNothing().when(service).criar(any(EstoqueRequest.class));

            // When & Then
            mockMvc.perform(post(BASE_URL)
                    .contentType(APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isBadRequest());
        }
    }

    @Nested
    class Adiciona {
        @Test
        void deveAdicionarEstoque() throws Exception {
            // Given
            var request = EstoqueUtils.buildAtualizaEstoqueRequest();

            doNothing().when(service).adicionar(1L, request);

            // When & Then
            mockMvc.perform(patch(ADICIONA_URL, 1L)
                    .contentType(APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isOk());
        }
        @Test
        void deveRetornarBadRequestQuandoQuantidadeNull() throws Exception {
            // Given
            var request = EstoqueUtils.buildAtualizaEstoqueRequest().withQuantidade(null);

            doNothing().when(service).adicionar(1L, request);

            // When & Then
            mockMvc.perform(patch(ADICIONA_URL, 1L)
                    .contentType(APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isBadRequest());
        }
    }

    @Nested
    class Retira {
        @Test
        void deveRetirarEstoque() throws Exception {
            // Given
            var request = EstoqueUtils.buildAtualizaEstoqueRequest();

            doNothing().when(service).retirar(1L, request);

            // When & Then
            mockMvc.perform(patch(RETIRA_URL, 1L)
                    .contentType(APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isOk());
        }
        @Test
        void deveRetornarBadRequestQuandoQuantidadeNull() throws Exception {
            // Given
            var request = EstoqueUtils.buildAtualizaEstoqueRequest().withQuantidade(null);

            doNothing().when(service).retirar(1L, request);

            // When & Then
            mockMvc.perform(patch(RETIRA_URL, 1L)
                    .contentType(APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isBadRequest());
        }
    }

    @Nested
    class BuscaPorUbs {
        @Test
        void deveBuscarEstoquePorUbs() throws Exception {
            // Given
            var response = EstoqueUtils.buildEstoqueComMedicamentoComQuantidadeListResponse();

            when(service.buscaPorUbsId(1L)).thenReturn(response);

            // When & Then
            mockMvc.perform(get(BUSCA_POR_UBS_URL, 1L)
                    .contentType(APPLICATION_JSON))
                    .andExpect(status().isOk());
        }
    }

    @Nested
    class BuscaPorMedicamento {
        @Test
        void deveBuscarEstoquePorMedicamento() throws Exception {
            // Given
            var response = EstoqueUtils.buildEstoqueUbsComQuantidadeResponse();

            when(service.buscaPorMedicamentoId(1L)).thenReturn(response);

            // When & Then
            mockMvc.perform(get(BUSCA_POR_MEDICAMENTO_URL, 1L)
                    .contentType(APPLICATION_JSON))
                    .andExpect(status().isOk());
        }
    }

}
