package com.nphase.domain.reward;

import java.math.BigDecimal;

import static java.math.RoundingMode.*;

public enum RewardType {
    PERCENT() {
        public BigDecimal calculate(BigDecimal rewardAmount, BigDecimal purchaseValue) {
            if (rewardAmount.compareTo(BigDecimal.ZERO) > 0 && rewardAmount.compareTo(BigDecimal.valueOf(100)) <= 0) {
                // We cannot have less than 0 discount and more than 100%
                return purchaseValue.subtract(calcPercentage(purchaseValue,rewardAmount));
            }
            return purchaseValue;
        }
    }, CASH() {
        public BigDecimal calculate(BigDecimal val, BigDecimal value) {
            throw new UnsupportedOperationException();
        }
    };
    public static final BigDecimal HUNDRED = new BigDecimal("100.00");

    public static BigDecimal calcPercentage(BigDecimal price, BigDecimal vat) {
        return price.multiply(vat.divide(HUNDRED, 5, UP)).setScale(2, CEILING);
    }

    abstract public BigDecimal calculate(BigDecimal rewardAmount, BigDecimal purchaseValue);
}
