package com.project.paymentemailgateway.checkout;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.View;


@Controller
public class IndexController {
    @RequestMapping("/checkout.html/{id}")
    public String getIndex(@PathVariable("id") String id, HttpServletRequest request){
        request.setAttribute(View.RESPONSE_STATUS_ATTRIBUTE, HttpStatus.TEMPORARY_REDIRECT);

        return "redirect:/checkoutView.html?id="+id;
    }
}