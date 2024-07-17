package com.retail.service;

import com.retail.model.Bill;

public interface DiscountCalculator {
    double calculateDiscount(Bill bill);
}
