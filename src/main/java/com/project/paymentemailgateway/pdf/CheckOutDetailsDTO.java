package com.project.paymentemailgateway.pdf;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CheckOutDetailsDTO {
    private String title;
    private String category;
    private int price;

}
