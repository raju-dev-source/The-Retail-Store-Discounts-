package com.retail.model;

import java.time.LocalDate;

public class User {
    private final Long userId;
    private final UserType userType;
    private final LocalDate registrationDate;

    public User(Long userId, UserType userType, LocalDate registrationDate) {
        this.userId = userId;
        this.userType = userType;
        this.registrationDate = registrationDate;
    }

    public Long getUserId() {
        return userId;
    }

    public UserType getUserType() {
        return userType;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }
}
