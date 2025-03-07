package br.com.hackaton.service;

import br.com.hackaton.entity.Receita;

import java.io.IOException;

public interface ReceitaParserService {
    String parseReceitaHtml(Receita receita) throws IOException;
}
