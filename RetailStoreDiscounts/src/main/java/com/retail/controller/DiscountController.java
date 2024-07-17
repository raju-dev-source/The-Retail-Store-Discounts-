package com.retail.controller;

import com.retail.model.Bill;
import com.retail.service.DiscountServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/discount")
public class DiscountController {
    private static final Logger logger = LoggerFactory.getLogger(DiscountController.class);

    private final DiscountServiceImpl discountService;

    @Autowired
    public DiscountController(DiscountServiceImpl discountService) {
        this.discountService = discountService;
    }

    @PostMapping("/calculate")
    public ResponseEntity<Double> getNetPayableAmount(@RequestBody Bill bill) {
        try {
            double netPayableAmount = discountService.calculateNetPayableAmount(bill);
            return ResponseEntity.ok(netPayableAmount);
        } catch (IllegalArgumentException e) {
            String errorMessage = "Error processing request for bill with total amount " + bill.getTotalAmount();
            logger.error(errorMessage, e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(0.0);
        } catch (Exception e) {
            String errorMessage = "Unexpected error processing request for bill with total amount " + bill.getTotalAmount();
            logger.error(errorMessage, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(0.0);
        }
    }
}
