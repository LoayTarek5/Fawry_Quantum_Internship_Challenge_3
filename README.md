# Fawry_Quantum_Internship_Challenge_3
# E-commerce System

A comprehensive Java-based e-commerce system demonstrating object-oriented programming principles, exception handling, and business logic implementation.

## Table of Contents
- [Overview](#overview)
- [Features](#features)
- [Architecture](#architecture)
- [Class Structure](#class-structure)
- [Installation](#installation)
- [Usage](#usage)
- [Testing](#testing)
- [Exception Handling](#exception-handling)
- [Design Patterns](#design-patterns)
- [Contributing](#contributing)
- [License](#license)

## Overview

This e-commerce system simulates a complete online shopping experience with features including product management, shopping cart functionality, customer balance management, shipping calculations, and comprehensive error handling.

![v1 1](https://github.com/user-attachments/assets/02b05f17-aa6a-4684-8706-84721b24d5d2)
![v1 2](https://github.com/user-attachments/assets/fb3016fc-58bc-4077-a874-30d43f084384)

## Features

### Core Functionality
- **Product Management**: Support for various product types (shippable, non-shippable, expirable)
- **Shopping Cart**: Add items, validate quantities, calculate totals
- **Customer Management**: Balance tracking, payment processing
- **Shipping System**: Weight-based shipping calculations and processing
- **Inventory Management**: Stock tracking and automatic updates

### Product Types
- **Standard Products**: Basic products with name, quantity, and price
- **Expirable Products**: Products with expiration dates
- **Shippable Products**: Products that can be physically shipped
- **Combined Types**: Products that are both expirable and shippable

### Business Logic
- Real-time inventory validation
- Automatic expiration checking
- Balance verification before purchase
- Shipping fee calculation based on weight
- Comprehensive receipt generation

## Architecture

The system follows a layered architecture with clear separation of concerns:

```
┌─────────────────────────────────────┐
│           Main Application          │
├─────────────────────────────────────┤
│         Business Logic Layer        │
│  (ECommerceSystem, ShippingService) │
├─────────────────────────────────────┤
│            Domain Layer             │
│  (Customer, ShoppingCart, Item)     │
├─────────────────────────────────────┤
│           Product Layer             │
│     (Product hierarchy)             │
├─────────────────────────────────────┤
│          Exception Layer            │
│     (Custom exceptions)             │
└─────────────────────────────────────┘
```

## Class Structure

### Interfaces
- **`Products`**: Defines contract for all product operations
- **`Shippable`**: Defines contract for shippable items

### Abstract Classes
- **`Product`**: Base class for all product types

### Concrete Product Classes
- **`Expirable`**: Products with expiration dates
- **`ShippableProduct`**: Products that can be shipped
- **`NonShippableProduct`**: Digital or service products
- **`ExpirableShippableProduct`**: Physical products with expiration
- **`ExpirableNonShippableProduct`**: Digital products with expiration

### Business Logic Classes
- **`Customer`**: Manages customer information and balance
- **`ShoppingCart`**: Handles cart operations and validation
- **`Item`**: Represents individual cart items
- **`ShippingService`**: Calculates shipping costs and processes shipments
- **`ECommerceSystem`**: Main orchestrator for checkout process

### Exception Classes
- **`EmptyCartException`**: Thrown when attempting to checkout empty cart
- **`InsufficientBalanceException`**: Thrown when customer balance is insufficient
- **`ProductUnavailableException`**: Base exception for product availability issues
- **`OutOfStockException`**: Thrown when requested quantity exceeds stock
- **`ExpiredProductException`**: Thrown when product has expired
- **`InvalidQuantityException`**: Thrown for invalid quantity values

## Installation

### Prerequisites
- Java 8 or higher
- Maven (optional, for dependency management)

### Setup
1. Clone the repository:
```bash
git clone https://github.com/yourusername/ecommerce-system.git
cd ecommerce-system
```

2. Compile the project:
```bash
javac -d bin src/org/example/*.java
```

3. Run the application:
```bash
java -cp bin org.example.Main
```

## Usage

### Creating Products
```java
// Create different types of products
Product cheese = new ExpirableShippableProduct("Cheese", 100, 10.0, 
    LocalDate.now().plusDays(30), 0.2);

Product tv = new ShippableProduct("TV", 50, 500.0, 10.0);

Product mobile = new NonShippableProduct("Mobile", 30, 800.0);
```

### Managing Customers
```java
// Create customer with initial balance
Customer customer = new Customer("John Doe", 1000.0);

// Check balance
if (customer.enoughBalance(totalAmount)) {
    customer.Rebalance(totalAmount);
}
```

### Shopping Cart Operations
```java
// Create shopping cart
ShoppingCart cart = new ShoppingCart();

// Add items to cart
try {
    cart.addItem(cheese, 2);
    cart.addItem(tv, 1);
} catch (InvalidQuantityException | ProductUnavailableException e) {
    System.out.println("Error: " + e.getMessage());
}

// Process checkout
ECommerceSystem.checkout(customer, cart);
```

## Testing

The system includes comprehensive test cases covering:

### Test Scenarios
1. **Successful Checkout**: Mixed products with shipping
2. **Empty Cart**: Validation of empty cart checkout
3. **Insufficient Balance**: Payment failure scenarios
4. **Out of Stock**: Inventory limitation handling
5. **Expired Products**: Expiration date validation
6. **Invalid Quantity**: Negative quantity validation
7. **Large Orders**: Multiple items with shipping

### Running Tests
```bash
java -cp bin org.example.Main
```

### Expected Output
- Successful transactions with detailed receipts
- Error messages for invalid operations
- Shipping notifications for physical products
- Balance updates after transactions

## Exception Handling

The system implements robust exception handling:

### Exception Hierarchy
```
Exception
├── EmptyCartException
├── InsufficientBalanceException
├── InvalidQuantityException
└── ProductUnavailableException
    ├── OutOfStockException
    └── ExpiredProductException
```

### Error Recovery
- Graceful degradation for failed operations
- Informative error messages for users
- System state preservation during errors
- Retry mechanisms where appropriate

## Design Patterns

### Patterns Implemented
- **Strategy Pattern**: Different product types with varying behaviors
- **Template Method**: Common product operations with specialized implementations
- **Factory Pattern**: Product creation with different configurations
- **Observer Pattern**: Inventory updates during transactions

### SOLID Principles
- **Single Responsibility**: Each class has a single, well-defined purpose
- **Open/Closed**: Easy to extend with new product types
- **Liskov Substitution**: Product subtypes are interchangeable
- **Interface Segregation**: Focused interfaces (Products, Shippable)
- **Dependency Inversion**: High-level modules depend on abstractions

## API Reference

### Key Methods

#### ECommerceSystem
- `checkout(Customer, ShoppingCart)`: Process complete checkout
- `validateCartProducts(ShoppingCart)`: Validate all cart items
- `collectShippableItems(ShoppingCart)`: Extract shippable items
- `updateInventory(ShoppingCart)`: Update product quantities

#### ShippingService
- `calculateShippingFee(List<Shippable>)`: Calculate shipping costs
- `processShipment(List<Shippable>)`: Process shipment details

#### Customer
- `checkBalance(double)`: Validate sufficient balance
- `Rebalance(double)`: Deduct amount from balance

#### ShoppingCart
- `addItem(Product, int)`: Add item with quantity validation
- `getSubtotal()`: Calculate total before shipping
- `checkCart()`: Validate cart for checkout

## Configuration

### Shipping Configuration
- Shipping rate: 15.0 per kg
- Configurable in `ShippingService.SHIPPING_RATE_PER_KG`

### Product Configuration
- Expiration date validation: Real-time checking
- Stock validation: Automatic quantity checking

## Performance Considerations

- **Memory Usage**: Efficient object creation and garbage collection
- **Time Complexity**: O(n) operations for cart processing
- **Scalability**: Designed for extension with minimal changes

## Development Guidelines
- Follow Java naming conventions
- Add comprehensive error handling
- Include appropriate unit tests
- Update documentation for new features
- Maintain backward compatibility

## Contact
- **Developer**: Loay Tarek Mostafa Elnomani
- **Email**: loaytareq44@gmail.com
- **GitHub**: [LoayTarek5](https://github.com/LoayTarek5/)
