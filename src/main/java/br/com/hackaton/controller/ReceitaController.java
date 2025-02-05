package br.com.hackaton.controller;

import br.com.hackaton.controller.request.ReceitaRequest;
import br.com.hackaton.service.ReceitaService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/receita")
public class ReceitaController {

    private final ReceitaService receitaService;

    public ReceitaController(ReceitaService receitaService) {
        this.receitaService = receitaService;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public void criar(@RequestBody ReceitaRequest request) {
        receitaService.criar(request);
    }

}
