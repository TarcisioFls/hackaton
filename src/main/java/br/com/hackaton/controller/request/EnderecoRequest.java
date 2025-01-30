package br.com.hackaton.controller.request;

public record EnderecoRequest(

        String cep,

        String logradouro,

        String numero,

        String complemento,

        String bairro,

        String cidade,

        String estado,

        String latitude,

        String longitude
) {}
