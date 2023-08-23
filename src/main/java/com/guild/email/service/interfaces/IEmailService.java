package com.guild.email.service.interfaces;

import com.guild.email.entity.Email;

public interface IEmailService {
    // To send a simple email
    public String sendSimpleMail(Email email);

    // To send an email with attachment
    public String sendMailWithAttachment(Email email);
}
