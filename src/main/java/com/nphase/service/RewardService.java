package com.nphase.service;

import com.nphase.domain.reward.RewardConfigure;
import com.nphase.entity.*;

import java.util.*;
import java.util.stream.Collectors;

public class RewardService {

    private final RewardConfigure configure;

    public RewardService(RewardConfigure configure) {
        this.configure = configure;
    }

    public Set<Reward> calculateRewards(ShoppingCart shoppingCart) {
        return configure.collectRewards(shoppingCart.getProducts());
    }
}
