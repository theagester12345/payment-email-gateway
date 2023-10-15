package com.project.paymentemailgateway.payment.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CardPaymentDTO {
    private String email;
    private String amount;
    private CardDetailsDTO card = new CardDetailsDTO();

}
