package com.nphase.service;

import com.nphase.domain.reward.RewardType;
import com.nphase.entity.*;

import java.math.*;
import java.util.Set;

public class ShoppingCartService {

    private final RewardService rewardService;

    public ShoppingCartService(RewardService rewardService) {
        this.rewardService = rewardService;
    }

    public BigDecimal calculateTotalPrice(ShoppingCart shoppingCart) {
        Set<Reward> rewards = rewardService.calculateRewards(shoppingCart);
        return shoppingCart.getProducts()
                .stream()
                .map(product -> getReward(rewards,product).recalculate(product.pricePerUnit().multiply(BigDecimal.valueOf(product.quantity()))))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }


    private Reward getReward(Set<Reward> rewards, Product product) {
        return rewards.stream().filter(reward -> reward.canApply(product))
                .findFirst()
                .orElse(new Reward("", BigDecimal.valueOf(0), RewardType.PERCENT));
    }
}
