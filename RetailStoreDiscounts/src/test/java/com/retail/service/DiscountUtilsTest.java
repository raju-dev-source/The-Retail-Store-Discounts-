package com.retail.service;

import com.retail.model.User;
import com.retail.model.UserType;
import org.junit.jupiter.api.Test;
import util.DiscountUtils;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class DiscountUtilsTest {

    @Test
    public void testCalculateFlatDiscount() {
        double totalAmount = 500.0;
        double expectedDiscount = 25.0; // (500 / 100) * 5
        assertEquals(expectedDiscount, DiscountUtils.calculateFlatDiscount(totalAmount));
    }

    @Test
    public void testIsLoyalCustomer() {
        User loyalUser = new User(1L, UserType.CUSTOMER, LocalDate.now().minusYears(3));
        User nonLoyalUser = new User(2L, UserType.CUSTOMER, LocalDate.now().minusYears(1));

        assertTrue(DiscountUtils.isLoyalCustomer(loyalUser));
        assertFalse(DiscountUtils.isLoyalCustomer(nonLoyalUser));
    }

    @Test
    public void testIsGrocery() {
        // Implement test for isGrocery if criteria is defined
        // For now, assume it always returns false
        assertFalse(DiscountUtils.isGrocery(null)); // Placeholder, adapt as per actual logic
    }

    @Test
    public void testCalculateAdditionalDiscount() {
        User employee = new User(1L, UserType.EMPLOYEE, LocalDate.now().minusYears(2));
        User affiliate = new User(2L, UserType.AFFILIATE, LocalDate.now().minusYears(1));
        User loyalCustomer = new User(3L, UserType.CUSTOMER, LocalDate.now().minusYears(3));

        double totalAmount = 500.0;

        assertEquals(150.0, DiscountUtils.calculateAdditionalDiscount(employee, totalAmount), 0.001);
        assertEquals(50.0, DiscountUtils.calculateAdditionalDiscount(affiliate, totalAmount), 0.001);
        assertEquals(25.0, DiscountUtils.calculateAdditionalDiscount(loyalCustomer, totalAmount), 0.001);
    }
}
