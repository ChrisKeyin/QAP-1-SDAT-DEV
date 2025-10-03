SDAT & DevOps QAP 1 – Report

1) Clean Code (with screenshots)
Focused helpers: `BankService#validatePositive` and `#requireAccount` keep logic small, readable, and testable.
Custom exceptions: `InvalidAmountException` and `InsufficientFundsException` make error paths explicit.
Encapsulation: `Account` fields are private; balance updates go through `BankService#setBalance(...)`.

2) Project Summary
Features: create accounts, deposit, withdraw, balance inquiry
How it works: `BankService` stores accounts in a Map, validates amounts, and throws specific exceptions.

3) Tests (positive + negative)
Deposit/withdraw update balances
Over-withdraw  `InsufficientFundsException`
Non-positive deposit  `InvalidAmountException`
Transfer moves funds A-B and validates inputs 

4) Dependencies
JUnit 5 (`org.junit.jupiter:junit-jupiter`) + Maven Surefire (see `pom.xml`).

5) Problems & Fixes
`setBalance` couldn't get access across packages,so I set as `public`.
Put in the wrong remote URL, fixed with `git remote set-url` / `git remote remove` and re-add.

6) Dev/Trunk Workflow Evidence
Branches: `feature/ci-setup`, `docs/readme-and-report`.
PRs opened; GitHub Actions ran on PRs; merged into `main`.

