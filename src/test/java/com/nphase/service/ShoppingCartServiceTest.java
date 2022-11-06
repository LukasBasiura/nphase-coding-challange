package com.nphase.service;


import com.nphase.domain.reward.*;
import com.nphase.entity.Product;
import com.nphase.entity.ShoppingCart;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.util.Arrays;

import static com.nphase.domain.product.ProductCategory.*;

public class ShoppingCartServiceTest {
    @Test
    public void calculatesPrice() {
        ShoppingCart cart = new ShoppingCart(Arrays.asList(
                new Product("Tea", BigDecimal.valueOf(5.0), DRINKS, 2),
                new Product("Coffee", BigDecimal.valueOf(6.5), DRINKS, 1)
        ));
        ShoppingCartService service = new ShoppingCartService(new RewardService(new ProductReward()));
        BigDecimal result = service.calculateTotalPrice(cart);

        Assertions.assertEquals(result, BigDecimal.valueOf(16.5));
    }

    @Test
    @DisplayName("Calculate price when there is a 10% discount on tea")
    public void calculatesPriceForTea() {
        BigDecimal teaPrice = BigDecimal.valueOf(5.0);
        BigDecimal coffeePrice = BigDecimal.valueOf(6.5);
        int teaQuantity = 4;
        int coffeeQuantity = 1;

        ShoppingCart cart = new ShoppingCart(Arrays.asList(
                new Product("Tea", teaPrice, DRINKS, teaQuantity),
                new Product("Coffee", coffeePrice, DRINKS, coffeeQuantity)
        ));

        ShoppingCartService service = new ShoppingCartService(new RewardService(new ProductReward()));
        BigDecimal result = service.calculateTotalPrice(cart);
        Assertions.assertEquals(result, BigDecimal.valueOf(24.50).setScale(2));
    }

    @Test
    @DisplayName("Calculate price when there is a 10% discount on tea and coffee")
    public void calculatesPriceForTeaAndCoffee() {
        BigDecimal teaPrice = BigDecimal.valueOf(5.0);
        BigDecimal coffeePrice = BigDecimal.valueOf(6.5);
        int teaQuantity = 4;
        int coffeeQuantity = 4;

        ShoppingCart cart = new ShoppingCart(Arrays.asList(
                new Product("Tea", teaPrice, DRINKS, teaQuantity),
                new Product("Coffee", coffeePrice, DRINKS, coffeeQuantity)
        ));

        ShoppingCartService service = new ShoppingCartService(new RewardService(new ProductReward()));
        BigDecimal result = service.calculateTotalPrice(cart);
        Assertions.assertEquals(result, BigDecimal.valueOf(41.40).setScale(2));
    }


    @Test
    @DisplayName("Calculate price when there is a 10% discount on drinks")
    public void calculatesPriceForDrinks() {
        BigDecimal teaPrice = BigDecimal.valueOf(5.0);
        BigDecimal coffeePrice = BigDecimal.valueOf(6.5);
        int teaQuantity = 3;
        int coffeeQuantity = 1;

        ShoppingCart cart = new ShoppingCart(Arrays.asList(
                new Product("Tea", teaPrice, DRINKS, teaQuantity),
                new Product("Coffee", coffeePrice, DRINKS, coffeeQuantity)
        ));

        ShoppingCartService service = new ShoppingCartService(new RewardService(new CategoryReward()));
        BigDecimal result = service.calculateTotalPrice(cart);
        Assertions.assertEquals(result, BigDecimal.valueOf(19.35).setScale(2));
    }

    @Test
    @DisplayName("Calculate price for task 2")
    public void calculatePriceForTask2() {
        BigDecimal teaPrice = BigDecimal.valueOf(5.0);
        BigDecimal coffeePrice = BigDecimal.valueOf(3.5);
        int teaQuantity = 5;
        int coffeeQuantity = 3;

        ShoppingCart cart = new ShoppingCart(Arrays.asList(
                new Product("Tea", teaPrice, DRINKS, teaQuantity),
                new Product("Coffee", coffeePrice, DRINKS, coffeeQuantity)
        ));

        ShoppingCartService service = new ShoppingCartService(new RewardService(new ProductReward()));
        BigDecimal result = service.calculateTotalPrice(cart);
        Assertions.assertEquals(result, BigDecimal.valueOf(33.00).setScale(2));
    }

    @Test
    @DisplayName("Calculate price for task 3")
    public void calculatePriceForTask3() {
        BigDecimal teaPrice = BigDecimal.valueOf(5.3);
        BigDecimal coffeePrice = BigDecimal.valueOf(3.5);
        int teaQuantity = 2;
        int coffeeQuantity = 2;

        ShoppingCart cart = new ShoppingCart(Arrays.asList(
                new Product("Tea", teaPrice, DRINKS, teaQuantity),
                new Product("Coffee", coffeePrice, DRINKS, coffeeQuantity),
                new Product("cheese",BigDecimal.valueOf(8),FOOD,2)
        ));

        ShoppingCartService service = new ShoppingCartService(new RewardService(new CategoryReward()));
        BigDecimal result = service.calculateTotalPrice(cart);
        Assertions.assertEquals(result, BigDecimal.valueOf(31.84).setScale(2));
    }
}