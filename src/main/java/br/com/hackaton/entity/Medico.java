package br.com.hackaton.entity;

import br.com.hackaton.controller.request.MedicoRequest;
import br.com.hackaton.controller.response.MedicoResponse;
import br.com.hackaton.exception.ExceptionAdvice;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.List;

import static br.com.hackaton.exception.CodigoError.CRM_MEDICO_OBRIGATORIO;
import static br.com.hackaton.exception.CodigoError.EMAIL_PACIENTE_OBRIGATORIO;
import static br.com.hackaton.exception.CodigoError.NOME_MEDICO_OBRIGATORIO;
import static jakarta.persistence.EnumType.STRING;
import static java.util.Objects.isNull;

@Getter
@Entity
@SuperBuilder
@Table(name = "medico")
public class Medico extends BaseEntity{

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String telefone;

    @Column(nullable = false, unique = true)
    private String crm;

    @ElementCollection
    @Enumerated(STRING)
    private List<Especialidade> especialidades;

    public Medico() {}

    public Medico(Long id, String nome, String email, String telefone, String crm, List<Especialidade> especialidades) {

        super(id);
        this.nome = validaNome(nome);
        this.crm = validaCrm(crm);
        this.email = validaEmail(email);
        this.telefone = validaTelefone(telefone);
        this.especialidades = especialidades;
    }

    public Medico(String nome, String email, String telefone, String crm, List<Especialidade> especialidades) {

        this.nome = validaNome(nome);
        this.crm = validaCrm(crm);
        this.email = validaEmail(email);
        this.telefone = validaTelefone(telefone);
        this.especialidades = especialidades;
    }

    public Medico(MedicoRequest dto){

        this(dto.nome(), dto.crm(), dto.email(), dto.telefone(), dto.especialidades());
    }

    public Medico(MedicoResponse medicoResponse) {

        this(medicoResponse.getId(), medicoResponse.getNome(), medicoResponse.getCrm(), medicoResponse.getEmail(), medicoResponse.getTelefone(),
                medicoResponse.getEspecialidades());
    }

    private String validaTelefone(String telefone) {

        if (isNull(telefone) || telefone.isBlank()) {
            throw new ExceptionAdvice(NOME_MEDICO_OBRIGATORIO);
        }

        return telefone;
    }

    private String validaEmail(String email) {

        if (isNull(email) || email.isBlank()) {
            throw new ExceptionAdvice(EMAIL_PACIENTE_OBRIGATORIO);
        }

        return email;
    }

    private String validaCrm(String crm) {

        if (isNull(crm) || crm.isBlank()) {
            throw new ExceptionAdvice(CRM_MEDICO_OBRIGATORIO);
        }

        return crm;
    }

    private String validaNome(String nome) {
        if (isNull(nome) || nome.isBlank()) {
            throw new ExceptionAdvice(NOME_MEDICO_OBRIGATORIO);
        }

        return nome;
    }


    public void atualizar(MedicoRequest medico) {

        this.nome = validaNome(medico.nome());
        this.email = validaEmail(medico.email());
        this.telefone = validaTelefone(medico.telefone());
        this.crm = validaCrm(medico.crm());
        this.especialidades = medico.especialidades();
    }
}
