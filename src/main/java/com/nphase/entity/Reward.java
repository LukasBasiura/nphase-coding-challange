package com.nphase.entity;

import com.nphase.domain.reward.RewardType;

import java.math.BigDecimal;

public record Reward(String productName, BigDecimal rewardAmount, RewardType type) {
    public BigDecimal recalculate(BigDecimal value) {
        return type.calculate(rewardAmount,value);
    }
    public boolean canApply(Product product) {
        return productName.equals(product.name()) || productName.equals(product.category().name());
    }
}
