package br.com.hackaton.entity;

import br.com.hackaton.controller.request.MedicoRequest;
import br.com.hackaton.controller.response.MedicoResponse;
import br.com.hackaton.exception.ExceptionAdvice;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;


import static br.com.hackaton.exception.CodigoError.NOME_MEDICAMENTO_OBRIGATORIO;
import static java.util.Objects.isNull;


@Data
@Entity
@Table(name = "medico")
public class Medico extends BaseEntity{

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String crm;

    @Column(nullable = false)
    private Especialidade especialidade;

    public Medico() {}

    public Medico(String nome, String crm, Especialidade especialidade) {
        this.nome = nome;
        this.crm = crm;
        this.especialidade = especialidade;
    }

    public Medico(MedicoRequest dto){
        this.nome = dto.nome();
        this.crm = dto.crm();
        this.especialidade = dto.especialidade();
    }

    private String validaNome(String nome) {
        if (isNull(nome) || nome.isBlank()) {
            throw new ExceptionAdvice(NOME_MEDICAMENTO_OBRIGATORIO);
        }

        return nome;
    }






}
