package br.com.hackaton.controller.response;

import br.com.hackaton.entity.Especialidade;
import br.com.hackaton.entity.Medico;
import lombok.Getter;

import java.time.LocalDateTime;
@Getter
public class MedicoResponse {

    private Long id;

    private LocalDateTime dataCriacao;

    private LocalDateTime dataAtualizacao;

    private String nome;

    private String crm;

    private Especialidade especialidade;

    public MedicoResponse() {}

    public MedicoResponse(Long id, LocalDateTime dataCriacao, LocalDateTime dataAtualizacao, String nome, String crm, Especialidade especialidade) {
        this.id = id;
        this.dataCriacao = dataCriacao;
        this.dataAtualizacao = dataAtualizacao;
        this.nome = nome;
        this.crm = crm;
        this.especialidade = especialidade;
    }

    public MedicoResponse(Medico medico) {
        this.id = medico.getId();
        this.dataCriacao = medico.getDataHoraCriacao();
        this.dataAtualizacao = medico.getDataHoraAtualizacao();
        this.nome = medico.getNome();
        this.crm = medico.getCrm();
        this.especialidade = medico.getEspecialidade();
    }
}
