package com.nphase.domain.reward;

import com.nphase.entity.*;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static com.nphase.domain.reward.RewardType.PERCENT;
import static java.math.BigDecimal.valueOf;

public class ProductReward implements RewardConfigure {
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
        return product.name();
    }

    public int limiter() {
        return 3;
    }

    @Override
    public Set<Reward> collectRewards(List<Product> products) {
        return products.stream()
                .filter(this::isAllowed)
                .map(product -> new Reward(getRewardCategory(product), getRewardValue(), getRewardType()))
                .collect(Collectors.toSet());
    }

}
