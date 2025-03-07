package br.com.hackaton.utilsTest;

import br.com.hackaton.controller.request.MedicoRequest;
import br.com.hackaton.controller.response.MedicoResponse;
import br.com.hackaton.entity.Especialidade;
import br.com.hackaton.entity.Medico;

import java.util.List;

public class MedicoUtils {

    private MedicoUtils() {}


    public static MedicoRequest buildMedicoRequest() {
        return MedicoRequest.builder()
                .nome("Dr. Medico")
                .email("email@email.com")
                .telefone("123456789")
                .crm("123456")
                .especialidades(List.of(Especialidade.CARDIOLOGIA))
                .build();
    }

    public static MedicoResponse buildMedicoResponse() {
        return MedicoResponse.builder()
                .id(1L)
                .nome("Dr. Medico")
                .email("email@email.com")
                .telefone("123456789")
                .crm("123456")
                .especialidades(List.of(Especialidade.CARDIOLOGIA))
                .build();
    }

    public static Medico buildMedico() {
        return Medico.builder()
                .id(1L)
                .nome("Dr. Medico")
                .email("email@email.com")
                .telefone("123456789")
                .crm("123456")
                .especialidades(List.of(Especialidade.CARDIOLOGIA))
                .build();
    }
}
