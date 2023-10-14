package com.rashadla.mailinginspring.service;

import com.rashadla.mailinginspring.dto.RequestDetails;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@RequiredArgsConstructor
public class MailService {
    private final JavaMailSender javaMailSender;

    @Scheduled(fixedRate = 600000)
    public void mailSender() throws MessagingException {
        String sendTo = "reshadla@hotmail.com";
        String sendFrom = "reshadla@hotmail.com";
        String emailSubject = "Email from mailSender()";
        String bodyText = "Spring Boot Email test from mailSender()!";

        // Set properties for RequestDetails object for simple email
        RequestDetails details = RequestDetails.builder()
                .to(sendTo)
                .from(sendFrom)
                .subject(emailSubject)
                .body(bodyText)
                .build();
        sendEmail(details); // Call Simple text email method


        // Set properties for RequestDetails object for email with attachment
        RequestDetails detailsWithAtt = RequestDetails.builder()
                .to(sendTo)
                .from(sendFrom)
                .subject(emailSubject)
                .body(bodyText)
                .build();
        sendEmailWithAttachment(detailsWithAtt); // Call email with attachment

        log.info("Email sent successfully!");
    }

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
