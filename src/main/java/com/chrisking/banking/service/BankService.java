package com.chrisking.banking.service;

import com.chrisking.banking.exception.InsufficientFundsException;
import com.chrisking.banking.exception.InvalidAmountException;
import com.chrisking.banking.model.Account;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

public class BankService {

    private final Map<String, Account> accounts = new HashMap<>();

    public Account createAccount(String id, String ownerName, double openingBalance) {
        if (accounts.containsKey(id)) {
            throw new IllegalArgumentException("Account with id '" + id + "' already exists");
        }
        Account acc = new Account(id, ownerName, openingBalance);
        accounts.put(id, acc);
        return acc;
    }

    public Optional<Account> findById(String id) {
        return Optional.ofNullable(accounts.get(id));
    }

    public double deposit(String id, double amount) {
        Account acc = requireAccount(id);
        validatePositive(amount, "Deposit amount must be positive");
        acc.setBalance(acc.getBalance() + amount);
        return acc.getBalance();
    }

    public double withdraw(String id, double amount) {
        Account acc = requireAccount(id);
        validatePositive(amount, "Withdrawal amount must be positive");
        if (acc.getBalance() < amount) throw new InsufficientFundsException("Not enough balance");
        acc.setBalance(acc.getBalance() - amount);
        return acc.getBalance();
    }

    public double getBalance(String id) {
        return requireAccount(id).getBalance();
    }

    public void transfer(String fromId, String toId, double amount) {
        if (fromId.equals(toId)) throw new IllegalArgumentException("Cannot transfer to the same account");
        validatePositive(amount, "Transfer amount must be positive");
        Account from = requireAccount(fromId);
        Account to = requireAccount(toId);
        if (from.getBalance() < amount) throw new InsufficientFundsException("Not enough balance in source");
        from.setBalance(from.getBalance() - amount);
        to.setBalance(to.getBalance() + amount);
    }

    private Account requireAccount(String id) {
        return findById(id).orElseThrow(() -> new NoSuchElementException("Account not found: " + id));
    }

    private static void validatePositive(double amount, String msg) {
        if (amount <= 0) throw new InvalidAmountException(msg);
    }
}
