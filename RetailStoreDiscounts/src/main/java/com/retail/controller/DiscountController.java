package com.retail.controller;

import com.retail.model.Bill;
import com.retail.service.DiscountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/discount")
public class DiscountController {

    private final DiscountServiceImpl discountService;

    @Autowired
    public DiscountController(DiscountServiceImpl discountService) {
        this.discountService = discountService;
    }

    @PostMapping("/calculate")
    public ResponseEntity<Double> getNetPayableAmount(@RequestBody Bill bill) {
        double netPayableAmount = discountService.calculateNetPayableAmount(bill);
        return ResponseEntity.ok(netPayableAmount);
    }

}
