package com.guild.email.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Email {
    private List<String> recipient;
    private String msgBody;
    private String subject;
    private String attachment;
}
