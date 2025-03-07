package br.com.hackaton.service;

import br.com.hackaton.entity.Medico;
import br.com.hackaton.exception.ExceptionAdvice;
import br.com.hackaton.repository.MedicoRepository;
import br.com.hackaton.service.impl.MedicoServiceImpl;
import br.com.hackaton.utilsTest.MedicoUtils;
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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class MedicoServiceImplTest {

    private final MedicoRepository medicoRepository = mock(MedicoRepository.class);
    private final MedicoService service = new MedicoServiceImpl(medicoRepository);

    @Nested
    class Criar {
        @Test
        void deveCriarMedico() {
            // Given
            var request = MedicoUtils.buildMedicoRequest();

            // When
            service.criar(request);

            // Then
            verify(medicoRepository, times(1)).save(any(Medico.class));
        }
    }

    @Nested
    class BuscarPorId {
        @Test
        void deveBuscarMedicoPorId() {
            // Given
            var medico = MedicoUtils.buildMedico();
            var id = 1L;

            when(medicoRepository.findById(id)).thenReturn(Optional.of(medico));

            // When
            service.buscarPorId(id);

            // Then
            verify(medicoRepository, times(1)).findById(id);
        }

        @Test
        void deveLancarExceptionQuandoMedicoNaoEncontrado() {
            // Given
            var id = 1L;

            when(medicoRepository.findById(id)).thenReturn(Optional.empty());

            // When & Then
            assertThrows(ExceptionAdvice.class, () -> service.buscarPorId(id));
        }
    }

    @Nested
    class Atualiza {
        @Test
        void deveAtualizarMedico() {
            // Given
            var medico = MedicoUtils.buildMedico();
            var request = MedicoUtils.buildMedicoRequest();
            var id = 1L;

            when(medicoRepository.findById(id)).thenReturn(Optional.of(medico));

            // When
            service.atualiza(id, request);

            // Then
            verify(medicoRepository, times(1)).findById(id);
            verify(medicoRepository, times(1)).save(medico);
        }

        @Test
        void deveLancarExceptionQuandoMedicoNaoEncontrado() {
            // Given
            var request = MedicoUtils.buildMedicoRequest();
            var id = 1L;

            when(medicoRepository.findById(id)).thenReturn(Optional.empty());

            // When & Then
            assertThrows(ExceptionAdvice.class, () -> service.atualiza(id, request));
        }
    }

    @Nested
    class BuscarTodos {
        @Test
        void deveBuscarTodosMedicos() {
            // Given
            var medicoPage = new PageImpl<>(List.of(MedicoUtils.buildMedico()));

            when(medicoRepository.findAll(any(PageRequest.class))).thenReturn(medicoPage);

            // When
            service.buscarTodos(0, 50);

            // Then
            verify(medicoRepository, times(1)).findAll(any(PageRequest.class));
        }
    }

    @Nested
    class Excluir {
        @Test
        void deveExcluirMedico() {
            // Given
            var medico = MedicoUtils.buildMedico();
            var id = 1L;

            when(medicoRepository.findById(id)).thenReturn(Optional.of(medico));

            // When
            service.excluir(id);

            // Then
            verify(medicoRepository, times(1)).findById(id);
            verify(medicoRepository, times(1)).delete(medico);
        }

        @Test
        void deveLancarExceptionQuandoMedicoNaoEncontrado() {
            // Given
            var id = 1L;

            when(medicoRepository.findById(id)).thenReturn(Optional.empty());

            // When & Then
            assertThrows(ExceptionAdvice.class, () -> service.excluir(id));
        }
    }

}
