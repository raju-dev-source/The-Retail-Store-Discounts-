# Retail Discounts

# Overview
This project implements a discount calculation service for a retail application. It calculates discounts based on user type (Employee, Affiliate, Customer) and applies them to the total bill amount.

# Features
Calculates discounts based on user type and bill amount.
Handles different user types: Employee, Affiliate, and regular Customer.
Checks loyalty status of customers based on registration date for additional discounts.
Placeholder method for future implementation: grocery check logic.

# Components
1. DiscountCalculator
   Service component to compute discounts based on user and bill details.
   Applies fixed discounts and additional percentage discounts based on user type.
2. DiscountService
   Service class to manage the application of discounts.
   Uses DiscountCalculator to calculate total discounts and net payable amounts.
3. DiscountController
   REST API controller to expose discount calculation endpoints.
   Handles HTTP requests to calculate net payable amount for a given bill.
4. Models (Bill, User, UserType)
   Bill: Represents a bill with total amount and associated user.
   User: Represents a user with ID, type (Employee, Affiliate, Customer), and registration date.
   UserType: Enumerates the different types of users (Employee, Affiliate, Customer).

# Setup and Usage
# Prerequisites
Java 8 or higher
Maven
IDE (e.g., IntelliJ IDEA, Eclipse)

# Running the Application

1)Clone the repository:
git clone <repository_url>
cd retail-discount-service

2)Build the project using Maven:

```bash
mvn clean install
```

3)Run the application:

```bash
mvn spring-boot:run
```
4)The application will start at http://localhost:8080. You can access the discount calculation API endpoints from there.

# API Endpoints
POST /api/discount/calculate
Calculates net payable amount for a given bill.
Request Body: JSON representing Bill object with total amount and User details.
Response: Net payable amount after applying discounts.


#Example Request
````
POST /api/discount/calculate
Content-Type: application/json

{
"totalAmount": 100.0,
"user": {
"userId": 1,
"userType": "EMPLOYEE",
"registrationDate": "2022-01-01"
}
}
````

# Example Response
````
HTTP/1.1 200 OK
Content-Type: application/json
100.0
````

# Error Handling
The application handles errors gracefully with appropriate HTTP status codes and error messages.
Logs errors and exceptions for easier troubleshooting and debugging.

# Future Improvements
Implement grocery check logic to differentiate groceries from other items.
Enhance security and validation for input data.
Extend functionality with more discount types or promotions.


## Authors
Dasarraju Raju
