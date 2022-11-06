package com.nphase.domain.reward;

import com.nphase.entity.*;

import java.math.BigDecimal;
import java.util.*;

public interface RewardConfigure {
    BigDecimal getRewardValue();

    RewardType getRewardType();

    String getRewardCategory(Product product);
    int limiter();

    default boolean isAllowed(Product product) {
        return product.quantity() > limiter();
    }

    Set<Reward> collectRewards(List<Product> products);
}
