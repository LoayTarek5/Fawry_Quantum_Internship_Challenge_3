package org.example;

// Custom Exceptions To Handling the errors of the process
class EmptyCartException extends Exception {
    public EmptyCartException(String message) {
        super(message);
    }
}

class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}

class ProductUnavailableException extends Exception {
    public ProductUnavailableException(String message) {
        super(message);
    }
}

class OutOfStockException extends ProductUnavailableException {
    public OutOfStockException(String message) {
        super(message);
    }
}

class ExpiredProductException extends ProductUnavailableException {
    public ExpiredProductException(String message) {
        super(message);
    }
}

class InvalidQuantityException extends Exception {
    public InvalidQuantityException(String message) {
        super(message);
    }
}