Christopher Roderick King,
QAP#1 Dev Ops & SDAT

Banking App

Features
- Create accounts
- Deposit / Withdraw
- Balance inquiry
- (Extra) Transfer between accounts

How it works
- Account: id, ownerName, balance (encapsulated).
- BankService: in-memory map + validation + custom exceptions.
- Exceptions: `InvalidAmountException`, `InsufficientFundsException`.
- Tests: positive + negative scenarios in `BankServiceTest` (+ optional `AccountTest`).

Clean code examples
1) Focused helpers: `validatePositive`, `requireAccount`
2) Custom exceptions for clear error paths
3) Encapsulation & SRP: `Account` holds state; `BankService` enforces rules

Run locally
- IntelliJ: run tests from `src/test/java`
- CLI: `mvn test`

CI
- `.github/workflows/ci.yml` runs tests on every Pull Request.
