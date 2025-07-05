package org.example;

// Interfaces
interface Products {
    String getName();
    int getQuantity();
    double getPrice();
    void decreaseQunatity(int numberItems);
    boolean isProductAvailable(int requestQuantity);
    void validateProductAvailability(int requestedQuantity) throws ProductUnavailableException;
}

interface Shippable {
    String getName();
    double getWeight();
}