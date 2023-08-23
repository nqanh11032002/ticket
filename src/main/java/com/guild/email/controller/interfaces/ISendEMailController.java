package com.guild.email.controller.interfaces;

import com.guild.email.entity.Email;

public interface ISendEMailController {

    public String sendSimpleMail(Email email);

    public String sendMailWithAttachment(Email email);
}
