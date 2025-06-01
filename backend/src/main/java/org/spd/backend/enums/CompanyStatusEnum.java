package org.spd.backend.enums;

public enum CompanyStatusEnum {
    /**
     * Company has registered but not yet verified their email
     */
    PENDING_VERIFICATION,

    /**
     * Email verified and account active
     */
    ACTIVE,

    /**
     * Temporarily deactivated by admin or due to suspicious activity
     */
    SUSPENDED,

    /**
     * Permanently deactivated (cannot be reactivated)
     */
    TERMINATED
}