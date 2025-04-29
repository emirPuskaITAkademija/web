package com.itakademija.shop.cart;

import com.itakademija.shop.Product;

import java.io.Serializable;
import java.math.BigDecimal;

public class ShoppingCartItem implements Serializable {
    private int quantity;
    private Product product;

    public ShoppingCartItem() {
    }

    public ShoppingCartItem(int quantity, Product product) {
        this.quantity = quantity;
        this.product = product;
    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getTotalPrice() {
        return quantity * product.getPrice();
    }
}
