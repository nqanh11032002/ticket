package com.guild.ticket.controller;

import com.guild.ticket.controller.interfaces.ISendEMailController;
import com.guild.ticket.entity.Email;
import com.guild.ticket.service.interfaces.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/email")
public class SendEmailController implements ISendEMailController {
    @Autowired
    private IEmailService emailService;

    // Sending a simple Email
    @Override
    @PreAuthorize("hasRole('client_admin')")
    @PostMapping("/send")
    public String sendSimpleMail(@RequestBody Email email)
    {
        String status  = emailService.sendSimpleMail(email);

        return status;
    }

    // Sending email with attachment
    @Override
    @PreAuthorize("hasRole('client_admin')")
    @PostMapping("/send-with-attachment")
    public String sendMailWithAttachment(@RequestBody Email email)
    {
        String status = emailService.sendMailWithAttachment(email);

        return status;
    }
}
