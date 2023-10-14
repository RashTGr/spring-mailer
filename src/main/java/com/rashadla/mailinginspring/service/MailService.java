package com.rashadla.mailinginspring.service;

import com.rashadla.mailinginspring.dto.RequestDetails;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
@Slf4j
@RequiredArgsConstructor
public class MailService {
    private final JavaMailSender javaMailSender;

//    @Scheduled(fixedRate = 600000)
//    public void mailSender() throws MessagingException {
//        String to = "reshadla@hotmail.com";
//        String subject = "Spring email test";
//        String body = "Spring Boot Email test!";
//
//        sendEmail(to, subject, body);
//
//        String subjectAtt = "This is simple text message method!";
////        sendEmailWithAttachment(subjectAtt);
//        log.info("Email sent successfully!");
//    }

    public void sendEmail(RequestDetails details) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(details.getTo());
        msg.setFrom(details.getFrom());
        msg.setSubject(details.getSubject());
        msg.setText(details.getBody());
        javaMailSender.send(msg);
        log.info("Email sent!");
    }

    public void sendEmailWithAttachment(RequestDetails details) throws MessagingException {
        MimeMessage msg = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);

        // Setting up details
        helper.setTo(details.getTo());
        helper.setFrom(details.getFrom());
        helper.setSubject(details.getSubject());
        helper.setText(details.getBody());

        Resource resource = new ClassPathResource("java logo.png");
        helper.addAttachment("photo.png", resource);
        javaMailSender.send(msg);
        log.info("Attachment sent!");
    }
}
