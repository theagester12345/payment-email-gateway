package com.project.paymentemailgateway.payment.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentRequestDTO {
    private String paymentMethod;
    private String cardNumber;
    private String cardHolderName;
    private String cardDate;
    private String cvv;
    private String price;
    private String email;
    private String cardMonth;
    private String cardYear;


    public static InitializePaymentDTO convertToInitializePaymentDTO(PaymentRequestDTO requestDTO){
        final InitializePaymentDTO initializePaymentDTO = new InitializePaymentDTO();
        initializePaymentDTO.setAmount(requestDTO.price);
        initializePaymentDTO.setCurrency("GHS");
        initializePaymentDTO.setEmail(requestDTO.email);

        return initializePaymentDTO;
    }

    public static CardPaymentDTO convertToCardPaymentDTO(PaymentRequestDTO paymentRequestDTO){
        final CardPaymentDTO cardPaymentDTO = new CardPaymentDTO();
        cardPaymentDTO.setEmail(paymentRequestDTO.email);
        cardPaymentDTO.setAmount(paymentRequestDTO.price);
        cardPaymentDTO.getCard().setCvv(paymentRequestDTO.cvv);
        cardPaymentDTO.getCard().setNumber(paymentRequestDTO.cardNumber);
        cardPaymentDTO.getCard().setExpiry_month(paymentRequestDTO.cardMonth);
        cardPaymentDTO.getCard().setExpiry_year(paymentRequestDTO.cardYear);

        return cardPaymentDTO;
    }
}
