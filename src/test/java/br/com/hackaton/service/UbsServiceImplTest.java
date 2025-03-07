package br.com.hackaton.service;

import br.com.hackaton.entity.Ubs;
import br.com.hackaton.exception.ExceptionAdvice;
import br.com.hackaton.repository.UbsRepository;
import br.com.hackaton.service.impl.UbsServiceImpl;
import br.com.hackaton.utilsTest.ReceitaUtils;
import br.com.hackaton.utilsTest.UbsUtils;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyDouble;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class UbsServiceImplTest {

    private final EmailService emailService = mock(EmailService.class);
    private final UbsParserService ubsParserService = mock(UbsParserService.class);
    private final ReceitaService receitaService = mock(ReceitaService.class);
    private final UbsRepository repository = mock(UbsRepository.class);
    private final UbsServiceImpl service = new UbsServiceImpl(emailService, ubsParserService, receitaService, repository);

    @Nested
    class Cria {
        @Test
        void deveCriarUbs() {
            // Given
            var request = UbsUtils.buildUbsRequest();

            // When
            service.cria(request);

            // Then
            verify(repository, times(1)).save(any(Ubs.class));
        }
    }

    @Nested
    class BuscarPorId {
        @Test
        void deveBuscarUbsPorId() {
            // Given
            var ubs = UbsUtils.buildUbs();

            when(repository.findById(1L)).thenReturn(Optional.of(ubs));

            // When
            var response = service.buscarPorId(1L);

            // Then
            assertNotNull(response);
            verify(repository, times(1)).findById(1L);
        }
        @Test
        void deveLancarExceptionQuandoUbsNaoEncontrada() {
            // Given
            when(repository.findById(1L)).thenReturn(Optional.empty());

            // When & Then
            assertThrows(ExceptionAdvice.class, () -> service.buscarPorId(1L));
            verify(repository, times(1)).findById(1L);
        }
    }

    @Nested
    class Atualiza {
        @Test
        void deveAtualizarUbs() {
            // Given
            var id = 1L;
            var request = UbsUtils.buildUbsRequest();
            var ubs = UbsUtils.buildUbs();

            when(repository.findById(id)).thenReturn(Optional.of(ubs));
            when(repository.save(ubs)).thenReturn(ubs);

            // When
            var response = service.atualiza(id, request);

            // Then
            assertNotNull(response);
            verify(repository, times(1)).save(ubs);
        }
    }

    @Nested
    class BuscarTodos {
        @Test
        void deveBuscarTodasUbs() {
            // Given
            var pageRequest = PageRequest.of(0, 10);
            var ubsPage = new PageImpl<>(List.of(UbsUtils.buildUbs()));

            when(repository.findAll(pageRequest)).thenReturn(ubsPage);

            // When
            var response = service.buscarTodos(0, 10);

            // Then
            assertNotNull(response);
            assertEquals(1, response.getTotalElements());
        }
    }

    @Nested
    class EncontrarUbsProximasDePacienteComMedicamentos {
        @Test
        void deveEncontrarUbsProximasDePacienteComMedicamentos() {
            // Given
            var receita = ReceitaUtils.buildReceita();

            when(receitaService.buscaEntidadePorId(1L)).thenReturn(receita);

            // When
            var responses = service.encontrarUbsProximasDePacienteComMedicamentos(1L);

            // Then
            assertNotNull(responses);
            verify(repository, times(1)).buscarUbsPorDistanciaMaxima(anyDouble(), anyDouble(), anyDouble());
        }
    }

    @Nested
    class EnviarEmailUbsProximasDePacienteComMedicamentos {
        @Test
        void deveEnviarEmailUbsProximasDePacienteComMedicamentos() throws IOException {
            // Given
            var receita = ReceitaUtils.buildReceita();
            var ubsList = List.of(UbsUtils.buildUbs());

            when(receitaService.buscaEntidadePorId(1L)).thenReturn(receita);
            when(repository.buscarUbsPorDistanciaMaxima(anyDouble(), anyDouble(), anyDouble())).thenReturn(ubsList);
            when(ubsParserService.parseUbsComMedicamentoHtml(any())).thenReturn("html");

            // When
            service.enviarEmailUbsProximasDePacienteComMedicamentos(1L);

            // Then
            verify(emailService, times(1)).enviarEmail(anyString(), anyString(), any());
        }
        @Test
        void deveLancarExceptionQuandoErroAoEnviarEmail() throws IOException {
            // Given
            var receita = ReceitaUtils.buildReceita();

            when(receitaService.buscaEntidadePorId(1L)).thenReturn(receita);
            when(repository.buscarUbsPorDistanciaMaxima(anyDouble(), anyDouble(), anyDouble())).thenReturn(List.of(UbsUtils.buildUbs()));
            when(ubsParserService.parseUbsComMedicamentoHtml(any())).thenThrow(IOException.class);

            // When & Then
            assertThrows(ExceptionAdvice.class, () -> service.enviarEmailUbsProximasDePacienteComMedicamentos(1L));
        }
    }

}