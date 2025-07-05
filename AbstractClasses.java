package org.example;

import java.time.LocalDate;

// Abstract Classes
abstract class Product implements Products {
    protected String name;
    protected int quantity;
    protected double price;

    public Product(String name, int quantity, double price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getQuantity() {
        return quantity;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public void decreaseQunatity(int numberItems) {
        if(numberItems <= quantity) {
            quantity -= numberItems;
        }
    }

    @Override
    public boolean isProductAvailable(int requestQuantity) {
        return quantity >= requestQuantity;
    }

    public void validateProductAvailability(int requestedQuantity) throws OutOfStockException, ExpiredProductException {
        if (!isProductAvailable(requestedQuantity)) {
            throw new OutOfStockException("Product " + name + " is out of stock. Available: " +
                    quantity + ", Requested: " + requestedQuantity);
        }
    }
}

class Expirable extends Product {
    private LocalDate expiryDate;

    public Expirable(String name, int quantity, double price, LocalDate expiryDate) {
        super(name, quantity, price);
        this.expiryDate = expiryDate;
    }

    public boolean isExpired() {
        return LocalDate.now().isAfter(expiryDate);
    }

    @Override
    public boolean isProductAvailable(int requestedQuantity) {
        return !isExpired() && super.isProductAvailable(requestedQuantity);
    }

    @Override
    public void validateProductAvailability(int requestedQuantity) throws ExpiredProductException, OutOfStockException {
        if (isExpired()) {
            throw new ExpiredProductException("Product " + name + " has expired on " + expiryDate);
        }
        super.validateProductAvailability(requestedQuantity);
    }
}