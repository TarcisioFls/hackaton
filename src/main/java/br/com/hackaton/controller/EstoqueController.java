package br.com.hackaton.controller;

import br.com.hackaton.controller.request.AtualizaEstoqueRequest;
import br.com.hackaton.controller.request.EstoqueRequest;
import br.com.hackaton.controller.response.EstoqueComMedicamentoComQuantidadeListResponse;
import br.com.hackaton.controller.response.EstoqueUbsComQuantidadeResponse;
import br.com.hackaton.service.EstoqueService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/estoques")
public class EstoqueController {

    private final EstoqueService estoqueService;

    @PostMapping
    public void criar(@Valid @RequestBody EstoqueRequest request) {

        estoqueService.criar(request);
    }

    @PatchMapping("/adiciona/{id}")
    public void adiciona(@PathVariable Long id, @Valid @RequestBody AtualizaEstoqueRequest request) {
        estoqueService.adicionar(id, request);
    }

    @PatchMapping("/retira/{id}")
    public void retira(@PathVariable Long id, @Valid @RequestBody AtualizaEstoqueRequest request) {
        estoqueService.retirar(id, request);
    }

    @GetMapping("/ubs/{ubsId}")
    public ResponseEntity<EstoqueComMedicamentoComQuantidadeListResponse> buscaPorUbs(@PathVariable Long ubsId) {

        return ResponseEntity.ok(estoqueService.buscaPorUbsId(ubsId));
    }

    @GetMapping("/medicamento/{medicamentoId}")
    public ResponseEntity<EstoqueUbsComQuantidadeResponse> buscaPorMedicamento(@PathVariable Long medicamentoId) {

        return ResponseEntity.ok(estoqueService.buscaPorMedicamentoId(medicamentoId));
    }
}
