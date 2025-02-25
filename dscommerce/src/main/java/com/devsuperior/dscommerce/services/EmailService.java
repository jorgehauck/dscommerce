package com.devsuperior.dscommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String remetente;

    public void enviarEmailRecuperarSenha(String emailDestino, String nomeUsuario) {
        String assunto = "Cadastro criado com sucesso!";
        String mensagem = "Seja bem vindo ao dscommerce," +  nomeUsuario.toUpperCase() + "aproveitas as nossas ofertas!";

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(emailDestino);
        email.setSubject(assunto);
        email.setText(mensagem);
        email.setFrom(remetente);

        mailSender.send(email);
    }
}
