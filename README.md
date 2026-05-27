# Bank Management System

## Overview

The Bank Management System is a console-based Java application designed to manage financial accounts. It leverages object-oriented programming concepts such as inheritance, abstraction, and polymorphism to handle different bank account types. The system features data persistence, allowing account records to be saved to and loaded from local text files.

---

## Features

* **Account Creation**: Add Fixed Deposit (FD) accounts or Savings accounts with validation to prevent duplicate usernames or account numbers.
* **Deposit and Withdrawal**: Process transactions dynamically with balance checks. The Fixed Deposit accounts include logic to handle early withdrawal interest penalties.
* **Balance Calculation**: Automatically compute up-to-date balances based on interest rates and the time elapsed since account creation.
* **Account Management**: View current balances or edit profile details like the account holder name, username, password, and account number.
* **Persistent Storage**: Automatically loads existing data on startup and saves updated records to text files upon program exit.

---

## File Structure and Storage

The application initializes and maintains two text files for local data storage:

1. **FD Account.txt**: Contains data for all registered Fixed Deposit accounts.
2. **Saving Account.txt**: Contains data for all registered Savings accounts, including bank card details if applicable.

---

## Class Architecture

* **Account.java**: An abstract base class defining core banking properties (holder name, username, password, account number, balance, creation date) and abstract transaction methods.
* **SavingAccount.java**: Inherits from `Account`. Manages a basic interest rate ($5\%$) and optional ATM card properties.
* **FdAccount.java**: Inherits from `Account`. Manages fixed-term investments with varying interest rates ($2.3\%$ to $2.55\%$) based on the chosen duration plan (3, 6, 9, or 12 months).
* **TestAccount.java**: The primary execution class housing the command-line menu interface, input validation, and file I/O systems.

---

## Technical Considerations and Limitations

* **Date Calculations**: The dynamic balance interest processing relies on `LocalDate.now()`. Ensure your system clock is accurate for appropriate calculations.
* **Console File Parsing**: The data parser uses explicit line-by-line ordering via `Scanner` to read and rebuild objects. Modifying the text files manually outside the program can cause parsing errors or an `InputMismatchException` during startup.

---

## How to Compile and Run

### Prerequisites

* Java Development Kit (JDK) 8 or higher installed.

### Execution Steps

1. Open your terminal or command prompt.
2. Navigate to the directory containing all four `.java` source files.
3. Compile all source files together:
```bash
javac Account.java SavingAccount.java FdAccount.java TestAccount.java

```


4. Run the main application:
```bash
java TestAccount

```
