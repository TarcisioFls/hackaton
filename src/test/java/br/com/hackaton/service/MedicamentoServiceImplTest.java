package br.com.hackaton.service;

import br.com.hackaton.entity.Medicamento;
import br.com.hackaton.exception.ExceptionAdvice;
import br.com.hackaton.repository.MedicamentoRepository;
import br.com.hackaton.service.impl.MedicamentoServiceImpl;
import br.com.hackaton.utilsTest.MedicamentoUtils;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class MedicamentoServiceImplTest {

    private final MedicamentoRepository medicamentoRepository = mock(MedicamentoRepository.class);
    private final MedicamentoService service = new MedicamentoServiceImpl(medicamentoRepository);

    @Nested
    class Cria {
        @Test
        void deveCriarMedicamento() {
            // Given
            var request = MedicamentoUtils.buildMedicamentoRequest();

            when(medicamentoRepository.findByNomeAndTarja(request.nome(), request.tarja())).thenReturn(Optional.empty());

            // When
            service.cria(request);

            // Then
            verify(medicamentoRepository, times(1)).findByNomeAndTarja(request.nome(), request.tarja());
            verify(medicamentoRepository, times(1)).save(any(Medicamento.class));
        }
        @Test
        void deveLancarExceptionQuandoMedicamentoJaCadastrado() {
            // Given
            var request = MedicamentoUtils.buildMedicamentoRequest();
            var medicamento = MedicamentoUtils.buildMedicamento();

            when(medicamentoRepository.findByNomeAndTarja(request.nome(), request.tarja())).thenReturn(Optional.ofNullable(medicamento));

            // When & Then
            assertThrows(ExceptionAdvice.class, () -> service.cria(request));
        }
    }

    @Nested
    class BuscarPorId {
        @Test
        void deveBuscarMedicamentoPorId() {
            // Given
            var id = 1L;
            var medicamento = MedicamentoUtils.buildMedicamento();

            when(medicamentoRepository.findById(id)).thenReturn(Optional.ofNullable(medicamento));

            // When
            var result = service.buscarPorId(id);

            // Then
            assertNotNull(result);
            verify(medicamentoRepository, times(1)).findById(id);
        }
        @Test
        void deveLancarExceptionQuandoMedicamentoNaoEncontrado() {
            // Given
            var id = 1L;

            when(medicamentoRepository.findById(id)).thenReturn(Optional.empty());

            // When & Then
            assertThrows(ExceptionAdvice.class, () -> service.buscarPorId(id));
        }
    }

    @Nested
    class Atualiza {
        @Test
        void deveAtualizarMedicamento() {
            // Given
            var id = 1L;
            var request = MedicamentoUtils.buildMedicamentoRequest();
            var medicamento = MedicamentoUtils.buildMedicamento();

            when(medicamentoRepository.findById(id)).thenReturn(Optional.ofNullable(medicamento));

            // When
            var result = service.atualiza(id, request);

            // Then
            assertNotNull(result);
            verify(medicamentoRepository, times(1)).findById(id);
            verify(medicamentoRepository, times(1)).save(medicamento);
        }
        @Test
        void deveLancarExceptionQuandoMedicamentoNaoEncontrado() {
            // Given
            var id = 1L;
            var request = MedicamentoUtils.buildMedicamentoRequest();

            when(medicamentoRepository.findById(id)).thenReturn(Optional.empty());

            // When & Then
            assertThrows(ExceptionAdvice.class, () -> service.atualiza(id, request));
        }
    }

    @Nested
    class BuscarTodos {
        @Test
        void deveBuscarTodosMedicamentos() {
            // Given
            var medicamentoPage = new PageImpl<>(List.of(MedicamentoUtils.buildMedicamento()));

            when(medicamentoRepository.findAll(any(PageRequest.class))).thenReturn(medicamentoPage);

            // When
            var result = service.buscarTodos(0, 50);

            // Then
            assertNotNull(result);
            verify(medicamentoRepository, times(1)).findAll(any(PageRequest.class));
        }
    }

}
