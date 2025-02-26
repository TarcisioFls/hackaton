package br.com.hackaton.service;

import java.util.Optional;

public interface ReceitaPdfService {
    Optional<byte[]> downloadPdf(Long id);
}
