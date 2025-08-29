package com.firefly.core.sca.interfaces.enums;

public enum SCAOperationTypeEnum {
    TRANSFER,        // e.g., a bank transfer requiring SCA
    PASSWORD_CHANGE, // e.g., user changing password
    LOGIN,           // e.g., user login (step-up SCA)
    LOAN_REQUEST,    // e.g., user applying for a loan
    ONBOARDING,      // e.g., onboarding process requiring SCA
    OTHER            // fallback for unspecified types
}
