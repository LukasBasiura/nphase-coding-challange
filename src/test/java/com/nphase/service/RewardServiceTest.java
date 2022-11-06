package com.nphase.service;

import com.nphase.domain.reward.*;
import com.nphase.entity.*;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.*;

import static com.nphase.domain.product.ProductCategory.*;
import static org.assertj.core.api.Assertions.assertThat;

class RewardServiceTest {

    @Test
    void calculateRewardsForProducts() {
        ShoppingCart cart = new ShoppingCart(Arrays.asList(
                new Product("Tea", BigDecimal.valueOf(5.0), DRINKS, 4),
                new Product("Coffee", BigDecimal.valueOf(6.5),DRINKS, 4),
                new Product("Sugar", BigDecimal.valueOf(7.5), FOOD,1)
        ));

        RewardService rewardService = new RewardService(new ProductReward());
        Set<Reward> rewards = rewardService.calculateRewards(cart);

        assertThat(rewards)
                .hasSize(2)
                .extracting(Reward::productName)
                .containsExactlyInAnyOrder(
                        "Tea" , "Coffee"
                );
    }

    @Test
    void calculateRewardsForCategories() {
        ShoppingCart cart = new ShoppingCart(Arrays.asList(
                new Product("Tea", BigDecimal.valueOf(5.0), DRINKS, 4),
                new Product("Coffee", BigDecimal.valueOf(6.5),DRINKS, 4),
                new Product("Sugar", BigDecimal.valueOf(7.5), FOOD,1)
        ));

        RewardService rewardService = new RewardService(new CategoryReward());
        Set<Reward> rewards = rewardService.calculateRewards(cart);

        assertThat(rewards)
                .hasSize(1)
                .extracting(Reward::productName)
                .containsExactlyInAnyOrder(
                        "DRINKS"
                );
    }
}