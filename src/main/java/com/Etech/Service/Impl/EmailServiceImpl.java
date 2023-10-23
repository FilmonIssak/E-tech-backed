package com.Etech.Service.Impl;

import com.Etech.Model.Email;
import com.Etech.Service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    @Value("${email.from-address}")
    private String FROM_ADDRESS;
    private final JavaMailSender javaMailSender;

    @Override
    public void send(Email email) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(FROM_ADDRESS);
            message.setTo(email.getReceiverEmail());
            message.setSubject(email.getSubject());
            message.setText(email.getBody());
            javaMailSender.send(message);
        } catch (MailException mailException) {
            mailException.printStackTrace();
        }
    }

    @Override
    public void sendWithHTMLBody(Email email) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message);
        try {
            mimeMessageHelper.setFrom(FROM_ADDRESS);
            mimeMessageHelper.setTo(email.getReceiverEmail());
            mimeMessageHelper.setSubject(email.getSubject());
            mimeMessageHelper.setText(email.getBody(), true);
            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
