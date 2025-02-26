package br.com.hackaton.controller;

import br.com.hackaton.controller.request.MedicoRequest;
import br.com.hackaton.controller.response.MedicoResponse;
import br.com.hackaton.service.MedicoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@RequestMapping("medicos")
public class MedicoController {

    private final MedicoService medicoService;

    @PostMapping
    @ResponseStatus(CREATED)
    public void criar(@RequestBody @Valid MedicoRequest request) {
       medicoService.criar(request);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicoResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(medicoService.buscarPorId(id));
    }

    @GetMapping
    public ResponseEntity<Page<MedicoResponse>> buscarTodos(@RequestParam(defaultValue = "0") int page,
                                                            @RequestParam(defaultValue = "50")int size) {
        return ResponseEntity.ok(medicoService.buscarTodos(page, size));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicoResponse> atualiza(@PathVariable Long id, @RequestBody @Valid MedicoRequest request) {
        return ResponseEntity.ok(medicoService.atualiza(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        medicoService.excluir(id);
        return ResponseEntity.noContent().build();
    }


}
