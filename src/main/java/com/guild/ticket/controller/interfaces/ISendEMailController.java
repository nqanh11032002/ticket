package com.guild.ticket.controller.interfaces;

import com.guild.ticket.entity.Email;

public interface ISendEMailController {

    public String sendSimpleMail(Email email);

    public String sendMailWithAttachment(Email email);
}
