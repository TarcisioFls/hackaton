package br.com.hackaton.controller.response;

import br.com.hackaton.entity.Receita;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ReceitaResponse {

    private Long id;

    private LocalDateTime dataCriacao;

    private List<PosologiaResponse> posologias;

    private MedicoResponse medico;

    private PacienteResponse paciente;

    public ReceitaResponse() {}

    public ReceitaResponse(Receita receita) {

        this.id = receita.getId();
        this.dataCriacao = receita.getDataHoraCriacao();
        this.medico = new MedicoResponse(receita.getMedico());
        this.paciente = new PacienteResponse(receita.getPaciente());
        this.posologias = new PosologiaResponse().getPosologiaResponseLis(receita.getPosologias());
    }
}
