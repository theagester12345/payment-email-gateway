package com.project.paymentemailgateway.pdf;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping (path = "/pdf")
public class PdfController {


    @GetMapping("{id}")
    public CheckOutDetailsDTO checkOutDetails(@PathVariable String id) {
        final CheckOutDetailsDTO detailsDTO = new CheckOutDetailsDTO();

        switch (id) {
            case "01":
                detailsDTO.setTitle("Men's Fitness Guide: The Complete Guide To Home Workouts");
                detailsDTO.setCategory("General PDF G-15");
                detailsDTO.setPrice(80);
                break;
            case "02":
                detailsDTO.setTitle("The Complete Photo Guide to Great Sex");
                detailsDTO.setCategory("General PDF G-16");
                detailsDTO.setPrice(80);
                break;
        }

        return detailsDTO;
    }

}
