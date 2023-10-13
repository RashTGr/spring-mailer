package com.rashadla.mailinginspring.service;

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

    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(to);
        msg.setFrom("reshadla@hotmail.com");
        msg.setSubject(subject);
        msg.setText(body);
        javaMailSender.send(msg);
        log.info("Email sent!");
    }

    public void sendEmailWithAttachment(String to, String subject, String body) throws MessagingException {
        MimeMessage msg = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        helper.setTo(to);
        helper.setFrom("reshadla@hotmail.com");
        helper.setSubject(subject);
        helper.setText(body);

//        helper.setText("<h3>Check attachment for image!</h3>", true);

//        default = text/plain
//
//        true = text/html
//        FileSystemResource file = new FileSystemResource(new File("classpath:android.png"));

//        InputStream input = resource.getInputStream();
//        ResourceUtils.getFile("classpath:android.png");

        FileSystemResource file = new FileSystemResource(new File())

        Resource resource = new ClassPathResource("java logo.png");
        helper.addAttachment("photo.png", resource);
        javaMailSender.send(msg);
        log.info("Attachment sent!");
    }
}
