package com.catalis.core.sca.interfaces.enums;

public enum SCAEventTypeEnum {
    CREATED,        // Operation/Challenge was created
    ATTEMPTED,      // User tried an OTP code
    VERIFIED,       // Verified successfully
    FAILED,         // Verification failed
    EXPIRED,        // Timed out or expired
    CANCELLED       // User/system cancelled the operation
}
