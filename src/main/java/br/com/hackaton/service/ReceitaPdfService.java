package br.com.hackaton.service;

public interface ReceitaPdfService {
    byte[] downloadPdf(Long id);
}
