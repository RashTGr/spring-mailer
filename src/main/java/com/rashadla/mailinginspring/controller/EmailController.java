package com.rashadla.mailinginspring.controller;

import com.rashadla.mailinginspring.dto.EmailRequest;
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
    public String sendEmail(@RequestBody EmailRequest requestDto) {
        mailService.sendEmail(requestDto.getTo(), requestDto.getSubject(), requestDto.getBody());
        return "Email sent successfully!";
    }

    // Send email with attachment (static)
    @PostMapping("/send-att")
    public String sendEmailAtt(@RequestBody EmailRequest requestDto) throws MessagingException {
        mailService.sendEmailWithAttachment(requestDto.getTo(), requestDto.getSubject(), requestDto.getBody());
        return "Email with attachment sent successfully!";
    }
}
