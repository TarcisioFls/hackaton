package br.com.hackaton.service;

import br.com.hackaton.entity.Receita;
import br.com.hackaton.exception.ExceptionAdvice;
import br.com.hackaton.repository.ReceitaRepository;
import br.com.hackaton.service.impl.ReceitaServiceImpl;
import br.com.hackaton.utilsTest.MedicamentoUtils;
import br.com.hackaton.utilsTest.MedicoUtils;
import br.com.hackaton.utilsTest.PacienteUtils;
import br.com.hackaton.utilsTest.ReceitaUtils;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class ReceitaServiceImplTest {

    private final ReceitaRepository receitaRepository = mock(ReceitaRepository.class);
    private final ReceitaParserService receitaParserService = mock(ReceitaParserService.class);
    private final EmailService emailService = mock(EmailService.class);
    private final MedicoService medicoService = mock(MedicoService.class);
    private final PacienteService pacienteService = mock(PacienteService.class);
    private final MedicamentoService medicamentoService = mock(MedicamentoService.class);
    private final ReceitaServiceImpl service = new ReceitaServiceImpl(
            receitaRepository,
            receitaParserService,
            emailService,
            medicoService,
            pacienteService,
            medicamentoService
    );

    @Nested
    class Criar {
        @Test
        void deveCriarReceita() {
            // Given
            var request = ReceitaUtils.buildReceitaRequest();
            var medicoResponse = MedicoUtils.buildMedicoResponse();
            var pacienteResponse = PacienteUtils.buildPacienteResponse();
            var medicamentoResponse = MedicamentoUtils.buildMedicamentoResponse();

            when(medicoService.buscarPorId(request.medicoId())).thenReturn(medicoResponse);
            when(pacienteService.buscarPorId(request.pacienteId())).thenReturn(pacienteResponse);
            when(medicamentoService.buscarPorId(any())).thenReturn(medicamentoResponse);

            // When
            service.criar(request);

            // Then
            verify(medicoService, times(1)).buscarPorId(request.medicoId());
            verify(pacienteService, times(1)).buscarPorId(request.pacienteId());
            verify(medicamentoService, times(1)).buscarPorId(any());
            verify(receitaRepository, times(1)).save(any(Receita.class));
        }
    }

    @Nested
    class BuscaEntidadePorId {
        @Test
        void deveBuscarReceitaPorId() {
            // Given
            var id = 1L;
            var receita = ReceitaUtils.buildReceita();

            when(receitaRepository.findById(id)).thenReturn(Optional.of(receita));

            // When
            var response = service.buscaEntidadePorId(id);

            // Then
            assertNotNull(response);
            verify(receitaRepository, times(1)).findById(id);
        }

        @Test
        void deveLancarExceptionQuandoReceitaNaoEncontrada() {
            // Given
            var id = 1L;

            when(receitaRepository.findById(id)).thenReturn(Optional.empty());

            // When & Then
            assertThrows(ExceptionAdvice.class, () -> service.buscaEntidadePorId(id));
        }
    }

    @Nested
    class BuscarPorId {
        @Test
        void deveBuscarReceitaPorId() {
            // Given
            var id = 1L;
            var receita = ReceitaUtils.buildReceita();

            when(receitaRepository.findById(id)).thenReturn(Optional.of(receita));

            // When
            var response = service.buscarPorId(id);

            // Then
            assertNotNull(response);
            verify(receitaRepository, times(1)).findById(id);
        }

        @Test
        void deveLancarExceptionQuandoReceitaNaoEncontrada() {
            // Given
            var id = 1L;

            when(receitaRepository.findById(id)).thenReturn(Optional.empty());

            // When & Then
            assertThrows(ExceptionAdvice.class, () -> service.buscarPorId(id));
        }
    }

    @Nested
    class BuscarTodos {
        @Test
        void deveBuscarTodasReceitas() {
            // Given
            var receitaPage = new PageImpl<>(List.of(ReceitaUtils.buildReceita()));
            var pageRequest = PageRequest.of(0, 50);

            when(receitaRepository.findAll(pageRequest)).thenReturn(receitaPage);

            // When
            var response = service.buscarTodos(0, 50);

            // Then
            assertNotNull(response);
            verify(receitaRepository, times(1)).findAll(pageRequest);
        }
    }

    @Nested
    class BuscarPorMedicoId {
        @Test
        void deveBuscarReceitasPorMedicoId() {
            // Given
            var id = 1L;
            var receitaPage = new PageImpl<>(List.of(ReceitaUtils.buildReceita()));
            var pageRequest = PageRequest.of(0, 50);

            when(receitaRepository.findByMedicoId(id, pageRequest)).thenReturn(receitaPage);

            // When
            var response = service.buscarPorMedicoId(id, 0, 50);

            // Then
            assertNotNull(response);
            verify(receitaRepository, times(1)).findByMedicoId(id, pageRequest);
        }
    }

    @Nested
    class BuscarPorPacienteId {
        @Test
        void deveBuscarReceitasPorPacienteId() {
            // Given
            var id = 1L;
            var receitaPage = new PageImpl<>(List.of(ReceitaUtils.buildReceita()));
            var pageRequest = PageRequest.of(0, 50);

            when(receitaRepository.findByPacienteId(id, pageRequest)).thenReturn(receitaPage);

            // When
            var response = service.buscarPorPacienteId(id, 0, 50);

            // Then
            assertNotNull(response);
            verify(receitaRepository, times(1)).findByPacienteId(id, pageRequest);
        }
    }

    @Nested
    class Deletar {
        @Test
        void deveDeletarReceita() {
            // Given
            var id = 1L;
            var receita = ReceitaUtils.buildReceita();

            when(receitaRepository.findById(id)).thenReturn(Optional.of(receita));

            // When
            service.deletar(id);

            // Then
            verify(receitaRepository, times(1)).delete(receita);
        }

        @Test
        void deveLancarExceptionQuandoReceitaNaoEncontrada() {
            // Given
            var id = 1L;

            when(receitaRepository.findById(id)).thenReturn(Optional.empty());

            // When & Then
            assertThrows(ExceptionAdvice.class, () -> service.deletar(id));
        }
    }

    @Nested
    class EnviarEmail {
        @Test
        void deveEnviarEmail() throws IOException {
            // Given
            var id = 1L;
            var receita = ReceitaUtils.buildReceita();
            var html = "<html></html>";

            when(receitaRepository.findById(id)).thenReturn(Optional.of(receita));
            when(receitaParserService.parseReceitaHtml(receita)).thenReturn(html);

            // When
            service.enviarEmail(id);

            // Then
            verify(emailService, times(1)).enviarEmail(
                    receita.getPaciente().getEmail(),
                    "RECEITA DIGITAL UBS",
                    html
            );
        }

        @Test
        void deveLancarExceptionQuandoErroAoProcessarHtml() throws IOException {
            // Given
            var id = 1L;
            var receita = ReceitaUtils.buildReceita();

            when(receitaRepository.findById(id)).thenReturn(Optional.of(receita));
            when(receitaParserService.parseReceitaHtml(receita)).thenThrow(new IOException());

            // When & Then
            assertThrows(ExceptionAdvice.class, () -> service.enviarEmail(id));
        }
    }
}