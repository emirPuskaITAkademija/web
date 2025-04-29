package com.itakademija.shop.cart;

import com.itakademija.shop.Product;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    private final List<ShoppingCartItem> items;

    public ShoppingCart() {
        this.items = new ArrayList<>();
    }

    public void addItem(Product product, int quantity) {
        for(ShoppingCartItem cartItem : this.items) {
            if(cartItem.getProduct().getId() == product.getId()) {
                cartItem.setQuantity(cartItem.getQuantity() + quantity);
                return;//blokiraj cijelu funkciju ispod..odnosno preostali blok koda funkcije
            }
        }
        ShoppingCartItem shoppingCartItem = new ShoppingCartItem(quantity, product);
        items.add(shoppingCartItem);
    }

    public List<ShoppingCartItem> getItems() {
        return items;
    }
}
