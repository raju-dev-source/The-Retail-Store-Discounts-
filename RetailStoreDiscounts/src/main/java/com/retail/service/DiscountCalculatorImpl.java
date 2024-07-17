package com.retail.service;

import com.retail.model.Bill;
import com.retail.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import util.DiscountUtils;

@Service
public class DiscountCalculatorImpl implements DiscountCalculator {
    private static final Logger logger = LoggerFactory.getLogger(DiscountCalculatorImpl.class);

    @Override
    public double calculateDiscount(Bill bill) {
        double discount = 0.0;
        User user = bill.getUser();
        double totalAmount = bill.getTotalAmount();

        // Calculate flat discount based on every $100 spent
        discount += DiscountUtils.calculateFlatDiscount(totalAmount);

        // Calculate additional discounts based on user type and loyalty
        try {
            if (!DiscountUtils.isGrocery(bill)) {
                discount += DiscountUtils.calculateAdditionalDiscount(user, totalAmount);
            }
            logger.info("Discount calculated successfully for bill with total amount: {}", totalAmount);
        } catch (Exception e) {
            String errorMessage = "Error calculating discount for bill with total amount " + totalAmount;
            logger.error(errorMessage, e);
            throw new IllegalArgumentException(errorMessage, e);
        }

        return discount;
    }
}
