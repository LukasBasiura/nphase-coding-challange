package com.nphase.domain.reward;

import com.nphase.entity.*;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static com.nphase.domain.reward.RewardType.PERCENT;
import static java.math.BigDecimal.valueOf;

public class CategoryReward implements RewardConfigure {
    @Override
    public BigDecimal getRewardValue() {
        return valueOf(10);
    }

    @Override
    public RewardType getRewardType() {
        return PERCENT;
    }

    @Override
    public String getRewardCategory(Product product) {
        return product.category().name();
    }

    public int limiter() {
        return 3;
    }

    @Override
    public Set<Reward> collectRewards(List<Product> products) {
        Map<String, List<Product>> collect = products.stream().collect(Collectors.groupingBy(this::getRewardCategory));
        return collect
                .entrySet()
                .stream()
                .filter(entry -> getQuantityPerCategory(entry.getValue()) > limiter())
                .map(entry -> new Reward(entry.getKey(), getRewardValue(), getRewardType()))
                .collect(Collectors.toSet());
    }

    private int getQuantityPerCategory(List<Product> products) {
        return products.stream().map(Product::quantity).reduce(Integer::sum).orElse(0);
    }
}
