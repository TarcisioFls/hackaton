package br.com.hackaton.utilsTest;

import br.com.hackaton.controller.request.PosologiaRequest;
import br.com.hackaton.controller.request.ReceitaRequest;
import br.com.hackaton.controller.response.PosologiaResponse;
import br.com.hackaton.controller.response.ReceitaResponse;
import br.com.hackaton.entity.Receita;

import java.time.LocalDateTime;
import java.util.List;

public class ReceitaUtils {

    private ReceitaUtils() {}


    public static ReceitaRequest buildReceitaRequest() {
        return ReceitaRequest.builder()
                .posologias(List.of(PosologiaUtils.buildPosologiaRequest()))
                .medicoId(1L)
                .pacienteId(1L)
                .build();
    }

    public static ReceitaResponse buildReceitaResponse() {
        return ReceitaResponse.builder()
                .id(1L)
                .dataCriacao(LocalDateTime.MAX)
                .posologias(List.of(PosologiaUtils.buildPosologiaResponse()))
                .medico(MedicoUtils.buildMedicoResponse())
                .paciente(PacienteUtils.buildPacienteResponse())
                .build();
    }

    public static Receita buildReceita() {
        return Receita.builder()
                .id(1L)
                .posologias(List.of(PosologiaUtils.buildPosologia()))
                .medico(MedicoUtils.buildMedico())
                .paciente(PacienteUtils.buildPaciente())
                .dataHoraCriacao(LocalDateTime.MAX)
                .build();
    }
}
