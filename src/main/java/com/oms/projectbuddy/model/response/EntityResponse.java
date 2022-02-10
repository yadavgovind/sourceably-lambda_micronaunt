package com.oms.projectbuddy.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EntityResponse {

    private Object response;
    private int status;

    public EntityResponse(Object response, int status) {
        this.response = response;
        this.status = status;
    }
}