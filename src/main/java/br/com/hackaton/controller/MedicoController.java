package br.com.hackaton.controller;

import br.com.hackaton.controller.request.MedicoRequest;
import br.com.hackaton.controller.response.MedicoResponse;
import br.com.hackaton.service.MedicoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@RequestMapping("medico")
public class MedicoController {

    private final MedicoService medicoService;

    @PostMapping
    @ResponseStatus(CREATED)
    public void criar(@RequestBody @Valid MedicoRequest request){
       medicoService.criar(request);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicoResponse> buscarPorId(@PathVariable Long id){
        var medico = medicoService.buscarPorId(id);
        return ResponseEntity.ok(medico);
    }

    @GetMapping
    public ResponseEntity<Page<MedicoResponse>> buscarTodos(@RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "50")int size){
        var medicos = medicoService.buscarTodos(page, size);

        return ResponseEntity.ok(medicos);

    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicoResponse> atualiza(@PathVariable Long id,@RequestBody @Valid MedicoRequest request){
        var medico = medicoService.atualiza(id, request);
        return ResponseEntity.ok(medico);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id){
        medicoService.excluir(id);
        return ResponseEntity.noContent().build();
    }


}
