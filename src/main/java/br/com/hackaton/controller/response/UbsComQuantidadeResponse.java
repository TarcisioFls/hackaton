package br.com.hackaton.controller.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
public class UbsComQuantidadeResponse {

    private Long id;

    private String nome;

    private String telefone;

    private String inicioAtendimento;

    private String fimAtendimento;

    private BigInteger quantidade;

    private EnderecoResponse endereco;

}
