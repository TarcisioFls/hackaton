package br.com.hackaton.service;

public interface EmailService {
    void enviarEmail(String to, String subject, String htmlContent);
}
