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

    /*
    * Driver method for other methods to send emails, and
    * is a scheduled task that sends emails with delays.
    * */
    @Scheduled(fixedDelay = 5000, initialDelay = 15000)
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

        sendEmailWithAttachment(details); // Call email with attachment

        log.info("Email sent successfully!");
    }


    /*
    * This method is set to run once on a predetermined time.
    * */
    @Scheduled(cron = "17 39 15 * * ?")
    public void simpleEmailScheduler() {
        String sendTo = "reshadla@hotmail.com";
        String sendFrom = "reshadla@hotmail.com";
        String emailSubject = "Scheduled email @15:39:17()";
        String bodyText = "Spring Boot Email test from mailSender()!";

        RequestDetails details = RequestDetails.builder()
                .to(sendTo)
                .from(sendFrom)
                .subject(emailSubject)
                .body(bodyText)
                .build();
        sendEmail(details); // Call Simple text email method

        log.info("Email sent successfully!");
    }

    /*
    * This method is scheduled to be run on every Mon from 16:00
    * to 17:00 at every 20 seconds.
    * */
    @Scheduled(cron = "0/20 * 16 * * MON")
    public void attachmentEmailScheduler() throws MessagingException {
        String sendTo = "reshadla@hotmail.com";
        String sendFrom = "reshadla@hotmail.com";
        String emailSubject = "Scheduled email at every 20 sec.";
        String bodyText = "This email with attachment is set to be sent every 20 sec at 16:00 on Mon!";

        RequestDetails detailsWithAtt = RequestDetails.builder()
                .to(sendTo)
                .from(sendFrom)
                .subject(emailSubject)
                .body(bodyText)
                .build();
        sendEmailWithAttachment(detailsWithAtt); // Call email with attachment

        log.info("Attachment email sent successfully!");
    }


    // Method for sending simple text emails
    public void sendEmail(RequestDetails details) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(details.getTo());
        msg.setFrom(details.getFrom());
        msg.setSubject(details.getSubject());
        msg.setText(details.getBody());
        javaMailSender.send(msg);
        log.info("Email sent!");
    }

    // Method for sending emails with attachment (static, using a hardcoded approach)
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
