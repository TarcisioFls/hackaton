package br.com.hackaton.controller.response;

import br.com.hackaton.entity.Paciente;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PacienteResponse {

    private Long id;

    private String nome;

    private String email;

    private String cpf;

    private String telefone;

    private String cns;

    private EnderecoResponse endereco;

    public PacienteResponse(Paciente paciente) {

        this.id = paciente.getId();
        this.nome = paciente.getNome();
        this.email = paciente.getEmail();
        this.cpf = paciente.getCpf();
        this.telefone = paciente.getTelefone();
        this.cns = paciente.getCns();
        this.endereco = new EnderecoResponse(paciente.getEndereco());
    }

}
