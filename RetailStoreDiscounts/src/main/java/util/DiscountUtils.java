package util;

import com.retail.model.Bill;
import com.retail.model.User;
import com.retail.model.UserType;

import java.time.LocalDate;
import java.time.Period;

public class DiscountUtils {

    /**
     * Calculates flat discount based on every $100 spent.
     *
     * @param totalAmount The total amount of the bill.
     * @return The flat discount amount.
     */
    public static double calculateFlatDiscount(double totalAmount) {
        return (int) (totalAmount / 100) * 5;
    }

    /**
     * Checks if the user is a loyal customer based on their registration date.
     *
     * @param user The user for whom loyalty status is checked.
     * @return {@code true} if the user has been registered for more than 2 years, otherwise {@code false}.
     */
    public static boolean isLoyalCustomer(User user) {
        LocalDate now = LocalDate.now();
        Period period = Period.between(user.getRegistrationDate(), now);
        return period.getYears() > 2;
    }

    /**
     * Placeholder method to implement grocery check logic based on actual criteria.
     *
     * @param bill The bill to check if it contains groceries.
     * @return {@code true} if the bill contains groceries, otherwise {@code false}.
     */
    public static boolean isGrocery(Bill bill) {
        // Placeholder method to implement grocery check logic based on actual criteria
        return false;
    }

    /**
     * Calculates additional discount based on user type.
     *
     * @param user        The user for whom the discount is calculated.
     * @param totalAmount The total amount of the bill.
     * @return The additional discount amount based on user type.
     */
    public static double calculateAdditionalDiscount(User user, double totalAmount) {
        double discount = 0.0;
        if (user.getUserType() == UserType.EMPLOYEE) {
            discount += totalAmount * 0.30;
        } else if (user.getUserType() == UserType.AFFILIATE) {
            discount += totalAmount * 0.10;
        } else if (isLoyalCustomer(user)) {
            discount += totalAmount * 0.05;
        }
        return discount;
    }
}
