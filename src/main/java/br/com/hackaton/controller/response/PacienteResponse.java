package br.com.hackaton.controller.response;

import br.com.hackaton.entity.Paciente;
import lombok.Getter;

@Getter
public class PacienteResponse {

    private Long id;

    private String nome;

    private String cpf;

    private String telefone;

    private String cns;

    private EnderecoResponse endereco;

    public PacienteResponse() {}

    public PacienteResponse(Long id, String nome, String cpf, String telefone, String cns, EnderecoResponse endereco) {

        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.cns = cns;
        this.endereco = endereco;
    }

    public PacienteResponse(Paciente paciente) {

        this.id = paciente.getId();
        this.nome = paciente.getNome();
        this.cpf = paciente.getCpf();
        this.telefone = paciente.getTelefone();
        this.cns = paciente.getCns();
        this.endereco = new EnderecoResponse(paciente.getEndereco());
    }

}
