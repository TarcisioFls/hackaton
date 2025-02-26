package br.com.hackaton.utils;

import br.com.hackaton.controller.request.MedicoRequest;
import br.com.hackaton.controller.response.MedicoResponse;
import br.com.hackaton.entity.Especialidade;

import java.util.List;

public class MedicoUtils {

    private MedicoUtils() {}


    public static MedicoRequest buildMedicoRequest() {
        return MedicoRequest.builder()
                .nome("Dr. Fulano")
                .email("email@email.com")
                .telefone("123456789")
                .crm("123456")
                .especialidades(List.of(Especialidade.CARDIOLOGIA))
                .build();
    }

    public static MedicoResponse buildMedicoResponse() {
        return MedicoResponse.builder()
                .id(1L)
                .nome("Dr. Fulano")
                .email("email@email.com")
                .telefone("123456789")
                .crm("123456")
                .especialidades(List.of(Especialidade.CARDIOLOGIA))
                .build();
    }
}
