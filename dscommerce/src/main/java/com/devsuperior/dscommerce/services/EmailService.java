package com.devsuperior.dscommerce.services;

import com.devsuperior.dscommerce.dto.UserDTO;
import com.devsuperior.dscommerce.services.exceptions.EmailException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String remetente;
    @Autowired
    private TemplateEngine templateEngine;

    public void enviaEmailBoasVindas(UserDTO userDTO) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            Context context = new Context();
            context.setVariable("nome", userDTO.getName());

            String conteudoHtml = templateEngine.process("boas-vindas-template", context);

            helper.setFrom(remetente);
            helper.setSubject("Seja Bem vindo ao DsCommerce!");
            helper.setTo(userDTO.getEmail());

            helper.setText(conteudoHtml, true);

            mailSender.send(message);
        } catch (Exception e) {
            throw new EmailException("Falha ao enviar e-mail!");
        }
    }
}
