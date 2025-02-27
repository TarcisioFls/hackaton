package br.com.hackaton.service;

import br.com.hackaton.entity.Estoque;
import br.com.hackaton.exception.ExceptionAdvice;
import br.com.hackaton.repository.EstoqueRepository;
import br.com.hackaton.service.impl.EstoqueServiceImpl;
import br.com.hackaton.utilsTest.EstoqueUtils;
import br.com.hackaton.utilsTest.MedicamentoUtils;
import br.com.hackaton.utilsTest.UbsUtils;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
class EstoqueServiceImplTest {

    private final EstoqueRepository estoqueRepository = mock(EstoqueRepository.class);
    private final MedicamentoService medicamentoService = mock(MedicamentoService.class);
    private final UbsService ubsService = mock(UbsService.class);
    private final EstoqueService service = new EstoqueServiceImpl(estoqueRepository, medicamentoService, ubsService);

    @Nested
    class Criar {
        @Test
        void deveCriarNovoEstoque() {
            // Given
            var request = EstoqueUtils.buildEstoqueRequest();
            var medicamentoResponse = MedicamentoUtils.buildMedicamentoResponse();
            var ubsResponse = UbsUtils.buildUbsResponse();

            when(medicamentoService.buscarPorId(request.medicamentoId())).thenReturn(medicamentoResponse);
            when(ubsService.buscarPorId(request.ubsId())).thenReturn(ubsResponse);

            // When
            service.criar(request);

            // Then
            verify(medicamentoService, times(1)).buscarPorId(request.medicamentoId());
            verify(ubsService, times(1)).buscarPorId(request.ubsId());
            verify(estoqueRepository, times(1)).save(any(Estoque.class));
        }
    }

    @Nested
    class Adicionar {
        @Test
        void deveAdicionarAoEstoque() {
            // Given
            var id = 1L;
            var request = EstoqueUtils.buildAtualizaEstoqueRequest();
            var estoque = EstoqueUtils.buildEstoque();

            when(estoqueRepository.findById(id)).thenReturn(Optional.ofNullable(estoque));

            // When
            service.adicionar(id, request);

            // Then
            verify(estoqueRepository, times(1)).findById(id);
            verify(estoqueRepository, times(1)).save(estoque);
        }
        @Test
        void deveRetornarExceptionEstoqueNaoEncontrado() {
            // Given
            var id = 1L;
            var request = EstoqueUtils.buildAtualizaEstoqueRequest();

            when(estoqueRepository.findById(id)).thenReturn(Optional.empty());

            // When & Then
            assertThrows(ExceptionAdvice.class, () -> service.adicionar(id, request));
        }
    }

    @Nested
    class Retirar {
        @Test
        void deveRetirarDoEstoque() {
            // Given
            var id = 1L;
            var request = EstoqueUtils.buildAtualizaEstoqueRequest();
            var estoque = EstoqueUtils.buildEstoque();

            when(estoqueRepository.findById(id)).thenReturn(Optional.ofNullable(estoque));

            // When
            service.retirar(id, request);

            // Then
            verify(estoqueRepository, times(1)).findById(id);
            verify(estoqueRepository, times(1)).save(estoque);
        }
        @Test
        void deveRetornarExceptionEstoqueNaoEncontrado() {
            // Given
            var id = 1L;
            var request = EstoqueUtils.buildAtualizaEstoqueRequest();

            when(estoqueRepository.findById(id)).thenReturn(Optional.empty());

            // When & Then
            assertThrows(ExceptionAdvice.class, () -> service.retirar(id, request));
        }
    }

    @Nested
    class BuscaPorUbsId {
        @Test
        void deveBuscarEstoquePorUbsId() {
            // Given
            var ubsId = 1L;
            var estoqueList = List.of(EstoqueUtils.buildEstoque().withUbs(UbsUtils.buildUbs()));

            when(estoqueRepository.findByUbsId(ubsId)).thenReturn(Optional.of(estoqueList));

            // When
            var response = service.buscaPorUbsId(ubsId);

            // Then
            assertNotNull(response);
            verify(estoqueRepository, times(1)).findByUbsId(ubsId);
        }
        @Test
        void deveRetornarExceptionEstoqueNaoEncontrado() {
            // Given
            var ubsId = 1L;

            when(estoqueRepository.findByUbsId(ubsId)).thenReturn(Optional.empty());

            // When & Then
            assertThrows(ExceptionAdvice.class, () -> service.buscaPorUbsId(ubsId));
        }
    }

    @Nested
    class BuscaPorMedicamentoId {
        @Test
        void deveBuscarEstoquePorMedicamentoId() {
            // Given
            var medicamentoId = 1L;
            var estoqueList = List.of(EstoqueUtils.buildEstoque().withUbs(UbsUtils.buildUbs()));

            when(estoqueRepository.findByMedicamentoId(medicamentoId)).thenReturn(Optional.of(estoqueList));

            // When
            var response = service.buscaPorMedicamentoId(medicamentoId);

            // Then
            assertNotNull(response);
            verify(estoqueRepository, times(1)).findByMedicamentoId(medicamentoId);
        }
        @Test
        void deveRetornarExceptionEstoqueNaoEncontrado() {
            // Given
            var medicamentoId = 1L;

            when(estoqueRepository.findByMedicamentoId(medicamentoId)).thenReturn(Optional.empty());

            // When & Then
            assertThrows(ExceptionAdvice.class, () -> service.buscaPorMedicamentoId(medicamentoId));
        }
    }

}
