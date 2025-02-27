package br.com.hackaton.utilsTest;

import br.com.hackaton.controller.request.PacienteRequest;
import br.com.hackaton.controller.response.PacienteResponse;
import br.com.hackaton.entity.Paciente;

public class PacienteUtils {

    private PacienteUtils() {}

    public static PacienteRequest buildPacienteRequest() {
        return PacienteRequest.builder()
                .nome("Paciente")
                .email("email@email.com")
                .cpf("12345678901")
                .telefone("123456789")
                .cns("123456")
                .enderecoRequest(EnderecoUtils.buildEnderecoRequest())
                .build();
    }

    public static PacienteResponse buildPacienteResponse() {
        return PacienteResponse.builder()
                .id(1L)
                .nome("Paciente")
                .email("email@email.com")
                .cpf("12345678901")
                .telefone("123456789")
                .cns("123456")
                .endereco(EnderecoUtils.buildEnderecoResponse())
                .build();
    }

    public static Paciente buildPaciente() {
        return Paciente.builder()
                .id(1L)
                .nome("Paciente")
                .email("email@email.com")
                .cpf("12345678901")
                .telefone("123456789")
                .cns("123456")
                .endereco(EnderecoUtils.buildEndereco())
                .build();
    }
}
