package com.oms.projectbuddy.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResetPassword {
    private String newPassword;
    private String email;
    private int code;
}
