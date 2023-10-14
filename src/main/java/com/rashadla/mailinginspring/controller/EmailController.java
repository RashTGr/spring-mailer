package com.rashadla.mailinginspring.controller;

import com.rashadla.mailinginspring.dto.RequestDetails;
import com.rashadla.mailinginspring.service.MailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {
    @Autowired
    private MailService mailService;

    // Send simple text email
    @PostMapping("/send")
    public String sendEmail(@RequestBody RequestDetails details) {
        mailService.sendEmail(details);
        return "Email sent successfully!";
    }

    // Send email with attachment (static)
    @PostMapping("/send-att")
    public String sendEmailAtt(@RequestBody RequestDetails details) throws MessagingException {
        mailService.sendEmailWithAttachment(details);
        return "Email with attachment sent successfully!";
    }
}
