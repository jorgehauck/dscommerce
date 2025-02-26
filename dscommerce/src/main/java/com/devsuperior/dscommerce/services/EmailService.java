package com.devsuperior.dscommerce.services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Map;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String remetente;

    private String loadEmailTemplate(String templateName, Map<String, String> replacements) throws IOException {
        String template = Files.readString(new ClassPathResource("templates/" + templateName).getFile().toPath(), StandardCharsets.UTF_8);

        for (Map.Entry<String, String> entry : replacements.entrySet()) {
            template = template.replace("${" + entry.getKey() + "}", entry.getValue());
        }
        return template;
    }
    public void enviarEmailRecuperacao(String email) throws MessagingException, IOException {
        String resetLink = "http://localhost:8080/resetar-senha?token=";
        String htmlContent = loadEmailTemplate("reset-senha-template.html", Map.of("resetLink", resetLink));

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setTo(email);
        helper.setSubject("Recuperação de Senha");
        helper.setText(htmlContent, true);

        mailSender.send(message);
    }
}
