package com.project.paymentemailgateway.payment.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InitializePaymentDTO {
    @NotNull
    @JsonProperty("amount")
    private String amount;

    @NotNull
    @JsonProperty("email")
    private String email;

    @NotNull
    @JsonProperty("currency")
    private String currency;

    @NotNull
    @JsonProperty("plan")
    private String plan;

    @NotNull
    @JsonProperty("channels")
    private String[] channels = {"visa"};
}
