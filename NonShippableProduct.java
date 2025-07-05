package org.example;

import java.time.LocalDate;

// Non-Shippable Product classes
class NonShippableProduct extends Product {
    public NonShippableProduct(String name, int quantity, double price) {
        super(name, quantity, price);
    }

    @Override
    public void validateProductAvailability(int requestedQuantity) throws OutOfStockException, ExpiredProductException {
        super.validateProductAvailability(requestedQuantity);
    }
}

class ExpirableNonShippableProduct extends Expirable {
    public ExpirableNonShippableProduct(String name, int quantity, double price,
                                        LocalDate expirationDate) {
        super(name, quantity, price, expirationDate);
    }

    @Override
    public void validateProductAvailability(int requestedQuantity) throws ExpiredProductException, OutOfStockException {
        super.validateProductAvailability(requestedQuantity);
    }
}