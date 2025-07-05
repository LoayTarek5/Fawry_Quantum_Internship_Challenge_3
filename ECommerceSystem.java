package org.example;

import java.util.ArrayList;
import java.util.List;

public class ECommerceSystem {

    public static void checkout(Customer customer, ShoppingCart cart) {
        try {
            // Validate cart is not empty
            cart.checkCart();

            // Validate all products in cart before proceeding
            validateCartProducts(cart);

            // Calculate subtotal
            double subtotal = cart.getSubtotal();

            // Collect shippable items
            List<Shippable> shippableItems = collectShippableItems(cart);

            // Calculate shipping fee
            double shippingFee = ShippingService.calculateShippingFee(shippableItems);
            double totalAmount = subtotal + shippingFee;

            // Validate customer balance
            customer.checkBalance(totalAmount);

            // Process shipment if there are shippable items
            if (!shippableItems.isEmpty()) {
                ShippingService.processShipment(shippableItems);
            }

            // Process payment and update inventory
            customer.Rebalance(totalAmount);
            updateInventory(cart);

            // Print checkout receipt
            printCheckoutReceipt(cart, subtotal, shippingFee, totalAmount, customer);

            // Clear cart
            cart.clear();

        } catch (EmptyCartException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (InsufficientBalanceException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (ProductUnavailableException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: An unexpected error occurred - " + e.getMessage());
        }
    }

    private static void validateCartProducts(ShoppingCart cart) throws ProductUnavailableException {
        for (Item item : cart.getItems()) {
            Products product = item.getProduct();
            product.validateProductAvailability(item.getQuantity());
        }
    }

    private static List<Shippable> collectShippableItems(ShoppingCart cart) {
        List<Shippable> shippableItems = new ArrayList<>();
        for (Item item : cart.getItems()) {
            if (item.getProduct() instanceof Shippable) {
                // Add multiple instances for quantity
                for (int i = 0; i < item.getQuantity(); i++) {
                    shippableItems.add((Shippable) item.getProduct());
                }
            }
        }
        return shippableItems;
    }

    private static void updateInventory(ShoppingCart cart) {
        for (Item item : cart.getItems()) {
            item.getProduct().decreaseQunatity(item.getQuantity());
        }
    }

    private static void printCheckoutReceipt(ShoppingCart cart, double subtotal,
                                             double shippingFee, double totalAmount, Customer customer) {
        System.out.println("** Checkout receipt **");
        for (Item item : cart.getItems()) {
            System.out.println(item.getQuantity() + "x " + item.getProduct().getName() +
                    " " + (int)item.getTotalPrice());
        }
        System.out.println("----------------------");
        System.out.println("Subtotal " + (int)subtotal);
        System.out.println("Shipping " + (int)shippingFee);
        System.out.println("Amount " + (int)totalAmount);
        System.out.println("Customer balance after payment: " + customer.getBalance());
    }
}