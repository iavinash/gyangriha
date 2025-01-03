package com.three.gyangriha.enums;

public enum SubscriptionType {
    MONTHLY,
    QUARTERLY,
    SEMI_ANNUAL,
    YEARLY;

    /**
     * Converts subscription type to the corresponding integer value.
     *
     * @param subscriptionType The subscription type
     * @return The corresponding integer value
     */
    public static int getSubscriptionInterval(SubscriptionType subscriptionType) {
        switch (subscriptionType) {
            case MONTHLY:
                return 1;   // 1 month
            case QUARTERLY:
                return 3;   // 3 months
            case SEMI_ANNUAL:
                return 6;   // 6 months
            case YEARLY:
                return 12;  // 12 months
            default:
                throw new IllegalArgumentException("Unknown subscription type: " + subscriptionType);
        }
    }
}
