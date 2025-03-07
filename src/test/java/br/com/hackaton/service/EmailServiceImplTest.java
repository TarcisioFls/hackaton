package br.com.hackaton.service;

import br.com.hackaton.exception.ExceptionAdvice;
import br.com.hackaton.service.impl.EmailServiceImpl;
import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class EmailServiceImplTest {

    private final JavaMailSender mailSender = mock(JavaMailSender.class);
    private final EmailServiceImpl service = new EmailServiceImpl(mailSender);

    @Nested
    class EnviarEmail {
        @Test
        void deveEnviarEmail() {
            // Given
            var to = "test@example.com";
            var subject = "Test Subject";
            var content = "<html><body>Test Content</body></html>";
            var mimeMessage = mock(MimeMessage.class);

            when(mailSender.createMimeMessage()).thenReturn(mimeMessage);

            // When
            service.enviarEmail(to, subject, content);

            // Then
            verify(mailSender, times(1)).createMimeMessage();
            verify(mailSender, times(1)).send(mimeMessage);
        }
        @Test
        void deveLancarExceptionQuandoErroAoEnviarEmail() {
            // Given
            var to = "test@example.com";
            var subject = "Test Subject";
            var content = "<html><body>Test Content</body></html>";
            var mimeMessage = mock(MimeMessage.class);

            when(mailSender.createMimeMessage()).thenReturn(mimeMessage);
            doThrow(new RuntimeException()).when(mailSender).send(any(MimeMessage.class));

            // When & Then
            assertThrows(ExceptionAdvice.class, () -> service.enviarEmail(to, subject, content));
            verify(mailSender, times(1)).createMimeMessage();
            verify(mailSender, times(1)).send(mimeMessage);
        }
    }

}