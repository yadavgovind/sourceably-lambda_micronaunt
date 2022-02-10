package com.oms.projectbuddy.controller;


import com.oms.projectbuddy.model.response.CustomResponseMessage;
import com.oms.projectbuddy.model.response.EntityResponse;
import com.oms.projectbuddy.services.IPaypalService;
import io.micronaut.http.annotation.*;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import jakarta.inject.Inject;

@ExecuteOn(TaskExecutors.IO)
@Controller("/api")
public class SetupApiController {

    @Inject
    private IPaypalService paypalService;

    @Post("/createProduct")
    public Object createProduct() {
        try {
            return new EntityResponse(paypalService.createProduct(),0);
        } catch (Exception e) {
            return new CustomResponseMessage(e.getMessage(), -1);
        }
    }
}

