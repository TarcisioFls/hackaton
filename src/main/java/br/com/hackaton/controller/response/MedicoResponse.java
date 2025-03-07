package br.com.hackaton.controller.response;

import br.com.hackaton.entity.Especialidade;
import br.com.hackaton.entity.Medico;
import lombok.Builder;
import lombok.Getter;
import lombok.With;

import java.time.LocalDateTime;
import java.util.List;

@With
@Getter
@Builder
public class MedicoResponse {

    private Long id;

    private LocalDateTime dataCriacao;

    private LocalDateTime dataAtualizacao;

    private String nome;

    private String email;

    private String telefone;

    private String crm;

    private List<Especialidade> especialidades;

    public MedicoResponse() {}

    public MedicoResponse(Long id, LocalDateTime dataCriacao, LocalDateTime dataAtualizacao, String nome, String email, String telefone, String crm, List<Especialidade> especialidades) {
        this.id = id;
        this.dataCriacao = dataCriacao;
        this.dataAtualizacao = dataAtualizacao;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.crm = crm;
        this.especialidades = especialidades;
    }

    public MedicoResponse(Medico medico) {
        this.id = medico.getId();
        this.dataCriacao = medico.getDataHoraCriacao();
        this.dataAtualizacao = medico.getDataHoraAtualizacao();
        this.nome = medico.getNome();
        this.email = medico.getEmail();
        this.telefone = medico.getTelefone();
        this.crm = medico.getCrm();
        this.especialidades = medico.getEspecialidades();
    }
}
