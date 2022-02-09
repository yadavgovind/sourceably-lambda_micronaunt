package com.oms.projectbuddy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oms.projectbuddy.model.response.CustomResponseMessage;
import com.oms.projectbuddy.model.response.EntityResponse;
import com.oms.projectbuddy.services.IPaypalService;

@ExecuteOn(TaskExecutors.IO)
@Controller("/api")
public class SetupApiController {

    @Autowired
    private IPaypalService paypalService;

    @PostMapping("/createProduct")
    public ResponseEntity<?> createProduct() {
        try {
            return new ResponseEntity<>(new EntityResponse(paypalService.createProduct(),0), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new CustomResponseMessage(e.getMessage(), -1), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

