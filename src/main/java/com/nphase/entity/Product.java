package com.nphase.entity;

import com.nphase.domain.product.ProductCategory;

import java.math.BigDecimal;

public record Product(String name, BigDecimal pricePerUnit, ProductCategory category, int quantity) {
}
