package org.example;

import java.time.LocalDate;

// Shippable Product classes
class ShippableProduct extends Product implements Shippable {
    private double weight;

    ShippableProduct(String name, int quantity, double price, double weight) {
        super(name, quantity, price);
        this.weight = weight;
    }

    @Override
    public double getWeight() {
        return weight;
    }

    public void validateProductAvailability(int requestedQuantity) throws ExpiredProductException, OutOfStockException {
        super.validateProductAvailability(requestedQuantity);
    }
}

class ExpirableShippableProduct extends Expirable implements Shippable {
    private double weight;

    public ExpirableShippableProduct(String name, int quantity, double price,
                                     LocalDate expirationDate, double weight) {
        super(name, quantity, price, expirationDate);
        this.weight = weight;
    }

    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public void validateProductAvailability(int requestedQuantity) throws ExpiredProductException, OutOfStockException {
        super.validateProductAvailability(requestedQuantity);
    }
}