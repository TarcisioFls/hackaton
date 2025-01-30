package br.com.hackaton.controller.request;

public record UbsRequest(

        String nome,

        String telefone,

        EnderecoRequest endereco,

        String inicioAtendimento,

        String fimAtendimento
) {}
