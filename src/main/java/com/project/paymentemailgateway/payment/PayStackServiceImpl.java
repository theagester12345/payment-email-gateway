package com.project.paymentemailgateway.payment;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.project.paymentemailgateway.payment.dtos.CardPaymentDTO;
import com.project.paymentemailgateway.payment.dtos.InitializePaymentDTO;
import com.project.paymentemailgateway.payment.dtos.PaymentRequestDTO;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.http.HttpClient;
import java.util.Map;

import static com.project.paymentemailgateway.payment.Constants.*;

@Service
public class PayStackServiceImpl {

    @Value("${paystack.secret.key}")
    private String secretKey;


    public InitializePaymentResponse initializePayment(PaymentRequestDTO paymentRequestDTO){
        InitializePaymentResponse initializePaymentResponse = null;

        final InitializePaymentDTO initializePaymentDTO = PaymentRequestDTO.convertToInitializePaymentDTO(paymentRequestDTO);

        final CardPaymentDTO cardPaymentDTO = PaymentRequestDTO.convertToCardPaymentDTO(paymentRequestDTO);

        try {
            Gson gson = new Gson();
            StringEntity postingString = new StringEntity(gson.toJson(cardPaymentDTO));
            CloseableHttpClient client = HttpClientBuilder.create().build();
            HttpPost post = new HttpPost(PAYSTACK_CHARGE);
            post.setEntity(postingString);
            post.addHeader("Content-type", "application/json");
            post.addHeader("Authorization", "Bearer " + secretKey);
            StringBuilder result = new StringBuilder();
            HttpResponse response = client.execute(post);

            if (response.getStatusLine().getStatusCode() == STATUS_CODE_OK) {

                BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

                String line;
                while ((line = rd.readLine()) != null) {
                    result.append(line);
                }
            } else {
                throw new Exception("Paystack is unable to initialize payment at the moment");
            }

            ObjectMapper mapper = new ObjectMapper();
            initializePaymentResponse = mapper.readValue(result.toString(), InitializePaymentResponse.class);
        } catch(Throwable ex) {
            ex.printStackTrace();
        }
        return initializePaymentResponse;
    }
}
