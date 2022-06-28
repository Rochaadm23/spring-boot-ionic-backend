package com.rsecinformation.cursomc.services;

import com.rsecinformation.cursomc.entities.Pedido;
import org.springframework.mail.SimpleMailMessage;

public interface EmailService {

    void sendOrderConfirmationEmail(Pedido obj);

    void  sendEmail(SimpleMailMessage msg);
}
