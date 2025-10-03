package com.chrisking.banking.model;

import java.util.Objects;

public class Account {
    private final String id;
    private final String ownerName;
    private double balance;

    public Account(String id, String ownerName, double openingBalance) {
        if (id == null || id.isBlank()) throw new IllegalArgumentException("id cannot be blank");
        if (ownerName == null || ownerName.isBlank()) throw new IllegalArgumentException("ownerName cannot be blank");
        if (openingBalance < 0) throw new IllegalArgumentException("opening balance cannot be negative");
        this.id = id;
        this.ownerName = ownerName;
        this.balance = openingBalance;
    }

    public String getId() { return id; }
    public String getOwnerName() { return ownerName; }
    public double getBalance() { return balance; }

    public void setBalance(double balance) { this.balance = balance; }

    @Override
    public String toString() {
        return "Account{id='%s', owner='%s', balance=%.2f}".formatted(id, ownerName, balance);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;
        Account account = (Account) o;
        return id.equals(account.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
