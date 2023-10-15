package com.project.paymentemailgateway.payment.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CardDetailsDTO {
    private String cvv;
    private String number;
    private String expiry_month;
    private String expiry_year;
}
