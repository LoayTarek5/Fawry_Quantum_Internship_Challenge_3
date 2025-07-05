package org.example;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        // Create products
        Product cheese = new ExpirableShippableProduct("Cheese", 100, 10,
                LocalDate.now().plusDays(30), 0.2); // 200g per unit
        Product biscuits = new ExpirableShippableProduct("Biscuits", 150, 5,
                LocalDate.now().plusDays(60), 0.7); // 700g per unit
        Product tv = new ShippableProduct("TV", 500, 3, 10.0); // 10kg per unit
        Product mobile = new NonShippableProduct("Mobile", 300, 8);
        Product scratchCard = new NonShippableProduct("Scratch Card", 50, 20);

        // Create customer
        Customer customer = new Customer("John Doe", 1000.0);

        // Create shopping cart
        ShoppingCart cart = new ShoppingCart();

        // Test Case 1: Successful checkout with mixed products
        System.out.println("=== Test Case 1: Successful Checkout ===");
        try {
            cart.addItem(cheese, 2);
            cart.addItem(biscuits, 1);
            cart.addItem(scratchCard, 1);
            ECommerceSystem.checkout(customer, cart);
            System.out.println("END.\n");
        } catch (Exception e) {
            System.out.println("Error adding items to cart: " + e.getMessage());
        }

        // Test Case 2: Empty cart
        System.out.println("=== Test Case 2: Empty Cart ===");
        ECommerceSystem.checkout(customer, cart);
        System.out.println();

        // Test Case 3: Insufficient balance
        System.out.println("=== Test Case 3: Insufficient Balance ===");
        Customer poorCustomer = new Customer("Jane Doe", 50.0);
        try {
            cart.addItem(tv, 1);
            ECommerceSystem.checkout(poorCustomer, cart);
            cart.clear();
        } catch (Exception e) {
            System.out.println("Error adding items to cart: " + e.getMessage());
            cart.clear();
        }
        System.out.println();

        // Test Case 4: Out of stock
        System.out.println("=== Test Case 4: Out of Stock ===");
        try {
            cart.addItem(tv, 5); // Only 3 available
            ECommerceSystem.checkout(customer, cart);
            cart.clear();
        } catch (Exception e) {
            System.out.println("Error adding items to cart: " + e.getMessage());
            cart.clear();
        }
        System.out.println();

        // Test Case 5: Expired product
        System.out.println("=== Test Case 5: Expired Product ===");
        Product expiredProduct = new ExpirableShippableProduct("Expired Milk", 80, 5,
                LocalDate.now().minusDays(1), 0.5);
        try {
            cart.addItem(expiredProduct, 1);
            ECommerceSystem.checkout(customer, cart);
            cart.clear();
        } catch (Exception e) {
            System.out.println("Error adding items to cart: " + e.getMessage());
            cart.clear();
        }
        System.out.println();

        // Test Case 6: Invalid quantity
        System.out.println("=== Test Case 6: Invalid Quantity ===");
        try {
            cart.addItem(cheese, -1);
        } catch (Exception e) {
            System.out.println("Error adding items to cart: " + e.getMessage());
        }
        System.out.println();

        // Test Case 7: Large order with shipping
        System.out.println("=== Test Case 7: Large Order with Shipping ===");
        try {
            cart.addItem(tv, 2);
            cart.addItem(cheese, 3);
            ECommerceSystem.checkout(customer, cart);
            System.out.println("END.");
        } catch (Exception e) {
            System.out.println("Error adding items to cart: " + e.getMessage());
        }
    }
}