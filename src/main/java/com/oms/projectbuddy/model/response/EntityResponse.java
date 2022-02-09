package com.oms.projectbuddy.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EntityResponse {

    private Object response;
    private int status;

}