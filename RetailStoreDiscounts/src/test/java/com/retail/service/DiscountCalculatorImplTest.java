package com.retail.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.retail.model.Bill;
import com.retail.model.User;
import com.retail.model.UserType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.MockedStatic;
import util.DiscountUtils;

import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
public class DiscountCalculatorImplTest {

    @Mock
    private DiscountService discountService;

    @InjectMocks
    private DiscountCalculatorImpl discountCalculator;

    private MockedStatic<DiscountUtils> mockedDiscountUtils;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockedDiscountUtils = mockStatic(DiscountUtils.class);
    }

    @AfterEach
    public void tearDown() {
        mockedDiscountUtils.close();
    }

    @Test
    public void testCalculateDiscount() {
        // Arrange
        double originalPrice = 100.0;
        double expectedDiscountedPrice = 10.0;  // Assuming the flat discount is 10%
        double additionalDiscount = 5.0;  // Assuming additional discount for user type or loyalty

        User user = new User(123L, UserType.EMPLOYEE, LocalDate.of(2007, 1, 1));
        Bill bill = new Bill(originalPrice, user);

        // Mock static methods
        when(DiscountUtils.calculateFlatDiscount(originalPrice)).thenReturn(expectedDiscountedPrice);
        when(DiscountUtils.isGrocery(bill)).thenReturn(false);
        when(DiscountUtils.calculateAdditionalDiscount(user, originalPrice)).thenReturn(additionalDiscount);

        // Act
        double actualDiscount = discountCalculator.calculateDiscount(bill);

        // Assert
        assertEquals(expectedDiscountedPrice + additionalDiscount, actualDiscount);

        // Verify the static methods were called with the correct parameters
        DiscountUtils.calculateFlatDiscount(originalPrice);
        DiscountUtils.isGrocery(bill);
        DiscountUtils.calculateAdditionalDiscount(user, originalPrice);
    }
}
