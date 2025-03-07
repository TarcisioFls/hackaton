package br.com.hackaton.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UbsComQuantidadeResponse {

    private Long id;

    private String nome;

    private String telefone;

    private String inicioAtendimento;

    private String fimAtendimento;

    private BigInteger quantidade;

    private EnderecoResponse endereco;

}
