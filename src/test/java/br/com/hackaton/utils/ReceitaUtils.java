package br.com.hackaton.utils;

import br.com.hackaton.controller.request.PosologiaRequest;
import br.com.hackaton.controller.request.ReceitaRequest;
import br.com.hackaton.controller.response.PosologiaResponse;
import br.com.hackaton.controller.response.ReceitaResponse;

import java.util.List;

public class ReceitaUtils {

    private ReceitaUtils() {}


    public static ReceitaRequest buildReceitaRequest() {
        return ReceitaRequest.builder()
                .posologias(List.of(buildPosologiaRequest()))
                .medicoId(1L)
                .pacienteId(1L)
                .build();
    }

    public static ReceitaResponse buildReceitaResponse() {
        return ReceitaResponse.builder()
                .id(1L)
                .posologias(List.of(buildPosologiaResponse()))
                .medico(MedicoUtils.buildMedicoResponse())
                .paciente(PacienteUtils.buildPacienteResponse())
                .build();
    }

    public static PosologiaRequest buildPosologiaRequest() {
        return PosologiaRequest.builder()
                .medicamentoId(1L)
                .quantidade(1)
                .descricao("descricao")
                .build();
    }

    public static PosologiaResponse buildPosologiaResponse() {
        return PosologiaResponse.builder()
                .descricao("descricao")
                .quantidade(1)
                .medicamento(MedicamentoUtils.buildMedicamentoResponse())
                .build();
    }
}
