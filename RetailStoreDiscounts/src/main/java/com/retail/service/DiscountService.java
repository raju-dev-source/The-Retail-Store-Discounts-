package com.retail.service;

import com.retail.model.Bill;

public interface DiscountService {
    double calculateNetPayableAmount(Bill bill);
}
