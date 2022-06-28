package com.rsecinformation.cursomc.services;

import com.rsecinformation.cursomc.entities.Pedido;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import java.util.Date;

public abstract class AbstractEmailService implements EmailService{

    @Value("${default.sender}")
    private String sender;

    @Override
    public void sendOrderConfirmationEmail(Pedido obj) {
        SimpleMailMessage sm = prepareSimplemailMassageFromPedido(obj);
        sendEmail(sm);
    }

    protected SimpleMailMessage prepareSimplemailMassageFromPedido(Pedido obj) {
        SimpleMailMessage smm = new SimpleMailMessage();
        smm.setTo(obj.getCliente().getEmail());
        smm.setFrom(sender);
        smm.setSubject("Pedido confirmado! Código: " + obj.getId());
        smm.setSentDate(new Date(System.currentTimeMillis()));
        smm.setText(obj.toString());
        return smm;
    }
}
