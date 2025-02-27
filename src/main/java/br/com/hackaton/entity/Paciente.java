package br.com.hackaton.entity;

import br.com.hackaton.controller.request.PacienteRequest;
import br.com.hackaton.controller.response.PacienteResponse;
import br.com.hackaton.exception.ExceptionAdvice;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import static br.com.hackaton.exception.CodigoError.CNS_PACIENTE_OBRIGATORIO;
import static br.com.hackaton.exception.CodigoError.CPF_PACIENTE_OBRIGATORIO;
import static br.com.hackaton.exception.CodigoError.EMAIL_PACIENTE_OBRIGATORIO;
import static br.com.hackaton.exception.CodigoError.NOME_PACIENTE_OBRIGATORIO;
import static br.com.hackaton.exception.CodigoError.TELEFONE_PACIENTE_OBRIGATORIO;
import static jakarta.persistence.CascadeType.ALL;
import static java.util.Objects.isNull;

@Getter
@Entity
@SuperBuilder
@Table(name = "paciente")
public class Paciente extends BaseEntity{

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String cpf;

    @Column(nullable = false)
    private String telefone;

    @Column(nullable = false)
    private String cns;

    @OneToOne(cascade = ALL)
    private Endereco endereco;

    public Paciente() {}

    public Paciente(Long id, String nome, String email, String cpf, String telefone, String cns, Endereco endereco) {

        super(id);
        this.nome = validaNome(nome);
        this.email = validaEmail(email);
        this.cpf = validaCpf(cpf);
        this.telefone = validaTelefone(telefone);
        this.cns = validaCns(cns);
        this.endereco = endereco;
    }

    public Paciente(String nome, String email, String cpf, String telefone, String cns, Endereco endereco) {

        this.nome = validaNome(nome);
        this.email = validaEmail(email);
        this.cpf = validaCpf(cpf);
        this.telefone = validaTelefone(telefone);
        this.cns = validaCns(cns);
        this.endereco = endereco;
    }

    public Paciente(PacienteRequest pacienteRequest) {

        this(pacienteRequest.nome(), pacienteRequest.email(), pacienteRequest.cpf(), pacienteRequest.telefone(), pacienteRequest.cns(),
                new Endereco(pacienteRequest.enderecoRequest()));
    }

    public Paciente(PacienteResponse pacienteResponse) {

        this(pacienteResponse.getId(), pacienteResponse.getNome(), pacienteResponse.getEmail(), pacienteResponse.getCpf(),
                pacienteResponse.getTelefone(), pacienteResponse.getCns(), new Endereco(pacienteResponse.getEndereco()));
    }

    private String validaEmail(String email) {

        if (isNull(email) || email.isBlank()) {
            throw new ExceptionAdvice(EMAIL_PACIENTE_OBRIGATORIO);
        }

        return email;
    }

    private String validaCns(String cns) {

        if (isNull(cns) || cns.isBlank()) {
            throw new ExceptionAdvice(CNS_PACIENTE_OBRIGATORIO);
        }

        return cns;
    }

    private String validaTelefone(String telefone) {

        if (isNull(telefone) || telefone.isBlank()) {
            throw new ExceptionAdvice(TELEFONE_PACIENTE_OBRIGATORIO);
        }

        return telefone;
    }

    private String validaCpf(String cpf) {

        if (isNull(cpf) || cpf.isBlank()) {
            throw new ExceptionAdvice(CPF_PACIENTE_OBRIGATORIO);
        }

        return cpf;
    }

    private String validaNome(String nome) {

        if (isNull(nome) || nome.isBlank()) {
            throw new ExceptionAdvice(NOME_PACIENTE_OBRIGATORIO);
        }

        return nome;
    }

    public void atualiza(PacienteRequest request) {

        this.nome = validaNome(request.nome());
        this.email = validaEmail(request.email());
        this.cpf = validaCpf(request.cpf());
        this.telefone = validaTelefone(request.telefone());
        this.cns = validaCns(request.cns());
        this.endereco = new Endereco(request.enderecoRequest());
    }
}
