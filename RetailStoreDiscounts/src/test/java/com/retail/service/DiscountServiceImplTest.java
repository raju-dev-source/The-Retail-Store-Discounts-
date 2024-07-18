package com.retail.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.retail.model.Bill;
import com.retail.model.User;
import com.retail.model.UserType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
public class DiscountServiceImplTest {

    @Mock
    private DiscountCalculator discountCalculator;

    @InjectMocks
    private DiscountServiceImpl discountService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testMockBehavior() {
        // Arrange
        User user = new User(123L, UserType.EMPLOYEE, LocalDate.of(2007, 1, 1));
        Bill bill = new Bill(100.0, user);
        double expectedDiscount = 10.0; // Assuming 10% discount

        // Mock the behavior of calculateDiscount to return the expected discount
        when(discountCalculator.calculateDiscount(bill)).thenReturn(expectedDiscount);

        // Act
        double actualDiscount = discountCalculator.calculateDiscount(bill);

        // Assert
        assertEquals(expectedDiscount, actualDiscount, 0.001); // Use delta for double comparison
        verify(discountCalculator).calculateDiscount(bill); // Verify that calculateDiscount was called
    }

    @Test
    public void testCalculateNetPayableAmount() {
        // Arrange
        User user = new User(123L, UserType.EMPLOYEE, LocalDate.of(2007, 1, 1));
        Bill bill = new Bill(100.0, user);
        double expectedDiscount = 10.0; // Assuming 10% discount
        double expectedNetPayableAmount = 90.0; // Expected net payable amount after discount

        // Mock the behavior of calculateDiscount to return the expected discount
        when(discountCalculator.calculateDiscount(bill)).thenReturn(expectedDiscount);

        // Act
        double actualNetPayableAmount = discountService.calculateNetPayableAmount(bill);

        // Assert
        assertEquals(expectedNetPayableAmount, actualNetPayableAmount, 0.001); // Use delta for double comparison
        verify(discountCalculator).calculateDiscount(bill); // Verify that calculateDiscount was called
    }
}
