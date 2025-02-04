package br.com.hackaton.controller.response;

import br.com.hackaton.entity.Paciente;
import lombok.Getter;

@Getter
public class PacienteResponse {

    private Long id;

    private String nome;

    private String email;

    private String cpf;

    private String telefone;

    private String cns;

    private EnderecoResponse endereco;

    public PacienteResponse() {}

    public PacienteResponse(Long id, String nome, String email, String cpf, String telefone, String cns, EnderecoResponse endereco) {

        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.telefone = telefone;
        this.cns = cns;
        this.endereco = endereco;
    }

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
