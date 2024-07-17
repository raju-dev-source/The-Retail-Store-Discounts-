package com.retail.service;

import com.retail.model.Bill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiscountServiceImpl implements DiscountService {
    private static final Logger logger = LoggerFactory.getLogger(DiscountServiceImpl.class);

    private final DiscountCalculator discountCalculator;

    @Autowired
    public DiscountServiceImpl(DiscountCalculator discountCalculator) {
        this.discountCalculator = discountCalculator;
    }

    @Override
    public double calculateNetPayableAmount(Bill bill) {
        try {
            double discount = discountCalculator.calculateDiscount(bill);
            double netPayableAmount = bill.getTotalAmount() - discount;
            logger.info("Net payable amount calculated successfully for bill with total amount: {}", bill.getTotalAmount());
            return netPayableAmount;
        } catch (IllegalArgumentException e) {
            String errorMessage = "Error calculating net payable amount for bill with total amount " + bill.getTotalAmount();
            logger.error(errorMessage, e);
            throw new IllegalArgumentException(errorMessage, e);
        }
    }
}
