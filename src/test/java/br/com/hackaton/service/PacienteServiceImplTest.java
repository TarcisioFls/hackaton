package br.com.hackaton.service;

import br.com.hackaton.entity.Paciente;
import br.com.hackaton.exception.ExceptionAdvice;
import br.com.hackaton.repository.PacienteRepository;
import br.com.hackaton.service.impl.PacienteServiceImpl;
import br.com.hackaton.utilsTest.PacienteUtils;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class PacienteServiceImplTest {

    private final PacienteRepository pacienteRepository = mock(PacienteRepository.class);
    private final PacienteService service = new PacienteServiceImpl(pacienteRepository);

    @Nested
    class Criar {
        @Test
        void deveCriarPaciente() {
            // Given
            var request = PacienteUtils.buildPacienteRequest();

            // When
            service.criar(request);

            // Then
            verify(pacienteRepository, times(1)).save(any(Paciente.class));
        }
    }

    @Nested
    class BuscarPorId {
        @Test
        void deveBuscarPacientePorId() {
            // Given
            var id = 1L;
            var paciente = PacienteUtils.buildPaciente();

            when(pacienteRepository.findById(id)).thenReturn(Optional.of(paciente));

            // When
            service.buscarPorId(id);

            // Then
            verify(pacienteRepository, times(1)).findById(id);
        }

        @Test
        void deveLancarExceptionQuandoPacienteNaoEncontrado() {
            // Given
            var id = 1L;

            when(pacienteRepository.findById(id)).thenReturn(Optional.empty());

            // When & Then
            assertThrows(ExceptionAdvice.class, () -> service.buscarPorId(id));
        }
    }

    @Nested
    class Atualizar {
        @Test
        void deveAtualizarPaciente() {
            // Given
            var id = 1L;
            var paciente = PacienteUtils.buildPaciente();
            var request = PacienteUtils.buildPacienteRequest();

            when(pacienteRepository.findById(id)).thenReturn(Optional.of(paciente));

            // When
            service.atualizar(id, request);

            // Then
            verify(pacienteRepository, times(1)).findById(id);
            verify(pacienteRepository, times(1)).save(paciente);
        }

        @Test
        void deveLancarExceptionQuandoPacienteNaoEncontrado() {
            // Given
            var id = 1L;
            var request = PacienteUtils.buildPacienteRequest();

            when(pacienteRepository.findById(id)).thenReturn(Optional.empty());

            // When & Then
            assertThrows(ExceptionAdvice.class, () -> service.atualizar(id, request));
        }
    }

    @Nested
    class BuscarTodos {
        @Test
        void deveBuscarTodosPacientes() {
            // Given
            var pacientePage = new PageImpl<>(List.of(PacienteUtils.buildPaciente()));

            when(pacienteRepository.findAll(any(PageRequest.class))).thenReturn(pacientePage);

            // When
            service.buscarTodos(0, 50);

            // Then
            verify(pacienteRepository, times(1)).findAll(any(PageRequest.class));
        }
    }
}