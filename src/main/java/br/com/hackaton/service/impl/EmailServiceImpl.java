package br.com.hackaton.service.impl;

import br.com.hackaton.exception.CodigoError;
import br.com.hackaton.exception.ExceptionAdvice;
import br.com.hackaton.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    @Override
    public void enviarEmail(String to, String subject, String htmlContent) {
        var mimeMessage = mailSender.createMimeMessage();
        try {
            var helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            helper.setFrom("hello@demomailtrap.com");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlContent, true);
            mailSender.send(mimeMessage);
        } catch (Exception e) {
            throw new ExceptionAdvice(CodigoError.ERRO_AO_ENVIAR_EMAIL);
        }
    }

}