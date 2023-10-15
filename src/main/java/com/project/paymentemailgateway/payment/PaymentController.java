package com.project.paymentemailgateway.payment;

import com.project.paymentemailgateway.payment.dtos.PaymentRequestDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/pay")
public class PaymentController {
    private final PayStackServiceImpl payStackService;

    public PaymentController (PayStackServiceImpl payStackService){
        this.payStackService = payStackService;
    }

    @PostMapping
    public InitializePaymentResponse processPayment (@RequestBody PaymentRequestDTO request){

        return payStackService.initializePayment(request);

    }



}

