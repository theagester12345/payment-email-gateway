package com.project.paymentemailgateway.pdf;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping (path = "/pdf")
public class controller {

    @GetMapping("{id}")
    public String checkOut (@PathVariable Integer id){
        System.out.println("ID sent from website:"+ id);
        return "null";
    }
}
