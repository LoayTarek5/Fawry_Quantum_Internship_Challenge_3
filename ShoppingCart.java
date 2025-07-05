package org.example;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<Item> items = new ArrayList<>();

    public void addItem(Product product, int quantity) throws InvalidQuantityException, ProductUnavailableException {
        if (quantity <= 0) {
            throw new InvalidQuantityException("Quantity must be positive, got: " + quantity);
        }

        // Use the product's validation method
        product.validateProductAvailability(quantity);
        items.add(new Item(product, quantity));
    }

    public List<Item> getItems() {
        return items;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public double getSubtotal() {
        return items.stream().mapToDouble(Item::getTotalPrice).sum();
    }

    public void clear() {
        items.clear();
    }

    public void checkCart() throws EmptyCartException {
        if (isEmpty()) {
            throw new EmptyCartException("Cannot checkout with an empty cart");
        }
    }
}