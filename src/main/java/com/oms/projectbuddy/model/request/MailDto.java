package com.oms.projectbuddy.model.request;

import lombok.Data;

@Data
public class MailDto {
    private String to;
    private String subject;
    private String body;
}
