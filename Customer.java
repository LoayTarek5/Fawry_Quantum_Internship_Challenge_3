package org.example;

public class Customer {
    private String name;
    private double balance;

    public Customer(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public void Rebalance(double amount) {
        balance -= amount;
    }

    public boolean enoughBalance(double amount) {
        return balance >= amount;
    }

    public void checkBalance(double amount) throws InsufficientBalanceException {
        if (!enoughBalance(amount)) {
            throw new InsufficientBalanceException("Insufficient balance. Required: " + amount +
                    ", Available: " + balance);
        }
    }
}