package com.chrisking.banking;

import com.chrisking.banking.exception.InsufficientFundsException;
import com.chrisking.banking.exception.InvalidAmountException;
import com.chrisking.banking.service.BankService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankServiceTest {

    private BankService bank;

    @BeforeEach
    void setup() {
        bank = new BankService();
        bank.createAccount("A1", "Chris", 100.0);
    }

    @Test
    void deposit_increasesBalance_positiveScenario() {
        double newBalance = bank.deposit("A1", 50.0);
        assertEquals(150.0, newBalance, 1e-6);
        assertEquals(150.0, bank.getBalance("A1"), 1e-6);
    }

    @Test
    void withdraw_decreasesBalance_positiveScenario() {
        double newBalance = bank.withdraw("A1", 40.0);
        assertEquals(60.0, newBalance, 1e-6);
    }

    @Test
    void withdraw_throwsWhenInsufficient() {
        assertThrows(InsufficientFundsException.class, () -> bank.withdraw("A1", 200.0));
        assertEquals(100.0, bank.getBalance("A1"), 1e-6);
    }

    @Test
    void deposit_throwsOnNonPositive() {
        assertThrows(InvalidAmountException.class, () -> bank.deposit("A1", 0.0));
        assertThrows(InvalidAmountException.class, () -> bank.deposit("A1", -5.0));
    }

    @Test
    void transfer_movesMoneyBetweenAccounts() {
        bank.createAccount("A2", "Noel", 10.0);
        bank.transfer("A1", "A2", 25.0);
        assertEquals(75.0, bank.getBalance("A1"), 1e-6);
        assertEquals(35.0, bank.getBalance("A2"), 1e-6);
    }
}
