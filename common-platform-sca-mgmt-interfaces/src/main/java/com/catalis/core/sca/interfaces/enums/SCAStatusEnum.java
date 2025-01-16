package com.catalis.core.sca.interfaces.enums;

public enum SCAStatusEnum {
    PENDING,   // SCA started but not yet verified
    VERIFIED,  // SCA successfully completed
    FAILED,    // SCA attempts failed (exceeded retries)
    EXPIRED,   // SCA operation timed out
    CANCELLED  // Operation was cancelled by the user/system
}
